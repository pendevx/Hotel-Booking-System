package com.group5.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class Card extends JPanel {
	public final int WIDTH;

	public Card(int width, int height, int hGap, int vGap) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, hGap, vGap));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		this.WIDTH = width;
		this.setPreferredSize(new Dimension(WIDTH, height));
	}
}
