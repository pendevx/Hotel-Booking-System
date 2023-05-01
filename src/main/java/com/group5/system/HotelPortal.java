package com.group5.system;

import com.group5.account.Account;
import com.group5.account.AccountPermission;


public class HotelPortal {

	/***
	 * Takes an account and evaluates if it has user or admin permissions.
	 * 
	 * @param account - the account to check
	 * @return HotelSystem, either with user or admin level functions
	 */
    public HotelSystem login(Account account) {
        if (account.getAccountType() == AccountPermission.USER) {
            return new HotelSystemUser(account, HotelBookingData.getBookingsForAccount(account));
        }
		else return new HotelSystemAdmin(account, HotelBookingData.getBookings());
    }
}
