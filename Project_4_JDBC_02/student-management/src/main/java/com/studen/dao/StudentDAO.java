package com.studen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.studen.Student;

public class StudentDAO {
    
    // JDBC URL, Username, Password - Change password to your MySQL password
    private static final String URL = "jdbc:mysql://localhost:3306/university";
    private static final String USER = "root";
    private static final String PASS = "#Ss9572716927"; // CHANGE THIS!

    // 1. CREATE (Insert a student) - NOW RETURNS BOOLEAN
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (id, name, course, grade) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the ID (0 means auto-increment)
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getCourse());
            pstmt.setDouble(4, student.getGrade());
            
            int rowsAffected = pstmt.executeUpdate();
            
            // Return true if the insert worked
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error inserting student: " + e.getMessage());
            e.printStackTrace();
            return false; // Return false if it failed
        }
    }

    // 2. READ (Get student by ID)
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("course"),
                    rs.getDouble("grade")
                );
                return s;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error fetching student: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Not found
    }

    // 3. READ ALL (Get all students)
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("course"),
                    rs.getDouble("grade")
                );
                students.add(s);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error fetching all students: " + e.getMessage());
            e.printStackTrace();
        }
        return students;
    }

    // 4. UPDATE (Update student grade)
    public void updateStudentGrade(int id, double newGrade) {
        String sql = "UPDATE students SET grade = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, newGrade);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Updated grade for student ID: " + id);
            } else {
                System.out.println("⚠️ Student with ID " + id + " not found.");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error updating student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 5. UPDATE (Update entire student)
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, course = ?, grade = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getCourse());
            pstmt.setDouble(3, student.getGrade());
            pstmt.setInt(4, student.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Updated student ID: " + student.getId());
            } else {
                System.out.println("⚠️ Student with ID " + student.getId() + " not found.");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error updating student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 6. DELETE (Delete student by ID)
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Deleted student with ID: " + id);
            } else {
                System.out.println("⚠️ Student with ID " + id + " not found.");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 7. SEARCH (Find students by course)
    public List<Student> findStudentsByCourse(String course) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE course = ? ORDER BY name";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, course);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("course"),
                    rs.getDouble("grade")
                );
                students.add(s);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error searching students: " + e.getMessage());
            e.printStackTrace();
        }
        return students;
    }
}