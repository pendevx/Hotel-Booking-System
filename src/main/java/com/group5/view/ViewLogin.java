package com.group5.view;

import com.group5.card.CardLogin;
import com.group5.component.GetCardComponent;
import com.group5.controller.Controller;
import javax.swing.JComponent;

public class ViewLogin extends View implements GetCardComponent {
	private CardLogin cardLogin;

	/**
	 * Login view
	 * @param controller 
	 */
	public ViewLogin(Controller controller) {
		super(controller);
		init();
	}

	private void init() {
		super.resetBasePanel();
		this.cardLogin = new CardLogin(); // fresh new form
		this.cardLogin.loginButton.addActionListener(getController());
		this.cardLogin.registerButton.addActionListener(getController());
		super.addToBase(cardLogin);
	}

	@Override
	public JComponent[] getCardComponents() {
		JComponent[] components = new JComponent[2];
		components[0] = cardLogin.usernameField;
		components[1] = cardLogin.passwordField;
		return components;
	}

	public CardLogin getCard() { return this.cardLogin; }
}
