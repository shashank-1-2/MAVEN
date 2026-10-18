package com.example.SpringSecurirty.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringSecurirty.model.Users;
import com.example.SpringSecurirty.repository.UserRepo;

@Service
public class UserServices {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public UserServices(UserRepo repo,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authManager,
                        JwtService jwtService) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public Users register(Users user) {
        if (repo.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }
        return repo.save(user);
    }

    // Checks username/password against MySQL and returns a JWT
    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        throw new BadCredentialsException("Invalid credentials");
    }
}