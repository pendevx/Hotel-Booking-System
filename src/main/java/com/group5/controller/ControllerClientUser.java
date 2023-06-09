package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardBookingManageUser;
import com.group5.hotel.Room;
import com.group5.view.ViewClientUser;
import com.group5.view.ViewGUI;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
				getCardBookingManage().showWarningPopup("Start date is in the past. Cannot book in the past...");
				return;
			}
			if (endDate.before(startDate)) {
				getCardBookingManage().showWarningPopup("End date must be after start date...");
				return;
			}
		}
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

	private Room[] parseRooms() {
		List<Room> rooms = new ArrayList<>();
	}

	private CardBookingManageUser getBookingUser() {
		if (clientView == null) return null;
		else return (CardBookingManageUser) clientView.getCardBookingManage();
	}
}
