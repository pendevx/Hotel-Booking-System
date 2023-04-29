package com.group5.account;

public class Account {
    public final String username;
    public final String firstName;
    public final String lastName;
    public final String phone;
    public final String email;
    final AccountPermission permissions;

    public Account(String username, 
			String firstName, String lastName,
			String phone, String email,
			AccountPermission permissions) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.permissions = permissions;
    }

	@Override
	public String toString() { return "(" + getAccountTypeName()+ ") " + getUsername(); }
	public String getAccountDetails() { return getFullName() + "\n" + getContact(); }
	public String getUsername () { return this.username; }

    public String getAccountTypeName() { return this.permissions.name(); }
    public AccountPermission getAccountType() { return this.permissions; }

	public String getFullName() { return getFirstName() + " " + getLastName(); }
	public String getFirstName() { return this.firstName; }
	public String getLastName() { return this.lastName; }

	public String getContact() { return getEmail() + "\n" + getPhone(); }
	public String getPhone() { return this.phone; }
	public String getEmail() { return this.email; }

    public boolean equals(Account acc) { return username.equals(acc.username); }
}
