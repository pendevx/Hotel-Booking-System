package com.group5.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.group5.account.Account;
import com.group5.account.AccountCredentials;
import com.group5.hotel.Booking;
import com.group5.hotel.Hotel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class FileIO {
    private static final String credentialsPath;
	private static final String accountsPath;
	private static final String bookingsPath;
	private static final String usernamesPath;
	private static final String hotelPath;
    private static final Gson gson;

	/***
	 * Paths to json files, and formatter for Gson
	 */
	static {
    	credentialsPath = "src/main/resources/credentials.json";
		accountsPath = "src/main/resources/account_details.json";
		bookingsPath = "src/main/resources/bookings.json";
		usernamesPath = "src/main/resources/usernames.json";
		hotelPath = "src/main/resources/hotel.json";
    	gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();
	}

	/***
	 * Reads from the json file storing credentials 
	 * 
	 * @return list collection of account credentials
	 */
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

	/***
	 * Reads from the json file storing accounts 
	 * 
	 * @return list collection of account 
	 */
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

	/***
	 * Reads from the json file storing bookings 
	 * 
	 * @return list collection of bookings 
	 */
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

	/***
	 * Reads from the json file storing bookings 
	 * 
	 * @return list collection of bookings 
	 */
	public static Set<String> loadUsernameJson() {
		Set<String> usernames = null;
		try {
			usernames = gson.fromJson(String.join("\n", Files.readAllLines(Paths.get(usernamesPath))),
					new TypeToken<Set<String>>() {
					}.getType()
			);
		}
		catch (IOException e) { System.out.println(""); }
		return usernames;
	}

	/***
	 * Reads from the json file storing hotels
	 * 
	 * @return list collection of hotels 
	 */
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

	/***
	 * Write to json file storing credentials
	 * 
	 * @param credentials - list collection of credentials
	 */
	public static void saveCredentials(List<AccountCredentials> credentials) {
		backupFile(credentialsPath);
		new Thread(() -> {
			try { Files.write(Paths.get(credentialsPath), gson.toJson(credentials).getBytes()); }
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

	/***
	 * Write to json file storing accounts
	 * 
	 * @param accounts - list collection of accounts
	 */
	public static void saveAccounts(List<Account> accounts) {
		backupFile(accountsPath);
		new Thread(() -> {
			try { Files.write(Paths.get(accountsPath), gson.toJson(accounts).getBytes()); }
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

	/***
	 * 
	 * Write to json file storing bookings
	 * 
	 * @param bookings - list collection of bookings
	 */
	public static void saveBookings(List<Booking> bookings) {
		backupFile(bookingsPath);
		new Thread(() -> {
			try {
				Files.write(Paths.get(bookingsPath), gson.toJson(bookings).getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

	/***
	 * Write to json file storing usernames
	 * 
	 * @param uniqueUsernames - set collection of usernames
	 */
	public static void saveUsernames(Set<String> uniqueUsernames) {
		backupFile(usernamesPath);
		new Thread(() -> {
			try {
				Files.write(Paths.get(usernamesPath), gson.toJson(uniqueUsernames).getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

	/***
	 * Write copy of file to disk into the log folder,
	 * overwrites if there is an existing backup
	 * 
	 * @param sourcePath - path of original file
	 */
	public static void backupFile(String sourcePath) {
		try {
			String rootPath = Paths.get(sourcePath).getParent().toString() + "/logs/"; // adds into log folder
			String originalName = Paths.get(sourcePath).getFileName().toString();
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmm")); // add time stamp
			String backupPath = rootPath + timestamp + "_" + originalName;

			Files.copy(Paths.get(sourcePath), Paths.get(backupPath), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (Exception ex) { System.out.println("File backup failed."); };
	}
}
