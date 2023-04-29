package com.group5.gh;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.*;

import com.group5.account.*;


public class HotelApp {
    private List<Credentials> credentials;
    private String credentialsPath = "src/main/resources/credentials.json";
    private Gson gson;

    public HotelApp() {
        gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();

        try {
            credentials = gson.fromJson(
                    String.join("\n", Files.readAllLines(Paths.get(credentialsPath))),
                    new TypeToken<List<Credentials>>(){}.getType()
            );
        } catch (IOException e) { throw new RuntimeException(e); }

        HotelData.init();
    }

    public UserSystem login(String username, String password) {
        try {
            Credentials loginCreds = new Credentials(username, password);
            Credentials match = credentials.stream().filter(x -> x.equals(loginCreds)).collect(Collectors.toList()).get(0);
            Account account = HotelData.getAccounts().stream().filter(x -> x.username.equals(match.getUsername())).collect(Collectors.toList()).get(0);

            if (account.getAccountType() == AccountPermission.ADMIN) {
                return new AdminSystem(account, HotelData.bookings);
            }

            List<Booking> bookingsForAccount = HotelData.getBookingsForAccount(account);
            return new ClientSystem(account, bookingsForAccount);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Credentials not found.");
            return null;
        }
    }

    public UserSystem login(Account account) {
        if (account.getAccountType() == AccountPermission.USER) {
            List<Booking> bookingsForAccount = HotelData.getBookingsForAccount(account);
            return new ClientSystem(account, bookingsForAccount);
        } else {
            return new AdminSystem(account, HotelData.getBookings());
        }
    }

    public boolean registerNewAccount(String username, String password, String firstName, String lastName, String phone, String email) {
        Credentials newCreds = new Credentials(username, password);

        credentials.add(newCreds);
        HotelData.register(username, firstName, lastName, phone, email);

        saveCredentials();

        // DUMMY
        return false;
    }

    private void saveCredentials() {
        new Thread(() -> {
            try {
                Files.write(Paths.get(credentialsPath), gson.toJson(credentials).getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
