package com.kh.example.practice2.model;

public class Music {

	private String artist;
	private String song;
	
	public Music() {
	}
	public Music(String artist, String song) {
		this.artist = artist;
		this.song = song;
	}
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
	}
	
	@Override
	public String toString() {
		return artist + " - " + song;
	}

}
