package com.group5.app;

import java.util.Scanner;

public class AppMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AppSession session = new AppSession();
		session.sessionMenu(scan);
		scan.close();
	}
}