package com.hexaware.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.*;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

public interface IBookingSystemServiceProvider {
	public void bookTickets();
	void bookTickets(List<Event> eventsList, int customerId, int eventId, int numTickets) throws EventNotFoundException;
	
	public ArrayList<Event> eventList = new ArrayList<>();
	public void calculateBookingCost(int numTickets);
	public void cancelBooking(int bookingId) throws SQLException, InvalidBookingIDException;
	public Booking getBookingDetails(int bookingId);
	
	public void cancelBooking() throws SQLException, InvalidBookingIDException;
	public Booking getBookingDetails();
	public boolean isTicketAvailable(List<Event> eventsList, int eventId, int numTickets);
	
}

 
//bsp.bookTickets();
//
//	bsp.cancelBooking();
//
//bsp.getBookingDetails();