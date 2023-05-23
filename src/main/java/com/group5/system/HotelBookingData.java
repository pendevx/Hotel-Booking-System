package com.group5.system;

import com.group5.hotel.Account;
import com.group5.hotel.Booking;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HotelBookingData {
	static List<Booking> bookings;
	static List<Account> accounts;

	/**
	 * Static initializer to populate bookings and accounts Lists
	 */
	static {
		bookings = FileIO.loadBookingJson();
		accounts = FileIO.loadAccountsJson();
	}

	/**
	 * Books a file in the system
	 * @param booking The booking to add
	 */
	static void book(Booking booking) {
		bookings.add(booking);
		FileIO.saveBookings(bookings);
	}

	/**
	 * Gets a list of accounts which match a predicate
	 * @param predicate The predicate to match the accounts to
	 * @return Returns a list of accounts which match the predicate
	 */
	static List<Account> getAccountWhere(Predicate<Account> predicate) {
		return accounts.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Gets a list of accounts which match a username
	 * @param username The username to match the accounts to
	 * @return Returns a list of accounts which username matches the parameter 'username'. If the size of the list is greater than 1 then there is an error in the data.
	 */
	static List<Account> getAccountByUser(String username) {
		return getAccountWhere(x -> x.getUsername().equals(username));
	}

	/**
	 * Gets a list of bookings which match a predicate
	 * @param predicate The predicate to match the actions to
	 * @return Returns a list of bookings which match the predicate
	 */
	static List<Booking> getBookingsWhere(Predicate<Booking> predicate) {
		return bookings.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Gets a list of bookings for an account
	 * @param account The account to match the bookings to
	 * @return Returns a list of bookings which were booked by the account (not managed!)
	 */
	static List<Booking> getBookingsForAccount(Account account) {
		return getBookingsWhere(x -> x.getAccount().equals(account));
	}

	// Getter for the bookings
	static List<Booking> getBookings() { return bookings; }
}
