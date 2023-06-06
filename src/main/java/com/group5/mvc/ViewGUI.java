package com.group5.mvc;

import com.group5.component.*;
import com.group5.module.CardAccount;
import com.group5.module.CardAccountEdit;
import com.group5.module.CardBookingCreateUser;
import com.group5.module.CardBookingListUser;
import com.group5.module.CardLogin;
import com.group5.module.CardRegister;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewGUI extends JFrame {
	public Card cardLogin;

	public Card cardRegister;

	public Card cardAccount;
	public Card cardAccountEdit;

	public Card cardBookingList;
	public Card cardBookingCreate;

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
		if (this.base != null) this.base.removeAll();
		this.cardLogin = new CardLogin(); // fresh new form
		((CardLogin) cardLogin).loginButton.addActionListener(controller);
		((CardLogin) cardLogin).registerButton.addActionListener(controller);
		this.base.add(cardLogin);
		this.renderPanel();
	}

	public void renderRegistration() {
		if (this.base != null) this.base.removeAll();
		this.cardRegister = new CardRegister(); // so fresh new form
		((CardRegister) cardRegister).cancelButton.addActionListener(controller);
		((CardRegister) cardRegister).submitButton.addActionListener(controller);
		this.base.add(cardRegister);
		this.renderPanel();
	}

	public void renderUser(Container...accountInfo) {
		if (this.base != null) this.base.removeAll();
		if (accountInfo.length > 0) this.cardAccount = new CardAccount(accountInfo);
		((CardAccount) cardAccount).editAccountButton.addActionListener(controller);
		((CardAccount) cardAccount).logoutButton.addActionListener(controller);

//		this.cardBookingList = new CardBookingListUser();
//		this.cardBookingCreate = new CardBookingCreateUser();
		this.base.addWithGap(cardAccount);
//		this.base.addWithGap(cardBookingCreate);
//		this.base.addWithGap(cardBookingList);
		this.renderPanel();
	}

	public void renderAccountEdit() {
		if (this.base != null) this.base.removeAll();
		this.cardAccountEdit = new CardAccountEdit();
		((CardAccountEdit) cardAccountEdit).cancelButton.addActionListener(controller);
		((CardAccountEdit) cardAccountEdit).saveButton.addActionListener(controller);
		this.base.add(cardAccountEdit);
		this.renderPanel();
	}

	public void renderAdmin() {
		System.out.println("Rendering Admin");
	}

	private void renderPanel() {
		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
