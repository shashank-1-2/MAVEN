package com.example.Docker_SpringBoot;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Health check endpoint
    @GetMapping("/status")
    public String getStatus() {
        return "Student service is active";
    }

    // GET all students from MySQL: http://localhost:8080/api/students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // GET student by ID: http://localhost:8080/api/students/1
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // POST create a student: http://localhost:8080/api/students
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    // DELETE a student: http://localhost:8080/api/students/1
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentRepo.deleteById(id);
        return "Student deleted successfully with ID: " + id;
    }
}