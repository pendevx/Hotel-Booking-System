package gh;

import com.group5.hotel.Booking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.group5.account.*;

class HotelData {
	 static List<Booking> bookings;
	 static List<Account> accounts;
	private static Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();
	 static String bookingsPath = "src/main/resources/bookings.json";
	 static String accountsPath = "src/main/resources/account_details.json";

	static void book(Booking booking) {
		bookings.add(booking);
		saveBookings();
	}

	static void register(String username, String firstName, String lastName, String phone, String email) {
		Account newAccount = new Account(username, firstName, lastName, phone, email, AccountPermission.USER);
		accounts.add(newAccount);
		saveAccounts();
	}

	static List<Booking> getBookingsWhere(Predicate<Booking> action) {
		return bookings.stream().filter(action).collect(Collectors.toList());
	}

	static List<Booking> getBookingsForAccount(Account account) {
		return getBookingsWhere(x -> x.getAccount().equals(account));
	}

	private static void saveBookings() {
		new Thread(() -> {
			try {
				Files.write(Paths.get(bookingsPath), gson.toJson(bookings).getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

	private static void saveAccounts() {
		new Thread(() -> {
			try {
				Files.write(Paths.get(accountsPath), gson.toJson(accounts).getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}
	static List<Account> getAccounts() { return accounts; }
	static List<Booking> getBookings() { return bookings; }

	static void init() {
		gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();

		try {
			bookings = gson.fromJson(
					String.join("\n", Files.readAllLines(Paths.get(bookingsPath))),
					new TypeToken<List<Booking>>(){}.getType()
			);

			accounts = gson.fromJson(
					String.join("\n", Files.readAllLines(Paths.get(accountsPath))),
					new TypeToken<List<Account>>(){}.getType()
			);
		} catch (IOException e) { throw new RuntimeException(e); }
	}
}
