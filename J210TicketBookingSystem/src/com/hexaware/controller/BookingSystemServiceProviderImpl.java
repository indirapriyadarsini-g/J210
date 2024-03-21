package com.hexaware.controller;

import java.sql.*;
import java.util.*;

import com.hexaware.dao.BookingSystemRepositoryImpl;
import com.hexaware.dao.IBookingSystemRepository;
import com.hexaware.entity.*;
import com.hexaware.exception.*;

public class BookingSystemServiceProviderImpl implements IBookingSystemServiceProvider {

	IEventServiceProvider esp = new EventServiceProviderImpl();

	Scanner si = new Scanner(System.in);
	IBookingSystemRepository repo = new BookingSystemRepositoryImpl();
	@Override
	public void calculateBookingCost(int numTickets) {
		System.out.println("Enter Event id:");
		try {
			repo.calculateBookingCost(si.nextInt(),numTickets);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	
	@Override
	public void bookTickets() {
		System.out.println("Enter the customerId");
		int customerId=si.nextInt();
		List<Event> eventsList = null;
		try {
			eventsList = esp.getEventDetails();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Event_id   Event_name   Available_tickets   Ticket_price   Event_type");
		for(Event event:eventsList) {
			System.out.println(event.getEventId()+"   "+event.getEventName()+"    "+
					event.getAvailableSeats()+"    "+event.getTicketPrice()+"      "+event.getEventType());
		}
		System.out.println("Enter the event id to book:");
		int eventId=si.nextInt();
		System.out.println("Enter the number of tickets:");
		int numTickets=si.nextInt();
		
		
		boolean available = isTicketAvailable(eventsList,eventId,numTickets);
		
		if(!available) {
			System.out.println("Tickets not available ");
			return;
		}
		// booking tickets
		try {
			bookTickets(eventsList,customerId,eventId,numTickets);
		} catch (EventNotFoundException e) {
			e.printStackTrace();
		}
		
		// updating available seats 
		try {
			esp.updateAvailableSeats(eventsList,eventId,numTickets);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void bookTickets(List<Event> eventsList, int customerId, int eventId, int numTickets) throws EventNotFoundException{
		double totalCost = 0;
		for (Event event : eventsList) {
			if (event.getEventId() == eventId) {
				totalCost = numTickets * event.getTicketPrice();
				break;
			}
		}
		try {
			repo.bookTickets(customerId, eventId, numTickets, totalCost);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void cancelBooking(int bookingId) throws SQLException, InvalidBookingIDException {
		repo.cancelBooking(bookingId);
		
	}
	
	@Override
	public Booking getBookingDetails() {
		System.out.println("Enter Booking Id:");
		int bookingId = si.nextInt();
		return getBookingDetails(bookingId);
		
	}

	@Override
	public Booking getBookingDetails(int bookingId) {
		Booking booking = null;
		try {

			booking = repo.getBookingDetails(bookingId);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidBookingIDException e) {
			e.printStackTrace();
		}  catch (EventNotFoundException e) {

			e.printStackTrace();
		}
		
		return booking ;
		
	}


	
	


	@Override
	public void cancelBooking()  {
		System.out.println("Enter bookingId:");
		int bookingId = si.nextInt();
		try {

			repo.cancelBooking(bookingId);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidBookingIDException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public boolean isTicketAvailable(List<Event> eventsList, int eventId, int numTickets) {

		for (Event event : eventsList) {
			if (event.getEventId() == eventId && event.getAvailableSeats() >= numTickets)
				return true;
		}
		return false;
	}
//	public void bookTickets(Event event, int numTickets, ArrayList<Booking> bookingList) {
//	
//	if(event == null) {
//		throw new EventNotFoundException();
//	}
//	
//	if(event.getAvailableSeats() >= numTickets) {
//		event.setAvailableSeats(event.getAvailableSeats()-numTickets);
//		
//		Booking booking = new Booking(event, numTickets, bookingList, (java.time.LocalDate.now()).toString());
//		System.out.println("Booking successful");
//		bookingList.add(booking);
//	}
//	else {
//		System.out.println("Tickets not available");
//	}
//	
//	
//}


	

}

