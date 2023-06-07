package com.group5.mvc;

import com.group5.app.AppSession;
import com.group5.card.*;
import com.group5.component.*;
import com.group5.component.Text.FontSize;
import com.group5.hotel.Account;
import com.group5.hotel.Hotel;
import com.group5.system.HotelSystemAdmin;
import com.group5.system.HotelSystemUser;
import com.group5.view.ViewLogin;
import com.group5.view.ViewRegister;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
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
		if (getCardLogin() != null) {
			if (e.getSource() == getCardLogin().loginButton) loginHandler();
			else if (e.getSource() == getCardLogin().registerButton) view.renderRegistration();
		}

		if (getCardRegister()!= null) {
			if (e.getSource() == getCardRegister().cancelButton) view.renderLogin();
			else if (e.getSource() == getCardRegister().submitButton) registrationHandler();
		}

		if (getCardAccount()!= null) {
			if (e.getSource() == getCardAccount().editAccountButton) renderAccountEdit();
//			if (e.getSource() == getCardAccount().editAccountButton) System.out.println("Edit");
			else if (e.getSource() == getCardAccount().logoutButton) logoutHandler();
		}

		if (getCardAccountEdit()!= null) {
			if (e.getSource() == getCardAccountEdit().cancelButton) renderView();
			else if (e.getSource() == getCardAccountEdit().saveButton) editAccountHandler();
		}
	}


	// split off these methods
	// into controller
	// controllerLogin extends controller
	// controllerRegister extends controller
	// controllerUser extends controller
	// controllerAdmin extends controller

	private void loginHandler() {
		String usr = getCardLogin().usernameField.getText().toLowerCase();
		String pwd = getCardLogin().passwordField.getText();
		if(model.loginPortal(usr, pwd)) this.renderView();
		else getCardLogin().showWarningPopup("Incorrect username or password!");
	}







	private void registrationHandler() {
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
			else getCardRegister().showWarningPopup("Username is taken.");
		}
	}

	private void editAccountHandler() {
		String newEmail = getCardAccountEdit().emailNew.getText();
		String newPhone = getCardAccountEdit().phoneNew.getText();
		if (!hasEmptyField(getCardAccountEdit(), newEmail, newPhone)) {
			model.hotelSystem.updateEmail(newEmail);
			model.hotelSystem.updatePhone(newPhone);
			renderView();
			System.out.println("Details updated.");
		}
	}

	private void logoutHandler() {
		int result = JOptionPane.showConfirmDialog(view, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			this.model.logout();
			this.model = null;
			this.model = new AppSession();
			view.renderLogin();
		}
	}

	private void renderView() {
		if (model.hotelSystem instanceof HotelSystemUser) {
			// ViewClientUser
			view.renderUser(getAccountInfo(), getHotelInfo());
		}
		else if (model.hotelSystem instanceof HotelSystemAdmin) {
			// ViewClientAdmin
			view.renderAdmin(getAccountInfo());
		}
	}

	private void renderAccountEdit() {
////		view.renderAccountEdit();
		view.clientView.renderAccountEdit();
//		System.out.println("Edit");
		getCardAccountEdit().setTextEmail(getAccount().email);
		getCardAccountEdit().setTextPhone(getAccount().phone);
	}






	private Container[] getAccountInfo() {
		if (model.hotelSystem != null) {
			final int width = 250;
			final int margin = 45;

			String accountType = getAccount().toString();
			String fullname = getAccount().getFullName();
			String email = getAccount().email;
			String phone = getAccount().phone;
			Container[] accountInfo = new Container[4];
			accountInfo[0] = new Container(FlowLayout.LEFT, width, margin, new Text(accountType, FontSize.H1));
			accountInfo[1] = new Container(FlowLayout.LEFT, width, margin, new Text(fullname, FontSize.H2));
			accountInfo[2] = new Container(FlowLayout.LEFT, width, margin, new Text(email, FontSize.H2));
			accountInfo[3] = new Container(FlowLayout.LEFT, width, margin, new Text(phone, FontSize.H2));
			return accountInfo;
		}
		return null;
	}

	private Container[] getHotelInfo() {
		if (model.hotelSystem != null) {
			final int width = 450;
			final int margin = 20;
			String hotelname = getHotel().getHotelName();
			String address = getHotel().getAddress();
			String phone = getHotel().phone;
			String email = getHotel().email;
			Container[] hotelInfo = new Container[4];
			hotelInfo[0] = new Container(FlowLayout.LEFT, width, 30, new Text(hotelname, FontSize.H2));
			hotelInfo[1] = new Container(FlowLayout.LEFT, width, margin, new Text(address, FontSize.H3));
			hotelInfo[2] = new Container(FlowLayout.LEFT, width, margin, new Text(phone, FontSize.H3));
			hotelInfo[3] = new Container(FlowLayout.LEFT, width, margin, new Text(email, FontSize.H3));
			return hotelInfo;
		}
		return null;
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

//	private CardLogin getCardLogin() { return (CardLogin) view.cardLogin; }
	private CardLogin getCardLogin() {
		if (view.loginView == null) return null;
		else return view.loginView.getCard();
	}


//	private CardRegister getCardRegister() { return (CardRegister) view.cardRegister; }
	private CardRegister getCardRegister() {
		if (view.registerView == null) return null;
		else return view.registerView.getCard();
	}

	private CardAccount getCardAccount() {
		if (view.clientView == null) return null;
		else return view.clientView.getCardAccount();
	}

	private CardAccountEdit getCardAccountEdit() {
		return (CardAccountEdit) view.cardAccountEdit;
	}

	private Account getAccount() { return model.hotelSystem.getAccount(); }
	private Hotel getHotel() { return model.hotelSystem.getHotel(); }
}
