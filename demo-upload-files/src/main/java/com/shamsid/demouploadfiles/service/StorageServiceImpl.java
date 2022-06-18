package com.shamsid.demouploadfiles.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

  @Value("${com.shamsid.application.file_path_name}")
  private String filePath;

  private Path uploadPath;

  @Override
  public void init() {
    try {
      uploadPath = Paths.get(filePath);
      Files.createDirectory(uploadPath);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.uploadPath.resolve(file.getOriginalFilename()));
    } catch (IOException e) {
      throw new RuntimeException("Could not store the file. Error:" + e.getMessage());
    }
  }

  @Override
  public Resource load(String fileName) {
    Path filePath = uploadPath.resolve(fileName);
    try {
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    if (uploadPath != null) {
      FileSystemUtils.deleteRecursively(uploadPath.toFile());
    }
  }

  @Override
  public void deleteFile(String fileName) {}

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.uploadPath, 1)
          .filter(path -> !path.equals(this.uploadPath))
          .map(this.uploadPath::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
}
