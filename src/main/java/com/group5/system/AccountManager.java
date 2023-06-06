package com.group5.system;

import com.group5.hotel.Account;
import com.group5.hotel.AccountPermission;
import com.group5.hotel.Credential;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;
import static com.group5.system.HotelBookingData.*;

public class AccountManager {

	private AccountManager() { }

	public static boolean checkUsernameExists(String username) {
		// streams in credentials and then checks usernames
		Stream<String> usernames = getCredentials().stream().map(x -> x.getUsername());
		Optional<String> findMatches = usernames.filter(x -> x.equals(username)).findAny();
		return findMatches.isPresent();
	}

	public static Account createAccount(String username, String password, String fname, String lname, String phone, String email) {
		Account account = new Account(username, fname, lname, phone, email, AccountPermission.USER);
		Credential credentials = new Credential(username, password);
		register(credentials, account);

		return account;
	}

	public static HotelSystem login(String username, String password) {
		Credential providedCredentials = new Credential(username, password);
		HashSet<Credential> credentialsHS = new HashSet<Credential>(getCredentials());

		// First, determine whether there is a matching set of credentials
		Optional<Credential> match = credentialsHS.stream().filter(x -> x.equals(providedCredentials)).findFirst();

		// If no matching credentials were found, throw an exception
		if (!match.isPresent())
			return null;

		// This will only run if a pair of matching credentials were found.
		// Therefore, it is safe to filter through via the username parameter directly.
		Optional<Account> accountMatch = getAccounts().stream().filter(x -> x.username.equals(username)).findFirst();

		if (!accountMatch.isPresent()) {
			throw new RuntimeException("not found");
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
