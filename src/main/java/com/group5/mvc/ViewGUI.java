package com.group5.mvc;

import com.group5.component.*;
import com.group5.card.CardAccount;
import com.group5.card.CardAccountEdit;
import com.group5.card.CardBookingCreateUser;
import com.group5.card.CardBookingListUser;
import com.group5.card.CardLogin;
import com.group5.card.CardRegister;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		this.setSize(1366,768);
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

		//////////////////////////////////////////////////
		// TEST ONLY
		//////////////////////////////////////////////////
		((CardLogin) cardLogin).usernameField.setText("user");
		((CardLogin) cardLogin).passwordField.setText("user");

		((CardLogin) cardLogin).addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) ((CardLogin) cardLogin).loginButton.doClick();
			}
		});
		((CardLogin) cardLogin).setFocusable(true);
		((CardLogin) cardLogin).requestFocusInWindow();
		//////////////////////////////////////////////////
		// TEST ONLY
		//////////////////////////////////////////////////
		
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

		this.cardBookingCreate = new CardBookingCreateUser();
		
		// button that makes a sql query with booking ID;
		// booking is in Booking Continer, make new coniner per booking
		// need make scrollable

		// opens up to boooking view that only shows info


		this.cardBookingList = new CardBookingListUser();
		this.base.addWithGap(cardAccount);
		this.base.addWithGap(cardBookingCreate);
		this.base.addWithGap(cardBookingList);
		this.renderPanel();


		//////////////////////////////////////////////////
		// TEST ONLY
		//////////////////////////////////////////////////
		((CardAccount) cardAccount).addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) ((CardAccount) cardAccount).logoutButton.doClick();
			}
		});
		((CardAccount) cardAccount).setFocusable(true);
		((CardAccount) cardAccount).requestFocusInWindow();
		//////////////////////////////////////////////////
		// TEST ONLY
		//////////////////////////////////////////////////
	}

	public void renderAdmin(Container...accountInfo) {
		if (this.base != null) this.base.removeAll();
		if (accountInfo.length > 0) this.cardAccount = new CardAccount(accountInfo);
		((CardAccount) cardAccount).editAccountButton.addActionListener(controller);
		((CardAccount) cardAccount).logoutButton.addActionListener(controller);
//		this.cardBookingList = new CardBookingListAdmin();
//		this.cardBookingCreate = new CardBookingCreateAdmin();
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

	private void renderPanel() {
		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
