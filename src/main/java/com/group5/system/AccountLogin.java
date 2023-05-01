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

	/***
	 * Constructor that load in the json files for credentials and accounts.
	 */
	public AccountLogin() {
		this.listCredentials = FileIO.loadCredentialsJson();
		this.accounts = FileIO.loadAccountsJson();
	}

	/***
	 * Handles the login process, with 3 attempts before program exits.
	 * 
	 * @param scan - user input
	 * @return 
	 */
	public Account login (Scanner scan) {
		boolean isSuccess = false;
		for(int attempts = 2; attempts >= 0 && !isSuccess; attempts--) { // handles if correct on third attempt
			System.out.println("Username: ");
			String enteredUsername = ParseInput.string(scan);
			System.out.println("Password: ");
			String enteredPassword = ParseInput.string(scan);
			isSuccess = loginHandler(new AccountCredentials(enteredUsername, enteredPassword), attempts);
		}
		return this.account;
	}

	/***
	 * Checks the enteredCredentials against what is stored in database.
	 * 
	 * @param enteredCredentials - credentials that are checked if correct
	 * @param attempts - number of attempts remaining
	 * @return boolean of true
	 */
	private boolean loginHandler(AccountCredentials enteredCredentials, int attempts) {
		boolean isCorrect = false;
		try
		{
			if(!checkCredentials(enteredCredentials)) printIncorrect(attempts);
			else isCorrect = loadAccount(enteredCredentials.getUsername()); // can only loadAccount if credentials are correct
		}
		catch (Exception e) { printIncorrect(attempts); }
		return isCorrect;
	}

	/***
	 * Loops through list of credentials and uses the credentials
	 * equals method to check if entered is same as one in database.
	 * 
	 * @param entereCredentials
	 * @return boolean value of if equal
	 */
	private boolean checkCredentials(AccountCredentials entereCredentials) {
		for (AccountCredentials c : listCredentials) {
			if (entereCredentials.equals(c)) return true;
		}
		return false;
	}

	/***
	 * Checks against collection to find equality matching username
	 * 
	 * @param username
	 * @return Account object of the equality matching username
	 */
	public Account matchAccount(String username) {
		for(Account account : accounts) {
			if(account.getUsername().equals(username)) return account;
		}
		return null;
	}

	/***
	 * Sets account to account that matches the username from collection
	 * 
	 * @param username - used to find equality in collection
	 * @return boolean of true
	 */
	private boolean loadAccount(String username) {
		this.account = matchAccount(username);
		return true;
	}

	/***
	 * Incorrect login message.
	 * 
	 * @param attempts - attempts left before system exits.
	 */
	private void printIncorrect(int attempts) {
		System.out.println("Incorrect login, attempts remaining " + attempts + "\n");
	}
}