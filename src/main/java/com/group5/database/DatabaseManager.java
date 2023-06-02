package com.group5.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseManager {
	private static final String dbName = "hotel_ebd";
	private static final String URL = "jdbc:derby:" + dbName + "; create=true";
	private static final String USERNAME = "hotel";
	private static final String PASSWORD = "hotel";

	Connection connection;

	public DatabaseManager() {
		establishConnection();
	}

	public static void main(String[] args) {
		DatabaseManager t = new DatabaseManager();
	}

	public void establishConnection() {
		if (this.connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("Connected: " + dbName);
			}
			catch (SQLException ex) {
				System.out.println("Unable to connect: " + dbName + "\n" + ex.getMessage());
			}
		}
	}

	public void closeConnection() {
		if (connection != null) {
			try { connection.close(); }
			catch (SQLException ex) { System.out.println(ex.getMessage()); }
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public ResultSet queryDB(String sql) {
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		}
		catch (SQLException ex) { System.out.println(ex.getMessage()); }

		return resultSet;
	}

	public void updateDB(String sql) {
		Statement statement = null;

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		}
		catch (SQLException ex) { System.out.println(ex.getMessage()); }

	}
}
