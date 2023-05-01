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

    /***
     * Constuctor creates a new hotel portal, used to determine which
     * account view to show based on if account is a user or admin. Used to
	 * connect AppSession to the system backend
     */
	public AppSession() {
		hotelPortal = new HotelPortal();
	}

    /***
     * Display main menu in console
     * @param scan - for users input
     */
	public void sessionMenu(Scanner scan) {
		System.out.println("1. Login");
		System.out.println("2. Create user account");
		System.out.println("0. QUIT");
		int input = ParseInput.integer(0, 2, scan); // valid input between 0 - 2
		if (input == 1) loginPortal(scan);
		else if (input == 2) createPortal(scan);
		else System.out.println("Program quit successfully.");
	}

    /***
     * Take user through to login process.
     * 
     * @param scan - for users input
     */
	private void loginPortal(Scanner scan) {
		Account account = new AccountLogin().login(scan); // login process gets the exsiting account
		if (account != null) login(scan, account); // logs account in
		else System.out.println("Too many failed attempts, program exited");
	}

    /***
     * Take user through to account registration process.
     * 
     * @param scan - for users input
     */
	private void createPortal(Scanner scan) {
        Account account = new AccountNew().createUser(scan); // creates user account
		if(account != null) login(scan, account); // logs account in
		else System.out.println("Too many failed attempts, program exited");
	}

    /***
     * Checks the account permission of account and loads
     * appropriate viewType, either user or admin.
     * 
     * @param scan - for user input
     * @param account - that is to be loaded into booking system
     */
	private void login(Scanner scan, Account account) {
		if (isUser(account)) { // checks if is USER
			viewType = new ViewUser((HotelSystemUser) hotelPortal.login(account)); // sets viewType to user
		}
		else if (isAdmin(account)) { // check if account is type ADMIN
			viewType = new ViewAdmin((HotelSystemAdmin) hotelPortal.login(account)); // set viewType to admin
		}
		viewType.confirmLogin(); // prints confirmation of login, with formatted string
		viewType.menuMain(scan); // opens the views menu
	}

    /***
     * @param account to be checked
     * @return boolean of if account type is USER
     */
	private boolean isUser(Account account) {
		return account.getAccountType() == AccountPermission.USER;
	}

    /***
     * @param account to be checked
     * @return boolean of if account type is ADMIN
     */
	private boolean isAdmin(Account account) {
		return account.getAccountType() == AccountPermission.ADMIN;
	}
}
