package com.group5.view;

import com.group5.component.*;
import javax.swing.JFrame;

public class ViewGUI extends JFrame {

	private Base base = new Base();

	/**
	 * JFrame that will hold the panels
	 */
	public ViewGUI() {
		this.setName("Hotel Booking System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366,768);
		this.setLocation(500,300);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Updates the Base(Panel) with new panel from relevant view
	 * @param newBase 
	 */
	public void updateDisplay(Base newBase) {
		if (newBase == null) return;
		if (this.base != null) this.getContentPane().removeAll();
		this.base = newBase;
		this.add(this.base);
		this.revalidate();
		this.repaint();
	}
}
