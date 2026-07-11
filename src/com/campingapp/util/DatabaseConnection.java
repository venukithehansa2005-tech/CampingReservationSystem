package com.campingapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/campingdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Connected to campingdb successfully!");
            
        com.campingapp.dao.CustomerDAO dao = new com.campingapp.dao.CustomerDAO();
        com.campingapp.model.Customer customer = new com.campingapp.model.Customer();
        customer.setFullName("John Silva");
        customer.setPhone("0771234567");
        customer.setEmail("john@example.com");
        dao.addCustomer(customer);
        System.out.println("Customer added successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}