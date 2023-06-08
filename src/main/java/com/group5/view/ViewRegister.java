package com.group5.view;

import com.group5.card.CardRegister;
import com.group5.component.GetCardComponent;
import com.group5.controller.Controller;
import javax.swing.JComponent;

public class ViewRegister extends View implements GetCardComponent {
	private CardRegister cardRegister;
	
	public ViewRegister(Controller controller) {
		super(controller);
		init();
	}

	private void init() {
		super.resetBasePanel();
		this.cardRegister = new CardRegister(); // so fresh new form
		this.cardRegister.cancelButton.addActionListener(getController());
		this.cardRegister.submitButton.addActionListener(getController());
		super.addToBase(cardRegister);
	}

	@Override
	public JComponent[] getCardComponents() {
		JComponent[] components = new JComponent[6];
		components[0] = cardRegister.userFieldNew;
		components[1] = cardRegister.passFieldNew;
		components[2] = cardRegister.firstNameNew;
		components[3] = cardRegister.lastNameNew;
		components[4] = cardRegister.emailNew;
		components[5] = cardRegister.phoneNew;
		return components;
	}

	public CardRegister getCard() { return this.cardRegister; }
}
