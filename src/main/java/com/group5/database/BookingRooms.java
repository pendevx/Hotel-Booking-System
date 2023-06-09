package com.group5.database;

import com.group5.hotel.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Represents the Bookings - Rooms tables relationship in the database
class BookingRooms {
    /**
     * The rooms table in the database
     * Maps a booking ID to a list of rooms
     */
	static HashMap<String, List<Room>> rooms;
    
    /**
     * Static initializer for the rooms
     */
	static {
		rooms = new HashMap<>();
	}
    
    /**
     * Adds a room and the booking ID it belongs to to the rooms
     * @param bookingID the booking ID which contains the room
     * @param room the room to add
     */
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
