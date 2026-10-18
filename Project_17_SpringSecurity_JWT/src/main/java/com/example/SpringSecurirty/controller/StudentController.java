package com.example.SpringSecurirty.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringSecurirty.model.Student;

@RestController 
public class StudentController {

    private List<Student> students = new ArrayList<>(
        List.of(
            new Student(1, "Shashank", 90),
            new Student(2, "Rohit", 80),
            new Student(3, "Ramesh", 70)
        ));
    
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) {
        students.add(student);
        return "Student added successfully";
    }
}