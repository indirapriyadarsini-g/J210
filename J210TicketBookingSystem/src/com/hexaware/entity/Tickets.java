package com.hexaware.entity;

public class Tickets {
	private String eventName;
	private int availableTickets;
	
	public Tickets() {
		
	}

	public Tickets(String eventName, int availableTickets) {
		
		this.eventName = eventName;
		this.availableTickets = availableTickets;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}

	@Override
	public String toString() {
		return "Tickets [eventName=" + eventName + ", availableTickets=" + availableTickets + "]";
	}
}
