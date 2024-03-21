package com.hexaware.entity;

//Create a subclass Movie that inherits from Event. Add the following attributes and methods:
//o Attributes:
//1. genre: Genre of the movie (e.g., Action, Comedy, Horror).
//2. ActorName
//3. ActresName
//o Methods:
//1. Implement default constructors and overload the constructor with Customer
//attributes, generate getter and setter methods.
//2. display_event_details(): Display movie details, including genre.

public class Movie extends Event{ //IS A
	private String genre;
	private String actorName;
	private String actressName;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActressName() {
		return actressName;
	}

	public void setActressName(String actressName) {
		this.actressName = actressName;
	}

	@Override
	public void displayEventDetails() {
		System.out.println("Movie Details:\nGenre: "+genre+"\nActor Name: "+actorName+"\nActress Name: "+actressName);
	}
}
