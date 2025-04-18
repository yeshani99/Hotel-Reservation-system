// Hotel.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class hotel {
    private String name;
    private List<Room> rooms;
    private List<Reservation> reservations;

    // Constructor
    public hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Add a room to the hotel
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Find available rooms for given dates
    public List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // Check if a specific room is available for the given dates
    private boolean isRoomAvailable(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        if (!room.isAvailable()) {
            return false;
        }
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == room.getRoomNumber()) {
                LocalDate resCheckIn = reservation.getCheckInDate();
                LocalDate resCheckOut = reservation.getCheckOutDate();
                // Check for date overlap
                if (!(checkOutDate.isBefore(resCheckIn) || checkInDate.isAfter(resCheckOut))) {
                    return false;
                }
            }
        }
        return true;
    }

    // Make a reservation
    public Reservation makeReservation(String guestName, int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found!");
        }
        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Room is not available for the selected dates!");
        }
        String reservationId = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(guestName, room, checkInDate, checkOutDate, reservationId);
        reservations.add(reservation);
        room.setAvailable(false); // Mark room as unavailable
        return reservation;
    }

    // Cancel a reservation
    public boolean cancelReservation(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                reservation.getRoom().setAvailable(true); // Free the room
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    // Find room by room number
    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    // Get all reservations
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    @Override
    public String toString() {
        return "Hotel: " + name + ", Rooms: " + rooms.size() + ", Reservations: " + reservations.size();
    }
}