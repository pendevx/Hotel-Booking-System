package com.group5.system;

import com.group5.account.Account;
import com.group5.account.AccountCredentials;
import com.group5.account.AccountPermission;
import com.group5.util.ParseInput;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

public class AccountNew {
	private List<AccountCredentials> credentialsList;
	private List<Account> accountList;
	private Set<String> usernamesList;

	/***
	 * Constructor that load in the json files for credentials and accounts, and username list.
	 */
	public AccountNew() {
		this.credentialsList = FileIO.loadCredentialsJson();
		this.accountList = FileIO.loadAccountsJson();
		this.usernamesList = FileIO.loadUsernameJson();
	}

	/***
	 * Creates a user with a unique username, account details are
	 * entered when prompted. Credentials, account, and username are
	 * written to json.
	 * 
	 * @param scan - user input
	 * @return 
	 */
	public Account createUser(Scanner scan) {
		String username = getUniqueUser(scan); // only gets String if username is unique
		Account newAccount = null;

		if (!username.isEmpty()) {
			System.out.println("Enter new password: ");
			String password = ParseInput.string(scan);
			System.out.println("Enter first name: ");
			String firstName = ParseInput.string(scan);
			System.out.println("Enter last name: ");
			String lastName = ParseInput.string(scan);
			System.out.println("Enter email: ");
			String email = ParseInput.string(scan);
			System.out.println("Enter phone: ");
			String phone = ParseInput.string(scan);

			this.credentialsList.add(new AccountCredentials(username, password));
			newAccount = new Account(username, firstName, lastName, phone, email, AccountPermission.USER);
			this.accountList.add(newAccount);

			FileIO.saveCredentials(credentialsList); // write to json
			FileIO.saveAccounts(accountList); // write to json
			FileIO.saveUsernames(usernamesList); // write to json

			System.out.println("Account created.");
		}
		return newAccount;
	}

	/***
	 * Gets a unique username, with limited attempts before program exits.
	 * Checks the username against set collection loaded from json file.
	 * 
	 * @param scan - user input new username
	 * @return the username string if it is unique, else empty.
	 */
	private String getUniqueUser(Scanner scan) {
		boolean isUnique = false;
		String username = "";
		for(int attempts = 2; attempts >= 0 && !isUnique; attempts--) {
			System.out.println("Enter new username: ");
			username = ParseInput.string(scan);
			isUnique = checkUserName(username);
			if (username.isEmpty()) isUnique = printInvalid(attempts);
			else if (!isUnique) printIncorrect(attempts);
		}
		return isUnique ? username : "";
	}

	/***
	 * Checks if the set collection contains the newUsername
	 * 
	 * @param newUsername - username that user specified
	 * @return false if in set collection
	 */
	private boolean checkUserName(String newUsername) {
		return !this.usernamesList.contains(newUsername);
	}

	/***
	 * Prints warning upon empty usernames input
	 * 
	 * @param attempts - remaining tries before program exits.
	 * @return false so that isUnqiue remains false;
	 */
	private boolean printInvalid(int attempts) {
		System.out.println("Invalid, attempts remaining " + attempts + "\n");
		return false;
	}

	/***
	 * Prints warning when entered username already exist. 
	 * 
	 * @param attempts - remaining tries before program exits.
	 */
	private void printIncorrect(int attempts) {
		System.out.println("User already exists, attempts remaining " + attempts + "\n");
	}
}