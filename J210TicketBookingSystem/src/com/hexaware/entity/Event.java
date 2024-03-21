package com.hexaware.entity;


import java.time.LocalDate;
import java.time.LocalTime;

public  class Event {
	private String eventName;
	private LocalDate eventDate;
	private LocalTime eventTime;
	private int venueId;
	private String venueName;
	private int totalSeats;
	private int availableSeats;
	private double ticketPrice;
	private EventType eventType;
	private Venue venue; // HAS A
	private int eventId;
	
	
	public Event(){
	}
	
	public Event(int eventId,String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, int totalSeats, int availableSeats,
			float ticketPrice, EventType eventType, Venue venue) {
		super();
		this.eventId = eventId++;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.venueName = venueName;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.ticketPrice = ticketPrice;
		this.eventType = eventType;
		this.venue = venue;
	}
	
	public Event(int eventId2, String eventName2, LocalDate date, LocalTime time, Venue venue2, int totalSeats2,
			int availableSeats2, double ticketPrice2, EventType valueOf) {
		// TODO Auto-generated constructor stub
	}

	public String getEventName() {
		return eventName;
	}
	
	public LocalDate getEventDate() {
		return eventDate;
	}
	
	public LocalTime getEventTime() {
		return eventTime;
	}
	
	public String getVenueName() {
		return venueName;
	}
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}
	
	public double getTicketPrice() {
		return ticketPrice;
	}
	
	public String getEventType() {
		return String.valueOf(eventType);
	}
	
	public void setEventName(String name) {
		this.eventName = name;
	}
	
	public void setEventDate(LocalDate date) {
		this.eventDate = date;
	}
	
	public void setEventTime(LocalTime event_time) {
		this.eventTime = event_time;
	}
	
	public void setVenueName(String venue_name) {
		this.venueName = venue_name;
	}
	
	public void setTotalSeats(int total_seats) {
		this.totalSeats = total_seats;
	}
	
	public void setAvailableSeats(int available_seats) {
		this.availableSeats = available_seats;
	}
	
	public void setTicketPrice(double ticketPrice2) {
		this.ticketPrice = ticketPrice2;
	}
	
	public void setEventType(EventType type) {
		this.eventType = type;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	public  void displayEventDetails() {
        System.out.println("Event Name: " + eventName +
                           "\nEvent Date: " + eventDate +
                           "\nEvent Time: " + eventTime +
                           "\nVenue Name: " + venueName +
                           "\nTotal Seats: " + totalSeats +
                           "\nAvailable Seats: " + availableSeats +
                           "\nTicket Price: " + ticketPrice +
                           "\nEvent Type: " + eventType +
                           "\nVenue: " + venue);
    }
	
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventDate=" + eventDate + ", eventTime="
				+ eventTime + ", venue=" + venue + ", totalSeats=" + totalSeats + ", availableSeats=" + availableSeats
				+ ", ticketPrice=" + ticketPrice + ", eventType=" + eventType + "]";
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}


	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	
}
