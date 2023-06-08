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
	private static AppSession model;

	protected abstract void init();

	public Controller(ViewGUI view, AppSession model) {
		this.view = view;
		this.model = model;
	}

	public void renderClient() {
		if (model != null) {
			if (model.hotelSystem instanceof HotelSystemUser) new ControllerClientUser(view, model);
			else if (model.hotelSystem instanceof HotelSystemAdmin) new ControllerClientAdmin(view, model);
		}
	}

	public void logout() { 
		this.getModel().logout();
		this.model = new AppSession();
		if (model != null) new ControllerLogin(view, getModel());
	}

	public boolean hasEmptyField(Card card, String...fields) {
		for (String s : fields) {
			if (s.isEmpty()) {
				card.showWarningPopup("Please fill in all empty sections...");
				return true;
			}
		}
		return false;
	}

	public void updateDisplay(Base newBase) { this.view.updateDisplay(newBase); }
	public ViewGUI getView() { return this.view; }
	public AppSession getModel() { return this.model; }
	public Account getAccount() { return getModel().hotelSystem.getAccount(); }
	public Hotel getHotel() { return getModel().hotelSystem.getHotel(); }
}
