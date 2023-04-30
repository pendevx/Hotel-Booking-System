package com.group5.app;

import com.group5.system.HotelSystemAdmin;
import com.group5.system.HotelPortal;
import com.group5.system.HotelSystemUser;
import com.group5.account.Account;
import com.group5.system.AccountLogin;
import com.group5.system.AccountNew;
import com.group5.account.AccountPermission;
import com.group5.util.ParseInput;
import com.group5.view.View;
import com.group5.view.ViewAdmin;
import com.group5.view.ViewUser;
import java.util.Scanner;


public class AppSession {
	private View viewType;
	private HotelPortal hotelPortal;

	public AppSession() {
		hotelPortal = new HotelPortal();
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

	private void login(Scanner scan, Account account) {
		if (isUser(account)) {
			viewType = new ViewUser((HotelSystemUser) hotelPortal.login(account));
		}
		else if (isAdmin(account)) {
			viewType = new ViewAdmin((HotelSystemAdmin) hotelPortal.login(account));
		}
		viewType.confirmLogin();
		viewType.menuMain(scan);
	}

	private boolean isUser(Account user) { return user.getAccountType()== AccountPermission.USER; }
	private boolean isAdmin(Account user) { return user.getAccountType()== AccountPermission.ADMIN; }
}
