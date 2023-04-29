package com.group5.app;

import com.group5.account.Account;
import com.group5.account.AccountLogin;
import com.group5.account.AccountNew;
import com.group5.account.AccountPermission;
import com.group5.util.ParseInput;
import com.group5.views.View;
import com.group5.views.ViewAdmin;
import com.group5.views.ViewUser;
import java.util.Scanner;

import com.group5.gh.*;

public class AppSession {
	View viewType;
	HotelApp hotelApp;

	AppSession() {
		hotelApp = new HotelApp();
	}

	public void sessionMenu(Scanner scan) {
		System.out.println("1. Login");
		System.out.println("2. Create user account");
		System.out.println("0. QUIT");

		int input = ParseInput.integer(0, 2, scan);
		if (input == 1) loginPortal(scan);
		else if (input == 2) createPortal(scan);
		else System.out.println("Program quit successfully.");
	}

	private void loginPortal(Scanner scan) {
		Account account = new AccountLogin().login(scan);
		if (account != null) login(scan, account);
		else System.out.println("Too many failed attempts, program exited");
	}

	private void createPortal(Scanner scan) {
		Account account = new AccountNew().createUser(scan);
		if(account != null) login(scan, account);
		else System.out.println("Too many failed attempts, program exited");
	}

	// TODO: take in account, load booking
	private void login(Scanner scan, Account account) {
		// send through the account to pick the type of login to use
		// allows for autologin
		if (isUser(account)) {
			ClientSystem system = (ClientSystem) hotelApp.login(account);
			this.viewType = new ViewUser(account, system);
		}  else if (isAdmin(account)) {
			AdminSystem system = (AdminSystem) hotelApp.login(account);
			this.viewType = new ViewAdmin(account, system);
		}

		this.viewType.confirmLogin();
		this.viewType.menuMain(scan);
	}

	private boolean isUser(Account user) { return user.getAccountType()== AccountPermission.USER; }
	private boolean isAdmin(Account user) { return user.getAccountType()== AccountPermission.ADMIN; }
}
