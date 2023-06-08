package com.group5.card;

import com.group5.component.*;
import javax.swing.JButton;

public class CardAccount extends Card {
	public JButton editAccountButton = new Button("Edit", "#9399b2", 100, 25);
	public JButton logoutButton = new Button("Logout", "#94e2d5", 100, 25);
	
	public CardAccount(Container[] accountInfo) {
		super(300);
		final int W = this.WIDTH - 50;
		this.add(new Container(W, 300, new CardAccountPic(250, 250)));
		this.add(new CardInfo(W, 250, accountInfo));
		this.add(new Container(W, 50, editAccountButton, logoutButton));
	}
}
