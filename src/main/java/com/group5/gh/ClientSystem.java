package com.group5.gh;

import java.util.Date;
import java.util.List;

import com.group5.account.*;

public class ClientSystem extends UserSystem {
	public ClientSystem(Account acc, List<Booking> bookings) {
		super(acc, bookings);
	}

	/**
	 *
	 * @param begin
	 * @param end
	 * @param rooms
	 * @param account account must equal to the account stored inside the ClientSystem's account
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
}
