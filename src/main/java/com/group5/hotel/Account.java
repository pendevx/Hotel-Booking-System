package com.group5.hotel;

public class Account {
    final String username;
    final String firstName;
    final String lastName;
    final String phone;
    final String email;
    final AccountPermission permissions;
    public Account(String username, String firstName, String lastName, String phone, String email, AccountPermission permissions) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.permissions = permissions;
    }

    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public AccountPermission getAccountType() { return permissions; }
    public boolean equals(Account acc) { return username.equals(acc.username); }
    public boolean equals(String username) { return username.equals(username); }
}
