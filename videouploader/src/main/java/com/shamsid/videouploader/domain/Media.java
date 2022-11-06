package com.shamsid.videouploader.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;

@Data @NoArgsConstructor
@Entity @Table(name = "interview_media_files")
public class Media {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "id", nullable = false, unique = true)
    private String mid;

    @Column(name = "name")
    private String name;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "extension")
    private String fileExtension;

    @Column(name="created_by")
    private String createdBy;

    @Column(name = "creation_time",nullable = false)
    private Date creationTime;

}
