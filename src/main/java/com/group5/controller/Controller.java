package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.component.*;
import com.group5.view.ViewGUI;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
	public ViewGUI view;
	private static AppSession model;

	protected abstract void init();

	public Controller(ViewGUI view, AppSession model) {
		this.view = view;
		this.model = model;
	}

	public void logoutHandler() {
		this.model.logout();
		this.model = new AppSession();
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

	public AppSession getModel() { return this.model; }
}
