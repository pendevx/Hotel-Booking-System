package com.group5.card;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class CardAccountPic extends JPanel {
	
	/**
	 * Creates a JPanel that is a placeholder for account picture
	 * @param width, of pic
	 * @param height, of pic
	 */
	public CardAccountPic(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
	}
}
