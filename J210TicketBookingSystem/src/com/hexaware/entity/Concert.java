package com.hexaware.entity;

//Create another subclass Concert that inherits from Event. Add the following attributes and
//methods:
//o Attributes:
//1. artist: Name of the performing artist or band.
//2. type: (Theatrical, Classical, Rock, Recital)
//o Methods:
//1. Implement default constructors and overload the constructor with Customer
//attributes, generate getter and setter methods.
//2. display_concert_details(): Display concert details, including the artist.

public class Concert extends Event{    // IS A
	
	private String artist;
	private ConcertType type;
	
	enum ConcertType{
		Theatrical, Classical, Rock, Recital
	}

	public Concert() {
	
	}
	
	public Concert(String artist, String type) {
//		ConcertType concertType = 
	}
	
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setType(ConcertType type) {
        this.type = type;
    }
    
    public ConcertType getType() {
        return type;
    }
    
    public String displayConcertDetails() {
    	return ("Artist: "+artist+ "Concert Type: "+ type);
    }
}
	

