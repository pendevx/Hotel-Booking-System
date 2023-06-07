package com.group5.system;

import com.group5.database.HotelDatabase;
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
		// later need to load credentials on each register instance
		// close session after logout.
		// open new session after login.
		// open a new session after register, make login after, or auto login
		credentials = HotelDatabase.loadCredentials();
		accounts = HotelDatabase.loadAccounts();
		
		// TODO: bookings
		bookings = FileIO.loadBookingJson();
	}

	/**
	 * Books a file in the system
	 * @param booking The booking to add
	 */
	static void book(Booking booking) {
		bookings.add(booking);
		FileIO.saveBookings(bookings);

		HotelDatabase.insertBookingTable(booking);
	}

	/**
	 * @return list of all registered usernames
	 */
	static Set<String> getUsernameList() {
		return getCredentials().stream().map(x -> x.getUsername()).collect(Collectors.toSet());
	}

	/**
	 * Gets a list of accounts which match a predicate
	 * @param predicate The predicate to match the accounts to
	 * @return Returns a list of accounts which match the predicate
	 */
	static List<Account> getAccountsWhere(Predicate<Account> predicate) {
		return accounts.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Gets a list of accounts which match a username
	 * @param username The username to match the accounts to
	 * @return Returns a list of accounts which username matches the parameter 'username'. If the size of the list is greater than 1 then there is an error in the data.
	 */
	static List<Account> getAccountsByUsername(String username) {
		return getAccountsWhere(x -> x.getUsername().equals(username));
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

	/**
	 * Registers an account into the system
	 * @param credentials The credentials of the account
	 * @param account The account object containing user information
	 */
	public static void register(Credential credentials, Account account) {
		if (!credentials.getUsername().equals(account.getUsername()))
			throw new RuntimeException("Credentials username and account username doesn't match!");

		// maybe remove this so reads database each time, but then
		// auto login won't work
		HotelBookingData.credentials.add(credentials);
		accounts.add(account);

		HotelDatabase.insertAccountTable(account);
		HotelDatabase.insertCredentialTable(credentials);
	}

	// Getter methods
	static List<Booking> getBookings() { return Collections.unmodifiableList(bookings); }
	static List<Account> getAccounts() { return Collections.unmodifiableList(accounts); }
	static Set<Credential> getCredentials() { return Collections.unmodifiableSet(credentials); }
}
