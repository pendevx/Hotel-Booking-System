package com.group5.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

public class Base extends JPanel{
	public Base() {
		this.setBackground(Color.decode("#1e1e2e"));
		this.setLayout(new GridBagLayout());
	}

	public void addWithGap(JPanel p) {
		GridBagConstraints constraints = new GridBagConstraints();
		int edge = 5;
		int cap = 0;
		constraints.insets = new Insets(cap, edge, cap, edge);
		this.add(p, constraints);
	}
}
