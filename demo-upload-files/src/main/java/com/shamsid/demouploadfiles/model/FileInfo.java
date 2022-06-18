package com.shamsid.demouploadfiles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class FileInfo {
    private String name;
    private String url;
    private String size;
    public FileInfo(String name,String url){
        this.name = name;
        this.url = url;
    }
}