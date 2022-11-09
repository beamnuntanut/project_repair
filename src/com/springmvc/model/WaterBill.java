package com.springmvc.model;

import java.sql.Date;

public class WaterBill {
	private String billid;
	private Date dates;
	private int last_unit;
	private int current_unit;
	private int amount;
	private double price;
	private Room room;
	
	public WaterBill() {
		super();
	}

	public WaterBill(String billid, Date dates, int last_unit, int current_unit, int amount, double price, Room room) {
		super();
		this.billid = billid;
		this.dates = dates;
		this.last_unit = last_unit;
		this.current_unit = current_unit;
		this.amount = amount;
		this.price = price;
		this.room = room;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public int getLast_unit() {
		return last_unit;
	}

	public void setLast_unit(int last_unit) {
		this.last_unit = last_unit;
	}

	public int getCurrent_unit() {
		return current_unit;
	}

	public void setCurrent_unit(int current_unit) {
		this.current_unit = current_unit;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public double calWaterPrice() {
		return calWaterAmount() * 15.0;
	}
	
	public int calWaterAmount() {
		return current_unit - last_unit;
	}
}
