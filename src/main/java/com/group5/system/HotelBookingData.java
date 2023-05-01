package com.group5.system;

import com.group5.hotel.Booking;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.group5.account.*;

public class HotelBookingData {
	static List<Booking> bookings;
	static List<Account> accounts;

	static {
		bookings = FileIO.loadBookingJson();
		accounts = FileIO.loadAccountsJson();
	}

	static void book(Booking booking) {
		bookings.add(booking);
		FileIO.saveBookings(bookings);
	}

	static List<Account> getAccountWhere(Predicate<Account> action) {
		return accounts.stream().filter(action).collect(Collectors.toList());
	}

	static List<Account> getAccountByUser(String username) {
		return getAccountWhere(x -> x.getUsername().equals(username));
	}

	static List<Booking> getBookingsWhere(Predicate<Booking> action) {
		return bookings.stream().filter(action).collect(Collectors.toList());
	}

	static List<Booking> getBookingsForAccount(Account account) {
		return getBookingsWhere(x -> x.getAccount().equals(account));
	}

	static List<Account> getAccounts() { return accounts; }
	static List<Booking> getBookings() { return bookings; }
}
