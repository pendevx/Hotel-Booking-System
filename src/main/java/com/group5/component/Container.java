package com.group5.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Container extends JPanel {
	public Container(int width, int height) {
		this.setBackground(Color.RED);
		setPreferredSize(new Dimension(width, height));
	}

	public Container(int width, int height, JComponent...components) {
		this(width, height);
		for (JComponent c : components) this.add(c);
	}

	public Container(int width, int height, JComponent p, String label) {
		this(width, height);
		this.add(new JLabel(label));
		this.add(p);
	}

//	public EntryField(String label, JTextField field ,int width, int height) {
//		this.setLayout(new FlowLayout(FlowLayout.CENTER));
//		this.setPreferredSize(new Dimension(width, height));
//		this.setBackground(Color.WHITE);
//		this.add(new JLabel(label));
//		this.add(field);
//	}
}
