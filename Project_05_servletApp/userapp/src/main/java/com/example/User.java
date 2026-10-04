package com.example;

public class User {
    private String name;
    private String email;
    private int age;

    // No-arg constructor (required for some frameworks)
    public User() {
    }

    // Constructor with 3 args (this is what your Servlet is calling)
    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}