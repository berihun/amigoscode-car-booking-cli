package com.innovatecksolutions.DbConfig;

import java.sql.*;

public class Insertsampledata {
    public static void main(String[] args) {
        String sqlUserTable = """
                CREATE TABLE IF NOT EXISTS users (
                    user_id VARCHAR(20) NOT NULL PRIMARY KEY,
                    name VARCHAR(150) NOT NULL
                );
                """;

        String sqlCarTable = """
                CREATE TABLE IF NOT EXISTS cars (
                    car_id VARCHAR(20) NOT NULL PRIMARY KEY,
                    registration_no VARCHAR(20) NOT NULL,
                    price DOUBLE PRECISION,
                    brand VARCHAR(20) NOT NULL,
                    car_type VARCHAR(20) NOT NULL
                );
                """;

        String sqlBookingTable = """
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

        // Insert Queries
        String insertUserSql = "INSERT INTO users (user_id, name) VALUES (?, ?)";
        String insertCarSql = "INSERT INTO cars (car_id, registration_no, price, brand, car_type) VALUES (?, ?, ?, ?, ?)";
        String insertBookingSql = "INSERT INTO bookings (booking_id, car_id, user_id, total_charge, start_date, end_date, status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Disable auto-commit for transaction handling
            connection.setAutoCommit(false);

            // 1. Create Tables
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sqlUserTable);
                stmt.executeUpdate(sqlCarTable);
                stmt.executeUpdate(sqlBookingTable);
                System.out.println("Tables created/verified successfully.");
            }

            // 2. Insert Users
            try (PreparedStatement pstmtUser = connection.prepareStatement(insertUserSql)) {
                pstmtUser.setString(1, "USR001");
                pstmtUser.setString(2, "Alice Smith");
                pstmtUser.executeUpdate();

                pstmtUser.setString(1, "USR002");
                pstmtUser.setString(2, "Bob Johnson");
                pstmtUser.executeUpdate();
                System.out.println("Users inserted.");
            }

            // 3. Insert Cars
            try (PreparedStatement pstmtCar = connection.prepareStatement(insertCarSql)) {
                pstmtCar.setString(1, "CAR001");
                pstmtCar.setString(2, "ABC-1234");
                pstmtCar.setDouble(3, 85.50);
                pstmtCar.setString(4, "Toyota");
                pstmtCar.setString(5, "Sedan");
                pstmtCar.executeUpdate();

                pstmtCar.setString(1, "CAR002");
                pstmtCar.setString(2, "XYZ-9876");
                pstmtCar.setDouble(3, 120.00);
                pstmtCar.setString(4, "Tesla");
                pstmtCar.setString(5, "Electric");
                pstmtCar.executeUpdate();
                System.out.println("Cars inserted.");
            }

            // 4. Insert Bookings (Referencing existing USR and CAR IDs)
            try (PreparedStatement pstmtBooking = connection.prepareStatement(insertBookingSql)) {
                pstmtBooking.setString(1, "BKG001");
                pstmtBooking.setString(2, "CAR001"); // FK to cars
                pstmtBooking.setString(3, "USR001"); // FK to users
                pstmtBooking.setDouble(4, 256.50);
                pstmtBooking.setDate(5, Date.valueOf("2026-03-01"));
                pstmtBooking.setDate(6, Date.valueOf("2026-03-04"));
                pstmtBooking.setString(7, "CONFIRMED");
                pstmtBooking.setDate(8, Date.valueOf("2026-02-28"));
                pstmtBooking.executeUpdate();

                pstmtBooking.setString(1, "BKG002");
                pstmtBooking.setString(2, "CAR002"); // FK to cars
                pstmtBooking.setString(3, "USR002"); // FK to users
                pstmtBooking.setDouble(4, 360.00);
                pstmtBooking.setDate(5, Date.valueOf("2026-03-10"));
                pstmtBooking.setDate(6, Date.valueOf("2026-03-13"));
                pstmtBooking.setString(7, "PENDING");
                pstmtBooking.setDate(8, Date.valueOf("2026-03-01"));
                pstmtBooking.executeUpdate();
                System.out.println("Bookings inserted.");
            }

            // Commit all operations together
            connection.commit();
            System.out.println("Sample data successfully inserted!");

        } catch (SQLException e) {
            System.err.println("Database error during batch setup: " + e.getMessage());
        }
    }
}