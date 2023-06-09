package com.group5.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class Card extends JPanel {
	public final int WIDTH;
	public final int HEIGHT = 700;

	/**
	 * Extends JPanel, acts as default styling for Card panels int .card
	 * 
	 * @param width  of panel
	 */
	public Card(int width) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		this.WIDTH = width;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public Card(int width, int height) {
		this(width);
		this.setPreferredSize(new Dimension(WIDTH, height));
	}

	/**
	 * Method to show warning popup.
	 * @param message to display
	 */
	public void showWarningPopup(String message) {
        JOptionPane.showMessageDialog(this, message, "Attention", JOptionPane.INFORMATION_MESSAGE);
    }
}
