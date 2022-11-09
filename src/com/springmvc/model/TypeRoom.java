package com.springmvc.model;

public class TypeRoom {
	private String typeid;
	private String typename;
	
	public TypeRoom() {
		super();
	}
	
	public TypeRoom(String typeid, String typename) {
		super();
		this.typeid = typeid;
		this.typename = typename;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
}
