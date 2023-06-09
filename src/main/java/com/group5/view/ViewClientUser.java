package com.group5.view;

import com.group5.card.CardAccount;
import com.group5.card.CardBookingList;
import com.group5.card.CardBookingManageUser;
import com.group5.component.*;
import com.group5.controller.Controller;

public class ViewClientUser extends ViewClient {

	public CardBookingManageUser cardBookingManage;

	public ViewClientUser(Controller controller, Container[] accountInfo, Container[] hotelInfo) {
		super(controller);
		init(accountInfo, hotelInfo);
	}

	private void init(Container[] accountInfo, Container[] hotelInfo) {
		super.resetBasePanel();
		if (accountInfo.length > 0) setCardAccount(new CardAccount(accountInfo));
		getCardAccount().editAccountButton.addActionListener(getController());
		getCardAccount().logoutButton.addActionListener(getController());
		this.cardBookingManage = new CardBookingManageUser(hotelInfo);
		this.cardBookingManage.cancelButton.addActionListener(getController());
		this.cardBookingManage.bookButton.addActionListener(getController());
		setCardBookingManage(cardBookingManage);

//		setCardBookingManage(new CardBookingManageUser(hotelInfo));
		setCardBookingList(new CardBookingList());


		super.addToBaseWithGap(getCardAccount(), cardBookingManage, getCardBookingList());
//		getCardBookingManage()..addActionListener(getController());
//		setCardBookingManage(new CardBookingManageUser(hotelInfo));
//		super.addToBaseWithGap(getCardAccount(), getCardBookingManage(), getCardBookingList());
	}
}
