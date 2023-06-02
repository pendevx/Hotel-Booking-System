package com.group5.database;

import static com.group5.database.Printer.printQuery;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class HotelDatabase {
	private static DatabaseManager dbManager;

	public HotelDatabase() {
		dbManager = new DatabaseManager();
		this.createTable("account", SQL.createAccountTable());
		this.createTable("credential", SQL.createCredentialTable());
		this.createTable("username", SQL.createUsernameTable());

		printQuery("account", dbManager.query(SQL.selectAll("account")));
		printQuery("credential", dbManager.query(SQL.selectAll("credential")));
		printQuery("username", dbManager.query(SQL.selectAll("username")));
	}

	public static void main(String[] args) {
		HotelDatabase t = new HotelDatabase();
	}

	public static Set<String> loadUsername() {
		Set<String> usernames = new HashSet<>();
		ResultSet resultSet = dbManager.query(SQL.selectAll("username"));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				do {
					usernames.add(resultSet.getString("username"));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return usernames;
	}

	private void createTable(String name, String[] sql) {
		if (!hasDuplicate(name)) this.dbManager.updateBatch(sql);
	}

	private boolean hasDuplicate(String name) {
		try {
			DatabaseMetaData metadata = getConnection().getMetaData();
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

	private Connection getConnection() { return this.dbManager.getConnection(); }

	// use on logout, close
	private void closeConnection() { this.dbManager.closeConnection(); }
}
