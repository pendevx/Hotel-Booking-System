package com.group5.hotel;

public class Hotel {
	private String hotelName;
	private Location location;
	public String phone;
	public String email;

    /***
     * Constructor for hotel, storing details about the hotel
     * 
     * @param hotelName - name of hotel
     * @param location - location of hotel
     * @param phone - contact number
     * @param email - contact email
     */
	public Hotel(String hotelName, Location location, String phone, String email) {
		this.hotelName = hotelName;
		this.location = location;
		this.email = email;
		this.phone = phone;
	}

    /***
     * @return formatted string of hotel name, address and contact
     */
	@Override
	public String toString() {
		return getHotelName() + "\n" + getAddress() + "\n" + getContact() + "\n";
	}

    /***
     * @return name of hotel
     */
	public String getHotelName() {
		return this.hotelName;
	}

    /***
     * @return toString location of hotel
     */
	public String getAddress() {
		return this.location.toString();
	}

    /***
     * @return formatted string of hotel email and phone
     */
	public String getContact() {
		return this.email + "\n" + this.phone;
	}
}
