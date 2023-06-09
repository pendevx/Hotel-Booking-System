package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardBookingManageAdmin;
import com.group5.hotel.Booking;
import com.group5.view.ViewClientAdmin;
import com.group5.view.ViewGUI;
import java.awt.event.ActionEvent;

public class ControllerClientAdmin extends ControllerClient {

	/**
	 * Admin view of client
	 * @param view
	 * @param model 
	 */
	public ControllerClientAdmin(ViewGUI view, AppSession model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		// sets clientView to be the admin verison
		super.clientView = new ViewClientAdmin(this, super.getAccountInfo(), super.getHotelInfo(), super.getBookings());
		super.updateDisplay(super.clientView.getBasePanel()); // update the ViewGUI to display it
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardAccount()!= null) {
			if (e.getSource() == getCardAccount().editAccountButton) editHandler();
			else if (e.getSource() == getCardAccount().logoutButton) logoutHandler();
			else if (e.getSource() == getBookingAdmin().deleteButton) deleteBooking();
		}
	}

	private CardBookingManageAdmin getBookingAdmin() {
		if (clientView == null) return null;
		else return (CardBookingManageAdmin) clientView.getCardBookingManage();
	}

	private void editHandler() {
		if (getModel() != null) new ControllerAccountEdit(getView(), getModel());
	}

	private void deleteBooking() {
		String bookingID = getBookingAdmin().bookingRef.getText().trim();

		Booking deleted = getModel().hotelSystem.deleteBooking(bookingID);
		if (deleted == null) {
			getCardBookingManage().showWarningPopup("Booking not found.");
			return;
		}
		else {
			getCardBookingManage().showWarningPopup("Booking " + deleted.bookingID + " deleted.");
		}
        
        getModel().hotelSystem.deleteBooking(bookingID);
		renderClient();
	}
}
