package com.group5.mvc;

import com.group5.component.*;
import com.group5.module.CardLogin;
import com.group5.module.CardRegister;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewGUI extends JFrame {
	public Card cardLogin;
	public Card cardRegister;
	public Card cardAccount;
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
		this.cardLogin = new CardLogin(); // fresh new form
		((CardLogin) cardLogin).loginButton.addActionListener(controller);
		((CardLogin) cardLogin).registerButton.addActionListener(controller);
		this.renderPanel(cardLogin);
	}

	public void renderRegistration() {
		this.cardRegister = new CardRegister(); // so fresh new form
		((CardRegister) cardRegister).cancelButton.addActionListener(controller);
		((CardRegister) cardRegister).submitButton.addActionListener(controller);
		this.renderPanel(cardRegister);
	}

	public void renderUser() {
		
		System.out.println("Rendering User");
	}

	public void renderAdmin() {
		System.out.println("Rendering Admin");
	}

	private void renderPanel(JPanel p) {
		if (this.base != null) this.base.removeAll();
		this.base.add(p);
		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
