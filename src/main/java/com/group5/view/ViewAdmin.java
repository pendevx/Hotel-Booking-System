package com.group5.view;

import com.group5.system.HotelSystemAdmin;
import com.group5.util.ParseInput;
import java.util.Scanner;

public class ViewAdmin extends View {

    /***
     * Constructor that extends from View. Used to instantiate a
     * HoteSystemAdmin system, which has admin permission
     * 
     * @param hotelSystem - type of system to use
     */
	public ViewAdmin(HotelSystemAdmin hotelSystem) {
		super(hotelSystem);
	}

    /***
     * Main menu for admin view, showing what actions admin can perform
     */
	@Override
	public void menuMain(Scanner scan) {
		do {
			System.out.println("------------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("1. View hotel details");
			System.out.println("2. View admin details");
			System.out.println("3. View all users.");
			System.out.println("4. View all bookings.");
			System.out.println("5. Delete booking.");
			System.out.println("0. to QUIT");
			System.out.println("------------------------------------");

			int input = ParseInput.integer(0, 5, scan); // parse valid, between 0 - 5
			if(input == 1) printHotelDetails();
			else if(input == 2) printAccountDetails();
			else if (input == 3) viewAllUsers();
			else if (input == 4) viewAllBookings();
			else if (input == 5) deleteBooking(scan);
			else if (input == 0) System.exit(0);
		}
		while(true);
	}

    /***
     * Print list of all registered users
     */
	private void viewAllUsers() {
		((HotelSystemAdmin) hotelSystem).printUserList();
	}


    /***
     * Print list of all bookings made by all users
     */
	private void viewAllBookings() {
		((HotelSystemAdmin) hotelSystem).printAllBookings();
	}

    /**
     * Deletes booking based entered bookingID
     * 
     * @param scan - input string of ID of booking to delete
     */
    private void deleteBooking(Scanner scan) {
		if (!hotelSystem.bookingIsEmpty()) {
			viewAllBookings(); // list of all bookings
			System.out.println("Enter id of booking to delete, x to cancel:");
			String bookingId = ParseInput.string(scan); // parses string input, trim()
			if (bookingId.equals("x")) return;

			try { ((HotelSystemAdmin) hotelSystem).deleteBookingByID(bookingId); } // try to delete
			catch (RuntimeException e) { System.out.println("Invalid booking ID"); } // catches exception if id doesn't match
		}
		else System.out.println("No bookings to delete.");
	}
}
