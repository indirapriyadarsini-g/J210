package com.hexaware.exception;

public class ArtWorkNotFoundException extends Exception{
	public ArtWorkNotFoundException() {
		System.out.println("The artwork is not found");
	}
	public String toString() {
		return "Artwork not found";
	}
}
