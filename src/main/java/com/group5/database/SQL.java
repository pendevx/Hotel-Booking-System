package com.group5.database;

import com.group5.hotel.Booking;

public class SQL {
	//////////////////////////////////////////////////
	// CREATE TABLE
	//////////////////////////////////////////////////

	// need add input limits
	static String[] createHotelTable() {
		return new String[] 
		{
			"CREATE TABLE Hotel ( "
				+ "hotelName VARCHAR(50),"
				+ "street VARCHAR(50),"
				+ "suburb VARCHAR(50),"
				+ "city VARCHAR(50),"
				+ "postcode VARCHAR(5),"
				+ "country VARCHAR(50),"
				+ "phone VARCHAR(50),"
				+ "email VARCHAR(50)"
				+ ")",

			"INSERT INTO Hotel VALUES "
			+ "('Queens Hotel', '10 Main Street', 'CBD', 'Auckland', '1010', 'New Zealand', '+64090888', 'hotel@hotel.com')"
		};
	}

	static String[] createCredentialTable() {
		return new String[]
		{
			"CREATE TABLE Credentials (\n" +
			"    username VARCHAR(20),\n" +
			"    password VARCHAR(20) NOT NULL,\n" +
			"    PRIMARY KEY (username)\n" +
			")",

			"INSERT INTO Credentials VALUES "
			+ "('admin', 'admin'),"
			+ "('user', 'user')"
		};
	}

	static String[] createAccountTable() {
		return new String[]
		{
			"CREATE TABLE Accounts (\n" +
			"    username VARCHAR(20),\n" +
			"    firstName VARCHAR(30) NOT NULL,\n" +
			"    lastName VARCHAR(30) NOT NULL,\n" +
			"    phone CHAR(12),\n" +
			"    email VARCHAR(60) NOT NULL,\n" +
			"    permissions VARCHAR(10) NOT NULL,\n" +
			"    PRIMARY KEY (username),\n" +
			"    FOREIGN KEY (username) REFERENCES Credentials(username)\n" +
			")",

			"INSERT INTO Accounts VALUES "
			+ "('admin', 'admin', 'admin', '+21153324', 'admin@hotel.com', 'ADMIN'),"
			+ "('user', 'user', 'user', '+21152297', 'group5@comp603.com', 'USER')"
		};
	}

	static String[] createBookingTable() {
		return new String[]
		{
			"CREATE TABLE Bookings (\n" +
			"    bookingID VARCHAR(22),\n" +
			"    startDate DATE NOT NULL,\n" +
			"    endDate DATE NOT NULL,\n" +
			"    price REAL NOT NULL,\n" +
			"    booker VARCHAR(20) NOT NULL,\n" +
			"    manager VARCHAR(20) NOT NULL,\n" +
			"\n" +
			"    PRIMARY KEY (bookingID),\n" +
			"    FOREIGN KEY (booker) REFERENCES Accounts(username),\n" +
			"    FOREIGN KEY (manager) REFERENCES Accounts(username)\n" +
			")"
		};
	}

	static String[] createRoomsTable() {
		return new String[] {
			"CREATE TABLE Rooms (\n" +
			"    bookingID VARCHAR(22) NOT NULL UNIQUE,\n" +
			"    room CHAR(2),\n" +
			"\n" +
			"    FOREIGN KEY (bookingID) REFERENCES Bookings(bookingID)\n" +
			")"
		};
	}


	//////////////////////////////////////////////////
	// INSERT TABLE
	//////////////////////////////////////////////////
	static String insertAccountTable(String username, String firstname, String lastname, 
			String phone, String email, String permission) {
		return "INSERT INTO account VALUES "
			+ "('"+username+"', '"+firstname+"', '"+lastname+"', '"+phone+"', '"+email+"', '"+permission+"')";
	}

	static String insertCredentialTable(String username, String password) {
		return "INSERT INTO credential VALUES "
				+ "('" + username + "', '" + password + "')";
	}

	static String insertBookingTable(Booking booking) {
		double price = booking.getRooms().size() * 100;
		return
			"INSERT INTO Bookings VALUES ('" +
				booking.bookingID + "','" +
				new java.sql.Date(booking.beginDate().getTime()) + "','" +
				new java.sql.Date(booking.endDate().getTime()) + "','" +
				price + "','" +
				false + "','" +
				booking.getAccount().username + "','" +
				booking.getBookingManager() + ";";
	}

	//////////////////////////////////////////////////
	// UPDATE TABLE
	//////////////////////////////////////////////////
	static String updateAccountEmail(String username, String newEmail) {
		return "UPDATE account "
				+ "SET email = '" + newEmail + "' "
				+ "WHERE username = '" + username + "'";
	}

	static String updateAccountPhone(String username, String newPhone) {
		return "UPDATE account "
				+ "SET email = '" + newPhone + "' "
				+ "WHERE username = '" + username + "'";
	}

	//////////////////////////////////////////////////
	// SELECT TABLE
	//////////////////////////////////////////////////
	static String selectAll(String name) {
		return "SELECT * FROM " + name;
	}

	//////////////////////////////////////////////////
	// DROP TABLE
	//////////////////////////////////////////////////
	static String dropTable(String name) {
		return "DROP TABLE " + name;
	}
}
