package com.studen;

import java.util.List;
import com.studen.dao.StudentDAO;

public class StudentManagement {

    private StudentDAO studentDAO;

    public StudentManagement() {
        this.studentDAO = new StudentDAO();
    }

    // Add student to DATABASE - RETURNS TRUE/FALSE
    public boolean addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        // Ask the DAO if it actually worked, and return that result
        return studentDAO.addStudent(student);
    }

    // Remove student from DATABASE
    public void removeStudent(int id) {
        studentDAO.deleteStudent(id);
    }

    // Find student from DATABASE
    public Student findStudent(int id) {
        return studentDAO.getStudentById(id);
    }

    // Get all students from DATABASE
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // Update student
    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    // Update grade
    public void updateStudentGrade(int id, double grade) {
        studentDAO.updateStudentGrade(id, grade);
    }

    // Search by course
    public List<Student> findStudentsByCourse(String course) {
        return studentDAO.findStudentsByCourse(course);
    }
}