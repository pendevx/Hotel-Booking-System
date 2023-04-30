package com.group5.system;

import com.group5.hotel.Room;
import com.group5.hotel.Booking;
import java.util.Date;
import java.util.List;

import com.group5.account.*;
import com.group5.dataHandler.DataHandlerRead;

public class HotelSystemAdmin extends HotelSystem {

	public HotelSystemAdmin(Account account, List<Booking> bookings) {
		super(account, bookings);
	}

	public void getUserList() {
		List<String> users = DataHandlerRead.getUserList();
		for (String user : users) System.out.println(user);
	}

//	@Override
//	public Booking makeBooking(Date begin, Date end, List<Room> rooms, Account user) {
//		Booking b = super.makeBooking(begin, end, rooms, user, account);
//		return b;
//	}

}
