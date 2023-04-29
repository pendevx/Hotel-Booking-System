package com.group5.hotel;

public class Location {
	public String street;
	public String suburb;
	public String city;
	public String postCode; // handle 0604
	public String country;

	public Location(String street, String suburb, String city, String postCode, String country) {
		this.street = street;
		this.suburb = suburb;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
	}

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
