package com.group5.system;

import com.google.gson.reflect.TypeToken;
import com.group5.account.Account;
import com.group5.account.AccountCredentials;
import com.group5.hotel.Booking;
import com.group5.hotel.Hotel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandlerRead extends DataHandler {

	public static List<AccountCredentials> loadCredentialsJson() {
		List<AccountCredentials> credentials = null;
		try {
			credentials = gson.fromJson(String.join("\n", Files.readAllLines(Paths.get(credentialsPath))),
					new TypeToken<List<AccountCredentials>>() {
					}.getType()
			);
		}
		catch (IOException e) { System.out.println(""); }
		return credentials;
	}

	public static List<Account> loadAccountsJson() {
		List<Account> accounts = null;
		try {
			accounts = gson.fromJson(
					String.join("\n", Files.readAllLines(Paths.get(accountsPath))),
					new TypeToken<List<Account>>() {
					}.getType()
			);
		}
		catch (IOException e) { System.out.println(""); }
		return accounts;
	}

	public static List<Booking> loadBookingJson() {
		List<Booking> bookings = null;
		try {
			bookings = gson.fromJson(String.join("\n", Files.readAllLines(Paths.get(bookingsPath))),
					new TypeToken<List<Booking>>() {
					}.getType()
			);
		}
		catch (IOException e) { System.out.println(""); }
		return bookings;
	}


	public static List<Hotel> loadHotelJson() {
		List<Hotel> hotels = null;
		try {
			hotels = gson.fromJson(
					String.join("\n", Files.readAllLines(Paths.get(hotelPath))),
					new TypeToken<List<Hotel>>() {
					}.getType()
			);
		}
		catch (IOException e) { System.out.println(""); }
		return hotels;
	}
}
