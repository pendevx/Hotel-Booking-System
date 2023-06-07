package com.group5.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLogin extends Controller {
	public TestViewLogin login;

	// master holds the view abd ni

	public ControllerLogin(TestViewGUI view) {
		super(view);
		init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == login.cardLogin.loginButton) System.out.println("Login");
		// new ControllerRegister()
		if (e.getSource() == login.cardLogin.loginButton) {
			ControllerRegister r = new ControllerRegister(view);

		}
	}

	@Override
	protected void init() {
		this.login = new TestViewLogin(this);
		this.view.updateDisplay(login.getBasePanel());
	}
}
