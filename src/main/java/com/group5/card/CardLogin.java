package com.group5.card;

import com.group5.component.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CardLogin extends Card implements KeyPressListener {
	public JButton loginButton = new Button("Login", "#94e2d5", 100, 25);
	public JButton registerButton = new Button("Register", "#9399b2", 100, 25);
	public JTextField usernameField = new JTextField(30);
	public JTextField passwordField = new JPasswordField(30);

	public CardLogin() {
		super(500, 500, 10, 20);
		final int MARGIN = 70;
		final int W = super.WIDTH - 120;

		this.add(new Container(W, 100));
		this.add(new Container(W, MARGIN, "Username:", usernameField));
		this.add(new Container(W, MARGIN, "Password:", passwordField));
		this.add(new Container(W, 40, loginButton, registerButton));
		this.addEnterKeyListener(usernameField, passwordField);
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) loginButton.doClick();
				}
			});
		}
	}
}
