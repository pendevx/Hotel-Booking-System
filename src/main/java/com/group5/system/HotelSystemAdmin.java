package com.group5.system;

import com.group5.hotel.Room;
import com.group5.hotel.Booking;
import java.util.Date;
import java.util.List;

import com.group5.account.*;
import java.util.ArrayList;

public class HotelSystemAdmin extends HotelSystem {
	// renamed from AdminSystem

	public HotelSystemAdmin(Account account, List<Booking> bookings) {
		super(account, bookings);
	}

	public void printUserList() {
		List<String> users = getUserList();
		for (String user : users) System.out.println(user);
	}

	private List<String> getUserList() {
		List<AccountCredentials> credentialsList = DataHandlerRead.loadCredentialsJson();
		List<String> users = new ArrayList<>();
		for (AccountCredentials c : credentialsList) users.add(c.getUsername());
		return users;
	}

	public void printAllBookings() {
		List<Booking> bookings = getAllBookings();
		for (Booking b : bookings) System.out.println(b.toString());
	}

	@Override
	public Booking makeBooking(Date begin, Date end, List<Room> rooms, Account user) {
		Booking b = super.makeBooking(begin, end, rooms, user, account);
		return b;
	}
}
