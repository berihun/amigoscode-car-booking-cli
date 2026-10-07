package com.innovatecksolutions.DbConfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final HikariDataSource dataSource;

    // Static block runs once when the class is loaded
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/students_db");
        config.setUsername("postgres");      // your postgres username
        config.setPassword("123456");      // your postgres password

        // Pool configuration
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);   // 30 seconds

        dataSource = new HikariDataSource(config);
    }

    // Call this method whenever you need a connection
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Optional: Call this when shutting down your application
    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}