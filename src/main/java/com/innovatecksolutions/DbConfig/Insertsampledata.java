package com.innovatecksolutions.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertStudentDemo {
    public static void main(String[] args) {
        String sql = """
                INSERT INTO students (student_id, name, course, mark)
                VALUES (?, ?, ?, ?)
                ON CONFLICT (student_id) DO NOTHING;
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Bind values to the ? placeholders
            pstmt.setString(1, "STU1001");
            pstmt.setString(2, "Alex Tan");
            pstmt.setString(3, "Java Programming");
            pstmt.setDouble(4, 88.5);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student record inserted successfully.");
            } else {
                System.out.println("Student already exists (no rows inserted).");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            DatabaseConnection.closePool();
        }
    }
}