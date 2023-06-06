package com.group5.hotel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Booking {
    public final String bookingID; // unix timestamp of booking created
    /**
     * Rooms booked will not be vacant starting from this date.
     */
    private Date startDate;
    /**
     * Rooms booked will be available again from this date.
     */
    private Date endDate;
    private List<Room> roomsBooked;
    private double totalPrice;
    private boolean isPaid;
    private Account user;
    private Account bookingManager;

    /**
     * Constructor to populate the Booking object
     * @param ID The ID of the booking
     * @param dateBooked The starting date for the booking
     * @param endDate The finishing date for the booking
     * @param roomsBooked A list of the rooms in the booking
     * @param user The user who is booking
     * @param manager The user who managed/created the booking
     */
    public Booking(String ID, Date dateBooked, Date endDate, List<Room> roomsBooked, Account user, Account manager) {
        bookingID = ID;
        this.startDate = dateBooked;
        this.endDate = endDate;
        this.roomsBooked = roomsBooked;
        totalPrice = 0;
        for (int i = 0; i < roomsBooked.size(); i++) {
            totalPrice += roomsBooked.get(i).price;
        }
        isPaid = false;
        this.user = user;
        bookingManager = manager;
    }

    /**
     *
     * @return Returns a nicely formatted string which represents a Booking
     */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        sb.append("Booking ID: " + bookingID);
        sb.append("\n\tDate: " + formatter.format(startDate) + " to " + formatter.format(endDate));

        sb.append("\n\tRooms booked: ");
        for (Room room : roomsBooked) {
            sb.append(room.getRoomNumber() + " ");
        }

        sb.append("\n\tBooker: " + user.firstName + " " + user.lastName);
        sb.append("\n\tBooking manager: " + (bookingManager == null ? "USER_BOOKED" : bookingManager.firstName + " " + bookingManager.lastName));

        return sb.toString();
    }

    // Getter functions to return roomsBooked, startDate, endDate, and user
    public List<Room> getRooms() { return roomsBooked; }
    public Date beginDate() { return startDate; }
    public Date endDate() { return endDate; }
    public Account getAccount() { return user; }
    public boolean equals(Booking b) { return bookingID.equals(b.bookingID); }
}
