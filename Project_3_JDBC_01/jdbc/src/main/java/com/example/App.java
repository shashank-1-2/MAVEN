package com.example;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        
        Connection connect = null;
        Statement statement = null;

        try {
            // Get connection from util
            connect = jdbcUtil.getConnection();

            // Creating statement
            statement = connect.createStatement();

            // INSERT DATA
            String sql = "INSERT INTO students (name, course, grade) VALUES ('Usaid', 'ECE', 100)";
            int rowsInserted = statement.executeUpdate(sql);
            System.out.println("✅ Success! " + rowsInserted + " row(s) added to the database.");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the resources using util
            try {
                jdbcUtil.closeConnection(connect, statement, null); // Pass null for ResultSet
                System.out.println("🔒 Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}