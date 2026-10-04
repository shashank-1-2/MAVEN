package com.example;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByGradeGreaterThanEqual(double grade);  // name only, no query
    List<Student> findByCourse(String course);                // WHERE course = ?
    List<Student> findByNameContaining(String text);          // WHERE name LIKE %text%
}