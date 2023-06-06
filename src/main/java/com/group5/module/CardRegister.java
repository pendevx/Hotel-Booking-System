package com.group5.module;

import com.group5.component.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CardRegister extends Card implements KeyPressListener {
	public JButton cancelButton = new Button("Cancel", "#9399b2", 100, 25);
	public JButton submitButton = new Button("Submit", "#94e2d5", 100, 25);
	public JTextField userFieldNew = new JTextField(30);
	public JTextField passFieldNew = new JPasswordField(30);
	public JTextField firstNameNew = new JTextField(30);
	public JTextField lastNameNew = new JTextField(30);
	public JTextField emailNew = new JTextField(30);
	public JTextField phoneNew = new JTextField(30);

	public CardRegister() {
		super(500, 500, 10, 5);
		final int MARGIN = 70;
		final int W = super.WIDTH - 120;

		this.add(new Container(W, MARGIN, "New Username:", userFieldNew));
		this.add(new Container(W, MARGIN, "New Password:", passFieldNew));
		this.add(new Container(W, MARGIN, "First name:", firstNameNew));
		this.add(new Container(W, MARGIN, "Last name:", lastNameNew));
		this.add(new Container(W, MARGIN, "Email address:", emailNew));
		this.add(new Container(W, MARGIN, "Phone number:", phoneNew));
		this.add(new Container(W, 40, cancelButton, submitButton));
		this.addEnterKeyListener(userFieldNew, passFieldNew, firstNameNew, lastNameNew, emailNew, phoneNew);
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) submitButton.doClick();
				}
			});
		}
	}
}
