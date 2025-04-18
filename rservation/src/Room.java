// Room.java
public class Room {
    private int roomNumber;
    private String roomType; // e.g., Single, Double, Suite
    private double pricePerNight;
    private boolean isAvailable;

    // Constructor
    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true; // Room is available by default
    }

    // Getters and Setters
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + "), Price: $" + pricePerNight + "/night, Available: " + isAvailable;
    }
}