package com.group5.controller;

import com.group5.app.AppSession;
import com.group5.card.CardAccountEdit;
import com.group5.component.Base;
import com.group5.component.KeyPressListener;
import com.group5.view.ViewAccountEdit;
import com.group5.view.ViewGUI;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class ControllerAccountEdit extends Controller implements KeyPressListener {

	private ViewAccountEdit accountEditView;

	public ControllerAccountEdit(ViewGUI view, AppSession model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		this.accountEditView = new ViewAccountEdit(this);
		getCardAccountEdit().setTextEmail(getAccount().email);
		getCardAccountEdit().setTextPhone(getAccount().phone);
		super.updateDisplay(accountEditView.getBasePanel());
		addEnterKeyListener(accountEditView.getCardComponents());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	if (getCardAccountEdit()!= null) {
		if (e.getSource() == getCardAccountEdit().cancelButton) super.renderClient();
		else if (e.getSource() == getCardAccountEdit().saveButton) saveHandler();
		}
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) getCardAccountEdit().saveButton.doClick();
				}
			});
		}
	}

	private void saveHandler() {
		String newEmail = getCardAccountEdit().emailNew.getText();
		String newPhone = getCardAccountEdit().phoneNew.getText();
		if (!hasEmptyField(getCardAccountEdit(), newEmail, newPhone)) {
			getModel().hotelSystem.updateEmail(newEmail);
			getModel().hotelSystem.updatePhone(newPhone);
			super.renderClient();
			System.out.println("Details updated.");
		}
	}

	private CardAccountEdit getCardAccountEdit() {
		if (accountEditView == null) return null;
		else return accountEditView.getCard();
	}
}
