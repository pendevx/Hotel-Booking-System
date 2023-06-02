package com.group5.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDatabase {
	private DatabaseManager dbManager;

	public HotelDatabase() {
		dbManager = new DatabaseManager();
		this.createTable("account", SQL.createAccountTable());
		this.createTable("credential", SQL.createCredentialTable());
		this.createTable("username", SQL.createUsernameTable());

		Printer.printQuery("account", dbManager.query("SELECT * FROM account"));
		Printer.printQuery("credential", dbManager.query("SELECT * FROM credential"));
		Printer.printQuery("username", dbManager.query("SELECT * FROM username"));
	}

	public static void main(String[] args) {
		HotelDatabase t = new HotelDatabase();
	}

	private void createTable(String name, String[] sql) {
		if (!hasDuplicate(name)) this.dbManager.updateBatch(sql);
	}

	private boolean hasDuplicate(String name) {
		try {
			DatabaseMetaData metadata = getConnection().getMetaData();
			String[] types = {"TABLE"};
			ResultSet resultSet = metadata.getTables(null, null, null, types);
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				if (tableName.equalsIgnoreCase(name)) return true;
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage()); }
		return false;
	}

	private Connection getConnection() {
		return this.dbManager.getConnection();
	}

	private void closeConnection() {
		this.dbManager.closeConnection();
	}
}
