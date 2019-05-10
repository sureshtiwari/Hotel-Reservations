package com.sap.hotel.services;

import java.util.ArrayList;
import java.util.Scanner;

import com.sap.hotel.HotelApplication;
import com.sap.hotel.model.Booking;
import com.sap.hotel.model.Room;

/*The Class Contains the Services and the queries needed 
 * for the booking a room like Validating the booking details,
 * checking availability, assigning room etc.
 */

public class BookingService {
	
	/*The Method takes in a booking and check for its 
	 * validity by checking the negative value and any 
	 * other value over 365 days from present day.
	*/
	public boolean isBookingValid(Booking booking)
	{
		if(booking.getStartDate()>=0 && booking.getStartDate()<365 && booking.getEndDate()>=0 && booking.getEndDate()<365 && booking.getStartDate()<=booking.getEndDate())
			return true;
		return false;
	}
	
	/* The method takes in a booking and check the availability 
	 * of it against the current booking . The method return the list of
	 * Available rooms or Null, if no rooms are available 
	*/
	public  ArrayList<Room> checkAvalability(Booking booking) {
		ArrayList<Room> allRooms = getCopyOfAllRooms();
		ArrayList<Room> roomsavailable = getCopyOfAllRooms();
		for(int i=booking.getStartDate(); i<=booking.getEndDate(); i++) // i represents the day for which we are searching for availability 
		{
			for(int j=0;j<allRooms.size();j++) // j represents the room checked for i th day
			{
				Room room = allRooms.get(j);
				ArrayList<Booking> roomBooking = room.getBookings();
				for(int k=0;k<roomBooking.size();k++) // k represents the bookings being checked for that room
				{
					Booking booking1 = roomBooking.get(k);
					if(i>=booking1.getStartDate() && i<=booking1.getEndDate())
					{
						int roomnumbertoremove = allRooms.get(j).getRoomNumber();
						for(int l=0;l<roomsavailable.size();l++)
							if(roomsavailable.get(l).getRoomNumber()== roomnumbertoremove)
							{
								roomsavailable.remove(l);
								break;
							}
						break;
					}
				}
				
			}
		}
		if(roomsavailable.size()==0)
			return null;
		return roomsavailable;
	}
	
	/* The Method takes in all the available rooms and booking.
	 * It calculates the smallest room number from the available rooms
	 * and assign the room to the given booking
	*/
	public boolean AssignRoom(ArrayList<Room> availableRooms, Booking booking) {
		int roomnumber = availableRooms.get(0).getRoomNumber();
		HotelApplication.getMyHotel().getRooms().get(roomnumber-1).getBookings().add(booking);
		return true;
	}
	
	/* The Method asks the user with start and end date of the
	 * booking and calls the required methods to check weather 
	 * booking can be entertained or not
	*/
	public void takeBooking() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter start date and end date of the booking:");
		Booking booking = new Booking(sc.nextInt(),sc.nextInt());
		if(isBookingValid(booking))
		{
			ArrayList<Room> availableRooms = checkAvalability(booking);
			if(availableRooms != null)
			{
				if(AssignRoom(availableRooms,booking))
					System.out.println("booking done");
			}else
				System.out.println("Rooms not available");
			
		}
		else
			System.out.println("invalid Booking");
	}
	
	/*The method creates a copy of the array list containing all the 
	 * Room details. This is done because the clone method of array list
	 * does a shallow copy and this method gives a deep copy of array list
	 */
	public ArrayList<Room> getCopyOfAllRooms()
	{
		ArrayList<Room> allRooms = new ArrayList<Room>();
		for(int i=0;i<HotelApplication.getMyHotel().getRooms().size();i++)
		{
			Room room = HotelApplication.getMyHotel().getRooms().get(i);
			allRooms.add(room);
		}
		return allRooms;
	}

}
