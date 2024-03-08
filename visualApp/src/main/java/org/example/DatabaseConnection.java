package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/searadardb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/searadardb", "postgres", "123");
        } catch (SQLException var1) {
            var1.printStackTrace();
            return null;
        }
    }
}