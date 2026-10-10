package com.innovatecksolutions.utility;

import com.innovatecksolutions.model.Booking;
import com.innovatecksolutions.model.User;
import com.innovatecksolutions.repository.*;
import com.innovatecksolutions.services.BookingService;
import com.innovatecksolutions.services.UserServices;

import java.util.List;
import java.util.Scanner;

public class Menus {
    // Instantiate repository and service so userServices is NOT null

    // initialize booking repo and service
    private final UsersRepository usersRepository = new UsersPostgresRepository();
    private final UserServices userServices = new UserServices(usersRepository);

    private final BookingRepository bookingRepository = new BookingPostgresRepository();
    private final CarRepository carRepository = new CarPostgesRepository();

    // Re-use usersRepository instead of creating usersRepository2
    private final BookingService bookingService = new BookingService(
            bookingRepository,
            carRepository,
            usersRepository
    );
    public static void main(String[] args) {
        Menus menuApp = new Menus();
        menuApp.start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Book Car");
            System.out.println("2. Delete Booking");
            System.out.println("3. View All User Booked a Car");
            System.out.println("4. View All Bookings");
            System.out.println("5. View Available Cars");
            System.out.println("6. View Available Electric Car");
            System.out.println("7. View All Users");
            System.out.println("8. Exit Application");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            if (choice == 8) {
                System.out.println("Exiting application...");
                break;
            }
            chooseMenu(choice);
        }
    }

    void chooseMenu(int no) {
        switch (no) {
            case 1:
                bookingService.saveBookings();
                break;
            case 2:
                bookingService.deleteBooking();
                break;
            case 3:
                bookingService.viewAllCarsByUser();
                break;
            case 4:
                List<Booking> bookings = bookingRepository.findAllBookings();
                if (bookings.isEmpty()) {
                    System.out.println("No bookings found in the database.");
                }else {
                    bookings.forEach(System.out::println);
                }
                break;
            case 5:
                System.out.println("View available cars selected.");
                break;
            case 6:
                System.out.println("View available electric cars selected.");
                break;
            case 7:
                List<User> usersList = userServices.findAllUsers();
                if (usersList.isEmpty()) {
                    System.out.println("No users found in database.");
                } else {
                    usersList.forEach(System.out::println);
                }
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}