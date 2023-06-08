package com.group5.view;

import com.group5.card.*;
import com.group5.component.*;
import com.group5.controller.Controller;

public abstract class ViewClient extends View {
	private CardAccount cardAccount;
	private Card cardBookingList;
	private Card cardBookingCreate;

	public ViewClient(Controller controller) {
		super(controller);
	}

	public CardAccount getCardAccount() { return this.cardAccount; }
//	public CardAccountEdit getCardAccountEdit() { return this.cardAccountEdit; }
	public Card getCardBookingList() { return this.cardBookingList; }
	public Card getCardBookingCreate() { return this.cardBookingCreate; }

	public void setCardAccount(CardAccount c) { this.cardAccount = c; }
	public void setCardBookingCreate(CardBookingCreate c) { this.cardBookingCreate = c; }
	public void setCardBookingList(CardBookingList c) { this.cardBookingList = c; }
}
