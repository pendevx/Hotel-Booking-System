package com.group5.card;

import com.group5.component.*;
import javax.swing.JButton;

public class CardAccount extends Card {
	public JButton editAccountButton = new Button("Edit", "#9399b2", 100, 25);
	public JButton logoutButton = new Button("Logout", "#94e2d5", 100, 25);
	
	public CardAccount(Container...accountInfo) {
		super(400, 800, 10, 20);
		final int W = this.WIDTH - 50;
		this.add(new Container(W, 300, new CardAccountPic(300, 300)));
		this.addAccountInfo(W, accountInfo);
		this.add(new Container(W, 50, editAccountButton, logoutButton));
	}

	private void addAccountInfo(int W, Container...accountInfo) {
		Container info = new Container(W, 300);
		info.add(new Container(W, 25));
		for (Container i : accountInfo) info.add(i);
		this.add(info);
	}
}
