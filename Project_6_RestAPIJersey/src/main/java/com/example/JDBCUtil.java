package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    
    static {
        try {
            // Load MySQL 8+ Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/university?useSSL=false&allowPublicKeyRetrieval=true"; 
        String user = "root";
        String password = "#Ss9572716927"; 

        Connection connect = DriverManager.getConnection(url, user, password);
        System.out.println("✅ Connected to MySQL 'university' database successfully!");
        return connect;
    }

    public static void closeConnection(Connection connect, Statement statement, ResultSet rs) throws SQLException {
        if (rs != null) rs.close();
        if (statement != null) statement.close();
        if (connect != null) connect.close();
    }
}