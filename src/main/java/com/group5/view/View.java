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

	public View(HotelSystem hotelSystem) {
		this.hotelSystem = hotelSystem;
	}
	
	public abstract void menuMain(Scanner scan);
	public void printUserDetails() { System.out.println(hotelSystem.getUserDetails()); }
	public void confirmLogin() { System.out.println(hotelSystem.getConfirmLogin()); }
	public void printHotelDetails() { System.out.println(hotelSystem.getHotelDetails()); }

	public Date getBeginDate(Scanner scan) {
		Date begin = null;
		while(true) {
			System.out.println("What date would you like to start your booking? (dd/mm/yyyy)");
			begin = ParseInput.date(scan);
			if (begin.compareTo(new Date()) < 0) {
				System.out.println("Sorry, you cannot begin before today's date!");
				continue;
			}
			break;
		}
		return begin;
	}

	public Date getEndDate(Scanner scan, Date begin) {
		Date end = null;
		while(true) {
			System.out.println("What date would you like to end your booking? (dd/mm/yyyy)");
			end = ParseInput.date(scan);
			if (end.compareTo(begin) < 0) {
				System.out.println("Sorry, you cannot end before your booking's start date!");
				continue;
			}
			break;
		}
		return end;
	}

	public void printBookingPeriod(Date begin, Date end) {
		System.out.println("Period: " + begin + " -> " + end + "\n");
	}

	public List<Room> getBookingRooms(Scanner scan, Date begin, Date start) {
		System.out.println("Please choose your rooms for booking, e.g. 3F.");
		System.out.println("Room numbers range from (1-10) and (A-J).");
		System.out.println("Finish adding rooms by entering an empty  line:");

		List<Room> rooms = new ArrayList<>();

		while (true) {
			String room = scan.nextLine().trim().toUpperCase();
			if (room.equals("")) break;

			if (room.length() < 2 || room.length() > 3) {
				System.out.println("Invalid room, please enter again: ");
				continue;
			}

			int floorNum = -1;
			try { floorNum = Integer.parseInt(room.substring(0, room.length() - 1)); }
			catch (Exception e) { System.out.println("Invalid floor number, please enter again: "); }

			char roomNum = room.charAt(room.length() - 1);

			if (floorNum < 1 || floorNum > 10 || roomNum < 'A' || roomNum > 'J') {
				System.out.println("Invalid room, please enter again: ");
				continue;
			}

			Room toBook = new Room(floorNum, roomNum);

			if (!hotelSystem.roomIsAvailable(toBook, begin)) {
				System.out.println("This room is unavailable, please try again.");
				continue;
			}

			if (rooms.contains(toBook)) {
				System.out.println("This room has already been booked by you; please try again.");
				continue;
			}
			rooms.add(toBook);
		}
		return rooms;
	}
}