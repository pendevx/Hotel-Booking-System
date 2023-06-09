package com.group5.component;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class Button extends JButton {

	/**
	 * Creates a button with a name and colour.
	 * @param name to display
	 * @param color to display
	 */
	public Button(String name, String color) {
		super(name);
		this.setBackground(Color.decode(color));
		this.setBorderPainted(false);
	}

	public Button(String name, String color, int width, int height) {
		this(name, color);
		this.setPreferredSize(new Dimension(width, height));
	}
}
