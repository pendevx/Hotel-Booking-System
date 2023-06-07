package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.*;
import com.group5.hotel.Account;
import com.group5.hotel.Hotel;
import com.group5.mvc.ViewGUI;

public abstract class TestController {
	public ViewGUI view;
	public AppSession model;

	public TestController(ViewGUI view) {
		this.view = view;
		this.model = new AppSession();
	}

	public CardLogin getCardLogin() {
		if (view.getLoginView() == null) return null;
		else return view.getLoginView().getCard();
	}

	public CardRegister getCardRegister() {
		if (view.getRegisetView() == null) return null;
		else return view.getRegisetView().getCard();
	}

	public CardAccount getCardAccount() {
		if (this.view.getClientView() == null) return null;
		else return view.getClientView().getCardAccount();
	}

	public CardAccountEdit getCardAccountEdit() {
		if (view.getClientView() == null) return null;
		return view.getClientView().getCardAccountEdit();
	}

	public Account getAccount() { return model.hotelSystem.getAccount(); }
	public Hotel getHotel() { return model.hotelSystem.getHotel(); }
}
