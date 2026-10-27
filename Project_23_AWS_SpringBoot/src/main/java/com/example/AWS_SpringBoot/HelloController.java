package com.example.AWS_SpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Application is running successfully on AWS!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}