package com.group5.system;

import com.group5.hotel.Account;
import com.group5.hotel.AccountPermission;
import com.group5.hotel.Credential;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class AccountLogin {
	static Set<Credential> credentials;
	static List<Account> accounts;

	private AccountLogin() { }

	static {
		credentials = FileIO.loadCredentialsJson();
		accounts = FileIO.loadAccountsJson();
	}

	/**
	 * Takes in a username and password, and returns a set of functionality based on the matching account's permissions
	 * @param username The username of the account
	 * @param password The password of the account
	 * @return A system containing the account and a set of functionality for the matching account
	 */
	public static HotelSystem login(String username, String password) {
		Credential providedCredentials = new Credential(username, password);
		HashSet<Credential> credentialsHS = new HashSet<Credential>(credentials);

		// First, determine whether there is a matching set of credentials
		Optional<Credential> match = credentialsHS.stream().filter(x -> x.equals(providedCredentials)).findFirst();

		// If no matching credentials were found, throw an exception
		if (!match.isPresent())
			return null;

		// This will only run if a pair of matching credentials were found.
		// Therefore, it is safe to filter through via the username parameter directly.
		Optional<Account> accountMatch = accounts.stream().filter(x -> x.username.equals(username)).findFirst();

		if (!accountMatch.isPresent()) {
			System.out.println("error");
			return null;
		}

		Account account = accountMatch.get();

		if (account.getAccountType() == AccountPermission.ADMIN) {
			return new HotelSystemAdmin(account, HotelBookingData.getBookings());
		} else if (account.getAccountType() == AccountPermission.USER) {
			return new HotelSystemUser(account, HotelBookingData.getBookingsWhere(x -> x.getAccount().equals(account)));
		}

		throw new RuntimeException("not found");
	}
}
