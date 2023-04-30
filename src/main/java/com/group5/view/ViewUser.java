package com.group5.view;

import com.group5.system.HotelSystemUser;
import com.group5.util.ParseInput;
import java.util.Scanner;

public class ViewUser extends View {

	public ViewUser(HotelSystemUser hotelSystem) {
		super(hotelSystem);
	}

	@Override
	public void menuMain(Scanner scan) {
		do {
			System.out.println("------------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("1. View hotel details");
			System.out.println("2. View user details");
			System.out.println("3. View my bookings.");
//			System.out.println("4. Create new booking.");
//			System.out.println("5. Cancel booking.");
			System.out.println("0. to QUIT");
			System.out.println("------------------------------------");

			int input = ParseInput.integer(0, 5, scan);
			if(input == 1) viewHotelDetails();
			else if(input == 2) viewUserDetails();
			else if(input == 3) viewBookings();
			else if(input == 4) createBooking();
			else if(input == 5) cancelBooking();
			else if (input == 0) System.exit(0);
		}
		while(true);
	}

	private void viewHotelDetails() { printHotelDetails(); }
	private void viewUserDetails() { printUserDetails(); }
	private void viewBookings() { ((HotelSystemUser) hotelSystem).printUserBookings(); }

	private void createBooking() {
		System.out.println("createBooking");
	}

	private void cancelBooking() {
		System.out.println("cancelBooking");
	}
}