package com.group5.card;

import com.group5.component.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class CardAccountEdit extends Card implements KeyPressListener{

	public JButton cancelButton = new Button("Cancel", "#9399b2", 100, 25);
	public JButton saveButton = new Button("Save", "#94e2d5", 100, 25);
	public JTextField emailNew = new JTextField(30);
	public JTextField phoneNew = new JTextField(30);
	
	public CardAccountEdit() {
		super(400, 800, 10, 20);
		final int W = this.WIDTH - 50;
		final int MARGIN = 70;

		this.add(new Container(W, 300, new CardAccountPic(300, 300)));
		this.add(new Container(W, MARGIN, "New email:", emailNew));
		this.add(new Container(W, MARGIN, "New phone:", phoneNew));
		this.add(new Container(W, 50, cancelButton, saveButton));
		this.addEnterKeyListener(emailNew, phoneNew);
	}

	public void setTextEmail(String exsitingEmail) {
		emailNew.setText(exsitingEmail);
	}

	public void setTextPhone(String exsitingPhone) {
		phoneNew.setText(exsitingPhone);
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) saveButton.doClick();
				}
			});
		}
	}
}
