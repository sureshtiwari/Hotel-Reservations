package com.sap.hotel.services;

import java.util.ArrayList;
import java.util.Scanner;

import com.sap.hotel.HotelApplication;
import com.sap.hotel.model.Booking;
import com.sap.hotel.model.Room;

public class BookingService {
	
	public boolean isBookingValid(Booking booking)
	{
		if(booking.getStartDate()>=0 && booking.getStartDate()<365 && booking.getEndDate()>=0 && booking.getEndDate()<365 && booking.getStartDate()<=booking.getEndDate())
			return true;
		return false;
	}

	public  ArrayList<Room> checkAvalability(Booking booking) {
		ArrayList<Room> allRooms = getCopyOfAllRooms();
		ArrayList<Room> roomsavailable = getCopyOfAllRooms();
		for(int i=booking.getStartDate(); i<=booking.getEndDate(); i++) // i represents the day for which we are searching for avalability 
		{
			for(int j=0;j<allRooms.size();j++) // j represents the room checked for ith day
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

	public boolean AssignRoom(ArrayList<Room> availableRooms, Booking booking) {
		int roomnumber = availableRooms.get(0).getRoomNumber();
		HotelApplication.getMyHotel().getRooms().get(roomnumber-1).getBookings().add(booking);
		return true;
	}

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
