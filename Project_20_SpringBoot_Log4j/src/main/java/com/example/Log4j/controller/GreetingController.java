package com.example.Log4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.Log4j.service.IGreet;
import org.springframework.web.bind.annotation.GetMapping;


@RestController // @ResponseBody + @Controller
public class GreetingController {

    private final IGreet service;

    GreetingController(IGreet service) {
        this.service = service;
    }

    @GetMapping("/api1")
    public ResponseEntity <String> generateGreetings(){
        String body = service.generateWishes();
        return new ResponseEntity<String>(body, HttpStatus.OK);
    }
    
}
