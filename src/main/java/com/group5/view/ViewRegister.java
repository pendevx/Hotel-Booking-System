package com.group5.view;

import com.group5.card.CardRegister;
import com.group5.mvc.Controller;

public class ViewRegister extends View {
	public CardRegister cardRegister;
	
	public ViewRegister(Controller controller) {
		super(controller);
		init();
	}

	public void init() {
		super.resetBasePanel();
		this.cardRegister = new CardRegister(); // so fresh new form
		this.cardRegister.cancelButton.addActionListener(getController());
		this.cardRegister.submitButton.addActionListener(getController());
		super.addToBase(cardRegister);
	}

	public CardRegister getCard() {
		return this.cardRegister;
	}
}
