package com.group5.views;

import gh.SystemClient;
import com.group5.account.Account;
import com.group5.util.ParseInput;

import java.util.Scanner;

public class ViewUser extends View {
	public ViewUser(Account userAccount, SystemClient system) {
		super(userAccount, system);
	}

	@Override
	public void menuMain(Scanner scan) {
		do {
			System.out.println("------------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("1. View hotel details");
			System.out.println("2. View user details");

			System.out.println("3. View my bookings.");
			// does not have access to all?, kind of does though
			// will parse the large file
			// shows list of bookings (date - date price), then options to go into
			// only need track allbookings and that they are less than available rooms
			// counter for bookings.
			// case hotel full
			System.out.println("4. Create new booking.");
			System.out.println("5. Cancel booking.");
			// return list of avalable rooms to choose
			// between dates

			System.out.println("0. to QUIT");
			System.out.println("------------------------------------");

			int input = ParseInput.integer(0, 5, scan);
			if(input == 1) printHotelDetails();
			else if(input == 2) printUserDetails();

			else if (input == 0) System.exit(0);
		}
		while(true);
	}


	// get from booking system
	public void createBooking() {
		// only one room type for now
		// enter dd/mm/yy
	}
	private void printAvailableRooms() {
		// inside create booking
		// user selects month and return list with how manuy room
		// available on that month
	}

	// get from booking system
	public void printBookingList() {
		// prints list of booking to choose from;
	}
	private void printBookingDetail() {
		// print details of a booking
	}
}
