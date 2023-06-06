package com.group5.module;

import com.group5.component.*;
import com.group5.component.Text.FontSize;

public class CardBookingListUser extends Card {
	
	public CardBookingListUser() {
		super(550, 800, 10, 20);
		final int W = this.WIDTH - 50;

		this.add(new Container(W, 50, new Text("Manage Bookings", FontSize.H1)));
		this.add(new Container(W, 690 ));
	}
}
