package com.group5.Components;

import java.awt.Color;
import javax.swing.JButton;

public class Button extends JButton {

	public Button(String name, String color) {
		super(name);
		this.setBackground(Color.decode(color));
		this.setBorderPainted(false);
	}
}
