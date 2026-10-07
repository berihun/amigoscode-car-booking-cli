package com.innovatecksolutions.repository;

import com.innovatecksolutions.model.Booking;

import java.util.List;

public interface BookingRepository {
    void save(Booking booking);
    void delete(Booking booking);
    List<Booking> findAllBookings();
    void updateBooking(Booking booking);
}
