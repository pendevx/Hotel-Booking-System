package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.component.*;
import com.group5.hotel.Account;
import com.group5.hotel.Hotel;
import com.group5.system.HotelSystemAdmin;
import com.group5.system.HotelSystemUser;
import com.group5.view.ViewGUI;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
	private ViewGUI view;
	private AppSession model;

	protected abstract void init();

	/**
	 * Abstract controller class that holds the ViewGUI and AppSession
	 * 
	 * @param view the JFrame
	 * @param model the AppSession.
	 */
	public Controller(ViewGUI view, AppSession model) {
		this.view = view;
		this.model = model;
	}

	/**
	 * Factory pattern, will load relevant Controller based on type of system, user or admin.
	 */
	public void renderClient() {
		if (model != null) {
			if (model.hotelSystem instanceof HotelSystemUser) new ControllerClientUser(view, model);
			else if (model.hotelSystem instanceof HotelSystemAdmin) new ControllerClientAdmin(view, model);
		}
	}

	/**
	 * Runs the AppSession logout method. Then creates a new app session and loades the loginController
	 */
	public void logout() { 
		this.getModel().logout();
		this.model = new AppSession();
		if (model != null) new ControllerLogin(view, getModel());
	}

	/**
	 * Takes a Card(JPanel) and the input data from JComponents, checking if any are empty.
	 * @param card to display warning
	 * @param fields array of String to check.
	 * @return 
	 */
	public boolean hasEmptyField(Card card, String...fields) {
		for (String s : fields) {
			if (s.isEmpty()) {
				card.showWarningPopup("Please fill in all empty sections...");
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates the ViewGUI(JFrame) with a new Panel.
	 * @param newBase 
	 */
	public void updateDisplay(Base newBase) {
		this.view.updateDisplay(newBase);
	}

	// get methods
	public ViewGUI getView() { return this.view; }
	public AppSession getModel() { return this.model; }
	public Account getAccount() { return getModel().hotelSystem.getAccount(); }
	public Hotel getHotel() { return getModel().hotelSystem.getHotel(); }
}
