package com.group5.mvc;

import com.group5.module.CardLogin;
import com.group5.module.CardRegister;
import com.group5.app.AppSession;
import com.group5.component.*;
import com.group5.component.Text.FontSize;
import com.group5.module.CardAccount;
import com.group5.system.HotelSystemAdmin;
import com.group5.system.HotelSystemUser;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {
	public ViewGUI view;
	public AppSession model;

	public Controller(ViewGUI view) {
		this.view = view;
		this.model = new AppSession();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardLogin() != null) {
			if (e.getSource() == getCardLogin().loginButton) loginHandler();
			else if (e.getSource() == getCardLogin().registerButton) view.renderRegistration();
		}

		if (getCardRegister()!= null) {
			if (e.getSource() == getCardRegister().cancelButton) view.renderLogin();
			else if (e.getSource() == getCardRegister().submitButton) registerHandler();
		}

		if (getCardAccount()!= null) {
			if (e.getSource() == getCardAccount().logoutButton) logoutHandler();
		}
	}

	private void loginHandler() {
		String usr = getCardLogin().usernameField.getText();
		String pwd = getCardLogin().passwordField.getText();

		if(model.loginPortal(usr, pwd)) {
			String accountType = model.hotelSystem.getAccount().toString();
			String fullname = model.hotelSystem.getAccount().getFullName();
			String email = model.hotelSystem.getAccount().email;
			String phone = model.hotelSystem.getAccount().phone;

			Container acc = new Container(FlowLayout.LEFT,300, 50, new Text(accountType, FontSize.H1));
			Container nme = new Container(FlowLayout.LEFT, 300, 50, new Text(fullname, FontSize.H1));
			Container eml = new Container(FlowLayout.LEFT, 300, 50, new Text(email, FontSize.H1));
			Container phn = new Container(FlowLayout.LEFT, 300, 50, new Text(phone, FontSize.H1));

			if (model.hotelSystem instanceof HotelSystemUser) view.renderUser(acc, nme, eml, phn);
			else if (model.hotelSystem instanceof HotelSystemAdmin) view.renderAdmin();
		}
		else getCardLogin().showWarningPopup("Incorrect username or password!");
	}

	private void logoutHandler() {
		int result = JOptionPane.showConfirmDialog(view, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			this.model.logout();
			this.model = new AppSession();
			view.renderLogin();
		}
	}

	private void registerHandler() {
		String usr = getCardRegister().userFieldNew.getText();
		String pwd = getCardRegister().passFieldNew.getText();
		String fname = getCardRegister().firstNameNew.getText();
		String lname = getCardRegister().lastNameNew.getText();
		String email = getCardRegister().emailNew.getText();
		String phone = getCardRegister().phoneNew.getText();

		if (!hasEmptyField(usr, pwd, fname, lname, email, phone)) {
			System.out.println("OK");
		}
	}

	private boolean hasEmptyField(String...fields) {
		for (String s : fields) {
			if (s.isEmpty()) {
				getCardRegister().showWarningPopup("Please fill in all empty sections...");
				return true;
			}
		}
		return false;
	}

	private CardLogin getCardLogin() { return (CardLogin) view.cardLogin; }
	private CardRegister getCardRegister() { return (CardRegister) view.cardRegister; }
	private CardAccount getCardAccount() { return (CardAccount) view.cardAccount; }
}
