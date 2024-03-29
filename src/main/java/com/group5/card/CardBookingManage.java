package com.group5.card;

import com.group5.component.*;
import com.group5.component.Text.FontSize;

public abstract class CardBookingManage extends Card {

	public final int W;

	/**
	 * Abstract class Panel for managing bookings.
	 * @param title of panel
	 * @param width of panel
	 * @param hotelInfo of hotel
	 */
	public CardBookingManage(String title, int width, Container...hotelInfo) {
		super(500);
		this.W = this.WIDTH - 50;
		this.add(new Container(W, 50, new Text(title, FontSize.H1)));
		this.add(new CardInfo(W, 125, hotelInfo));
	}
}
