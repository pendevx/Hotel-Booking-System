package com.group5.Panel;

import com.group5.Components.Button;
import com.group5.Components.Card;
import com.group5.Components.EntryField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelRegistration extends Card {
	public JButton cancelButton = new Button("Cancel", "#9399b2");
	public JButton submitButton = new Button("Submit", "#94e2d5");
	public JTextField userFieldNew = new JTextField(40);
	public JTextField passFieldNew = new JPasswordField(40);
	public JTextField firstNameNew = new JTextField(40);
	public JTextField lastNameNew = new JTextField(40);
	public JTextField emailNew = new JTextField(40);
	public JTextField phoneNew = new JTextField(40);
	private final int MARGIN = 70;

	public PanelRegistration() {
		super(500, 500, 10, 5);
		this.add(new EntryField("New Username:", userFieldNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("New Password:", passFieldNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("First name:", firstNameNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("Last name:", lastNameNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("Email:", emailNew, WIDTH - 50, MARGIN));
		this.add(new EntryField("Phone:", phoneNew, WIDTH - 50, MARGIN));
		this.add(cancelButton);
		this.add(submitButton);
		// checks the username here
		// need popup warning if not correct
	}
}
