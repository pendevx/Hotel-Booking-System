package com.group5.system;

import com.group5.hotel.Account;
import com.group5.hotel.Credential;
import com.group5.hotel.AccountPermission;
import com.group5.util.ParseInput;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Scanner;
import java.util.stream.Stream;

public class AccountNew {
	private static Set<Credential> credentialsList;
	private static List<Account> accountList;
	private static Set<String> usernamesList;

	/***
	 * Constructor that load in the json files for credentials and accounts, and username list.
	 */
	static {
		credentialsList = FileIO.loadCredentialsJson();
		accountList = FileIO.loadAccountsJson();
		usernamesList = FileIO.loadUsernameJson();
	}

	public static boolean checkUsernameExists(String username) {
		Stream<String> usernames = credentialsList.stream().map(x -> x.getUsername());
		Optional<String> findMatches = usernames.filter(x -> x.equals(username)).findAny();
		return findMatches.isPresent();
	}

	public static Account createAccount(String username, String password, String fname, String lname, String phone, String email) {
		Account account = new Account(username, fname, lname, phone, email, AccountPermission.USER);
		credentialsList.add(new Credential(username, password));
		AccountLogin.credentials.add(new Credential(username, password));
		AccountLogin.accounts.add(account);

		return account;
	}
}