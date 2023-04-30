package com.group5.system;

import com.group5.hotel.Booking;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.group5.account.*;
import com.group5.dataHandler.DataHandlerRead;
import com.group5.dataHandler.DataHandlerWrite;

public class BookingData {
	static List<Booking> bookings;
	static List<Account> accounts;

	static void init() {
		bookings = DataHandlerRead.loadBookingJson();
		accounts = DataHandlerRead.loadAccountsJson();
	}

	static void book(Booking booking) {
		bookings.add(booking);
		DataHandlerWrite.saveBookings(bookings);
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
