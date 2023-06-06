package com.group5.view;

import com.group5.hotel.Booking;
import com.group5.system.HotelSystemAdmin;
import com.group5.util.ParseInput;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
		Set<String> users = ((HotelSystemAdmin) hotelSystem).getUserList();
		for (String user : users) System.out.println(user);
	}


    /***
     * Print list of all bookings made by all users
     */
	private void viewAllBookings() {
		List<Booking> bookings = hotelSystem.getAllBookings();

		if (bookings.size() == 0) {
			System.out.println("No bookings to view.");
			return;
		}

		for (Booking b : bookings)
			System.out.println(b.toString() + "\n");
	}

    /**
     * Deletes booking based entered bookingID
     * 
     * @param scan - scanner to receive user input
     */
    private void deleteBooking(Scanner scan) {
		if (hotelSystem.bookingIsEmpty()) {
			System.out.println("No bookings to delete.");
			return;
		}

		viewAllBookings(); // list of all bookings
		System.out.println("Enter id of booking to delete, x to cancel:");
		String bookingId = ParseInput.string(scan); // parses string input, trim()
		if (bookingId.equals("x")) return;

		try {
			// try to delete
			Booking deleted = ((HotelSystemAdmin) hotelSystem).deleteBookingByID(bookingId);
			System.out.println("Deleted:");
			System.out.println(deleted.toString());
		} catch (RuntimeException e) {
			// catches exception if id doesn't match
			System.out.println("Invalid booking ID");
		}

	}
}
