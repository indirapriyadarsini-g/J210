package com.hexaware.exception;

public class NullPointerException extends Exception{
	public NullPointerException() {
		System.out.println("Null pointer");
	}
	public String toString() {
		return "Null pointer Exception";
	}
}
