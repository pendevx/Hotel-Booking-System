package com.group5.system;

import com.group5.hotel.Account;
import com.group5.hotel.Booking;
import com.group5.hotel.Credential;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class HotelBookingData {
	private static final List<Booking> bookings;
	private static final List<Account> accounts;
	private static final Set<Credential> credentials;

	/**
	 * Static initializer to populate bookings and accounts Lists
	 */
	static {
		bookings = FileIO.loadBookingJson();
		accounts = FileIO.loadAccountsJson();
		credentials = FileIO.loadCredentialsJson();
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

	public static void register(Credential credentials, Account account) {
		if (!credentials.getUsername().equals(account.getUsername()))
			throw new RuntimeException("Credentials username and account username doesn't match!");

		HotelBookingData.credentials.add(credentials);
		accounts.add(account);
	}

	// Getter methods

	static List<Booking> getBookings() { return Collections.unmodifiableList(bookings); }
	static List<Account> getAccounts() { return Collections.unmodifiableList(accounts); }
	static Set<Credential> getCredentials() { return Collections.unmodifiableSet(credentials); }
}
