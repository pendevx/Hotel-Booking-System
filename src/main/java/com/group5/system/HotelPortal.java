package com.group5.system;

import com.group5.account.*;
import java.util.List;


public class HotelPortal {

    public HotelPortal() {
        BookingData.init();
    }

    public HotelSystem login(Account account) {
        if (account.getAccountType() == AccountPermission.USER) {
            return new HotelSystemUser(account, BookingData.getBookingsForAccount(account));
        }
		else return new HotelSystemAdmin(account, BookingData.getBookings());
    }
}
