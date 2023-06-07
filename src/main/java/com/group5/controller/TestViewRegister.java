package com.group5.controller;

import com.group5.card.*;
import com.group5.controller.*;

public class TestViewRegister extends TestView {
	public CardRegister cardRegister;
	
	public TestViewRegister(ControllerRegister controller) {
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
}
