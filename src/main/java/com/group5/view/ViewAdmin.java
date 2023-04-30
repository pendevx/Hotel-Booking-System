package com.group5.view;

import com.group5.account.Account;
import com.group5.hotel.Room;
import com.group5.system.HotelSystemAdmin;
import com.group5.util.ParseInput;
import java.util.Date;
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
			System.out.println("3. View all users.");
			System.out.println("4. View all bookings.");
			System.out.println("5. Delete booking.");

			System.out.println("6. Create booking for user.");

			System.out.println("0. to QUIT");
			System.out.println("------------------------------------");

			int input = ParseInput.integer(0, 6, scan);
			if(input == 1) viewHotelDetails();
			else if(input == 2) viewAdminDetails();
			else if (input == 3) viewAllUsers();
			else if (input == 4) viewAllBookings();
			else if (input == 5) deleteBooking(scan);

			else if (input == 6) createBookingForUser(scan);

			else if (input == 0) System.exit(0);
		}
		while(true);
	}

	private void viewHotelDetails() { printHotelDetails(); }
	private void viewAdminDetails() { printUserDetails(); }
	private void viewAllUsers() { ((HotelSystemAdmin)hotelSystem).printUserList(); }
	private void viewAllBookings() { ((HotelSystemAdmin)hotelSystem).printAllBookings(); }

	private void deleteBooking(Scanner scan) {
		viewAllBookings();
		System.out.println("Enter id of booking to delete:");
		String bookingId = ParseInput.string(scan);
		((HotelSystemAdmin) hotelSystem).deleteBookingByID(bookingId);
	}

	private void createBookingForUser(Scanner scan) {
		System.out.println("Please enter username of account to book under: ");
		String username = ParseInput.string(scan);
		Account user = ((HotelSystemAdmin) hotelSystem).getUserAccountByUsername(username);

		if(user != null) {
			Date begin = super.getBeginDate(scan);
			Date end = super.getEndDate(scan, begin);
			super.printBookingPeriod(begin, end);
			List<Room> rooms = getBookingRooms(scan, begin, end);

			if (!rooms.isEmpty()) {
//				hotelSystem.makeBooking(begin, end, rooms, user);
				((HotelSystemAdmin)hotelSystem).makeBooking(begin, end, rooms, user);
			}
			else System.out.println("No rooms valid rooms selected, no bookings made.");
		}
		else System.out.println("User not found");
	}
}
