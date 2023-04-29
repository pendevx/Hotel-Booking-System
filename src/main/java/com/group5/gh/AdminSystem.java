package com.group5.gh;

import java.util.Date;
import java.util.List;

import com.group5.account.*;

public class AdminSystem extends UserSystem {
	public AdminSystem(Account account, List<Booking> bookings) {
		super(account, bookings);
	}

	@Override
	public Booking makeBooking(Date begin, Date end, List<Room> rooms, Account user) {
		Booking b = super.makeBooking(begin, end, rooms, user, account);
		return b;
	}
}
