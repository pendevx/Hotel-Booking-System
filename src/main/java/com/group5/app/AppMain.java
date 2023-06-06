package com.group5.app;

import com.group5.mvc.Controller;
import com.group5.mvc.ViewGUI;

public class AppMain {

    /***
     * Application entry point.
     */
	public static void main(String[] args) {
		AppMain mvc = new AppMain();
	}

	public AppMain() {
		ViewGUI view = new ViewGUI();
		Controller controller = new Controller(view);
		view.addController(controller);
		view.renderLogin();
//		view.renderUser();
	}
}