package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardLogin;
import com.group5.component.KeyPressListener;
import com.group5.view.ViewGUI;
import com.group5.view.ViewLogin;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class ControllerLogin extends Controller implements KeyPressListener {
	private ViewLogin loginView;

	/**
	 * Controller for login view
	 * @param view
	 * @param model 
	 */
	public ControllerLogin(ViewGUI view, AppSession model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		this.loginView = new ViewLogin(this);
		super.updateDisplay(loginView.getBasePanel()); // set display to show login
		addEnterKeyListener(loginView.getCardComponents()); // add key listener to components
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardLogin() != null) {
			if (e.getSource() == getCardLogin().loginButton) loginHandler();
			else if (e.getSource() == getCardLogin().registerButton) registerHandler();
		}
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) { // add "enter" key listener to components
					if(e.getKeyCode() == KeyEvent.VK_ENTER) getCardLogin().loginButton.doClick();
				}
			});
		}
	}

	/**
	 * Takes input from JComponenets and then passes throught model to check if valid.
	 * Logs user in if so.
	 */
	private void loginHandler() {
		String usr = getCardLogin().usernameField.getText().toLowerCase();
		String pwd = getCardLogin().passwordField.getText();
		if(getModel().loginPortal(usr, pwd)) super.renderClient();
		else getCardLogin().showWarningPopup("Incorrect username or password!");
	}

	/**
	 * Load the register view and controller
	 */
	private void registerHandler() {
		new ControllerRegister(getView(), getModel());
	}

	private CardLogin getCardLogin() {
		if (loginView == null) return null;
		else return loginView.getCard();
	}
}
