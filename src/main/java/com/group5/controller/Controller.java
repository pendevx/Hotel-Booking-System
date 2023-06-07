package com.group5.controller;

import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
	public TestViewGUI view;

	public Controller(TestViewGUI view) {
		this.view = view;
	}

	protected abstract void init();
}