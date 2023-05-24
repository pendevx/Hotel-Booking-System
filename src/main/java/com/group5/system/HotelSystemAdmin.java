package com.group5.system;

import com.group5.hotel.Account;
import com.group5.hotel.Room;
import com.group5.hotel.Booking;
import java.util.Date;
import java.util.List;

import java.util.Set;

public class HotelSystemAdmin extends HotelSystem {
	/**
	 * Constructor to populate the system object
	 * @param account The account to create the system with
	 * @param bookings The bookings which the system contains
	 */
	HotelSystemAdmin(Account account, List<Booking> bookings) {
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
	public Account getAccountByUsername(String usernameToFind) {
		List<Account> foundUser = HotelBookingData.getAccountsByUsername(usernameToFind);
		if (!foundUser.isEmpty()) return foundUser.get(0);
		else return null;
	}

	/**
	 * Gets the list of usernames of each user in the system
	 * @return Returns the list of usernames of each user in the system
	 */
	public Set<String> getUserList() {
		return FileIO.loadUsernameJson();
	}

	/**
	 * Deletes a booking from the system where the specified ID matches bookingId
	 * @param bookingId The ID of the booking to delete
	 */
	public Booking deleteBookingByID(String bookingId) {
		Booking deleted = super.deleteBooking(bookingId);
		return deleted;
	}
}
