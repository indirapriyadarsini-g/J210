package com.hexaware.exception;

public class EventNotFoundException extends Exception{
	public EventNotFoundException() {
		System.out.println("The event is not found");
	}
	
	public String toString() {
		return "Event not found";
	}
}
