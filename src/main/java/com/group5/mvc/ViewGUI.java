package com.group5.mvc;

import com.group5.Components.Base;
import com.group5.module.CardLogin;
import com.group5.module.CardRegister;
import javax.swing.JFrame;

public class ViewGUI extends JFrame {
	public CardLogin cardLogin;
	public CardRegister cardRegister;
	private Base base = new Base();
	private Controller controller;

	public ViewGUI() {
		this.setName("Hotel Booking System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1600,900);
		this.setLocation(500,300);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addController(Controller controller) {
		this.controller = controller;
	}

	public void renderLogin() {
		if (base != null) base.removeAll();
		this.cardLogin = new CardLogin(); // fresh new form
		this.cardLogin.loginButton.addActionListener(controller);
		this.cardLogin.registerButton.addActionListener(controller);
		this.base.add(cardLogin);
		this.renderPanel();
	}

	public void renderRegistration() {
		if (base != null) base.removeAll();
		this.cardRegister = new CardRegister(); // so fresh new form
		this.cardRegister.cancelButton.addActionListener(controller);
		this.cardRegister.submitButton.addActionListener(controller);
		this.base.add(cardRegister);
		this.renderPanel();
	}

	public void renderUser() {
		if (base != null) base.removeAll();
		// logout AppSession null
		// Database close and null
		// HotelSytem close and null

		this.renderPanel();
		System.out.println("Rendering User");
	}

	public void renderAdmin() {
		if (base != null) base.removeAll();

		this.renderPanel();
		System.out.println("Rendering Admin");
	}

	private void renderPanel() {
		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
