package com.group5.card;

import com.group5.component.Button;
import com.group5.component.Container;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CardBookingManageAdmin extends CardBookingManage {

	public JButton deleteButton = new Button("Delete", "#94e2d5", 100, 25);
	public JTextField bookingRef = new JTextField(30); // to be parsed
	

	public CardBookingManageAdmin(Container...hotelInfo) {
		super("Delete Booking" ,500, hotelInfo);
		final int MARGIN = 50;

		this.add(new Container(W, 30));
		this.add(new Container(W, MARGIN, "Booking Reference:", bookingRef));
		this.add(new Container(W, MARGIN, deleteButton));
	}
}
