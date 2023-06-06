package com.group5.mvc;

import com.group5.module.CardLogin;
import com.group5.module.CardRegister;
import com.group5.app.AppSession;
import com.group5.component.*;
import com.group5.component.Text.FontSize;
import com.group5.database.HotelDatabase;
import com.group5.hotel.Account;
import com.group5.module.CardAccount;
import com.group5.module.CardAccountEdit;
import com.group5.system.AccountManager;
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
			if (e.getSource() == getCardAccount().editAccountButton) renderAccountEdit();
			else if (e.getSource() == getCardAccount().logoutButton) logoutHandler();
		}

		if (getCardAccountEdit()!= null) {
			if (e.getSource() == getCardAccountEdit().cancelButton) renderView();
			else if (e.getSource() == getCardAccountEdit().saveButton) editAccountHandler();
		}
	}

	private void loginHandler() {
		String usr = getCardLogin().usernameField.getText().toLowerCase();
		String pwd = getCardLogin().passwordField.getText();

		if(model.loginPortal(usr, pwd)) this.renderView();
		else getCardLogin().showWarningPopup("Incorrect username or password!");
	}

	private Container[] getAccountInfo() {
		if (model.hotelSystem != null) {
			String accountType = getAccount().toString();
			String fullname = getAccount().getFullName();
			String email = getAccount().email;
			String phone = getAccount().phone;
			Container[] accountInfo = new Container[4];
			accountInfo[0] = new Container(FlowLayout.LEFT,300, 50, new Text(accountType, FontSize.H1));
			accountInfo[1] = new Container(FlowLayout.LEFT, 300, 50, new Text(fullname, FontSize.H1));
			accountInfo[2] = new Container(FlowLayout.LEFT, 300, 50, new Text(email, FontSize.H1));
			accountInfo[3] = new Container(FlowLayout.LEFT, 300, 50, new Text(phone, FontSize.H1));
			return accountInfo;
		}
		return null;
	}

	private void logoutHandler() {
		int result = JOptionPane.showConfirmDialog(view, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			this.model.logout();
			this.model = new AppSession();
			view.renderLogin();
		}
	}

	private void renderView() {
		if (model.hotelSystem instanceof HotelSystemUser) view.renderUser(getAccountInfo());
		else if (model.hotelSystem instanceof HotelSystemAdmin) view.renderAdmin(getAccountInfo());
	}

	private void renderAccountEdit() {
		view.renderAccountEdit();
		getCardAccountEdit().setTextEmail(getAccount().email);
		getCardAccountEdit().setTextPhone(getAccount().phone);
	}

	private void editAccountHandler() {
		String newEmail = getCardAccountEdit().emailNew.getText();
		String newPhone = getCardAccountEdit().phoneNew.getText();
		if (!hasEmptyField(getCardAccountEdit(), newEmail, newPhone)) {
			model.hotelSystem.updateEmail(newEmail);
			model.hotelSystem.updatePhone(newPhone);
			renderView();
		}
	}

	private void registerHandler() {
		String usr = getCardRegister().userFieldNew.getText().toLowerCase();
		String pwd = getCardRegister().passFieldNew.getText();
		String fname = getCardRegister().firstNameNew.getText();
		String lname = getCardRegister().lastNameNew.getText();
		String email = getCardRegister().emailNew.getText().toLowerCase();
		String phone = getCardRegister().phoneNew.getText();

		if (!hasEmptyField(getCardRegister(), usr, pwd, fname, lname, email, phone)) {
			if (!model.checkUserNameExists(usr)) {
				model.registerPortal(usr, pwd, fname, lname, email, phone);
				if(model.loginPortal(usr, pwd)) this.renderView();
				else getCardLogin().showWarningPopup("Incorrect username or password!");
			}
//			if (!model.registerPortal(usr, pwd, fname, lname, email, phone)) {
//			}
			else getCardRegister().showWarningPopup("Username is taken.");
//			if (!model.checkUserExist(usr)) {
//			if (!AccountManager.checkUsernameExists(usr)) {
//			if (model.checkUserExist(usr)) {
//				System.out.println("all good");

				// write to db
//				if(model.loginPortal(usr, pwd)) this.renderView();
//			}
		}
//		usernameExists = AccountManager.checkUsernameExists(username); // only gets String if username is unique
//			if (usernameExists) System.out.println("Username taken");
//
//		Account newAccount = AccountManager.createAccount(username, password, firstName, lastName, phone, email);
////
//		if (newAccount == null) {
//			System.out.println("An error occurred when creating your account; please try again.");
//			return;
//		}
////
//		HotelSystem system = AccountManager.login(username, password);
	}

	private boolean hasEmptyField(Card card, String...fields) {
		for (String s : fields) {
			if (s.isEmpty()) {
				card.showWarningPopup("Please fill in all empty sections...");
				return true;
			}
		}
		return false;
	}

	private CardLogin getCardLogin() { return (CardLogin) view.cardLogin; }
	private CardRegister getCardRegister() { return (CardRegister) view.cardRegister; }
	private CardAccount getCardAccount() { return (CardAccount) view.cardAccount; }
	private CardAccountEdit getCardAccountEdit() { return (CardAccountEdit) view.cardAccountEdit; }
	private Account getAccount() { return model.hotelSystem.getAccount(); }
}
