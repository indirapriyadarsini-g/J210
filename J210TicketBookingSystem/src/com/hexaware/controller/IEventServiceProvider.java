package com.hexaware.controller;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.entity.*;


public interface IEventServiceProvider {
	public void createEvent() throws SQLException;
	public List<Event> getEventDetails() throws SQLException;
	public List<Tickets> getAvailableNoOfTickets() throws SQLException;
	public List<Revenue> getTotalRevenue() throws SQLException;
	void updateAvailableSeats(List<Event> events, int eventId, int numTickets) throws SQLException;

}


//	esp.createEvent();
//	
//		List<Event> eventList = esp.getEventDetails();
//		
//		List<Tickets> ticketsList = esp.getAvailableNoOfTickets();
//		

//	
//	List<Revenue> revenue = esp.getTotalRevenue();
//	

