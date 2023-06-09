package com.group5.card;

import com.group5.app.AppSession;
import com.group5.component.*;
import com.group5.component.Text.FontSize;
import com.group5.controller.Controller;
import com.group5.hotel.Booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CardBookingList extends Card {
	public CardBookingList(AppSession appModel) {
		// show booking for a user, reusing in admin view to filter bookings
		super(500);
		final int W = this.WIDTH - 50;

		this.add(new Container(W, 50, new Text("Manage Bookings", FontSize.H1)));

		String[] columnNames = { "Booking ID", "Start", "End", "Price", "Booker", "Manager" };
		List<Booking> bookings = appModel.hotelSystem.getAllBookings();
		Object[][] data = new Object[bookings.size()][];

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < bookings.size(); i++) {
			Booking booking = bookings.get(i);
			data[i] = new Object[] {
				booking.bookingID,
				booking.beginDate().toString().split(" ")[0],
				booking.endDate().toString().split(" ")[0],
				booking.totalPrice,
				booking.getAccount().getFullName(),
				booking.getBookingManager().getFullName()
			};
		}

		TableModel model = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int r, int c) { return false; }
		};

		JTable table = new JTable(model);
		JScrollPane scroller = new JScrollPane(table);

		add(new Container(W, 500, scroller));
	}
}
