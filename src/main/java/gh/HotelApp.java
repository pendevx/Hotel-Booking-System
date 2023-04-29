package gh;

import com.group5.hotel.Booking;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.*;

import com.group5.account.*;


public class HotelApp {
    private List<AccountCredentials> credentials;
    private String credentialsPath = "src/main/resources/credentials.json";
    private Gson gson;

    public HotelApp() {
        gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();

        try {
            credentials = gson.fromJson(
                    String.join("\n", Files.readAllLines(Paths.get(credentialsPath))),
                    new TypeToken<List<AccountCredentials>>(){}.getType()
            );
        } catch (IOException e) { throw new RuntimeException(e); }

        HotelData.init();
    }

    public SystemUser login(String username, String password) {
        try {
            AccountCredentials loginCreds = new AccountCredentials(username, password);
            AccountCredentials match = credentials.stream().filter(x -> x.equals(loginCreds)).collect(Collectors.toList()).get(0);
            Account account = HotelData.getAccounts().stream().filter(x -> x.username.equals(match.getUsername())).collect(Collectors.toList()).get(0);

            if (account.getAccountType() == AccountPermission.ADMIN) {
                return new SystemAdmin(account, HotelData.bookings);
            }

            List<Booking> bookingsForAccount = HotelData.getBookingsForAccount(account);
            return new SystemClient(account, bookingsForAccount);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Credentials not found.");
            return null;
        }
    }

    public SystemUser login(Account account) {
        if (account.getAccountType() == AccountPermission.USER) {
            List<Booking> bookingsForAccount = HotelData.getBookingsForAccount(account);
            return new SystemClient(account, bookingsForAccount);
        } else {
            return new SystemAdmin(account, HotelData.getBookings());
        }
    }

//    public boolean registerNewAccount(String username, String password, String firstName, String lastName, String phone, String email) {
//        AccountCredentials newCreds = new AccountCredentials(username, password);
//
//        credentials.add(newCreds);
//        HotelData.register(username, firstName, lastName, phone, email);
//
//        saveCredentials();
//
//        // DUMMY
//        return false;
//    }
//
//    private void saveCredentials() {
//        new Thread(() -> {
//            try {
//                Files.write(Paths.get(credentialsPath), gson.toJson(credentials).getBytes());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();
//    }
}
