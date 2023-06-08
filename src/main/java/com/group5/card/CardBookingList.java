package com.group5.card;

import com.group5.component.*;
import com.group5.component.Text.FontSize;

public class CardBookingList extends Card {
	
	public CardBookingList() {
		// show booking for a user, resusing in admin view to filter bookings
		super(500);
		final int W = this.WIDTH - 50;

		this.add(new Container(W, 50, new Text("Manage Bookings", FontSize.H1)));
//		this.add(new Container(W, 690 ));
	}
}
