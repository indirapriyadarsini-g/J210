package com.hexaware.entity;

import java.time.LocalDate;

public class Artist {
	private int artistId;
	private String name;
	private String biography;
	private LocalDate birthDate;
	private String nationality;
	private String website;
	private String contactInformation;
	
	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
}
