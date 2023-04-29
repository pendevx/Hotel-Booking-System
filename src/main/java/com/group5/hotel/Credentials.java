package com.group5.hotel;

class Credentials implements Comparable<Credentials> {
    private final String username;
    private final String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    @Override
    public int compareTo(Credentials o) {
        return username.equals(o.username) && password.equals(o.password) ? 0 : -1;
    }
    public boolean equals(Credentials o) {
        return username.equals(o.username) && password.equals(o.password);
    }
}
