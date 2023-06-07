package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardRegister;
import com.group5.component.Base;
import com.group5.view.ViewGUI;
import com.group5.view.ViewRegister;
import java.awt.event.ActionEvent;

public class ControllerRegister extends Controller {

	private ViewRegister registerView;

	public ControllerRegister(ViewGUI view, AppSession model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		this.registerView = new ViewRegister(this);
		this.view.updateDisplay(registerView.getBasePanel());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardRegister()!= null) {
			if (e.getSource() == getCardRegister().cancelButton) cancelHandler();
			else if (e.getSource() == getCardRegister().submitButton) submitHandler();
		}
	}

	private void cancelHandler() {
		view.updateDisplay(new ControllerLogin(view, getModel()).getLoginPanel());
	}

	private void submitHandler() {
		System.out.println("submitted");
	}

	public Base getRegisterPanel() {
		return this.registerView.getBasePanel();
	}

	private CardRegister getCardRegister() {
		if (registerView == null) return null;
		else return registerView.getCard();
	}
}
