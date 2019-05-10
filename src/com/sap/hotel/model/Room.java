package com.sap.hotel.model;

import java.util.ArrayList;

/*
 * Class to create the model Object for holding details about Room
 */

public class Room {
	
	private int roomNumber;
	private ArrayList<Booking> bookings = new ArrayList<Booking>();

	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
}
