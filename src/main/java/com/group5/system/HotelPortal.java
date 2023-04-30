package com.group5.system;

import com.group5.account.*;


public class HotelPortal {

    public HotelPortal() {
        BookingData.init();
    }

    public HotelSystem login(Account account) {
        if (account.getAccountType() == AccountPermission.USER) {
			System.out.println("user");
			return null;
//            List<Booking> bookingsForAccount = BookingData.getBookingsForAccount(account);
//            return new HotelSystemUser(account, bookingsForAccount);
//            return new HotelSystemUser(account, BookingData.getBookingsForAccount(account));
        }
		else return new HotelSystemAdmin(account, BookingData.getBookings());
    }
}
