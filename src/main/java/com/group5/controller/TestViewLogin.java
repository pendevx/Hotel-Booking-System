
package com.group5.controller;

import com.group5.card.*;
import com.group5.controller.*;

public class TestViewLogin extends TestView {
	public CardLogin cardLogin;

	public TestViewLogin(ControllerLogin controller) {
		super(controller);
		init();
	}

	private void init() {
		super.resetBasePanel();
		this.cardLogin = new CardLogin(); // fresh new form
		this.cardLogin.loginButton.addActionListener(getController());
		super.addToBase(cardLogin);
	}
}
