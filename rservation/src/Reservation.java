// Reservation.java
import java.time.LocalDate;

public class Reservation {
    private String guestName;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String reservationId;

    // Constructor
    public Reservation(String guestName, Room room, LocalDate checkInDate, LocalDate checkOutDate, String reservationId) {
        if (checkInDate == null || checkOutDate == null) {
                        throw new IllegalArgumentException("Check-in and check-out dates cannot be null.");
                  }
                   if (!checkOutDate.isAfter(checkInDate)) {
                       throw new IllegalArgumentException("Check-out date must be after check-in date.");
                 }
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationId = reservationId;
    }

    // Getters
    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    // Calculate total cost based on number of nights
    public double calculateTotalCost() {
        long numberOfNights = checkInDate.until(checkOutDate).getDays();
        return numberOfNights * room.getPricePerNight();
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Guest: " + guestName +
               ", Room: " + room.getRoomNumber() + ", Check-in: " + checkInDate +
               ", Check-out: " + checkOutDate + ", Total Cost: $" + calculateTotalCost();
    }
}
