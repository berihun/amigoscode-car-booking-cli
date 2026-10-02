package com.innovatecksolutions.model;

import com.innovatecksolutions.enumerations.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking {
    private Long bookingId;
    private LocalDate startDate;
    private LocalDate endDate;

    private Long userId;
    private Long carId;
    private BigDecimal price;
    private BookingStatus status = BookingStatus.ACTIVE;


}
