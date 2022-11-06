package com.shamsid.videouploader.controller;

import com.shamsid.videouploader.domain.Media;
import com.shamsid.videouploader.service.UploadMediaService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import static org.springframework.http.ResponseEntity.*;

@Slf4j
@RestController
@RequestMapping("/media")
public class MediaController {


    @Value("${server.mime-types}")
    private List<String> allowedMediaExtensions;

    @Autowired
    UploadMediaService uploadMediaService;

    @PostMapping(
            value = "/upload/video",
            consumes = {
                    MediaType.MULTIPART_FORM_DATA_VALUE,
                    MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<?> createVideo(
            @RequestPart("content") @Valid @NotNull @NotEmpty MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        Map<String,String> response = new HashMap<>();
        if (!allowedMediaExtensions.contains(contentType)) {
            response.put("message","file extension not allowed");
            response.put("status",String.valueOf(HttpStatus.BAD_REQUEST.value()));
            log.info("file extension not allowed."+contentType);
            return badRequest().body(response);
        }
        Media metaData = uploadMediaService.saveFile(file,"1");
        return ok(metaData);
    }


    @GetMapping("/download/{vid}/video")
    public ResponseEntity<?> getVideoOriginal(@PathVariable("vid") @NotNull @NotEmpty String vid) throws IOException {

        Optional<Media> mediaOptional = uploadMediaService.findById(vid);
        if (mediaOptional.isEmpty()) {
            return noContent().build();
        }

        Media media = mediaOptional.get();
        String path = media.getFilePath();
        byte[] file = uploadMediaService.getFile(path);

        return ok(file);
    }

    @GetMapping("/download/path/{vid}/video")
    public ResponseEntity<?> getVideoFilePath(@PathVariable("vid") @NotNull @NotEmpty String vid) {

        Map<String,String> response = new HashMap<>();
        Optional<Media> mediaOptional = uploadMediaService.findById(vid);
        if (mediaOptional.isEmpty()) {
            return noContent().build();
        }

        Media media = mediaOptional.get();
        String path = media.getFilePath();
        response.put("status",String.valueOf(HttpStatus.OK.value()));
        response.put("filePath",path);
        return ok(response);
    }
}
