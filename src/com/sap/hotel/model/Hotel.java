package com.sap.hotel.model;

import java.util.ArrayList;

public class Hotel {
	
	private  int size;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	public Hotel(int size)
	{
		this.size=size;
		for(int i=1;i<=size;i++)
		{
			rooms.add(new Room(i));
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	

}
