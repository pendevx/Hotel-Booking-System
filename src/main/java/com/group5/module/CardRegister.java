package com.group5.module;

import com.group5.component.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CardRegister extends Card {
	public JButton cancelButton = new Button("Cancel", "#9399b2");
	public JButton submitButton = new Button("Submit", "#94e2d5");
	public JTextField userFieldNew = new JTextField(40);
	public JTextField passFieldNew = new JPasswordField(40);
	public JTextField firstNameNew = new JTextField(40);
	public JTextField lastNameNew = new JTextField(40);
	public JTextField emailNew = new JTextField(40);
	public JTextField phoneNew = new JTextField(40);
	private final int MARGIN = 70;

	public CardRegister() {
		super(500, 500, 10, 5);
		this.add(new EntryField("New Username:", userFieldNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("New Password:", passFieldNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("First name:", firstNameNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("Last name:", lastNameNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("Email:", emailNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("Phone:", phoneNew, WIDTH - 50, MARGIN));
		this.add(cancelButton);
		this.add(submitButton);
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
