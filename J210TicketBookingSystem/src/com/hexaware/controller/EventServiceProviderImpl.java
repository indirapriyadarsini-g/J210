package com.hexaware.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.*;
import com.hexaware.entity.*;

public class EventServiceProviderImpl implements IEventServiceProvider{

	
	Scanner si = new Scanner(System.in);
	IBookingSystemRepository repo = new BookingSystemRepositoryImpl();
	
	@Override
	public void createEvent() throws SQLException{
		
		Event event = new Event();
		
		System.out.println("Enter event name:");
		String eventName = si.next();
		event.setEventName(eventName);
		
		System.out.println("Enter event date (YYYY-MM-DD):");
		LocalDate date = LocalDate.parse(si.next());
		event.setEventDate(date);
		
		System.out.println("Enter event time (HH:MM:SS):");
		LocalTime time = LocalTime.parse(si.next());
		event.setEventTime(time);
		
		System.out.println("Enter total seats :");
		int totalSeats = si.nextInt();
		event.setTotalSeats(totalSeats);
		
		System.out.println("Enter ticket price:");
		float ticketPrice = si.nextInt();
		event.setTicketPrice(ticketPrice);
		
		System.out.println("Enter event type (Movie/Sports/Concert):");
		String typeOfEvent = si.next().toUpperCase();
		EventType eventType = EventType.valueOf(typeOfEvent);
		event.setEventType(eventType);
		
		repo.createEvent(event);
	}

    @Override
	public List<Event> getEventDetails() throws SQLException{
		List<Event> eventsList = null;
		try {
			eventsList = repo.getEventDetails();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return eventsList;
	}
	
	
	@Override
	public List<Tickets> getAvailableNoOfTickets() throws SQLException {
		List<Tickets> availableTickets = repo.getAvailableNoOfTickets();
		return availableTickets;
	}

	@Override
	public void updateAvailableSeats(List<Event> events, int eventId, int numTickets) throws SQLException {

		int updatedAvailableSeats = 0;
		for (Event e : events) {
			if (e.getEventId() == eventId) {
				updatedAvailableSeats = e.getAvailableSeats() - numTickets;
			}
		}
		
		repo.updateAvailableSeats(eventId, numTickets, updatedAvailableSeats);
	}


	@Override
	public List<Revenue> getTotalRevenue() throws SQLException {
		List<Revenue> list = repo.getTotalRevenue();
		return list;
	}
	
	// To sort events by event name using comparator
		public static Comparator<Event> eventNameCompartor = new Comparator<Event>() {

			public int compare(Event event1, Event event2) {

				return event1.getEventName().toLowerCase().compareTo(event2.getEventName().toLowerCase());
			}

		};

		//To sort events by event name and location using comparator
		public static Comparator<Event> eventNameLocationCompartor = new Comparator<Event>() {

			public int compare(Event event1, Event event2) {

				int nameLocationComparison = event1.getEventName().toLowerCase()
						.compareTo(event2.getEventName().toLowerCase());

				if (nameLocationComparison != 0) {
					return nameLocationComparison;
				}

				return event1.getVenue().getAddress().toLowerCase().compareTo(event2.getVenue().getAddress().toLowerCase());
			}

		};

}

