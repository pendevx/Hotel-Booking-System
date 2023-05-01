package com.group5.system;

import com.group5.hotel.Room;
import com.group5.hotel.Booking;
import java.util.Date;
import java.util.List;

import com.group5.account.*;
import java.util.ArrayList;
import java.util.Set;

public class HotelSystemAdmin extends HotelSystem {
	/**
	 * Constructor to populate the system object
	 * @param account The account to create the system with
	 * @param bookings The bookings which the system contains
	 */
	public HotelSystemAdmin(Account account, List<Booking> bookings) {
		super(account, bookings);
	}

	@Override
	public Booking makeBooking(Date begin, Date end, List<Room> rooms, Account user) {
		Booking b = super.makeBooking(begin, end, rooms, user, this.account);
		return b;
	}

	/**
	 * Returns the first user which username matches usernameToFind
	 * @param usernameToFind The username to match the account to
	 * @return Returns the first match of users which username matches usernameToFind.
	 */
	public Account getUserAccountByUsername(String usernameToFind) {
		List<Account> foundUser = HotelBookingData.getAccountByUser(usernameToFind);
		if (!foundUser.isEmpty()) return foundUser.get(0);
		else return null;
	}

	/**
	 * Prints the list of users in the system
	 */
	public void printUserList() {
		List<String> users = getUserList();
		for (String user : users) System.out.println(user);
	}

	/**
	 * Gets the list of usernames of each user in the system
	 * @return Returns the list of usernames of each user in the system
	 */
	private List<String> getUserList() {
		Set<AccountCredentials> credentialsList = FileIO.loadCredentialsJson();
		List<String> users = new ArrayList<>();
		for (AccountCredentials c : credentialsList) users.add(c.getUsername());
		return users;
	}

	/**
	 * Prints all the bookings in the system
	 */
	public void printAllBookings() {
		List<Booking> bookings = getAllBookings();
                if (bookings.size() == 0) {
                    System.out.println("No bookings to view.");
                    return;
                }
		for (Booking b : bookings) System.out.println(b.toString() + "\n");
	}

	/**
	 * Deletes a booking from the system where the specified ID matches bookingId
	 * @param bookingId The ID of the booking to delete
	 */
	public void deleteBookingByID(String bookingId) {
		Booking deleted = super.deleteBooking(bookingId);
		System.out.println("Deleted:");
		System.out.println(deleted.toString());
	}
}
