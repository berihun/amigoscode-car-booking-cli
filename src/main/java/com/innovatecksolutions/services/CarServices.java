package com.innovatecksolutions.services;

import com.innovatecksolutions.model.Booking;
import com.innovatecksolutions.model.Car;
import com.innovatecksolutions.repository.BookingRepository;
import com.innovatecksolutions.repository.CarRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarServices {
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    public CarServices(CarRepository carRepository, BookingRepository bookingRepository) {
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    public void getAvailabeCars() {
        List<Booking> bookings = bookingRepository.findAllBookings();
        List<Car> cars = carRepository.findAllCars();

        if (bookings.isEmpty()) {
            System.out.println("No bookings found");
        }
        if (cars.isEmpty()) {
            System.out.println("No cars found");
        }



        // 1. Collect all carIds that currently have an active booking
        Set<String> bookedCarIds = bookings.stream()
                .map(Booking::getCarId)
                .collect(Collectors.toSet());

        // 2. Filter out cars whose carId is present in the booked set
        List<Car> availableCars = cars.stream()
                .filter(car -> !bookedCarIds.contains(car.getCarId()))
                .toList();

        if (availableCars.isEmpty()) {
            System.out.println("No cars found in inventory.");
        }
        availableCars.stream().forEach(System.out::println);
    }
    public void getElectricCars() {
        List<Car> cars = carRepository.findAllCars();
        if (cars.isEmpty()) {
            System.out.println("No cars found");
        }
        List<Car> electricCars= cars.stream()
                .filter(car -> car.getCarType().equals("Electric"))
                .toList();
        if (electricCars.isEmpty()) {
            System.out.println("No electric cars found in inventory.");
        }
        electricCars.stream().forEach(System.out::println);
    }

}
