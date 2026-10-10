package com.innovatecksolutions.repository;

import com.innovatecksolutions.model.Booking;

import java.util.List;

public interface BookingRepository {
    String save(Booking booking);
    void delete(String bookingId);
    List<Booking> findAllBookings();
    void updateBooking(Booking booking);
}
