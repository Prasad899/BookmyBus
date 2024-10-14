import java.util.ArrayList;
import java.util.Scanner;

// Bus class definition
class Bus {
    private String busNumber;
    private String destination;
    private int availableSeats;

    public Bus(String busNumber, String destination, int availableSeats) {
        this.busNumber = busNumber;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    public void cancelSeat() {
        availableSeats++;
    }

    @Override
    public String toString() {
        return "Bus [Bus Number=" + busNumber + ", Destination=" + destination + ", Available Seats=" + availableSeats + "]";
    }
}

// Booking class definition
class Booking {
    private String bookingId;
    private String busNumber;

    public Booking(String bookingId, String busNumber) {
        this.bookingId = bookingId;
        this.busNumber = busNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    @Override
    public String toString() {
        return "Booking [Booking ID=" + bookingId + ", Bus Number=" + busNumber + "]";
    }
}

// BusTicketBookingSystem class definition
public class BusTicketBookingSystem {

    private static ArrayList<Bus> buses = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static int bookingCounter = 1;

    public static void main(String[] args) {
        initializeBuses();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nBus Ticket Booking System:");
            System.out.println("1. View Available Buses");
            System.out.println("2. Book a Ticket");
            System.out.println("3. View All Bookings");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableBuses();
                    break;
                case 2:
                    bookTicket(scanner);
                    break;
                case 3:
                    viewAllBookings();
                    break;
                case 4:
                    cancelBooking(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Initialize buses
    private static void initializeBuses() {
        buses.add(new Bus("BUS001", "City Bengaluru", 10));
        buses.add(new Bus("BUS002", "City Chennai", 5));
        buses.add(new Bus("BUS003", "City Hyderabad", 2));
        buses.add(new Bus("BUS004", "City Vishakapatnam", 11));
        buses.add(new Bus("BUS005", "City Tirupati", 7));
    }

    // View available buses
    private static void viewAvailableBuses() {
        System.out.println("\nAvailable Buses:");
        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }

    // Book a ticket
    private static void bookTicket(Scanner scanner) {
        System.out.print("Enter Bus Number to book: ");
        String busNumber = scanner.next();

        for (Bus bus : buses) {
            if (bus.getBusNumber().equalsIgnoreCase(busNumber)) {
                if (bus.getAvailableSeats() > 0) {
                    String bookingId = "BOOK" + (bookingCounter++);
                    bookings.add(new Booking(bookingId, busNumber));
                    bus.bookSeat();
                    System.out.println("Ticket booked successfully! Booking ID: " + bookingId);
                } else {
                    System.out.println("No available seats on this bus.");
                }
                return;
            }
        }
        System.out.println("Bus not found.");
    }

    // View all bookings
    private static void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("\nList of All Bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    // Cancel a booking
    private static void cancelBooking(Scanner scanner) {
        System.out.print("Enter Booking ID to cancel: ");
        String bookingId = scanner.next();

        Booking bookingToCancel = null;
        for (Booking booking : bookings) {
            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            for (Bus bus : buses) {
                if (bus.getBusNumber().equalsIgnoreCase(bookingToCancel.getBusNumber())) {
                    bus.cancelSeat();
                    break;
                }
            }
            bookings.remove(bookingToCancel);
            System.out.println("Booking with ID " + bookingId + " canceled successfully.");
        } else {
            System.out.println("Booking ID not found.");
        }
    }
}
