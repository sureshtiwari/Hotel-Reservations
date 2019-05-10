package com.sap.hotel;

import java.util.Scanner;

/*main class to start the Application and set
 *  up all the things need for the Application to function
 */

import com.sap.hotel.model.Hotel;
import com.sap.hotel.services.BookingService;

public class HotelApplication {
	
	private static Hotel myHotel;
	
	/*The main method welcomes the user with a welcome message and 
	 * shows the user with the required menu
	 */

	public static void main(String[] args) {
		BookingService bookingService = new BookingService();
		int userChoice=0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Hotel Booking System");
		System.out.println("enter the Number of rooms in your hotel:");
		int size = sc.nextInt();
		myHotel = new Hotel(size);
		do {
		System.out.println("Press 1 to give a booking");
		System.out.println("Press 2 to Exit ");
		userChoice = sc.nextInt();
		switch(userChoice)
			{
				case 1:bookingService.takeBooking();
					   break;
					   
				case 2:sc.close();
					   System.exit(0);
					   
				default:System.out.println("Please Select the correct menu Options");
			}
		}while(true);
	}

	public static Hotel getMyHotel() {
		return myHotel;
	}

	public static void setMyHotel(Hotel myHotel) {
		HotelApplication.myHotel = myHotel;
	}
	
	

}
