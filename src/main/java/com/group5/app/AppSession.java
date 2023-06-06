package com.group5.app;

import com.group5.database.HotelDatabase;
import com.group5.system.*;
import com.group5.hotel.Account;

public class AppSession {

	public HotelSystem hotelSystem;
	private HotelDatabase hotelDatabase = new HotelDatabase();

	public boolean checkUserNameExists(String usr) {
		return AccountManager.checkUsernameExists(usr); 
	}

    /***
     * Take user through to login process.
     * 
     * @param scan - for users input
     */
	public boolean loginPortal(String enteredUsername, String enteredPassword) {
		this.hotelSystem = AccountManager.login(enteredUsername, enteredPassword);
		if (hotelSystem != null) return true;
		else return false;
	}

    /***
     * Take user through to account registration process.
     * 
     * @param scan - for users input
     */
	public void registerPortal(String usr, String pwd, String fname, String lname, String email, String phone) {
		Account newAccount = AccountManager.createAccount(usr, pwd, fname, lname, phone, email);
		if (newAccount == null) {
			System.out.println("An error occurred when creating your account; please try again.");
			return;
		}
	}

	public void logout() {
		this.hotelDatabase.closeConnection();
		this.hotelSystem = null;
		this.hotelDatabase = null;
		System.out.println("Logged out\n");
	}
}
