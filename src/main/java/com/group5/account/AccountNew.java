package com.group5.account;

import com.group5.dataHandler.DataHandlerRead;
import com.group5.dataHandler.DataHandlerWrite;
import com.group5.util.ParseInput;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class AccountNew {
	private List<AccountCredentials> credentialsList;
	private List<Account> accountList;
	private List<String> userList;

	public AccountNew() {
		this.credentialsList = DataHandlerRead.loadCredentialsJson();
		this.accountList = DataHandlerRead.loadAccountsJson();
		this.userList = getUserList();
	}

	private List<String> getUserList() {
		List<String> users = new ArrayList<>();
		for (AccountCredentials c : this.credentialsList) users.add(c.getUsername());
		return users;
	}

	public Account createUser(Scanner scan) {
		String username = getUniqueUser(scan);
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
			DataHandlerWrite.saveCredentials(credentialsList);

			newAccount = new Account(username, firstName, lastName, phone, email, AccountPermission.USER);
			this.accountList.add(newAccount);
			DataHandlerWrite.saveAccounts(accountList);

			System.out.println("Account created.");
		}
		return newAccount;
	}
	
	private String getUniqueUser(Scanner scan) {
		boolean isUnique = false;
		String username = "";
		for(int attempts = 2; attempts >= 0 && !isUnique; attempts--) {
			System.out.println("Enter new username: ");
			username = ParseInput.string(scan);
			isUnique = checkUserName(username);
			if (!isUnique) printIncorrect(attempts);
		}
		return isUnique ? username : "";
	}

	private boolean checkUserName(String newUsername) { return !this.userList.contains(newUsername); }

	private void printIncorrect(int attempts) {
		System.out.println("User already exists, attempts remaining " + attempts + "\n");
	}
}