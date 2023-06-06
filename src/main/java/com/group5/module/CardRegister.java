package com.group5.module;

import com.group5.component.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CardRegister extends Card implements KeyPressListener {
	public JButton cancelButton = new Button("Cancel", "#9399b2");
	public JButton submitButton = new Button("Submit", "#94e2d5");
	public JTextField userFieldNew = new JTextField(30);
	public JTextField passFieldNew = new JPasswordField(30);
	public JTextField firstNameNew = new JTextField(30);
	public JTextField lastNameNew = new JTextField(30);
	public JTextField emailNew = new JTextField(30);
	public JTextField phoneNew = new JTextField(30);
	private final int MARGIN = 70;

	public CardRegister() {
		super(500, 500, 10, 5);
		this.add(new Container(WIDTH - 120, MARGIN, userFieldNew, "New Username:"));
		this.add(new Container(WIDTH - 120, MARGIN, passFieldNew, "New Username:"));
		this.add(new Container(WIDTH - 120, MARGIN, firstNameNew, "First name:"));
		this.add(new Container(WIDTH - 120, MARGIN, lastNameNew, "Last name:"));
		this.add(new Container(WIDTH - 120, MARGIN, emailNew, "Email address:"));
		this.add(new Container(WIDTH - 120, MARGIN, phoneNew, "Phone number:"));
		this.add(new Container(WIDTH - 120, 40, cancelButton, submitButton));
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
