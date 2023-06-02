package com.group5.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Printer {
	
	public static void printAccount(ResultSet resultSet) {
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				do {
					System.out.print(resultSet.getString("username") + ", ");
					System.out.print(resultSet.getString("firstname") + ", ");
					System.out.print(resultSet.getString("lastname") + ", ");
					System.out.print(resultSet.getString("phone") + ", ");
					System.out.print(resultSet.getString("email") + ", ");
					System.out.println(resultSet.getString("permission"));
				} while (resultSet.next());
			}
			resultSet.close();
		}
		catch (SQLException ex) { System.out.println(ex.getMessage());}
	}
}
