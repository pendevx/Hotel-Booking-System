package com.group5.view;

import com.group5.card.*;
import com.group5.component.*;
import com.group5.controller.Controller;
import com.group5.hotel.Booking;
import java.util.List;

public abstract class ViewClient extends View {
	private CardAccount cardAccount;
	private CardBookingList cardBookingList;
	public CardBookingManage cardBookingManage;

	public ViewClient(Controller controller) {
		super(controller);
	}

	public CardAccount getCardAccount() { return this.cardAccount; }
	public CardBookingList getCardBookingList() { return this.cardBookingList; }
	public Card getCardBookingManage() { return this.cardBookingManage; }

	public void setCardAccount(CardAccount c) { this.cardAccount = c; }
	public void setCardBookingManage(CardBookingManage c) { this.cardBookingManage = c; }
	public void setCardBookingList(CardBookingList c) { this.cardBookingList = c; }

	public void refreshBookingList(List<Booking> updateBooking) {
		setCardBookingList(new CardBookingList(updateBooking));
	}
}
