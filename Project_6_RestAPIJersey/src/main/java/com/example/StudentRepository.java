package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    // 1. Fetch all students from MySQL database via JDBCUtil
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        String query = "SELECT id, name, course, grade FROM students";
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = JDBCUtil.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setCourse(rs.getString("course"));
                s.setGrade(rs.getDouble("grade"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.closeConnection(con, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // 2. Fetch single student by ID
    public Student getStudent(int id) {
        String query = "SELECT id, name, course, grade FROM students WHERE id = ?";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setCourse(rs.getString("course"));
                s.setGrade(rs.getDouble("grade"));
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.closeConnection(con, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 3. Persist new student record into MySQL
    public void creat(Student s1) {
        String query = "INSERT INTO students (id, name, course, grade) VALUES (?, ?, ?, ?)";
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, s1.getId());
            ps.setString(2, s1.getName());
            ps.setString(3, s1.getCourse());
            ps.setDouble(4, s1.getGrade());

            ps.executeUpdate();
            System.out.println("Inserted record into MySQL database: " + s1.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.closeConnection(con, ps, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 4. Update an existing student record in MySQL
    public boolean update(Student s1) {
        String query = "UPDATE students SET name = ?, course = ?, grade = ? WHERE id = ?";
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(query);
            
            ps.setString(1, s1.getName());
            ps.setString(2, s1.getCourse());
            ps.setDouble(3, s1.getGrade());
            ps.setInt(4, s1.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                JDBCUtil.closeConnection(con, ps, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}