package com.group5.module;

import com.group5.Components.Button;
import com.group5.Components.Card;
import com.group5.Components.EntryField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CardLogin extends Card {
	public JButton loginButton = new Button("Login", "#94e2d5");
	public JButton registerButton = new Button("Register", "#9399b2");
	public JTextField usernameField = new JTextField(40);
	public JTextField passwordField = new JPasswordField(40);
	private final int MARGIN = 70;

	public CardLogin() {
		super(500, 500, 10, 20);
		this.add(new EntryField("Username:", usernameField, WIDTH - 50, MARGIN));
		this.add(new EntryField("Password:", passwordField, WIDTH - 50, MARGIN));
		this.add(loginButton);
		this.add(registerButton);
	}
}
