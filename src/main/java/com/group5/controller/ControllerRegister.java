package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardRegister;
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
		super.updateDisplay(registerView.getBasePanel());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardRegister()!= null) {
			if (e.getSource() == getCardRegister().cancelButton) cancelHandler();
			else if (e.getSource() == getCardRegister().submitButton) submitHandler();
		}
	}

	private void cancelHandler() {
		new ControllerLogin(getView(), getModel());
	}

	private void submitHandler() {
		String usr = getCardRegister().userFieldNew.getText().toLowerCase();
		String pwd = getCardRegister().passFieldNew.getText();
		String fname = getCardRegister().firstNameNew.getText();
		String lname = getCardRegister().lastNameNew.getText();
		String email = getCardRegister().emailNew.getText().toLowerCase();
		String phone = getCardRegister().phoneNew.getText();

		if (!hasEmptyField(getCardRegister(), usr, pwd, fname, lname, email, phone)) {
			if (!getModel().checkUserNameExists(usr)) {
				getModel().registerPortal(usr, pwd, fname, lname, email, phone);
				if(getModel().loginPortal(usr, pwd)) super.renderClient();
				else getCardRegister().showWarningPopup("Incorrect username or password!");
			}
			else getCardRegister().showWarningPopup("Username is taken.");
		}
	}

	private CardRegister getCardRegister() {
		if (registerView == null) return null;
		else return registerView.getCard();
	}
}
