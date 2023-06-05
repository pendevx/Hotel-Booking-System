package com.group5.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class Card extends JPanel implements KeyPressListener{
	public final int WIDTH;

	public Card(int width, int height, int hGap, int vGap) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, hGap, vGap));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		this.WIDTH = width;
		this.setPreferredSize(new Dimension(WIDTH, height));
	}

	public void showWarningPopup(String message) {
        JOptionPane.showMessageDialog(this, message, "Attention", JOptionPane.INFORMATION_MESSAGE);
    }
}
