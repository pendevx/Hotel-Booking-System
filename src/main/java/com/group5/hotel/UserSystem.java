package com.group5.hotel;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class UserSystem {
	protected Account account;
	protected List<Booking> bookings;
	UserSystem(Account account, List<Booking> bookings) {
		this.account = account;
		this.bookings = bookings;
	}

	protected Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account, Account manager) {
		Booking booking = new Booking(String.valueOf(System.currentTimeMillis()), begin, end, rooms, account, manager);
		HotelData.book(booking);
		bookings.add(booking);
		return booking;
	}

	public abstract Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account);

	protected List<Booking> getBookingsWhere(Predicate<Booking> action) {
		return bookings.stream().filter(action).collect(Collectors.toList());
	}


	public Booking deleteBooking(String bookingId) {
		try {
			Booking booking = getBookingsWhere(x -> x.bookingID.equals(bookingId)).get(0);
			bookings.remove(booking);
			return booking;
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean roomIsAvailable(Room room, Date beginDate) {
		for (Booking b : bookings) {
			if (!(b.beginDate().before(beginDate) || b.endDate().after(beginDate))) continue;

			for (Room r : b.getRooms()) {
				if (r.equals(room)) {
					return false;
				}
			}
		}

		return true;
	}

	public List<Booking> getAllBookings() {
		return bookings;
	}

	public Account getAccount() {
		return account;
	}
}
