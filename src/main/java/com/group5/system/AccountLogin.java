package com.group5.system;

import com.group5.account.Account;
import com.group5.account.AccountCredentials;
import com.group5.util.ParseInput;
import java.util.List;
import java.util.Scanner;


public class AccountLogin {
	private List<AccountCredentials> listCredentials;
	private List<Account> accounts;
	private Account account;

	public AccountLogin() {
		this.listCredentials = DataHandlerRead.loadCredentialsJson();
		this.accounts = DataHandlerRead.loadAccountsJson();
	}

	public Account login (Scanner scan) {
		boolean isSuccess = false;
		for(int attempts = 2; attempts >= 0 && !isSuccess; attempts--) {
			System.out.println("Username: ");
			String enteredUsername = ParseInput.string(scan);
			System.out.println("Password: ");
			String enteredPassword = ParseInput.string(scan);
			isSuccess = loginHandler(new AccountCredentials(enteredUsername, enteredPassword), attempts);
		}
		return this.account;
	}

	private boolean loginHandler(AccountCredentials enteredCredentials, int attempts) {
		boolean isCorrect = false;
		try
		{
			if(!checkCredentials(enteredCredentials)) printIncorrect(attempts);
			else isCorrect = loadAccount(enteredCredentials.getUsername());
		}
		catch (Exception e) { printIncorrect(attempts); }
		return isCorrect;
	}

	private boolean checkCredentials(AccountCredentials entereCredentials) {
		for (AccountCredentials c : listCredentials) {
			if (entereCredentials.equals(c)) return true;
		}
		return false;
	}

	public Account matchAccount(String username) {
		for(Account account : accounts) {
			if(account.getUsername().equals(username)) return account;
		}
		return null;
	}

	private boolean loadAccount(String username) {
		this.account = matchAccount(username);
		return true;
	}

	private void printIncorrect(int attempts) {
		System.out.println("Incorrect login, attempts remaining " + attempts + "\n");
	}
}