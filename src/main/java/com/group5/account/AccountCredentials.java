package com.group5.account;

public class AccountCredentials implements Comparable<AccountCredentials> {
    private final String username;
    private final String password;

    public AccountCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    @Override
    public int compareTo(AccountCredentials o) {
        return username.equals(o.username) && password.equals(o.password) ? 0 : -1;
    }
    public boolean equals(AccountCredentials o) {
        return username.equals(o.username) && password.equals(o.password);
    }
}
