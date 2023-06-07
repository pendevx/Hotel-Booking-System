package com.group5.mvc;

import com.group5.component.*;
import com.group5.view.ViewClient;
import com.group5.view.ViewClientAdmin;
import com.group5.view.ViewClientUser;
import com.group5.view.ViewLogin;
import com.group5.view.ViewRegister;
import javax.swing.JFrame;

public class ViewGUI extends JFrame {

	private ViewLogin loginView;
	private ViewRegister registerView;
	private ViewClient clientView;

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

	public void renderAccountEdit() {
		refreshFrame();
		this.clientView.renderAccountEdit();
		this.base = this.clientView.getBasePanel();
		renderPanel();
	}

	public void renderUser(Container[] accountInfo, Container[] hotelInfo) {
		refreshFrame();
		this.clientView = new ViewClientUser(controller, accountInfo, hotelInfo);
		this.base = clientView.getBasePanel();
		renderPanel();
	}

	public void renderAdmin(Container[] accountInfo, Container[] hotelInfo) {
		refreshFrame();
		this.clientView = new ViewClientAdmin(controller, accountInfo, hotelInfo);
		this.base = clientView.getBasePanel();
		renderPanel();
	}

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

	public ViewLogin getLoginView() { return this.loginView; }
	public ViewRegister getRegisetView() { return this.registerView; }
	public ViewClient getClientView() { return this.clientView; }
}
