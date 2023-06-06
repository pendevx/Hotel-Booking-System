package com.group5.module;

import com.group5.component.*;
import javax.swing.JButton;

public class CardAccount extends Card {
	public JButton logoutButton = new Button("Logout", "#9399b2");
	public JButton editAccountButton = new Button("Edit", "#9399b2");
	
	public CardAccount() {
		super(400, 800, 10, 20);
		this.add(new Container(350, 300, new CardAccountPic(300, 300)));
		this.add(new Container(350, 50, editAccountButton));
		this.add(new Container(350, 50, logoutButton));

//		Container editPanel = new Container(350, 50, editAccountButton);
//		this.add(editPanel);
//		JPanel buttonPanel = new JPanel();
//		buttonPanel
//		buttonPanel.add(logoutButton);
//		this.add(buttonPanel);
		// load from database
//		this.add(new EntryField("Username:", usernameField, WIDTH - 50, MARGIN));
//		this.add(new EntryField("Password:", passwordField, WIDTH - 50, MARGIN));
//		this.add(registerButton);
	}
}
