package com.innovatecksolutions.DbConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Createschema {
    public static void main(String[] args) {
        String sqlUser = """
                CREATE TABLE IF NOT EXISTS users (
                    user_id VARCHAR(20) NOT NULL PRIMARY KEY,
                    name VARCHAR(150) NOT NULL
                );
                """;

        String sqlCar = """
                CREATE TABLE IF NOT EXISTS cars (
                    car_id VARCHAR(20) NOT NULL PRIMARY KEY,
                    registration_no VARCHAR(20) NOT NULL,
                    price DOUBLE PRECISION,
                    brand VARCHAR(20) NOT NULL,
                    car_type VARCHAR(20) NOT NULL
                );
                """;

        String sqlBooking = """
                CREATE TABLE IF NOT EXISTS bookings (
                    booking_id VARCHAR(20) NOT NULL PRIMARY KEY,
                    car_id VARCHAR(20) NOT NULL,
                    user_id VARCHAR(20) NOT NULL,
                    total_charge DOUBLE PRECISION,
                    start_date DATE,
                    end_date DATE,
                    status VARCHAR(20) NOT NULL,
                    created_at DATE,
                    CONSTRAINT fk_booking_car FOREIGN KEY (car_id) REFERENCES cars(car_id) ON DELETE CASCADE,
                    CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
                );
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt1 = connection.createStatement();
             Statement stmt2 = connection.createStatement();
             Statement stmt3 = connection.createStatement()) {

            // Create users table
            stmt1.executeUpdate(sqlUser);
            System.out.println("Users table created or already exists.");

            // Create cars table
            stmt2.executeUpdate(sqlCar);
            System.out.println("Cars table created or already exists.");

            // Create bookings table with foreign keys
            stmt3.executeUpdate(sqlBooking);
            System.out.println("Bookings table created or already exists.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}