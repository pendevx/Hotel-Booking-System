package com.group5.view;

import com.group5.card.CardAccount;
import com.group5.card.CardAccountEdit;
import com.group5.card.CardBookingCreateUser;
import com.group5.card.CardBookingListUser;
import com.group5.component.*;
import com.group5.mvc.Controller;

public class ViewClientUser extends ViewClient {

	public ViewClientUser(Controller controller, Container[] accountInfo, Container[] hotelInfo) {
		super(controller);
		init(accountInfo, hotelInfo);
	}

	public void init(Container[] accountInfo, Container[] hotelInfo) {
		super.resetBasePanel();
		if (accountInfo.length > 0) super.cardAccount = new CardAccount(accountInfo);
		super.cardAccount.editAccountButton.addActionListener(getController());
		super.cardAccount.logoutButton.addActionListener(getController());

		super.cardBookingCreate = new CardBookingCreateUser(hotelInfo);
		super.cardBookingList = new CardBookingListUser();

		super.addToBaseWithGap(cardAccount, cardBookingCreate, cardBookingList);
	}
}
