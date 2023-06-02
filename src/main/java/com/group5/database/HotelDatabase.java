package com.group5.database;

import java.sql.Connection;
import java.sql.Statement;

public class HotelDatabase {
	private DatabaseManager dbManager;
	private Connection connection;
	private Statement statement;

	public HotelDatabase() {
		dbManager = new DatabaseManager();
		connection = dbManager.getConnection();
	}
	
}
