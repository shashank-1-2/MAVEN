package com.example;

import java.sql.*;

public class UserDAO {

    // CHANGE THESE VALUES TO MATCH YOUR MYSQL SETUP
    private String jdbcURL = "jdbc:mysql://localhost:3306/servlet_db?useSSL=false";
    private String jdbcUsername = "root";  // Your MySQL username
    private String jdbcPassword = "#Ss9572716927";  // Your MySQL password

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean insertUser(User user) {
        String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getAge());

            int row = statement.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}