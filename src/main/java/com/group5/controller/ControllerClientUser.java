package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardBookingManageUser;
import com.group5.hotel.Room;
import com.group5.view.ViewClientUser;
import com.group5.view.ViewGUI;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControllerClientUser extends ControllerClient {

	public ControllerClientUser(ViewGUI view, AppSession model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		super.clientView = new ViewClientUser(this, super.getAccountInfo(), super.getHotelInfo());
		super.updateDisplay(super.clientView.getBasePanel());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardAccount()!= null) {
			if (e.getSource() == getCardAccount().editAccountButton) editHandler();
			else if (e.getSource() == getCardAccount().logoutButton) logoutHandler();
			else if (e.getSource() == getBookingUser().cancelButton) cancelHandler();
			else if (e.getSource() == getBookingUser().bookButton) bookHandler();
		}
	}

	private void editHandler() {
		if (getModel() != null) new ControllerAccountEdit(getView(), getModel());
	}

	private void cancelHandler() {
		getBookingUser().rooms.setText("");
		getBookingUser().startDate.clearDate();
		getBookingUser().endDate.clearDate();
	}

	private void bookHandler() {
		Date startDate = getBookingUser().startDate.getDate();
		Date endDate = getBookingUser().endDate.getDate();
		String rooms = getBookingUser().rooms.getText();

		String start = (startDate != null) ? startDate.toString() : "";
		String end = (endDate != null) ? endDate.toString() : "";

		if (!hasEmptyField(getBookingUser(), start, end, rooms)) {
			if (startBeforeToday(startDate)) {
				getCardBookingManage().showWarningPopup("End date must be after start date...");
				return;
			}
			if (endDate.before(startDate)) {
				getCardBookingManage().showWarningPopup("End date must be after start date...");
				return;
			}

			String[] parsed = parseRoom(rooms);
			Set<Room> selectedRooms = checkValidInput(parsed);
			if (selectedRooms != null && checkRoomAvailable(selectedRooms, startDate)) {
				List<Room> roomsToBook = new ArrayList<>(selectedRooms);
				createBooking(startDate, endDate, roomsToBook);
				getCardBookingManage().showWarningPopup(getBookingConfirm(startDate, endDate, roomsToBook.size()));
			}
		}
	}

	private String getBookingConfirm(Date startDate, Date endDate, int qty) {
		return "Booking created.\n" + getBookingPeriod(endDate, endDate) + "\nFor: " + qty + " rooms.";

	}

	private String getBookingPeriod(Date begin, Date end) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return "Date: " + formatter.format(begin) + " to " + formatter.format(end);
	}

	private void createBooking(Date startDate, Date endDate, List<Room> roomsToBook) {
		if (!roomsToBook.isEmpty()) { 
			getModel().hotelSystem.makeBooking(startDate, endDate, roomsToBook, getAccount());
		}
		else getCardBookingManage().showWarningPopup("Error booking. Try again.");
	}

	private boolean checkRoomAvailable(Set<Room> selectRooms, Date startDate) {
		for (Room r : selectRooms) {
			if (!getModel().hotelSystem.roomIsAvailable(r, startDate)) {
				getCardBookingManage().showWarningPopup("Room: " + r.getRoomNumber() + " is not available.");
				return false;
			}
		}
		return true;
	}

	private Set<Room> checkValidInput(String[] rooms) {
		Set<Room> selectedRooms = new HashSet<>();

		for (String r : rooms) {
			if (r.length() < 2 || r.length() > 3) { // length is valid for format. e.g. 3F, 10F
				getCardBookingManage().showWarningPopup("Room: " + r + " is not valid input.");
				return null;
			}

			int floorNum = -1;
			try { floorNum = Integer.parseInt(r.substring(0, r.length() - 1)); }
			catch (Exception e) { System.out.println("Invalid floor number, please enter again: "); }

			char roomNum = r.charAt(r.length() - 1); // gets char from room string

			if (floorNum < 1 || floorNum > 10 || roomNum < 'A' || roomNum > 'J') {
				getCardBookingManage().showWarningPopup("Room: " + r + " is invalid.");
				return null;
			}

			Room toBook = new Room(floorNum, roomNum);
			selectedRooms.add(toBook);
		}
		return selectedRooms;
	}

	private String[] parseRoom(String selectedRooms) {
		String[] parsedRoom = selectedRooms.split(",");
		for (int i = 0; i < parsedRoom.length; i++) parsedRoom[i] = parsedRoom[i].trim().toUpperCase();
		return parsedRoom;
	}

	private boolean startBeforeToday(Date date) {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		if (date.before(today.getTime())) return true;
		else return false;
	}

	private CardBookingManageUser getBookingUser() {
		if (clientView == null) return null;
		else return (CardBookingManageUser) clientView.getCardBookingManage();
	}
}
