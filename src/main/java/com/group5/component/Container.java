package com.group5.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Container extends JPanel {
	public Container(int width, int height) {
		this.setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width, height));
	}

	public Container(int align, int width, int height, JComponent...components) {
		this(width, height);
		this.setLayout(new FlowLayout(align));
		for (JComponent c : components) this.add(c);
	}

	public Container(int width, int height, JComponent...components) {
		this(width, height);
		for (JComponent c : components) this.add(c);
	}

	public Container(int width, int height, String label) {
		this(width, height);
		this.add(new JLabel(label));
	}

	public Container(int width, int height, String label, JComponent component) {
		this(width, height, label);
		this.add(component);
	}
}