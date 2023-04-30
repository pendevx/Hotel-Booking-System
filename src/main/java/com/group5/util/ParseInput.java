package com.group5.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ParseInput {
	
	public static String string(Scanner scan) {
		return scan.nextLine().trim();
	}

	public static int integer(Scanner scan) {
		int input = -1;
		do {
			try { input = Integer.parseInt(scan.nextLine()); }
 			catch (NumberFormatException e) { System.out.println("Please enter a valid option."); }
		} while(input < 0);
		return input;
	}

	public static int integer(int low, int max, Scanner scan) {
		int input = -1;
		do {
			try {
				input = Integer.parseInt(scan.nextLine());
				if (!(low <= input && input <= max)) System.out.println("Please enter a valid option.");
			}
			catch (NumberFormatException e) { System.out.println("Please enter a valid option."); }
		} while (!(low <= input && input <= max));
		return input;
	}
	
	public static Date date(Scanner scan) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		formatter.setLenient(false);
		Date output = null;
		do {
			try { output = formatter.parse(string(scan)); }
			catch (ParseException e) { System.out.println("Invalid date, please enter again."); }
		} while (output == null);
		return output;
	}
}
