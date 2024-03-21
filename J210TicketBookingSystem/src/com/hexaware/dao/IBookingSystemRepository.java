package com.hexaware.dao;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Revenue;
import com.hexaware.entity.Tickets;
import com.hexaware.entity.Venue;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;

public interface IBookingSystemRepository {
	
	//for event
	
	public void createEvent(Event event) throws SQLException;
	public List<Event> getEventDetails() throws SQLException;
	public List<Tickets> getAvailableNoOfTickets() throws SQLException;
	public void updateAvailableSeats(int eventId, int numTickets, int updatedAvailableSeats) throws SQLException;
	public List<Revenue> getTotalRevenue() throws SQLException;
	
	// for booking
	
	public Booking getBookingDetails(int bookingId) throws SQLException, EventNotFoundException, InvalidBookingIDException;
	public void cancelBooking(int bookingId) throws SQLException, InvalidBookingIDException;
	double calculateBookingCost(int bookingId, int numTickets) throws SQLException;
	public void bookTickets(int customerId, int eventId, int numTickets, double totalCost) throws SQLException;	
	
	public Venue getVenueById(int venueId) throws SQLException, NullPointerException;
	public Customer getCustomerDetails(int customerId) throws SQLException;
	public Event getEventDetailsById(int eventId) throws SQLException, EventNotFoundException;

	
	
}

// for event --
//repo.createEvent(event);
//eventsList = repo.getEventDetails();
//List<Tickets> availableTickets = repo.getAvailableNoOfTickets();
//repo.updateAvailableSeats(eventId, numTickets, updatedAvailableSeats);
//List<Revenue> list = repo.getTotalRevenue();

// for booking --
//repo.bookTickets(customerId, eventId, numTickets, totalCost);
//booking = repo.getBookingDetails(bookingId);
//repo.cancelBooking(bookingId);
//repo.calculateBookingCost(si.nextInt(),numTickets);
