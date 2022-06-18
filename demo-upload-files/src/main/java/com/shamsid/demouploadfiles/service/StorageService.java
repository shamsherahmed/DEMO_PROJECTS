package com.shamsid.demouploadfiles.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    public void init();
    public void save(MultipartFile file);
    public Resource load(String fileName);
    public void deleteAll();
    public void deleteFile(String fileName);
    public Stream<Path> loadAll();
}
