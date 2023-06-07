package com.group5.card;

import com.group5.component.*;
import javax.swing.JButton;

public class CardAccount extends Card {
	public JButton editAccountButton = new Button("Edit", "#9399b2", 100, 25);
	public JButton logoutButton = new Button("Logout", "#94e2d5", 100, 25);
	
	public CardAccount(Container...accountInfo) {
		super(300);
		final int W = this.WIDTH - 50;
		this.add(new Container(W, 250, new CardAccountPic(250, 250)));
		this.addAccountInfo(W, accountInfo);
		this.add(new Container(W, 50, editAccountButton, logoutButton));
	}

	private void addAccountInfo(int W, Container...accountInfo) {
		Container info = new Container(W, 250);
		info.add(new Container(W, 20));
		for (Container i : accountInfo) info.add(i);
		this.add(info);
	}
}
