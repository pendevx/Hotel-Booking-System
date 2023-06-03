package com.group5.database;

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
		this.createTable("account", SQL.createAccountTable());
		this.createTable("credential", SQL.createCredentialTable());
		this.createTable("username", SQL.createUsernameTable());
		this.createTable("hotel", SQL.createHotelTable());

//		Printer.printQuery("account", dbManager.query(SQL.selectAll("account")));
//		Printer.printQuery("credential", dbManager.query(SQL.selectAll("credential")));
//		Printer.printQuery("username", dbManager.query(SQL.selectAll("username")));
//		Printer.printQuery("hotel", dbManager.query(SQL.selectAll("hotel")));
	}

	public static void main(String[] args) {
		HotelDatabase t = new HotelDatabase();
		t.loadHotel();
	}

	public static List<Hotel> loadHotel() {
		if (!tableExists("hotel")) return null;

		List<Hotel> hotelList = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll("hotel"));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				hotelList = new ArrayList<>();
				do {
					String hotelName = resultSet.getString("hotelName");
					String street = resultSet.getString("street");
					String suburb = resultSet.getString("suburb");
					String city = resultSet.getString("city");
					String postcode = resultSet.getString("postcode");
					String country = resultSet.getString("country");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email");

					hotelList.add(new Hotel(hotelName, street, suburb, city, postcode, country, phone, email));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return hotelList;
	}

	private void createTable(String name, String[] sql) {
		if (!tableExists(name)) this.dbManager.updateBatch(sql);
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
	private void closeConnection() { this.dbManager.closeConnection(); }
}
