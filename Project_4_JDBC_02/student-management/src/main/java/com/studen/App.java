package com.studen;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        System.out.println("🎉 Running Student Management System!");
        System.out.println("Java Version: " + System.getProperty("java.version") + "\n");

        StudentManagement management = new StudentManagement();

        // ==========================================
        // ADDING MULTIPLE STUDENTS AT ONCE
        // ==========================================
        
        // Create a List to hold the students
        List<Student> studentsToAdd = new ArrayList<>();

        // Add multiple students (ID is 0 so database auto-generates ID)
        studentsToAdd.add(new Student(0, "Abhijeet", "Mathematics & Computing", 99.5));
        studentsToAdd.add(new Student(0, "Rohan", "Mechanical", 82.5));
        studentsToAdd.add(new Student(0, "Priya", "Computer Science", 91.0));

        // Loop through the list and add each one
        System.out.println("Adding multiple students...");
        for (Student s : studentsToAdd) {
            boolean isSuccess = management.addStudent(s);
            if (isSuccess) {
                System.out.println("✅ Added: " + s.getName());
            } else {
                System.out.println("❌ Failed to add: " + s.getName());
            }
        }
        System.out.println("==========================================\n");

        // Get all students from DATABASE
        System.out.println("\n📚 All Students:");
        management.getAllStudents()
                  .forEach(System.out::println);

        // Find student by ID
        System.out.println("\n🔍 Searching for Student ID 5:");
        Student found = management.findStudent(5);

        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Student not found.");
        }
    }
}