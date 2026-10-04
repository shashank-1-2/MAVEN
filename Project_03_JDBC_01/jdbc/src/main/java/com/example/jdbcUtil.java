package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class jdbcUtil {
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/university"; 
        String user = "root";
        String password = "#Ss9572716927"; 

        Connection connect = DriverManager.getConnection(url, user, password);
        System.out.println("✅ Connected to database successfully!");
        return connect;
    }

    // This method MUST accept 3 arguments
    public static void closeConnection(Connection connect, Statement statement, ResultSet rs) throws SQLException {
        if (rs != null) rs.close();
        if (statement != null) statement.close();
        if (connect != null) connect.close();
    }
}