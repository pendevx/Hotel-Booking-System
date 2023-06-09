package com.group5.view;

import com.group5.hotel.Room;
import com.group5.system.HotelSystemUser;
import com.group5.util.ParseInput;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OLDViewUser extends OLDView {

    /***
     * Constructor that extends from View. Used to instantiate a
     * HoteSystemUser system, which has user permission
     * 
     * @param hotelSystem - type of system to use
     */
	public OLDViewUser(HotelSystemUser hotelSystem) {
		super(hotelSystem);
	}

	@Override
	public void menuMain(Scanner scan) {
		do {
			System.out.println("3. View my bookings.");
			System.out.println("4. Create new booking.");
		}
		while(true);
	}

	/***
	 * Prints the booking associated with the users account
	 */
	private void viewBookings() {
		((HotelSystemUser) hotelSystem).printUserBookings();
	}

	private void createBooking(Scanner scan) {
		Date start = super.getStartDate(scan); 
		if (start == null) {
			printBookingProcessCanclled();
			return;
		}

		Date end = super.getEndDate(scan, start); 
		if (end == null) {
			printBookingProcessCanclled();
			return;
		}
		super.printBookingPeriod(start, end);
		List<Room> rooms = getBookingRooms(scan, start, end);

		if (!rooms.isEmpty()) { // creates booking if list of rooms not empty
			hotelSystem.makeBooking(start, end, rooms, hotelSystem.getAccount());
		}
		else System.out.println("No rooms valid rooms selected, no bookings made.");
	}

	private void printBookingProcessCanclled() {
		System.out.println("Booking process cancelled");
	}
}