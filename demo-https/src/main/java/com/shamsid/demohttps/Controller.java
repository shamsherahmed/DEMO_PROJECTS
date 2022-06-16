package com.shamsid.demohttps;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/ssl-url")
    public String greeting(){
        return "SSL connection is working";
    }
}
