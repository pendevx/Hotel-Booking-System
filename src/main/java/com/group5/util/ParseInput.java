package com.group5.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ParseInput {
	// add limitier of 50char
	
    /***
     * Static function to parse and trim strings
     * 
     * @param scan - input nextLine()
     * @return string that is trim()
     */
	public static String string(Scanner scan) {
		return scan.nextLine().trim();
	}

    /***
     * Static function to parse integer from input, handling
     * invalid integer
     * 
     * @param scan - input integer
     * @return an integer
     */
	public static int integer(Scanner scan) {
		int input = -1;
		do {
			try { input = Integer.parseInt(scan.nextLine()); }
 			catch (NumberFormatException e) { System.out.println("Please enter a valid option."); }
		} while(input < 0);
		return input;
	}

    /***
     * Static function to parse integer from input, between 
     * a range low - max, handling invalid integer and out
     * of range values
     * 
     * @param scan - input integer
     * @return a valid integer
     */
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
	
    /***
     * Static function to parse a valid date input,
     * following predefined format.
     * 
     * @param scan - input date
     * @return a valid date
     */
//	public static Date date(Scanner scan) {
	public static Date date(String date) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		formatter.setLenient(false);
		Date output = null;
		try {
			output = formatter.parse(date);
		}
		catch (ParseException e) { System.out.println("Invalid date, please enter again."); }
		return output;
	}
}
