/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group5.card;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author coriandar
 */
public class ScrollTest {
	public static void main(String[] args) {
		        // Create a JFrame
        JFrame frame = new JFrame("Scrollable Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel with a layout
        JPanel panel = new JPanel(new GridLayout(0, 1));

        // Create a scrollable panel
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add content to the panel
        for (int i = 1; i <= 20; i++) {
            panel.add(new JLabel("Label " + i));
        }

        // Add the scrollable panel to the frame
        frame.add(scrollPane);

        // Display the frame
        frame.setVisible(true);
	}
	
}
