package com.dkupadhy.learningSpringBootApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/")
    public String hello(){
        System.out.println((int)'न');
        return "नमस्ते विश्व, दीपक की ओर से";
    }
}
