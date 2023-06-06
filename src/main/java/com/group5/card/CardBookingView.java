package com.group5.card;

import com.group5.component.*;
import com.group5.component.Text.FontSize;

public class CardBookingView extends Card {

	public CardBookingView() {
		super(550, 800, 10, 20);
		final int W = this.WIDTH - 50;

		// load booking content
		this.add(new Container(W, 50, new Text("Manage Bookings", FontSize.H1)));
		this.add(new Container(W, 690 ));
	}
}
