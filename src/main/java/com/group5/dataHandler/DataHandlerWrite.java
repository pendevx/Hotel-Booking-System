package com.group5.dataHandler;

import com.group5.account.Account;
import com.group5.account.AccountCredentials;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class DataHandlerWrite extends DataHandler {
	public static void saveCredentials(List<AccountCredentials> credentials) {
		DataBackup.backupFile(credentialsPath);
		new Thread(() -> {
			try { Files.write(Paths.get(credentialsPath), gson.toJson(credentials).getBytes()); }
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

	public static void saveAccounts(List<Account> accounts) {
		DataBackup.backupFile(accountsPath);
		new Thread(() -> {
			try { Files.write(Paths.get(accountsPath), gson.toJson(accounts).getBytes()); }
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

//	public static void saveBookings(List<Booking> bookings) {
//		DataBackup.backupFile(bookingsPath);
//		new Thread(() -> {
//			try { Files.write(Paths.get(bookingsPath), gson.toJson(bookings).getBytes()); }
//			catch (IOException e) { throw new RuntimeException(e); }
//		}).start();
//	}

//	public static void saveHotel(List<Hotel> hotel) {
//		DataBackup.backupFile(accountsPath);
//		new Thread(() -> {
//			try { Files.write(Paths.get(hotelPath), gson.toJson(hotel).getBytes()); }
//			catch (IOException e) { throw new RuntimeException(e); }
//		}).start();
//	}

}
