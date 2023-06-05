package com.group5.mvc;

import com.group5.Components.Base;
import com.group5.Panel.PanelLogin;
import com.group5.Panel.PanelRegistration;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewGUI extends JFrame {
	public PanelLogin panelLogin = new PanelLogin();
	public PanelRegistration panelRegistration = new PanelRegistration();
	public Base base = new Base();

	public ViewGUI() {
		this.setName("Hotel Booking System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1600,900);
		this.setLocation(500,300);
		this.setResizable(false);
		this.setVisible(true);
		this.renderLogin();
	}

	public void addController(Controller controller) {
		this.panelLogin.loginButton.addActionListener(controller);
		this.panelLogin.registerButton.addActionListener(controller);
		this.panelRegistration.cancelButton.addActionListener(controller);
		this.panelRegistration.submitButton.addActionListener(controller);
	}

	public void renderLogin() {
		if (base != null) base.removeAll();
		this.base.add(panelLogin);
		this.renderPanel();
	}

	public void renderRegistration() {
		if (base != null) base.removeAll();
		this.base.add(panelRegistration);
		this.renderPanel();
	}

	public void renderUser() {
	}

	public void renderAdmin() {
	}

	private void renderPanel() {
		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
