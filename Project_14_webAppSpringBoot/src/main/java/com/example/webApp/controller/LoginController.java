package com.example.webApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class LoginController {
    
    @GetMapping (value="/login" , produces = "text/plain")
    public String login(){
        return "Login Page ... ";
    }

}
