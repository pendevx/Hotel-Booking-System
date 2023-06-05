package com.group5.app;

import com.group5.mvc.Controller;
import com.group5.mvc.ViewGUI;
import java.util.Scanner;

public class AppMain {

    /***
     * Application entry point.
     */
	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		AppSession session = new AppSession();
//		session.sessionMenu(scan);
//		scan.close();
		AppMain mvc = new AppMain();
	}

	public AppMain() {
		ViewGUI view = new ViewGUI();
		AppSession model = new AppSession();
		Controller controller = new Controller(view, model);
		view.addController(controller);
	}
}