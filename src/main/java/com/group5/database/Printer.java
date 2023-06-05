package com.group5.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Printer {
	
	public static void printQuery(String query, ResultSet resultSet) {
		if (resultSet == null) return;
		try {
			if (!resultSet.next()) System.out.println("No results");
			else if (query.equals("account")) queryAccount(resultSet);
			else if (query.equals("credential")) queryCredentials(resultSet);
			else if (query.equals("username")) queryUsername(resultSet);
			else if (query.equals("hotel")) queryHotel(resultSet);
			resultSet.close();
		}
		catch (SQLException ex) { System.out.println(ex.getMessage());}
	}

	public static void queryAccount(ResultSet resultSet) throws SQLException {
		do {
			System.out.print(resultSet.getString("username") + ", ");
			System.out.print(resultSet.getString("firstname") + ", ");
			System.out.print(resultSet.getString("lastname") + ", ");
			System.out.print(resultSet.getString("phone") + ", ");
			System.out.print(resultSet.getString("email") + ", ");
			System.out.println(resultSet.getString("permission"));
		} while (resultSet.next());
	}

	public static void queryCredentials(ResultSet resultSet) throws SQLException {
		do {
			System.out.print(resultSet.getString("username") + ", ");
			System.out.println(resultSet.getString("password"));
		} while (resultSet.next());
	}

	public static void queryUsername(ResultSet resultSet) throws SQLException {
		do {
			System.out.println(resultSet.getString("username"));
		} while (resultSet.next());
	}

	public static void queryHotel(ResultSet resultSet) throws SQLException {
		do {
			System.out.print(resultSet.getString("hotelName") + ", ");
			System.out.print(resultSet.getString("street") + ", ");
			System.out.print(resultSet.getString("suburb") + ", ");
			System.out.print(resultSet.getString("city") + ", ");
			System.out.print(resultSet.getString("postcode") + ", ");
			System.out.print(resultSet.getString("country") + ", ");
			System.out.print(resultSet.getString("phone") + ", ");
			System.out.println(resultSet.getString("email"));
		} while (resultSet.next());
	}
}