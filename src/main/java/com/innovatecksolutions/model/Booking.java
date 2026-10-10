package com.innovatecksolutions.model;

import com.innovatecksolutions.enumerations.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private LocalDate startDate;
    private LocalDate endDate;

    private String userId;
    private String carId;
    private BigDecimal totalCharge;
    private BookingStatus status = BookingStatus.ACTIVE;
    private LocalDate createdAt;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal price) {
        this.totalCharge = price;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId='" + userId + '\'' +
                ", carId='" + carId + '\'' +
                ", totalCharge=" + totalCharge +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
