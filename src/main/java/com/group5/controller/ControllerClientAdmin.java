package com.group5.controller;

import com.group5.app.AppSession;
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
		}
	}

	// loads the Account edit panel to show, along with its controller
	private void editHandler() {
		if (getModel() != null) new ControllerAccountEdit(getView(), getModel());
	}
}
