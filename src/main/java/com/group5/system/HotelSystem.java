package com.group5.system;

import com.group5.hotel.Room;
import com.group5.hotel.Booking;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.group5.account.*;
import com.group5.hotel.Hotel;

public abstract class HotelSystem {
	protected Hotel hotel;
	protected Account account;
	protected List<Booking> bookings;

	/**
	 * Constructor to populate the system object
	 * @param account The account to create the system with
	 * @param bookings The bookings which the system contains
	 */
	HotelSystem(Account account, List<Booking> bookings) {
		this.hotel = FileIO.loadHotelJson().get(0);
		this.account = account;
		this.bookings = bookings;
	}

	/**
	 * Makes a booking.
	 * @param begin The beginning date for the booking
	 * @param end The end date for the booking
	 * @param rooms The rooms to book
	 * @param account The account that wants to book
	 * @return Returns a booking object which represents the booking
	 */
	public abstract Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account);

	/**
	 * Makes a booking in the system. This is the method called by any system which would make bookings inside the system
	 * @param begin The beginning date for the booking
	 * @param end The end date for the booking
	 * @param rooms The rooms to book
	 * @param account The account that wants to book
	 * @param manager The account which manages the booking
	 * @return Returns a booking object which represents the booking
	 */
	protected Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account, Account manager) {
		Booking booking = new Booking(String.valueOf(System.currentTimeMillis()), begin, end, rooms, account, manager);
		HotelBookingData.book(booking);
		bookings.add(booking);
		return booking;
	}

	/**
	 * Gets a list of bookings which match a predicate
	 * @param predicate The predicate to match the bookings to
	 * @return Returns a list of bookings which match the predicate.
	 */
	protected List<Booking> getBookingsWhere(Predicate<Booking> predicate) {
		return bookings.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Deletes a booking from the system
	 * @param bookingId The ID of the booking to be deleted
	 * @return Returns the booking which was deleted from the system
	 */
	public Booking deleteBooking(String bookingId) {
		try {
			Booking booking = getBookingsWhere(x -> x.bookingID.equals(bookingId)).get(0);
			bookings.remove(booking);
			FileIO.saveBookings(bookings);
			return booking;
		}
		catch (IndexOutOfBoundsException e) { throw new RuntimeException(e); }
	}

	/**
	 * Checks whether a room is available on a specific date.
	 * @param room The room to check availability for
	 * @param date The date to check availability on
	 * @return Returns true if the room is available. Otherwise false will be returned.
	 */
	public boolean roomIsAvailable(Room room, Date date) {
		// need an all bookings list to compare against
		// when the user logins in it on ly loads there bookings,
		// so only compares these to their own bookings
		for (Booking b : HotelBookingData.getBookings()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(b.beginDate());
			cal.add(Calendar.DATE, -1);
 			// if the date is on or after the begin date of this room, AND before (end-1) date of this room, then return true
			if (date.after(cal.getTime()) && date.before(b.endDate()))
				for (Room r : b.getRooms())
					if (r.equals(room)) return false;
		}
		return true;
	}

	// Getter methods for bookings, user details, login confirmation, hotel details, and account.
	public List<Booking> getAllBookings() { return bookings; }
	public String getUserDetails() { return account.toString() + "\n" + account.getAccountDetails() + "\n"; }
	public String getConfirmLogin() { return "\nLogged in as" + " " + account.toString() + "\n"; }
	public String getHotelDetails() { return this.hotel.toString(); }
	public Account getAccount() { return account; }
	public boolean bookingIsEmpty() { return bookings.isEmpty(); }
}
