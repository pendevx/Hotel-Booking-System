package com.group5.mvc;

import com.group5.component.*;
import com.group5.card.CardAccount;
import com.group5.card.CardAccountEdit;
import com.group5.card.CardBookingCreateUser;
import com.group5.card.CardBookingListUser;
import com.group5.card.CardLogin;
import com.group5.card.CardRegister;
import com.group5.view.View;
import com.group5.view.ViewClient;
import com.group5.view.ViewClientUser;
import com.group5.view.ViewLogin;
import com.group5.view.ViewRegister;
import javax.swing.JFrame;

// DEV ONLY
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewGUI extends JFrame {
	// login view
	public Card cardLogin;

	// register view
	public Card cardRegister;

	// account view abstract
	public Card cardAccount;
	public Card cardAccountEdit;
	public Card cardBookingList;
	public Card cardBookingCreate;

	public ViewLogin loginView;
	public ViewRegister registerView;
	public ViewClient clientView;


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
		refreshFrame();
		this.loginView = new ViewLogin(this.controller);
		this.base = loginView.getBasePanel();
		renderPanel();
	}

	public void renderRegistration() {
		refreshFrame();
		this.registerView = new ViewRegister(controller);
		this.base = registerView.getBasePanel();
		renderPanel();
	}

	// split off these methods
	// into view
	// viewLogin extends View
	// viewRegister extends View
	// viewUser extends View
	// viewAdmin extends View


	public void renderUser(Container[] accountInfo, Container[] hotelInfo) {
		refreshFrame();
		this.clientView = new ViewClientUser(controller, accountInfo, hotelInfo);
		this.base = clientView.getBasePanel();
		renderPanel();

//		refreshFrame();
//		if (this.base != null) this.base.removeAll();
//		if (accountInfo.length > 0) this.cardAccount = new CardAccount(accountInfo);
//		((CardAccount) cardAccount).editAccountButton.addActionListener(controller);
//		((CardAccount) cardAccount).logoutButton.addActionListener(controller);
//
//		this.cardBookingCreate = new CardBookingCreateUser(hotelInfo);
//		
//		// button that makes a sql query with booking ID;
//		// booking is in Booking Continer, make new coniner per booking
//		// need make scrollable
//
//		// opens up to boooking view that only shows info
//
//
//		this.cardBookingList = new CardBookingListUser();
//		this.base.addWithGap(cardAccount);
//		this.base.addWithGap(cardBookingCreate);
//		this.base.addWithGap(cardBookingList);
//		this.renderPanel();
//
//
//		//////////////////////////////////////////////////
//		// TEST ONLY
//		//////////////////////////////////////////////////
//		((CardAccount) cardAccount).addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_ENTER) ((CardAccount) cardAccount).logoutButton.doClick();
//			}
//		});
//		((CardAccount) cardAccount).setFocusable(true);
//		((CardAccount) cardAccount).requestFocusInWindow();
//		//////////////////////////////////////////////////
//		// TEST ONLY
//		//////////////////////////////////////////////////
	}

	public void renderAdmin(Container...accountInfo) {
		refreshFrame();

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


	// client view
//	public void renderAccountEdit() {
//		if (this.base != null) this.base.removeAll();
//		this.cardAccountEdit = new CardAccountEdit();
//		((CardAccountEdit) cardAccountEdit).cancelButton.addActionListener(controller);
//		((CardAccountEdit) cardAccountEdit).saveButton.addActionListener(controller);
//		this.base.add(cardAccountEdit);
//		this.renderPanel();
//	}






	private void refreshFrame() {
		if (this.base != null) {
			this.getContentPane().removeAll();
			this.base = null;
		}
	}

	private void renderPanel() {
		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
