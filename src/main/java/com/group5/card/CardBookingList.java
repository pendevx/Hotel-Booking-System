package com.group5.card;

import com.group5.component.*;
import com.group5.component.Text.FontSize;
import com.group5.hotel.Booking;
import com.group5.hotel.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CardBookingList extends Card {

	/**
	 * Create panel that will display bookings that client has access to.
	 * @param bookings 
	 */
	public CardBookingList(List<Booking> bookings) {
		super(500);
		final int W = this.WIDTH - 50;

		this.add(new Container(W, 50, new Text("Manage Bookings", FontSize.H1)));

		String[] columnNames = { "Booking ID", "Start", "End", "Price", "Rooms", "Booker", "Manager" };
		Object[][] data = new Object[bookings.size()][];

		for (int i = 0; i < bookings.size(); i++) {
			Booking booking = bookings.get(i);

			StringBuilder sb = new StringBuilder();
			List<Room> rooms = booking.getRooms();
			for (int j = 0; j < rooms.size(); j++) {
				sb.append(rooms.get(j).getRoomNumber());
				if (j != booking.getRooms().size() - 1)  sb.append(", ");
			}
            
            java.util.Date startDate = new java.util.Date(new java.sql.Date(booking.beginDate().getTime()).getTime());
            java.util.Date endDate = new java.util.Date(new java.sql.Date(booking.endDate().getTime()).getTime());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String start = formatter.format(startDate).split(" ")[0];
            String end = formatter.format(endDate).split(" ")[0];

			data[i] = new Object[] {
				booking.bookingID,
                start,
                end,
				booking.totalPrice,
				sb.toString(),
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
