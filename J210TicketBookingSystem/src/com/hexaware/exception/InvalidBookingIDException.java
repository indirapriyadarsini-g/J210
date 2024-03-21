package com.hexaware.exception;

public class InvalidBookingIDException extends Exception{
	public InvalidBookingIDException() {
		System.out.println("The booking ID is invalid");
	}
	
	public String toString() {
		return "Invalid Booking Exception";
	}
}
