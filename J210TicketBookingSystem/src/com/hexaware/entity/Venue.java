package com.hexaware.entity;

//2. Venue Class
//• Attributes:
//o venue_name,
//o address
//• Methods and Constructors:
//o display_venue_details(): Display venue details.

public class Venue {
	private int venueId;
	private String venueName;
	private String address;
	
	
	public Venue(int venueId, String venueName, String address) {
		
		this.venueId = venueId;
		this.venueName = venueName;
		this.address = address;
	}
	
	 public void setVenueName(String venueName) {
	        this.venueName = venueName;
	    }

	    public String getVenueName() {
	        return venueName;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }


	    public String getAddress() {
	        return address;
	    }
	    
	    public void displayVenueDetails() {
	    	System.out.println("Venue Name: "+venueName+"\nAddress: "+address);
	    	
	    }

		public int getVenueId() {
			return venueId;
		}

		public void setVenueId(int venueId) {
			this.venueId = venueId;
		}
		
		@Override
		public String toString() {
			return "Venue [VenueId=" + venueId + ", venueName=" + venueName + ", address=" + address + "]";
		}
	
}
