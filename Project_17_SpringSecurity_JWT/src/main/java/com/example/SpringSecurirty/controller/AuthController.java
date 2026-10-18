package com.example.SpringSecurirty.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringSecurirty.model.Users;
import com.example.SpringSecurirty.service.UserServices;

@RestController
public class AuthController {

    private final UserServices userServices;

    public AuthController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        try {
            userServices.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            String token = userServices.verify(user);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}