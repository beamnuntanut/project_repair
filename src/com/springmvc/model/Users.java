package com.springmvc.model;

public class Users {
	private String fullname;
	private Room room;
	
	public Users() {
		super();
	}
		
	public Users(String fullname, Room room) {
		super();
		this.fullname = fullname;
		this.room = room;
	}

	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
}
