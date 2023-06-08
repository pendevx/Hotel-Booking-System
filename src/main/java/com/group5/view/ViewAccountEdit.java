package com.group5.view;

import com.group5.card.CardAccountEdit;
import com.group5.component.GetCardComponent;
import com.group5.controller.Controller;
import javax.swing.JComponent;

public class ViewAccountEdit extends View implements GetCardComponent {
	private CardAccountEdit cardAccountEdit;

	public ViewAccountEdit(Controller controller) {
		super(controller);
		init();
	}

	private void init() {
		super.resetBasePanel();
		this.cardAccountEdit = new CardAccountEdit();
		cardAccountEdit.cancelButton.addActionListener(getController());
		cardAccountEdit.saveButton.addActionListener(getController());
		addToBase(cardAccountEdit);
	}

	@Override
	public JComponent[] getCardComponents() {
		JComponent[] components = new JComponent[2];
		components[0] = cardAccountEdit.emailNew;
		components[1] = cardAccountEdit.phoneNew;
		return components;
	}
	
	public CardAccountEdit getCard() { return this.cardAccountEdit; }
}
