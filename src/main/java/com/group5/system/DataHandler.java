package com.group5.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataHandler {
    protected static final String credentialsPath;
	protected static final String accountsPath;
	protected static final String bookingsPath;
	protected static final String hotelPath;
    protected static final Gson gson;

	static {
    	credentialsPath = "src/main/resources/credentials.json";
		accountsPath = "src/main/resources/account_details.json";
		bookingsPath = "src/main/resources/bookings.json";
		hotelPath = "src/main/resources/hotel.json";
    	gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();
	}
}
