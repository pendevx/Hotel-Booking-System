package com.group5.controller;

import java.awt.event.ActionEvent;

public class ControllerRegister extends Controller {
	public TestViewRegister register;
	
	public ControllerRegister(TestViewGUI view) {
		super(view);
		init();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	protected void init() {
//		System.out.println("Register");
		this.register = new TestViewRegister(this);
		this.view.updateDisplay(register.getBasePanel());
	}
	
}
