package com.group5.mvc;

import com.group5.app.AppSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	public ViewGUI view;
	public AppSession model;

	public Controller(ViewGUI view, AppSession model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.panelLogin.loginButton) {
			System.out.println("Login button clicked.");
		}
		else if (e.getSource() == view.panelLogin.registerButton) {
			view.renderRegistration();
		}
		else if (e.getSource() == view.panelRegistration.cancelButton) {
			view.renderLogin();
		}
		else if (e.getSource() == view.panelRegistration.submitButton) {
			System.out.println("Submit button clicked.");
		}
//		else if (e.getSource() == view.submitRegistration) {}
	}
}
