package com.group5.view;

import com.group5.system.HotelSystemAdmin;
import com.group5.hotel.Booking;
import com.group5.util.ParseInput;

import java.util.List;
import java.util.Scanner;

public class ViewAdmin extends View {

	public ViewAdmin(HotelSystemAdmin hotelSystem) {
		super(hotelSystem);
	}

	@Override
	public void menuMain(Scanner scan) {
		do {
			System.out.println("------------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("1. View hotel details");
			System.out.println("2. View admin details");
			System.out.println("3. View all bookings.");
			System.out.println("4. View all users.");
			System.out.println("0. to QUIT");
			System.out.println("------------------------------------");

			int input = ParseInput.integer(0, 5, scan);
			if(input == 1) printHotelDetails();
			else if(input == 2) printUserDetails();
			else if (input == 3) getAllBookings();
			else if (input == 4) viewAllUsers();
			else if (input == 0) System.exit(0);
		}
		while(true);
	}

	private void getAllBookings() {
		List<Booking> bookings = hotelSystem.getAllBookings();
		for (Booking b : bookings) {
			System.out.println(b.toString());
		}
	}

	private void viewAllUsers() {
		((HotelSystemAdmin)hotelSystem).getUserList();
	}
}
