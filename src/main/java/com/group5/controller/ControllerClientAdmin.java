package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardBookingManageAdmin;
import com.group5.view.ViewClientAdmin;
import com.group5.view.ViewGUI;
import java.awt.event.ActionEvent;

public class ControllerClientAdmin extends ControllerClient {

	public ControllerClientAdmin(ViewGUI view, AppSession model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		super.clientView = new ViewClientAdmin(this, super.getAccountInfo(), super.getHotelInfo());
		super.updateDisplay(super.clientView.getBasePanel());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardAccount()!= null) {
			if (e.getSource() == getCardAccount().editAccountButton) editHandler();
			else if (e.getSource() == getCardAccount().logoutButton) logoutHandler();
		}
	}

	private void editHandler() {
		if (getModel() != null) new ControllerAccountEdit(getView(), getModel());
	}
}
