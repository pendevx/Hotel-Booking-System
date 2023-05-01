package com.group5.view;

import com.group5.hotel.Room;
import com.group5.system.HotelSystemUser;
import com.group5.util.ParseInput;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ViewUser extends View {

    /***
     * Constructor that extends from View. Used to instantiate a
     * HoteSystemUser system, which has user permission
     * 
     * @param hotelSystem - type of system to use
     */
	public ViewUser(HotelSystemUser hotelSystem) {
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
			System.out.println("2. View user details");
			System.out.println("3. View my bookings.");
			System.out.println("4. Create new booking.");
			System.out.println("0. to QUIT");
			System.out.println("------------------------------------");

			int input = ParseInput.integer(0, 4, scan); // parse valid, between 0 - 4
			if(input == 1) printHotelDetails();
			else if(input == 2) printAccountDetails();
			else if(input == 3) viewBookings();
			else if(input == 4) createBooking(scan);
			else if (input == 0) System.exit(0);
		}
		while(true);
	}

	/***
	 * Prints the booking associated with the users account
	 */
	private void viewBookings() {
		((HotelSystemUser) hotelSystem).printUserBookings();
	}

	/***
	 * Creates a new booking, taking inputs from prompts.
	 * 
	 * @param scan - user inputs
	 */
	private void createBooking(Scanner scan) {
		Date begin = super.getBeginDate(scan); 
		Date end = super.getEndDate(scan, begin); 
		super.printBookingPeriod(begin, end);
		List<Room> rooms = getBookingRooms(scan, begin, end);

		if (!rooms.isEmpty()) { // creates booking if list of rooms not empty
			((HotelSystemUser)hotelSystem).makeBooking(begin, end, rooms, hotelSystem.getAccount());
		}
		else System.out.println("No rooms valid rooms selected, no bookings made.");
	}
}