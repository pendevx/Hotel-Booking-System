package com.group5.card;

import com.group5.component.Button;
import com.group5.component.Container;
import com.group5.component.Text;
import com.group5.component.Text.FontSize;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CardBookingManageUser extends CardBookingManage {
	public JButton cancelButton = new Button("Cancel", "#9399b2", 100, 25);
	public JButton bookButton = new Button("Book", "#94e2d5", 100, 25);
	public CardDatePicker startDate = new CardDatePicker(325, 200);
	public CardDatePicker endDate = new CardDatePicker(325, 200);
	public JTextField rooms = new JTextField(30); // to be parsed
	
	/**
	 * Creates the panel that a user will see, with create booking functionality.
	 * @param hotelInfo of hotel
	 */
	public CardBookingManageUser(Container...hotelInfo) {
		super("Create Booking", 500, hotelInfo);
		final int MARGIN = 50;
		this.add(new Container(W, 30));
		this.add(new Container(W, 60, "Start Date:", startDate));
		this.add(new Container(W, 60, "End Date:", endDate));
		this.add(new Container(W, MARGIN, "Rooms:", rooms));
		this.add(new Container(W, 20, new Text("Please choose your rooms for booking, e.g. 3F.", FontSize.H3)));
		this.add(new Container(W, 20, new Text("Room numbers range from (1-9 and (A-J).", FontSize.H3)));
		this.add(new Container(W, 20, new Text("Input in comma seperated line, e.g. 1A,1B,1C", FontSize.H3)));
		this.add(new Container(W, MARGIN, cancelButton, bookButton));
	}
}
