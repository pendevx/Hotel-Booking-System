package com.group5.app;

import com.group5.system.*;
import com.group5.hotel.Account;
import com.group5.util.ParseInput;
import com.group5.view.View;
import com.group5.view.ViewAdmin;
import com.group5.view.ViewUser;

import java.util.Scanner;


public class AppSession {
	private View viewType;

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
		else if (input == 2) registerPortal(scan);
		else System.out.println("Program quit successfully.");
	}

    /***
     * Take user through to login process.
     * 
     * @param scan - for users input
     */
	private void loginPortal(Scanner scan) {
		boolean isSuccess = false;
		String enteredUsername;
		String enteredPassword;
		HotelSystem system = null;
		for(int attempts = 2; attempts >= 0 && !isSuccess; attempts--) { // handles if correct on third attempt
			System.out.println("Username: ");
			enteredUsername = ParseInput.string(scan);
			System.out.println("Password: ");
			enteredPassword = ParseInput.string(scan);
			system = AccountManager.login(enteredUsername, enteredPassword);

			if (system != null)
				break;

			System.out.println("Invalid credentials!");
		}

		if (system == null) {
			System.out.println("You entered bad credentials 3 times >=(");
		}

		init(system, scan);
	}

    /***
     * Take user through to account registration process.
     * 
     * @param scan - for users input
     */
	private void registerPortal(Scanner scan) {
		String username = "";
		boolean usernameExists = true;

		for (int i = 0; i < 3 && usernameExists; i++) {
			System.out.println("Enter new username: ");
			username = ParseInput.string(scan);
			usernameExists = AccountManager.checkUsernameExists(username); // only gets String if username is unique
		}

		if (usernameExists) {
			System.out.println("Error creating your account: Please see logs for more details, or try again with another username.");
		}

		System.out.println("Enter new password: ");
		String password = ParseInput.string(scan);
		System.out.println("Enter first name: ");
		String firstName = ParseInput.string(scan);
		System.out.println("Enter last name: ");
		String lastName = ParseInput.string(scan);
		System.out.println("Enter email: ");
		String email = ParseInput.string(scan);
		System.out.println("Enter phone: ");
		String phone = ParseInput.string(scan);

		Account newAccount = AccountManager.createAccount(username, password, firstName, lastName, phone, email);

		if (newAccount == null) {
			System.out.println("An error occurred when creating your account; please try again.");
			return;
		}

		HotelSystem system = AccountManager.login(username, password);

		init(system, scan);
	}

	private void init(HotelSystem system, Scanner scan) {
		if (system.getClass() == HotelSystemAdmin.class) {
			viewType = new ViewAdmin((HotelSystemAdmin) system);
		} else {
			viewType = new ViewUser((HotelSystemUser) system);
		}

		viewType.confirmLogin();
		viewType.menuMain(scan);
	}
}
