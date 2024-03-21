package com.hexaware.exception;

public class UserNotFoundException extends Exception{
	public UserNotFoundException() {
		System.out.println("The user is not found");
	}
	public String toString() {
		return "User not found";
	}
}
