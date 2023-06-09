package com.group5.view;

import com.group5.card.CardAccount;
import com.group5.card.CardBookingList;
import com.group5.card.CardBookingManageAdmin;
import com.group5.component.*;
import com.group5.controller.Controller;

public class ViewClientAdmin extends ViewClient {
	
	public ViewClientAdmin(Controller controller, Container[] accountInfo, Container[] hotelInfo) {
		super(controller);
		init(accountInfo, hotelInfo);
	}

	private void init(Container[] accountInfo, Container[] hotelInfo) {
		super.resetBasePanel();
		if (accountInfo.length > 0) setCardAccount(new CardAccount(accountInfo));
		getCardAccount().editAccountButton.addActionListener(getController());
		getCardAccount().logoutButton.addActionListener(getController());
		setCardBookingManage(new CardBookingManageAdmin(hotelInfo));
		setCardBookingList(new CardBookingList());
		super.addToBaseWithGap(getCardAccount(), getCardBookingManage(), getCardBookingList());
	}
	
}
