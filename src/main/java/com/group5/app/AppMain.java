package com.group5.app;

import com.group5.controller.Controller;
import com.group5.controller.ControllerLogin;
import com.group5.view.ViewGUI;

public class AppMain {

    /***
     * Application entry point.
     */
	public static void main(String[] args) {
		AppMain mvc = new AppMain();
	}

	public AppMain() {
		ViewGUI view = new ViewGUI();
		Controller controller = new ControllerLogin(view, new AppSession());
	}
}