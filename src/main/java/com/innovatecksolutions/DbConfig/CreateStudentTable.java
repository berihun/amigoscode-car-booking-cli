package com.innovatecksolutions.DbConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStudentTable {

    public static void main(String[] args) {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    student_id VARCHAR(50) PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    course VARCHAR(100) NOT NULL,
                    mark DOUBLE PRECISION NOT NULL
                );
                """;

        // Obtain connection directly from DatabaseConnection
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
            System.out.println("Student table created successfully in PostgreSQL!");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            // Optional: Close the connection pool since the CLI program is exiting
            DatabaseConnection.closePool();
        }
    }
}