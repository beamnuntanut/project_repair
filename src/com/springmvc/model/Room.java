package com.springmvc.model;

import java.util.Vector;

public class Room {
	
	private String roomid;
	private String floor;
	private TypeRoom type;
	private Vector<Users> users;
	private Vector<RoomBill> roombill;
	
	public Room() {
		super();
	}

	public Room(String roomid, String floor, TypeRoom type, Vector<Users> users, Vector<RoomBill> roombill) {
		super();
		this.roomid = roomid;
		this.floor = floor;
		this.type = type;
		this.users = users;
		this.roombill = roombill;
	}
	
	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public TypeRoom getType() {
		return type;
	}

	public void setType(TypeRoom type) {
		this.type = type;
	}

	public Vector<Users> getUsers() {
		return users;
	}

	public void setUsers(Vector<Users> users) {
		this.users = users;
	}

	public Vector<RoomBill> getRoombill() {
		return roombill;
	}

	public void setRoombill(Vector<RoomBill> roombill) {
		this.roombill = roombill;
	}

	public int calRoomcost() {
		if("1".equals(type.getTypeid())) {
			return 3000;
		}
		else if("2".equals(type.getTypeid())) {
			return 2500;
		}
		return 0;
	}
}
