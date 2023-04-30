package com.group5.system;

import com.group5.account.*;
import java.util.List;


public class HotelPortal {

    public HotelSystem login(Account account) {
        if (account.getAccountType() == AccountPermission.USER) {
            return new HotelSystemUser(account, HotelBookingData.getBookingsForAccount(account));
        }
		else return new HotelSystemAdmin(account, HotelBookingData.getBookings());
    }
}
