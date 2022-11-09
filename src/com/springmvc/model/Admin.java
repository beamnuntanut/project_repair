package com.springmvc.model;

import java.util.Vector;

public class Admin {
	private String fullname;
	private Vector<Room> room;
	
	public Admin() {
		super();
	}
	
	public Admin(String fullname, Vector<Room> room) {
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
	
	public Vector<Room> getRoom() {
		return room;
	}
	
	public void setRoom(Vector<Room> room) {
		this.room = room;
	}
}
