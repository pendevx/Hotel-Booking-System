package com.group5.views;

import gh.SystemAdmin;
import com.group5.hotel.Booking;
import com.group5.account.Account;
import com.group5.util.ParseInput;

import java.util.List;
import java.util.Scanner;

public class ViewAdmin extends View {

	public ViewAdmin(Account userAccount, SystemAdmin system) {
		super(userAccount, system);
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
			System.out.println("5. Delete booking."); // maybe from user, by ID?

//			System.out.println("5. View all booking for user"); // part 2
//			System.out.println("6. Create booking for user."); // try maybe only part 2.

			System.out.println("0. to QUIT");
			System.out.println("------------------------------------");

			int input = ParseInput.integer(0, 5, scan);
			if(input == 1) printHotelDetails();
			else if(input == 2) printUserDetails();
			else if (input == 3) {
				List<Booking> bookings = system.getAllBookings();
				for (Booking b : bookings) {
					System.out.println(b.toString());
				}
			}
			else if (input == 0) System.exit(0);
		}
		while(true);
	}
}
