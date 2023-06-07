package com.group5.card;

import com.group5.component.*;
import com.group5.component.Text.FontSize;

public class CardBookingCreateUser extends Card {

	public CardBookingCreateUser(Container...hotelInfo) {
		// takes user as input so create booking for user
		// extends this class for admin
		// show Booking of for...
		super(500);
		final int W = this.WIDTH - 50;

		this.add(new Container(W, 50, new Text("Make Booking", FontSize.H1)));
		this.add(new CardInfo(W, 125, hotelInfo));
//		this.addInfo(W, hotelInfo);
//		this.add(new CardInfo(W, hotelInfo));
//		this.add(new Container(W, 250 ));
//		this.add(new Container(W, 250 ));
	}
}
