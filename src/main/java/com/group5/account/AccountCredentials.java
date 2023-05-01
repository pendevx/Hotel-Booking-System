package com.group5.account;

public class AccountCredentials implements Comparable<AccountCredentials> {
    private final String username;
    private final String password;

    /***
     * Contructor to store the account credentials, used for
     * verifying login
     * 
     * @param username - unique username of user
     * @param password - used to secure account
     */
    public AccountCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /***
     * @return username of credentials
     */
	public String getUsername() {
		return username;
	}

    /***
     * 
     * @param o - credentials to compare to
     * @return compareTo values of string username && password
     */
    @Override
    public int compareTo(AccountCredentials o) {
        return username.equals(o.username) && password.equals(o.password) ? 0 : -1;
    }

    /***
     * 
     * @param o - credentials to check for string equality
     * @return boolean of string equality of password && username
     */
    public boolean equals(AccountCredentials o) {
        return username.equals(o.username) && password.equals(o.password);
    }
}
