package com.group5.view;

import com.group5.card.*;
import com.group5.mvc.Controller;

public class ViewLogin extends View {
	public CardLogin cardLogin;

	public ViewLogin(Controller controller) {
		super(controller);
		init();
	}

	public void init() {
		super.resetBasePanel();
		this.cardLogin = new CardLogin(); // fresh new form
		this.cardLogin.loginButton.addActionListener(getController());
		this.cardLogin.registerButton.addActionListener(getController());
		super.addToBase(cardLogin);

//		//////////////////////////////////////////////////
//		// TEST ONLY
//		//////////////////////////////////////////////////
		cardLogin.usernameField.setText("user");
		cardLogin.passwordField.setText("user");
//		//////////////////////////////////////////////////
//		// TEST ONLY
//		//////////////////////////////////////////////////
	}

	public CardLogin getCard() {
		return this.cardLogin;
	}

}
