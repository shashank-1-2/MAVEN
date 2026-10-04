package com.example;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    //Replace StudentDAO -> StudentRepository
    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // GET all: http://localhost:8080/api/student
    @GetMapping
    public List<Student> getStudents() {
        return repo.findAll();
    }

    // GET by ID: http://localhost:8080/api/student/14
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET filter: http://localhost:8080/api/student/filter?minGrade=85
    @GetMapping("/filter")
    public List<Student> filterByGrade(
            @RequestParam(name = "minGrade", defaultValue = "0.0") double minGrade) {
        return repo.findByGradeGreaterThanEqual(minGrade);
    }

        // GET http://localhost:8080/api/student/course?name=Java
    @GetMapping("/course")
    public List<Student> byCourse(@RequestParam("name") String name) {
        return repo.findByCourse(name);
    }

    // GET http://localhost:8080/api/student/search?text=ra
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam("text") String text) {
        return repo.findByNameContaining(text);
    }

    // POST create: http://localhost:8080/api/student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = repo.save(student);
        return ResponseEntity.created(URI.create("/api/student/" + saved.getId())).body(saved);
    }

    // PUT full update: http://localhost:8080/api/student/14
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id,
                                                 @RequestBody Student studentData) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentData.setId(id);
        return ResponseEntity.ok(repo.save(studentData));
    }

    // PATCH partial update: http://localhost:8080/api/student/14
    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable("id") int id,
                                                @RequestBody Map<String, Object> updates) {
        return repo.findById(id).map(student -> {
            if (updates.containsKey("name"))
                student.setName((String) updates.get("name"));
            if (updates.containsKey("course"))
                student.setCourse((String) updates.get("course"));
            if (updates.containsKey("grade"))
                student.setGrade(((Number) updates.get("grade")).doubleValue());
            return ResponseEntity.ok(repo.save(student));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE: http://localhost:8080/api/student/14
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}