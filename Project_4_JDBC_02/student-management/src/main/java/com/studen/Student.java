package com.studen;

public class Student {
    private int id;
    private String name;
    private String course;
    private double grade;

    // Constructor (The ONLY constructor you need)
    public Student(int id, String name, String course, double grade) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.grade = grade;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public double getGrade() { return grade; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }

    // Setter for grade with validation
    public void setGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
    }

    // Calculate grade letter
    public String getGradeLetter() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', course='%s', grade=%.2f, letter='%s'}",
                id, name, course, grade, getGradeLetter());
    }
}