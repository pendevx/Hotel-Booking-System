package com.group5.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.group5.account.Account;
import com.group5.account.AccountCredentials;
import com.group5.hotel.Booking;
import com.group5.hotel.Hotel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class FileIO {
    private static final String credentialsPath;
	private static final String accountsPath;
	private static final String bookingsPath;
	private static final String usernamesPath;
	private static final String hotelPath;
    private static final Gson gson;
    private static final boolean createLog = false;

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
	 * Generic for reading json files. Couldn't remember if this would count as
	 * "3" separate read functions, so created function per json file.
	 * 
	 * @param path - path to json file
	 * @return E - generic type
	 */
//	public static <E> E readJsonFile(String path) {
//		try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(path).toFile()));
//			E object = gson.fromJson(bufferedReader, new TypeToken<E>.getType());
//			bufferedReader.close();
//			return object;
//		}	
// 		catch (IOException e) { throw new RuntimeException(e); }
//	}


	/***
	 * Reads from the json file storing credentials.
	 * Implement with BufferedReader, more appropriate for reading in text data.
	 * 
	 * @return list collection of account credentials
	 */
	public static Set<AccountCredentials> loadCredentialsJson() {
		Set<AccountCredentials> credentials = null;
		try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(credentialsPath).toFile()));
    		credentials = gson.fromJson(bufferedReader, new TypeToken<Set<AccountCredentials>>(){}.getType());
            bufferedReader.close();
		}
 		catch (IOException e) { throw new RuntimeException(e); }
		return credentials;
	}


	/***
	 * Reads from the json file storing accounts 
	 * Implement with BufferedReader, more appropriate for reading in text data.
	 * 
	 * @return list collection of account 
	 */
	public static List<Account> loadAccountsJson() {
		List<Account> accounts = null;
		try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(accountsPath).toFile()));
    		accounts = gson.fromJson(bufferedReader, new TypeToken<List<Account>>(){}.getType());
            bufferedReader.close();
		}
 		catch (IOException e) { throw new RuntimeException(e); }
		return accounts;
	}

	/***
	 * Reads from the json file storing bookings 
	 * Implement with BufferedReader, more appropriate for reading in text data.
	 * 
	 * @return list collection of bookings 
	 */
	public static List<Booking> loadBookingJson() {
		List<Booking> bookings = null;
		try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(bookingsPath).toFile()));
    		bookings = gson.fromJson(bufferedReader, new TypeToken<List<Booking>>(){}.getType());
            bufferedReader.close();
		}
 		catch (IOException e) { throw new RuntimeException(e); }
		return bookings;
	}

	/***
	 * Reads from the json file storing bookings 
	 * Implement with BufferedReader, more appropriate for reading in text data.
	 * 
	 * @return list collection of bookings 
	 */
	public static Set<String> loadUsernameJson() {
		Set<String> usernames = null;
		try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(usernamesPath).toFile()));
    		usernames = gson.fromJson(bufferedReader, new TypeToken<Set<String>>(){}.getType());
            bufferedReader.close();
		}
 		catch (IOException e) { throw new RuntimeException(e); }
		return usernames;
	}

	/***
	 * Reads from the json file storing hotels
	 * Implement with BufferedReader, more appropriate for reading in text data.
	 * 
	 * @return list collection of hotels 
	 */
	public static List<Hotel> loadHotelJson() {
		List<Hotel> hotels = null;
		try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(hotelPath).toFile()));
    		hotels = gson.fromJson(bufferedReader, new TypeToken<List<Hotel>>(){}.getType());
            bufferedReader.close();
		}
 		catch (IOException e) { throw new RuntimeException(e); }
		return hotels;
	}

	/***
	 * Write to json file storing credentials
	 * 
	 * @param credentials - list collection of credentials
	 */
	public static void saveCredentials(Set<AccountCredentials> credentials) {
        if (createLog) backupFile(credentialsPath, "credentials.json");

		new Thread(() -> {
			try {

				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(credentialsPath).toFile()));
				gson.toJson(credentials, bufferedWriter);
				bufferedWriter.close();
			}
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

	/***
	 * Write to json file storing accounts
	 * 
	 * @param accounts - list collection of accounts
	 */
	public static void saveAccounts(List<Account> accounts) {
        if (createLog) backupFile(accountsPath, "account_details.json");

		new Thread(() -> {
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(accountsPath).toFile()));
				gson.toJson(accounts, bufferedWriter);
				bufferedWriter.close();
			}
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
        if (createLog) backupFile(bookingsPath, "bookings.json");

		new Thread(() -> {
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(bookingsPath).toFile()));
				gson.toJson(bookings, bufferedWriter);
				bufferedWriter.close();
			}
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

	/***
	 * Write to json file storing usernames
	 * 
	 * @param usernames - set collection of usernames
	 */
	public static void saveUsernames(Set<String> usernames) {
        if (createLog) backupFile(usernamesPath, "usernames.json");

		new Thread(() -> {
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(usernamesPath).toFile()));
				gson.toJson(usernames, bufferedWriter);
				bufferedWriter.close();
			}
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

	/***
	 * Write copy of file to disk into the log folder,
	 * overwrites if there is an existing backup
	 * 
	 * @param sourcePath - path of original file
	 */
	public static void backupFile(String sourcePath, String file) {
		try {
			String rootPath = Paths.get(sourcePath).getParent().toString() + "/logs/"; // adds into log folder
			String originalName = Paths.get(sourcePath).getFileName().toString();
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmm")); // add time stamp
			String backupPath = rootPath + timestamp + "_" + originalName;
			Files.copy(Paths.get(sourcePath), Paths.get(backupPath), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (Exception ex) { System.out.println("File backup failed: " + file); };
	}
}
