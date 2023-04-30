package com.group5.view;

import com.group5.system.HotelSystem;
import java.util.Scanner;

public abstract class View {
	public HotelSystem hotelSystem;

	public View (HotelSystem hotelSystem) { this.hotelSystem = hotelSystem; }
	
	public abstract void menuMain(Scanner scan);
	public void printUserDetails() { System.out.println(hotelSystem.getUserDetails()); }
	public void confirmLogin() { System.out.println(hotelSystem.getConfirmLogin()); }
	public void printHotelDetails() { System.out.println(hotelSystem.getHotelDetails()); }
}
