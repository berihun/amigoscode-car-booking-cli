package com.innovatecksolutions.services;

import com.innovatecksolutions.enumerations.BookingStatus;
import com.innovatecksolutions.model.Booking;
import com.innovatecksolutions.model.Car;
import com.innovatecksolutions.repository.BookingRepository;
import com.innovatecksolutions.repository.CarPostgesRepository;
import com.innovatecksolutions.repository.CarRepository;
import com.innovatecksolutions.repository.UsersRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UsersRepository usersRepository;

    public BookingService(BookingRepository bookingRepository,
                          CarRepository carRepository,
                          UsersRepository usersRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.usersRepository = usersRepository;
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAllBookings();
    }



    public void saveBookings() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Saving bookings...");

        System.out.print("Enter Booking Id: ");
        String bookingId = sc.nextLine().trim();

        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(sc.nextLine().trim());

        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(sc.nextLine().trim());

        if (endDate.isBefore(startDate)) {
            System.out.println("Error: End date cannot be before start date.");
            return;
        }

        System.out.print("Enter user Id: ");
        String userId = sc.nextLine().trim();

        // 1. Check if user exists
        boolean userExists = usersRepository.findAllUsers().stream()
                .anyMatch(u -> u.getUserId().equals(userId));

        if (!userExists) {
            System.out.println("Error: User with ID '" + userId + "' does not exist in the database!");
            return;
        }

        System.out.print("Enter car Id: ");
        String carId = sc.nextLine().trim();

        // 2. Fetch car safely
        Optional<Car> carOptional = carRepository.findAllCars().stream()
                .filter(c -> c.getCarId().equals(carId))
                .findFirst();

        if (carOptional.isEmpty()) {
            System.out.println("Error: Car with ID '" + carId + "' does not exist in the database!");
            return;
        }

        Car car = carOptional.get();

        BigDecimal pricePerDay = car.getPricePerDay();
        if (pricePerDay == null) {
            System.out.println("Error: Price per day is not set for car ID '" + carId + "'.");
            return;
        }

// 3. Calculate total charge
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days == 0) days = 1; // Minimum 1-day charge

        BigDecimal totalCharge = pricePerDay.multiply(BigDecimal.valueOf(days));
        // 4. Build and save Booking
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setUserId(userId);
        booking.setCarId(carId);
        booking.setTotalCharge(totalCharge);
        booking.setStatus(BookingStatus.ACTIVE);
        booking.setCreatedAt(LocalDate.now());

        bookingRepository.save(booking);
        System.out.println("Booking created successfully! Total charge: $" + totalCharge);
    }
    public void deleteBooking() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Saving bookings...");
        System.out.print("Enter Booking Id :BKG001: ");
        String bookingId = sc.nextLine().trim();

        boolean bookingExists = bookingRepository.findAllBookings().stream()
                .anyMatch(u -> u.getBookingId().equals(bookingId));

        if (!bookingExists) {
            System.out.println("Error: Booking with ID '" + bookingId + "' does not exist in the database!");
            return;
        }
        bookingRepository.delete(bookingId);
    }

    public void viewAllCarsByUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Viewing cars...");
        System.out.println("Enter user Id:");
        String userId = sc.nextLine().trim();
        boolean userExists = usersRepository.findAllUsers().stream()
                .anyMatch(u -> u.getUserId().equals(userId));

        if (!userExists) {
            System.out.println("Error: user with ID '" + userId + "' does not exist in the database!");
            return;
        }
        List<Booking> bookingList = bookingRepository.findAllBookings().stream()
                .filter(ll->ll.getUserId().equalsIgnoreCase(userId))
                .toList();

        if (bookingList.isEmpty()) {
            System.out.println("No cars booked with user ID '" + userId + "' in the database!");
        }

        bookingList.stream()
                .map(ll -> {
                    System.out.println(ll);
                    return null;
                })
                .forEach(ll -> {}); // Terminal operation to trigger the stream
    }
}
