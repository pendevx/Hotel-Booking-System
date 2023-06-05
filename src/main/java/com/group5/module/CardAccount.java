package com.group5.module;

import com.group5.component.*;
import javax.swing.JComponent;

public class CardAccount extends Card {
	
	public CardAccount() {
		super(500, 500, 10, 20);
//		this.add(new EntryField("Username:", usernameField, WIDTH - 50, MARGIN));
//		this.add(new EntryField("Password:", passwordField, WIDTH - 50, MARGIN));
//		this.add(loginButton);
//		this.add(registerButton);
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
