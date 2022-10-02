package com.shamsid.springdemodockerfile.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World !!";
    }
}
