package com.hexaware.entity;

//Create another subclass Sports that inherits from Event. Add the following attributes and
//methods:
//o Attributes:
//
//© Hexaware Technologies Limited. All rights www.hexaware.com
//
//1. sportName: Name of the game.
//2. teamsName: (India vs Pakistan)
//o Methods:
//1. Implement default constructors and overload the constructor with Customer
//attributes, generate getter and setter methods.
//2. display_sport_details(): Display concert details, including the artist.

public class Sports extends Event{
	private String sportName;
	private String teamsName;
	
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getTeamsName() {
		return teamsName;
	}
	public void setTeamsName(String teamsName) {
		this.teamsName = teamsName;
	}
	
	public Sports() {
		
	}
	
	public Sports(String sportName,String teamsName) {
		this.sportName = sportName;
		this.teamsName = teamsName;
	}
	
	public void displaySportDetails(Sports s) {
		System.out.println("Sports:\nSportName: "+sportName+"\nTeamsName: "+teamsName);
	}
	
}
