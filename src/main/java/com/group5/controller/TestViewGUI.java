package com.group5.controller;

import com.group5.component.*;
import javax.swing.JFrame;

public class TestViewGUI extends JFrame {

	private Base base = new Base();

	public TestViewGUI() {
		this.setName("Hotel Booking System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366,768);
		this.setLocation(500,300);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void updateDisplay(Base base) {
		if (this.base != null) {
			this.getContentPane().removeAll();
			this.base = null;
		}

		// code here
		this.base = base;
		// code here

		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
