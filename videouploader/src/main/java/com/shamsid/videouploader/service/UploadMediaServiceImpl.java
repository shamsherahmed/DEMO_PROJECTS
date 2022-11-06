package com.shamsid.videouploader.service;

import com.shamsid.videouploader.domain.Media;
import com.shamsid.videouploader.repository.MediaRepository;
import com.shamsid.videouploader.utils.FileUploadUtilService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class UploadMediaServiceImpl implements  UploadMediaService{


    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private FileUploadUtilService storageUtil;

    @Override
    public Media saveFile(MultipartFile multipartFile) {
        return null;
    }

    public byte[] getFile(String path) throws IOException {
        return storageUtil.getFile(path);
    }

    public Optional<Media> findById(String id) {
        return Optional.ofNullable(mediaRepository.findByMid(id));
    }

    @Override
    public Media saveFile(MultipartFile multipartFile, String uid) {

        try {
            String path = storageUtil.createFile(multipartFile);
            Media media = new Media();
            media.setName(multipartFile.getName());
            //media.setFilePath(Paths.get(Objects.requireNonNull(multipartFile.getOriginalFilename())).getFileName().toString());
            media.setFilePath(path);
            media.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
            media.setCreatedBy(uid);
            media.setFileExtension(FilenameUtils.getExtension(media.getName()));
            return mediaRepository.save(media);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
