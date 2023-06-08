package com.group5.database;

import com.group5.hotel.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class BookingRooms {
	static HashMap<String, List<Room>> rooms;
	static {
		rooms = new HashMap<>();
	}
	static void addRoom(String bookingID, Room room) {
		if (rooms.containsKey(bookingID)) {
			rooms.get(bookingID).add(room);
		} else {
			List<Room> roomsList = new ArrayList<Room>();
			roomsList.add(room);
			rooms.put(bookingID, roomsList);
		}
	}
}
