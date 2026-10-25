package com.example.Docker_SpringBoot;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    
    // Optional query methods based on the new fields
    List<Student> findByCourse(String course);
    List<Student> findByGradeGreaterThanEqual(Double grade);
}