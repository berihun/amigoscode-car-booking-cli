package com.innovatecksolutions.repository;

import com.innovatecksolutions.DbConfig.DatabaseConnection;
import com.innovatecksolutions.enumerations.BookingStatus;
import com.innovatecksolutions.model.Booking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingPostgresRepository implements BookingRepository {

    @Override
    public String save(Booking booking) {
        String insertBookingSql = "INSERT INTO bookings (booking_id, car_id, user_id, total_charge, start_date, end_date, status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmtBooking = connection.prepareStatement(insertBookingSql)) {
            pstmtBooking.setString(1, booking.getBookingId());
            pstmtBooking.setString(2, booking.getCarId());
            pstmtBooking.setString(3, booking.getUserId());
            pstmtBooking.setDouble(4, 300);
            pstmtBooking.setDate(5, Date.valueOf(booking.getStartDate()));
            pstmtBooking.setDate(6, Date.valueOf(booking.getEndDate()));
            pstmtBooking.setString(7, BookingStatus.ACTIVE.name()); // "ACTIVE"
            pstmtBooking.setDate(8, Date.valueOf(booking.getCreatedAt()));
            pstmtBooking.executeUpdate();
        } catch (Exception e) {
            System.out.println("Unable to save booking");
        }
        return "booking created successfuly";
    }

    @Override
    public void delete(String bookingId) {
        String deleteBookingSql = "DELETE FROM bookings WHERE booking_id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement pstmtBooking = connection.prepareStatement(deleteBookingSql)) {

            pstmtBooking.setString(1, bookingId);
            pstmtBooking.executeUpdate();

            System.out.println("Booking has been deleted successfully");
        }catch (Exception e){
            System.out.println("Unable to delete booking");
        }
    }

    @Override
    public List<Booking> findAllBookings() {
        String sql = "select * from bookings";
        List<Booking> bookingList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {


            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getString("booking_id"));
                booking.setCarId(resultSet.getString("car_id"));
                booking.setUserId(resultSet.getString("user_id"));
                booking.setEndDate(resultSet.getDate("end_date").toLocalDate());
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setTotalCharge(resultSet.getBigDecimal("total_charge"));
                booking.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
                booking.setStatus(BookingStatus.valueOf(resultSet.getString("status")));
                bookingList.add(booking);


            }

        } catch (Exception e) {
            System.out.println("Database error fetching bookings: " + e.getMessage());
        }
        return bookingList;
    }

    @Override
    public void updateBooking(Booking booking) {

    }
}
