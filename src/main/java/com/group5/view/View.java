package com.group5.view;

import com.group5.hotel.Room;
import com.group5.system.HotelSystem;
import com.group5.util.ParseInput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public abstract class View {
	public HotelSystem hotelSystem;

    /***
     * Abstract constructor for View that instantiates a HotelSystem,
     * HotelSystem is to handle backend task, while views
     * acts as the frontend.
     * 
     * @param hotelSystem - will be either for user or admin
     */
	public View(HotelSystem hotelSystem) {
		this.hotelSystem = hotelSystem;
	}
	
    /***
     * Abstract method for a menuMain for admin and user Views
     * @param scan
     */
	public abstract void menuMain(Scanner scan);

    /***
     * Prints the logged in accounts details
     */
	public void printAccountDetails() { System.out.println(hotelSystem.getUserDetails()); }

    /***
     * Prints formatted cofirmation of login, showing userType and username
     */
	public void confirmLogin() { System.out.println(hotelSystem.getConfirmLogin()); }

    /***
     * Prints the formatted string of hotel details
     */
	public void printHotelDetails() { System.out.println(hotelSystem.getHotelDetails()); }

    /***
     * Gets the start date of the booking that meet
 requirements.
     * 
     * @param scan - input of date, which is parsed
     * @return valid date
     */
	public Date getStartDate(Scanner scan) {
		Date start = null;
		while(true) {
			System.out.println("Date to start your booking? (dd/mm/yyyy), x to cancel");
			start = ParseInput.date(scan); // parses in a valid date, based on predefined format
			if (start == null) break;
			else if (start.compareTo(new Date()) < 0) { // compares date to current
				System.out.println("Sorry, you cannot begin before today's date!");
				continue; // breaks one iteration of loop, continues next iteration
			}
			break;
		}
		return start;
	}

    /***
     * Gets the end date of the booking that meet
     * requirements.
     * 
     * @param scan - input of date, which is parsed
     * @return valid date
     */
	public Date getEndDate(Scanner scan, Date start) {
		Date end = null;
		while(true) {
			System.out.println("Date to end your booking? (dd/mm/yyyy), x to cancel");
			end = ParseInput.date(scan);
			if (end == null) break;
			else if (end.compareTo(start) < 0) { // checks that end is not before start date
				System.out.println("Sorry, you cannot end before your booking's start date!");
				continue; // breaks one iteration of loop, continues next iteration
			}
			break;
		}
		return end;
	}

    /***
     * Prints the selected booking period
     */
	public void printBookingPeriod(Date start, Date end) {
		System.out.println("Period: " + start + " -> " + end + "\n");
	}

    /***
     * Gets the user to select the rooms that would like
     * to add to their booking. Rooms can only be chosen if
     * it is available between the begin and end dates.
     * 
     * @param scan - input of room, as prompted
     * @param start - the selected start date
     * @param start - the selected end date
     * @return list of chosen rooms to book
     */
	public List<Room> getBookingRooms(Scanner scan, Date start, Date end) {
		System.out.println("Please choose your rooms for booking, e.g. 3F.");
		System.out.println("Room numbers range from (1-10) and (A-J).");
		System.out.println("Finish adding rooms by entering an empty  line:");

		List<Room> rooms = new ArrayList<>();

		while (true) {
			String room = scan.nextLine().trim().toUpperCase();

			if (room.equals("")) break; // breaks if empty is entered

			if (room.length() < 2 || room.length() > 3) { // length is valid for format. e.g. 3F, 10F
				System.out.println("Invalid room, please enter again: ");
				continue;
			}

			int floorNum = -1;
			try { floorNum = Integer.parseInt(room.substring(0, room.length() - 1)); } // parses int from room string
			catch (Exception e) { System.out.println("Invalid floor number, please enter again: "); }

			char roomNum = room.charAt(room.length() - 1); // gets char from room string

			if (floorNum < 1 || floorNum > 10 || roomNum < 'A' || roomNum > 'J') {
				System.out.println("Invalid room, please enter again: "); // checks if room is valid
				continue;
			}

			Room toBook = new Room(floorNum, roomNum);

			if (!hotelSystem.roomIsAvailable(toBook, start)) { // checks if room is availble between start and end dates
				System.out.println("This room is unavailable, please try again.");
				continue;
			}

			if (rooms.contains(toBook)) { // checks to see if room has already been booked in current booking session
				System.out.println("This room has already been booked by you; please try again.");
				continue;
			}
			rooms.add(toBook); // add the room to booking
		}
		return rooms;
	}
}