package com.group5.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDatabase {
//	private DatabaseManager dbManager;
	public DatabaseManager dbManager; // testing only

	public HotelDatabase() {
		dbManager = new DatabaseManager();
	}

	public static void main(String[] args) {
		HotelDatabase h = new HotelDatabase();
		h.checkDuplicateTable("promotion");
		h.dbManager.update("CREATE TABLE PROMOTION (CATEGORY VARCHAR(20), DISCOUNT INT)");
h.dbManager.update("INSERT INTO PROMOTION VALUES ('Fiction', 0),\n"
                    + "('Non-fiction', 10),\n"
                    + "('Textbook', 20)");
	}

	public void checkDuplicateTable(String name) {
		try {
			DatabaseMetaData metadata = getConnection().getMetaData();
			String[] types = {"TABLE"};

			ResultSet resultSet = metadata.getTables(null, null, null, types);
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				if (tableName.equalsIgnoreCase(name)) {
					dbManager.update(SQL.dropTable(tableName));
					System.out.println("Duplicate table [" + name + "] dropped...");
					break;
				}
			}
			resultSet.close();
		}
		catch (SQLException ex) { System.out.println(ex.getMessage()); }
	}

	private Connection getConnection() {
		return this.dbManager.getConnection();
	}

	public void closeConnection() {
		this.dbManager.closeConnection();
	}
}
