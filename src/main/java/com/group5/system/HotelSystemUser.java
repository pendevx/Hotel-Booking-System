package com.group5.system;

import com.group5.hotel.Room;
import com.group5.hotel.Booking;
import java.util.Date;
import java.util.List;

import com.group5.account.*;

public class HotelSystemUser extends HotelSystem {
	// renamed from UserSystem

	public HotelSystemUser(Account account, List<Booking> bookings) {
		super(account, bookings);
	}

	/**
	 *
	 * @param begin
	 * @param end
	 * @param rooms
	 * @param account account must equal to the account stored inside the HotelSystemUser's account
	 * @return
	 */
	public Booking makeBooking(Date begin, Date end, List<Room> rooms, Account account) {
		if (!account.equals(this.account)) {
			return null;
		}
		Booking booking = super.makeBooking(begin, end, rooms, account, this.account);
		bookings.add(booking);
		return booking;
	}

	public void printUserBookings() {
		if(!bookings.isEmpty()) {
			for(Booking b : bookings) {
				System.out.println(b.toString());
			}
		}
		else System.out.println("No bookings to view.");
	}
}
