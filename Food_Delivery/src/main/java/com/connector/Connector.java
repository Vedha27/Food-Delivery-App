package com.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    // Load DB details from environment variables
    private static final String URL = "jdbc:mysql://localhost:3306/fooddelivery";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Vedha@276";

    public static Connection CON = null;

    public static Connection getConnection() {
        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            // Connect to DB
            CON = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB Connection Failed: " + e.getMessage());
            e.printStackTrace();
        }

        return CON;
    }
}
