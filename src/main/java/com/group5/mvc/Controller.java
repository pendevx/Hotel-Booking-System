package com.group5.mvc;

import com.group5.Module.CardLogin;
import com.group5.Module.CardRegister;
import com.group5.app.AppSession;
import com.group5.system.HotelSystemAdmin;
import com.group5.system.HotelSystemUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	public ViewGUI view;
	public AppSession model;

	public Controller(ViewGUI view) {
		this.view = view;
		this.model = new AppSession();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getCardLogin().loginButton) {
			String usr = getCardLogin().usernameField.getText();
			String pwd = getCardLogin().passwordField.getText();
			model.loginPortal(usr, pwd);

			if(model.loginPortal(usr, pwd)) {
				if (model.hotelSystem instanceof HotelSystemUser) view.renderUser();
				else if (model.hotelSystem instanceof HotelSystemAdmin) view.renderAdmin();
			}
			else getCardLogin().showWarningPopup("Incorrect username or password!");
		}
		else if (e.getSource() == getCardLogin().registerButton) {
			view.renderRegistration();
		}
//		else if (e.getSource() == getCardRegister().cancelButton) {
//			view.renderLogin();
//		}
		else if (e.getSource() == getCardRegister().submitButton) {
//			System.out.println("Submit button clicked.");
//			// check this
//			String usr = getCardRegister().userFieldNew.getText();
			getCardRegister().showWarningPopup("Username exists!");
//
//			if (true) {
//				String pwd = getCardRegister().passFieldNew.getText();
//				String fname = getCardRegister().firstNameNew.getText();
//				String lname = getCardRegister().lastNameNew.getText();
//				String email = getCardRegister().emailNew.getText();
//				String phone = getCardRegister().phoneNew.getText();
//			}
//
//			view.renderRegistration();
		}
		else if (e.getSource() == getCardRegister().cancelButton) {
			this.model.logout();
			this.model = new AppSession();
			view.renderLogin();
		}
	}

	private CardLogin getCardLogin() { return view.cardLogin; }
	private CardRegister getCardRegister() { return view.cardRegister; }
}
