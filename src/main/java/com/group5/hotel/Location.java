package com.group5.hotel;

public class Location {
	public String street;
	public String suburb;
	public String city;
	public String postCode; // will handle 0604
	public String country;

    /***
     * Constructor for location, storing address details
     * 
     * @param street - street address, including number
     * @param suburb - of location
     * @param city - of location
     * @param postCode - of location
     * @param country - of location
     */
	public Location(String street, String suburb, String city, String postCode, String country) {
		this.street = street;
		this.suburb = suburb;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
	}

    /***
     * @return formatted string of location details
     */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder(this.street + "\n");
		out.append(this.suburb+"\n");
		out.append(this.city+", ");
		out.append(this.postCode+"\n");
		out.append(this.country);
		return out.toString();
	}
}
