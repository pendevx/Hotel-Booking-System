package com.group5.views;

import gh.SystemUser;
import com.group5.account.Account;
import com.group5.dataHandler.DataHandlerRead;
import com.group5.hotel.Hotel;

import java.util.Scanner;


public abstract class View {
	public Account account;
	public Hotel hotel;
	protected SystemUser system;

	public View (Account account, SystemUser system) {
		this.account = account;
		this.hotel = DataHandlerRead.loadHotelJson().get(0);
		this.system = system;
	}

	public abstract void menuMain(Scanner scan);

	public void confirmLogin() {
		System.out.println("\nLogged in as" + " " + account.toString() + "\n");
	}

	public void printUserDetails() {
		System.out.println(account.toString());
		System.out.println(account.getAccountDetails() + "\n");
	}

	public void printHotelDetails() {
		System.out.println(this.hotel.toString());
	}
}
