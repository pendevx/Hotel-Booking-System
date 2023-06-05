package com.group5.module;

import com.group5.component.*;
import javax.swing.JButton;
import javax.swing.JComponent;

public class CardAccount extends Card {
	public JButton logoutButton = new Button("Logout", "#9399b2");
	
	public CardAccount() {
		super(400, 700, 10, 20);
		this.add(logoutButton);
//		this.add(new EntryField("Username:", usernameField, WIDTH - 50, MARGIN));
//		this.add(new EntryField("Password:", passwordField, WIDTH - 50, MARGIN));
//		this.add(registerButton);
	}
}
