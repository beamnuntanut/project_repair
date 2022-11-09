package com.springmvc.model;

import java.sql.Date;

public class RoomBill {
	private String room_billid;
	private Date date;
	private String room_id;
	private String typeid;
	private int room_cost;
	private double price;
	private String status;
	private ElectricBill electric_bill;
	private WaterBill water_bill;

	public RoomBill() {
		super();
	}

	public RoomBill(String room_billid, Date date, String room_id, String typeid, int room_cost, double price,
			String status, ElectricBill electric_bill, WaterBill water_bill) {
		super();
		this.room_billid = room_billid;
		this.date = date;
		this.room_id = room_id;
		this.typeid = typeid;
		this.room_cost = room_cost;
		this.price = price;
		this.status = status;
		this.electric_bill = electric_bill;
		this.water_bill = water_bill;
	}

	public String getRoom_billid() {
		return room_billid;
	}

	public void setRoom_billid(String room_billid) {
		this.room_billid = room_billid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public int getRoom_cost() {
		return room_cost;
	}

	public void setRoom_cost(int room_cost) {
		this.room_cost = room_cost;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ElectricBill getElectric_bill() {
		return electric_bill;
	}

	public void setElectric_bill(ElectricBill electric_bill) {
		this.electric_bill = electric_bill;
	}

	public WaterBill getWater_bill() {
		return water_bill;
	}

	public void setWater_bill(WaterBill water_bill) {
		this.water_bill = water_bill;
	}
	
	public double calPrice() {
		return electric_bill.getPrice() + water_bill.getPrice() + this.room_cost;
	}
}
