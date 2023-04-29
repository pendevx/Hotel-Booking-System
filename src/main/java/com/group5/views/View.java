package com.group5.views;

import com.group5.account.Account;
import com.group5.dataHandler.DataHandlerRead;
import com.group5.hotel.Hotel;

import java.util.Scanner;

import com.group5.gh.*;

public abstract class View {
	public Account account;
	public Hotel hotel;
	protected UserSystem system;

	public View (Account account, UserSystem system) {
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
