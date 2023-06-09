package com.group5.view;

import com.group5.card.CardAccount;
import com.group5.card.CardBookingList;
import com.group5.card.CardBookingManageAdmin;
import com.group5.component.*;
import com.group5.controller.Controller;
import com.group5.hotel.Booking;
import java.util.List;

public class ViewClientAdmin extends ViewClient {
	
	/**
	 * View for admin client
	 * @param controller, controller
	 * @param accountInfo, container with admin account info
	 * @param hotelInfo, container with hotel info
	 * @param bookings, all bookings in system
	 */
	public ViewClientAdmin(Controller controller, Container[] accountInfo, Container[] hotelInfo, List<Booking> bookings) {
		super(controller);
		init(accountInfo, hotelInfo, bookings);
	}

	private void init(Container[] accountInfo, Container[] hotelInfo, List<Booking> bookings) {
		super.resetBasePanel();
		if (accountInfo.length > 0) setCardAccount(new CardAccount(accountInfo)); // create accountCard
		getCardAccount().editAccountButton.addActionListener(getController()); // add controller
		getCardAccount().logoutButton.addActionListener(getController()); // add controller
		setCardBookingManage(new CardBookingManageAdmin(hotelInfo)); // booking manager for admin
		setCardBookingList(new CardBookingList(bookings)); // card displays all bookings
		super.addToBaseWithGap(getCardAccount(), getCardBookingManage(), getCardBookingList()); // add to base with gaps
	}
}
