package com.group5.system;

import com.group5.hotel.Room;
import com.group5.hotel.Booking;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.group5.account.*;
import com.group5.hotel.Hotel;

public abstract class HotelSystem {
	// renamed from UserSystem

	protected Hotel hotel;
	protected Account account;
	protected List<Booking> bookings;

	HotelSystem(Account account, List<Booking> bookings) {
		this.hotel = FileIO.loadHotelJson().get(0);
		this.account = account;
		this.bookings = bookings;
	}

	public abstract Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account);

	protected Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account, Account manager) {
		Booking booking = new Booking(String.valueOf(System.currentTimeMillis()), begin, end, rooms, account, manager);
		HotelBookingData.book(booking);
		bookings.add(booking);
		return booking;
	}

	protected List<Booking> getBookingsWhere(Predicate<Booking> action) {
		return bookings.stream().filter(action).collect(Collectors.toList());
	}

	public Booking deleteBooking(String bookingId) {
		try {
			Booking booking = getBookingsWhere(x -> x.bookingID.equals(bookingId)).get(0);
			bookings.remove(booking);
			FileIO.saveBookings(bookings);
			return booking;
		}
		catch (IndexOutOfBoundsException e) { throw new RuntimeException(e); }
	}

	public boolean roomIsAvailable(Room room, Date beginDate) {
		// need an all bookings list to compare against
		// when the user logins in it on ly loads there bookings,
		// so only compares these to their own bookings
		for (Booking b : bookings) {
			if (!(b.beginDate().before(beginDate) || b.endDate().after(beginDate))) continue;

			for (Room r : b.getRooms()) {
				if (r.equals(room)) return false;
			}
		}
		return true;
	}

	public List<Booking> getAllBookings() { return bookings; }
	public Account getAccount() { return account; }
	public String getUserDetails() { return account.toString() + "\n" + account.getAccountDetails() + "\n"; }
	public String getConfirmLogin() { return "\nLogged in as" + " " + account.toString() + "\n"; }
	public String getHotelDetails() { return this.hotel.toString(); }

}
