package com.group5.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.group5.hotel.Account;
import com.group5.hotel.Credential;
import com.group5.hotel.Booking;
import com.group5.hotel.Hotel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
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
	private static <E> E readJsonFile(String path, Type type) {
		try {
			Path filePath = Paths.get(path);
			if (!Files.exists(filePath)) throw new FileNotFoundException("Missing");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(path).toFile()));
			E object = gson.fromJson(bufferedReader, type); // type erasure if use new TypeToken here
			bufferedReader.close();
			return object;
		}	
 		catch (IOException e) { throw new RuntimeException(e); }
	}

//	public static Set<Credential> loadCredentialsJson() {
//		return readJsonFile(credentialsPath, new TypeToken<Set<Credential>>(){}.getType());
//	}
//
//	public static List<Account> loadAccountsJson() {
//		return readJsonFile(accountsPath, new TypeToken<List<Account>>(){}.getType());
//	}

	/***
	 * Reads from the json file storing bookings 
	 * Implement with BufferedReader, more appropriate for reading in text data.
	 * 
	 * @return list collection of bookings 
	 */
	public static List<Booking> loadBookingJson() {
		return readJsonFile(bookingsPath, new TypeToken<List<Booking>>(){}.getType());
	}

//	public static Set<String> loadUsernameJson() {
//		return readJsonFile(usernamesPath, new TypeToken<Set<String>>(){}.getType());
//	}
//
//	public static List<Hotel> loadHotelJson() {
//		return readJsonFile(hotelPath, new TypeToken<List<Hotel>>(){}.getType());
//	}

//	public static void saveCredentials(Set<Credential> credentials) {
//        if (createLog) backupFile(credentialsPath, "credentials.json");
//
//		new Thread(() -> {
//			try {
//
//				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(credentialsPath).toFile()));
//				gson.toJson(credentials, bufferedWriter);
//				bufferedWriter.close();
//			}
//			catch (IOException e) { throw new RuntimeException(e); }
//		}).start();
//	}

//	public static void saveAccounts(List<Account> accounts) {
//        if (createLog) backupFile(accountsPath, "account_details.json");
//
//		new Thread(() -> {
//			try {
//				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(accountsPath).toFile()));
//				gson.toJson(accounts, bufferedWriter);
//				bufferedWriter.close();
//			}
//			catch (IOException e) { throw new RuntimeException(e); }
//		}).start();
//	}

	/***
	 * 
	 * Write to json file storing bookings
	 * 
	 * @param bookings - list collection of bookings
	 */
	public static void saveBookings(List<Booking> bookings) {
//        if (createLog) backupFile(bookingsPath, "bookings.json");

		new Thread(() -> {
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(bookingsPath).toFile()));
				gson.toJson(bookings, bufferedWriter);
				bufferedWriter.close();
			}
			catch (IOException e) { throw new RuntimeException(e); }
		}).start();
	}

//	public static void saveUsernames(Set<String> usernames) {
//        if (createLog) backupFile(usernamesPath, "usernames.json");
//
//		new Thread(() -> {
//			try {
//				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(usernamesPath).toFile()));
//				gson.toJson(usernames, bufferedWriter);
//				bufferedWriter.close();
//			}
//			catch (IOException e) { throw new RuntimeException(e); }
//		}).start();
//	}

//	public static void backupFile(String sourcePath, String file) {
//		try {
//			String rootPath = Paths.get(sourcePath).getParent().toString() + "/logs/"; // adds into log folder
//			String originalName = Paths.get(sourcePath).getFileName().toString();
//			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmm")); // add time stamp
//			String backupPath = rootPath + timestamp + "_" + originalName;
//			Files.copy(Paths.get(sourcePath), Paths.get(backupPath), StandardCopyOption.REPLACE_EXISTING);
//		}
//		catch (Exception ex) { System.out.println("File backup failed: " + file); };
//	}
}
