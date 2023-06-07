package com.group5.controller;

import com.group5.component.Base;


public class TestMain {
	public static void main(String[] args) {
		TestMain mvcTest = new TestMain();
	}

	public TestMain() {
		TestViewGUI view = new TestViewGUI();
		Controller c = new ControllerLogin(view);
	}
}
