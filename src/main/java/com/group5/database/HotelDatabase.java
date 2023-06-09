package com.group5.database;

import com.group5.exceptions.BookingNotFoundException;
import com.group5.hotel.*;

import java.awt.print.Book;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HotelDatabase {
	private static DatabaseManager dbManager;
	private static List<Account> cacheAccounts;

	public HotelDatabase() {
		dbManager = new DatabaseManager();
		createTable("hotel", SQL.createHotelTable());
		createTable("credentials", SQL.createCredentialTable());
		createTable("accounts", SQL.createAccountTable());
		createTable("bookings", SQL.createBookingTable());
		createTable("rooms", SQL.createRoomsTable());
//		loadBookings();

//		Account acc = new Account("user", null, null, null, null, AccountPermission.USER);
//		List<Room> roomsBooked = new LinkedList<Room>();
//		roomsBooked.add(new Room(1, 'A'));
//		roomsBooked.add(new Room(1, 'B'));
//		Booking booking = new Booking("1", new java.util.Date(), new java.util.Date(), roomsBooked, roomsBooked.size()*100, acc, acc);

//		try {
//			for (String command : SQL.insertBookingTable(booking)) {
//				dbManager.update(command);
//			}
//		} catch (Exception e) {
//
//		}
//
//		Printer.printQuery("hotel", dbManager.query(SQL.selectAll("hotel"))); // for testing
//		Printer.printQuery("credentials", dbManager.query(SQL.selectAll("credentials"))); // for testing
//		Printer.printQuery("accounts", dbManager.query(SQL.selectAll("accounts"))); // for testing
//		// TODO
		Printer.printQuery("bookings", dbManager.query(SQL.selectAll("bookings"))); // for testing
//		System.out.println(tableExists("bookings"));
		Printer.printQuery("rooms", dbManager.query(SQL.selectAll("rooms")));
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
		String tableName = "credentials";
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
		String tableName = "accounts";
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
					String permission = resultSet.getString("permissions");

					AccountPermission accountPermission = null;
					if (permission.equalsIgnoreCase("ADMIN")) accountPermission = AccountPermission.ADMIN;
					else accountPermission = AccountPermission.USER;

					accounts.add(new Account(username, firstName, lastName, phone, email, accountPermission));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		cacheAccounts = accounts;
		return accounts;
	}

	private static Map<String, List<Room>> getRooms() {
		String tableName = "rooms";
		if (!tableExists(tableName)) return null;

		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				do {
					String bookingID = resultSet.getString("bookingID");
					String room = resultSet.getString("room");
					Room roomObj = new Room(room.charAt(0) - '0', room.charAt(1));

					BookingRooms.addRoom(bookingID, roomObj);
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return BookingRooms.rooms;
	}

	public static List<Booking> loadBookings() {
		Map<String, List<Room>> bookingRooms = getRooms();
		String tableName = "bookings";
		if (!tableExists(tableName)) return null;

		List<Booking> bookings = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				bookings = new ArrayList<>();
				do {
					String bookingID = resultSet.getString("bookingID");
					java.util.Date startDate = resultSet.getTimestamp("startDate");
					java.util.Date endDate = resultSet.getTimestamp("endDate");
					float price = resultSet.getFloat("price");
					String booker = resultSet.getString("booker");
					String manager = resultSet.getString("manager");

					List<Room> currentRooms = bookingRooms.get(bookingID);
					Optional<Account> bookerAcc = cacheAccounts.stream().filter(x -> x.getUsername().equals(booker)).findFirst();
 					Optional<Account> managerAcc = cacheAccounts.stream().filter(x -> x.getUsername().equals(manager)).findFirst();

					Booking booking = new Booking(bookingID, startDate, endDate, currentRooms, price, bookerAcc.get(), managerAcc.get());
					bookings.add(booking);
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}

		cacheAccounts = null;
		if (bookings == null) bookings = new ArrayList<>();
		return bookings;
	}

	public static void insertCredentialTable(Credential credential) {
		new Thread(() -> {
			try {
				dbManager.update(SQL.insertCredentialTable(credential));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

	public static void insertAccountTable(Account account) {
		new Thread(() -> {
			try {
				dbManager.update(SQL.insertAccountTable(account));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).start();
	}


	public static void insertBookingTable(Booking booking) {
		new Thread(() -> {
			try {
				for (String command : SQL.insertBookingTable(booking)) {
					dbManager.update(command);
				}
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
		try {
			dbManager.update(SQL.updateAccountPhone(username, newPhone));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void updateAccountEmail(Account account) {
		String username = account.getUsername();
		String newEmail = account.getEmail().toLowerCase();

		try {
			dbManager.update(SQL.updateAccountEmail(username, newEmail));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void deleteBooking(Booking booking) throws BookingNotFoundException {
		try {
			ResultSet result = dbManager.query("SELECT * FROM Bookings WHERE bookingID = '" + booking + "'");
			dbManager.update(SQL.deleteBooking(booking.bookingID));

			if (!result.next()) {
				throw new BookingNotFoundException("The booking ID does not exist.");
			}
		} catch (SQLException e) {
			throw new BookingNotFoundException("The booking ID does not exist.");
		}
	}

	private static void createTable(String name, String...sql) {
		try {
			if (!tableExists(name)) dbManager.updateBatch(sql);
		} catch (SQLException e) {
			System.out.println("Table " + name + " exists.");
		}
	}

	private static boolean tableExists(String name) {
		try {
			DatabaseMetaData metadata = dbManager.getConnection().getMetaData();
			ResultSet resultSet = metadata.getTables(null, null, null, null);

			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");

				if (tableName.equalsIgnoreCase(name))
					return true;
			}
			resultSet.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}

	// use on logout, close
	public void closeConnection() {
		this.dbManager.closeConnection();
	}
}
