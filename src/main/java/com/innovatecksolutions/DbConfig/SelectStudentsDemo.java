package com.innovatecksolutions.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStudentsDemo {
    public static void main(String[] args) {
        String sql = "SELECT student_id, name, course, mark FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Student Records");
            System.out.println("----------------------------------------");

            boolean hasRecords = false;

            while (resultSet.next()) {
                hasRecords = true;
                String studentId = resultSet.getString("student_id");
                String name = resultSet.getString("name");
                String course = resultSet.getString("course");
                double mark = resultSet.getDouble("mark");

                System.out.println("Student ID : " + studentId);
                System.out.println("Name       : " + name);
                System.out.println("Course     : " + course);
                System.out.printf("Mark       : %.2f%n", mark);
                System.out.println("----------------------------------------");
            }

            if (!hasRecords) {
                System.out.println("No student records found in the database.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            // Safely close the HikariCP connection pool on exit
            DatabaseConnection.closePool();
        }
    }
}