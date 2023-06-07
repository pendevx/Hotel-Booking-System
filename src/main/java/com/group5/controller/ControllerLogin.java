package com.group5.controller;

import com.group5.mvc.ViewGUI;
import com.group5.view.ViewLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLogin extends TestController implements ActionListener {
	public ViewLogin viewLogin;

	public ControllerLogin(ViewGUI view) {
		super(view);
		this.viewLogin = new ViewLogin(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardLogin() != null) {
			if (e.getSource() == getCardLogin().loginButton) loginHandler();
//			else if (e.getSource() == getCardLogin().registerButton) view.renderRegistration();
		}
	}

	private void loginHandler() {
		System.out.println("hi");
//		String usr = getCardLogin().usernameField.getText().toLowerCase();
//		String pwd = getCardLogin().passwordField.getText();
//		if(model.loginPortal(usr, pwd)) this.renderView();
//		else getCardLogin().showWarningPopup("Incorrect username or password!");
	}
}
