package com.sap.hotel.model;

public class Booking {
	
	private int startDate;
	private int endDate;
	public int getStartDate() {
		return startDate;
	}
	
	public Booking(int startDate, int endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

}
