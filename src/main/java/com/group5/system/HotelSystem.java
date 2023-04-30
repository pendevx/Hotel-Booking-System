package com.group5.system;

import com.group5.hotel.Room;
import com.group5.hotel.Booking;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.group5.account.*;
import com.group5.dataHandler.DataHandlerRead;
import com.group5.hotel.Hotel;

public abstract class HotelSystem {
	// renamed from UserSystem
	// chnag epermisison afteer
	public Account account;
	public Hotel hotel;
	public List<Booking> bookings;

	public HotelSystem(Account account, List<Booking> bookings) {
		this.hotel = DataHandlerRead.loadHotelJson().get(0);
		this.account = account;
		this.bookings = bookings;
	}

//	protected Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account, Account manager) {
//		Booking booking = new Booking(String.valueOf(System.currentTimeMillis()), begin, end, rooms, account, manager);
//		BookingHandler.book(booking);
//		bookings.add(booking);
//		return booking;
//	}
//
//	public abstract Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account);
//
//	protected List<Booking> getBookingsWhere(Predicate<Booking> action) {
//		return bookings.stream().filter(action).collect(Collectors.toList());
//	}
//
//	public Booking deleteBooking(String bookingId) {
//		try {
//			Booking booking = getBookingsWhere(x -> x.bookingID.equals(bookingId)).get(0);
//			bookings.remove(booking);
//			return booking;
//		}
//		catch (IndexOutOfBoundsException e) { throw new RuntimeException(e); }
//	}
//
//	public boolean roomIsAvailable(Room room, Date beginDate) {
//		for (Booking b : bookings) {
//			if (!(b.beginDate().before(beginDate) || b.endDate().after(beginDate))) continue;
//
//			for (Room r : b.getRooms()) {
//				if (r.equals(room)) return false;
//			}
//		}
//		return true;
//	}

	public List<Booking> getAllBookings() { return bookings; } // should be moved to admin

	public Account getAccount() { return account; }
	public String getUserDetails() { return account.toString() + "\n" + account.getAccountDetails() + "\n"; }
	public String getConfirmLogin() { return "\nnLogged in as" + " " + account.toString() + "\n"; }
	public String getHotelDetails() { return this.hotel.toString(); }

}
