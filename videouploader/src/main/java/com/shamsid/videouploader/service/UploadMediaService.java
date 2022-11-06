package com.shamsid.videouploader.service;

import com.shamsid.videouploader.domain.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UploadMediaService {
    public Media saveFile(MultipartFile multipartFile);

    Media saveFile(MultipartFile multipartFile, String uid);
    public Optional<Media> findById(String id);

    public byte[] getFile(String path) throws IOException;
}
