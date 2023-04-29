package com.group5.hotel;

public class Hotel {
	private String hotelName;
	private Location location;
	public String phone;
	public String email;

	public Hotel(String hotelName, Location location, String phone, String email) {
		this.hotelName = hotelName;
		this.location = location;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() { return getHotelName() + "\n" + getAddress() + "\n" + getContact() + "\n"; }
	public String getHotelName() { return this.hotelName; }
	public String getAddress() { return this.location.toString(); }
	public String getContact() { return this.email + "\n" + this.phone; }
}
