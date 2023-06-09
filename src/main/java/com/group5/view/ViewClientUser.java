package com.group5.view;

import com.group5.card.CardAccount;
import com.group5.card.CardBookingCreate;
import com.group5.card.CardBookingList;
import com.group5.component.*;
import com.group5.controller.Controller;

public class ViewClientUser extends ViewClient {

	public ViewClientUser(Controller controller, Container[] accountInfo, Container[] hotelInfo) {
		super(controller);
		init(accountInfo, hotelInfo);
	}

	private void init(Container[] accountInfo, Container[] hotelInfo) {
		super.resetBasePanel();
		if (accountInfo.length > 0) setCardAccount(new CardAccount(accountInfo));
		getCardAccount().editAccountButton.addActionListener(getController());
		getCardAccount().logoutButton.addActionListener(getController());
		setCardBookingCreate(new CardBookingCreate(hotelInfo));
		setCardBookingList(new CardBookingList(getController().getModel()));
		super.addToBaseWithGap(getCardAccount(), getCardBookingCreate(), getCardBookingList());
	}

}
