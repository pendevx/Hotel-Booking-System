package com.group5.controller;

import com.group5.mvc.ViewGUI;

public class TestMain {
	public static void main(String[] args) {
		TestMain mvcTest = new TestMain();
	}

	public TestMain() {
		ViewGUI view = new ViewGUI();
		TestController controller = new TestController(view) {};
//		view.addController(controller);
		view.renderLogin();
//		view.renderUser();
//		view.renderRegistration();
	}
	
}
