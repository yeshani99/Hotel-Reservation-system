// Main.java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create a hotel
        hotel hotel = new hotel("Grand Oasis");

        // Add rooms
        hotel.addRoom(new Room(101, "Single", 100.0));
        hotel.addRoom(new Room(102, "Double", 150.0));
        hotel.addRoom(new Room(201, "Suite", 300.0));

        // Display hotel details
        System.out.println(hotel);

        // Check available rooms for a date range
        LocalDate checkIn = LocalDate.of(2025, 5, 1);
        LocalDate checkOut = LocalDate.of(2025, 5, 5);
        System.out.println("\nAvailable rooms from " + checkIn + " to " + checkOut + ":");
        for (Room room : hotel.findAvailableRooms(checkIn, checkOut)) {
            System.out.println(room);
        }

        // Make a reservation
        try {
            Reservation reservation = hotel.makeReservation("John Doe", 101, checkIn, checkOut);
            System.out.println("\nReservation made: " + reservation);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Check available rooms again
        System.out.println("\nAvailable rooms after reservation:");
        for (Room room : hotel.findAvailableRooms(checkIn, checkOut)) {
            System.out.println(room);
        }

        // Display all reservations
        System.out.println("\nAll reservations:");
        for (Reservation res : hotel.getReservations()) {
            System.out.println(res);
        }

        // Cancel a reservation
        String reservationId = hotel.getReservations().get(0).getReservationId();
        if (hotel.cancelReservation(reservationId)) {
            System.out.println("\nReservation " + reservationId + " cancelled successfully.");
        }

        // Check available rooms after cancellation
        System.out.println("\nAvailable rooms after cancellation:");
        for (Room room : hotel.findAvailableRooms(checkIn, checkOut)) {
            System.out.println(room);
        }
    }
}