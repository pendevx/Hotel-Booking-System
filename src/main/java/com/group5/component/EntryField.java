package com.group5.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryField extends JPanel {

	public EntryField(String label, JTextField field ,int width, int height) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.WHITE);
		this.add(new JLabel(label));
		this.add(field);
	}
}
