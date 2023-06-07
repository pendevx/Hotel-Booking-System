package com.group5.database;

import com.group5.hotel.Account;
import com.group5.hotel.AccountPermission;
import com.group5.hotel.Booking;
import com.group5.hotel.Credential;
import com.group5.hotel.Hotel;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HotelDatabase {
	private static DatabaseManager dbManager;

	public HotelDatabase() {
		dbManager = new DatabaseManager();
		createTable("hotel", SQL.createHotelTable());
		createTable("credentials", SQL.createCredentialTable());
		createTable("accounts", SQL.createAccountTable());
		createTable("bookings", SQL.createBookingTable());
		createTable("rooms", SQL.createRoomsTable());

//		Printer.printQuery("hotel", dbManager.query(SQL.selectAll("hotel"))); // for testing
//		Printer.printQuery("credential", dbManager.query(SQL.selectAll("credential"))); // for testing
//		Printer.printQuery("account", dbManager.query(SQL.selectAll("account"))); // for testing
		// TODO
//		this.createTable("booking", SQL.createBookingTable());
//		Printer.printQuery("booking", dbManager.query(SQL.selectAll("booking"))); // for testing
	}

	public static void main(String[] args) {
		HotelDatabase t = new HotelDatabase();
	}

	public static List<Hotel> loadHotel() {
		String tableName = "hotel";
		if (!tableExists(tableName)) return null;

		List<Hotel> hotels = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				hotels = new ArrayList<>();
				do {
					String hotelName = resultSet.getString("hotelName");
					String street = resultSet.getString("street");
					String suburb = resultSet.getString("suburb");
					String city = resultSet.getString("city");
					String postcode = resultSet.getString("postcode");
					String country = resultSet.getString("country");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email");
					hotels.add(new Hotel(hotelName, street, suburb, city, postcode, country, phone, email));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return hotels;
	}

	public static Set<Credential> loadCredentials() {
		String tableName = "credential";
		if (!tableExists(tableName)) return null;

		Set<Credential> credentials = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				credentials = new HashSet<>();
				do {
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					credentials.add(new Credential(username, password));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return credentials;
	}

	public static List<Account> loadAccounts() {
		String tableName = "account";
		if (!tableExists(tableName)) return null;

		List<Account> accounts = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				accounts = new ArrayList<>();
				do {
					String username = resultSet.getString("username");
					String firstName = resultSet.getString("firstname");
					String lastName = resultSet.getString("lastName");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email");
					String permission = resultSet.getString("permission");

					AccountPermission accountPermission = null;
					if (permission.equalsIgnoreCase("ADMIN")) accountPermission = AccountPermission.ADMIN;
					else accountPermission = AccountPermission.USER;

					accounts.add(new Account(username, firstName, lastName, phone, email, accountPermission));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return accounts;
	}

	// TODO:
	public static List<Booking> loadBooking() {
		return new ArrayList<>();
	}

	// Added to LOWERCASE username UNTESTED
	public static void insertCredentialTable(Credential credential) {
		new Thread(() -> {
			try {
				String username = credential.getUsername().toLowerCase();
				System.out.println(username);
				String password = credential.getPassword();
				dbManager.update(SQL.insertCredentialTable(username, password));
			}
			catch (Exception e) { throw new RuntimeException(e); }
		}).start();
	}

	// Added to LOWERCASE username UNTESTED
	public static void insertAccountTable(Account account) {
		new Thread(() -> {
			try {
				String username = account.getUsername().toLowerCase();
				String firstname = account.getFirstName();
				String lastname = account.getLastName();
				String phone = account.getPhone();
				String email = account.getEmail().toLowerCase();
				String permission = account.getAccountTypeName();
				dbManager.update(SQL.insertAccountTable(username, firstname, lastname, phone, email, permission));
			}
			catch (Exception e) { throw new RuntimeException(e); }
		}).start();
	}

	public static void insertBookingTable(Booking booking) {
		new Thread(() -> {
			try {
				dbManager.update(SQL.insertBookingTable(booking));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	// need reload database after
	// change account info in system, then write to database
	// need modify account, change to no final
	// only need for account
	public static void updateAccountPhone(Account account) {
		String username = account.getUsername();
		String newPhone = account.getPhone();
		dbManager.update(SQL.updateAccountPhone(username, newPhone));
	}
	
	public static void updateAccountEmail(Account account) {
		String username = account.getUsername();
		String newEmail = account.getEmail().toLowerCase();
		dbManager.update(SQL.updateAccountEmail(username, newEmail));
	}

	private static void createTable(String name, String...sql) {
		if (!tableExists(name)) dbManager.update(sql);
	}

	private static boolean tableExists(String name) {
		try {
			DatabaseMetaData metadata = dbManager.getConnection().getMetaData();
			String[] types = {"TABLE"};
			ResultSet resultSet = metadata.getTables(null, null, null, null);
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				if (tableName.equalsIgnoreCase(name)) return true;
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage()); }
		return false;
	}

	// use on logout, close
	public void closeConnection() {
		this.dbManager.closeConnection();
	}
}
