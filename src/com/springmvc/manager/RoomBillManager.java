package com.springmvc.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.springmvc.model.ElectricBill;
import com.springmvc.model.RoomBill;
import com.springmvc.model.WaterBill;

public class RoomBillManager {
	public Vector<RoomBill> getRoomBill(String roomid) {
		Vector<RoomBill> listbill = new Vector<RoomBill>();
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			String sql = "select room_billid,dates,roomid,typeid,room_cost,price,status from room_bill where roomid ='"+roomid+"';";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				RoomBill bill = new RoomBill();
				bill.setRoom_billid(rs.getString(1));
				bill.setDate(rs.getDate(2));
				bill.setRoom_id(rs.getString(3));
				bill.setTypeid(rs.getString(4));
				bill.setRoom_cost(rs.getInt(5));
				bill.setPrice(rs.getDouble(6));
				bill.setStatus(rs.getString(7));
				bill.setWater_bill(getWaterBill(bill.getRoom_billid()));
				bill.setElectric_bill(getElectricBill(bill.getRoom_billid()));
				listbill.add(bill);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return listbill;
	}
	
	public WaterBill getWaterBill(String room_billid) {
		WaterBill bill = new WaterBill();
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			String sql = "select water_billid,dates,last_unit,current_unit,amount,price from water_bill where room_billid ='"+room_billid+"';";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bill.setBillid(rs.getString(1));
				bill.setDates(rs.getDate(2));
				bill.setLast_unit(rs.getInt(3));
				bill.setCurrent_unit(rs.getInt(4));
				bill.setAmount(rs.getInt(5));
				bill.setPrice(rs.getDouble(6));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return bill;
	}
	
	public ElectricBill getElectricBill(String room_billid) {
		ElectricBill bill = new ElectricBill();
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			String sql = "select electric_billid,dates,last_unit,current_unit,amount,price from electric_bill where room_billid ='"+room_billid+"';";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bill.setBillid(rs.getString(1));
				bill.setDates(rs.getDate(2));
				bill.setLast_unit(rs.getInt(3));
				bill.setCurrent_unit(rs.getInt(4));
				bill.setAmount(rs.getInt(5));
				bill.setPrice(rs.getDouble(6));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return bill;
	}
	
	public void addRoomBill(RoomBill roombill) {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		try {
			CallableStatement cstmt = con.prepareCall("{call insertRoomBill(?,?,?,?,?,?,?)}");
			cstmt.setString(1, roombill.getRoom_billid());
			cstmt.setDate(2, roombill.getDate());
			cstmt.setString(3, roombill.getRoom_id());
			cstmt.setString(4, roombill.getTypeid());
			cstmt.setInt(5, roombill.getRoom_cost());
			cstmt.setDouble(6, roombill.getPrice());
			cstmt.setString(7, roombill.getStatus());
			cstmt.execute();
			this.addElectricBill(roombill);
			this.addWaterBill(roombill);
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addElectricBill(RoomBill bill) {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		try {
			CallableStatement cstmt = con.prepareCall("{call insertElectricBill(?,?,?,?,?,?,?)}");
			cstmt.setString(1, bill.getElectric_bill().getBillid());
			cstmt.setDate(2, bill.getElectric_bill().getDates());
			cstmt.setInt(3, bill.getElectric_bill().getLast_unit());
			cstmt.setInt(4, bill.getElectric_bill().getCurrent_unit());
			cstmt.setInt(5, bill.getElectric_bill().getAmount());
			cstmt.setDouble(6, bill.getElectric_bill().getPrice());
			cstmt.setString(7, bill.getRoom_billid());
			cstmt.execute();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addWaterBill(RoomBill bill) {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		try {
			CallableStatement cstmt = con.prepareCall("{call insertWaterBill(?,?,?,?,?,?,?)}");
			cstmt.setString(1, bill.getWater_bill().getBillid());
			cstmt.setDate(2, bill.getWater_bill().getDates());
			cstmt.setInt(3, bill.getWater_bill().getLast_unit());
			cstmt.setInt(4, bill.getWater_bill().getCurrent_unit());
			cstmt.setInt(5, bill.getWater_bill().getAmount());
			cstmt.setDouble(6, bill.getWater_bill().getPrice());
			cstmt.setString(7, bill.getRoom_billid());
			cstmt.execute();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void confirmRoomBill(String room_billid) {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		try {
			CallableStatement cstmt = con.prepareCall("{call confirmRoomBill(?)}");
			cstmt.setString(1, room_billid);
			cstmt.execute();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void editRoomBill(String room_billid) {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();

		try {
			CallableStatement cstmt = con.prepareCall("{call editRoomBill(?)}");
			cstmt.setString(1, room_billid);
			cstmt.execute();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteRoomBill(String room_billid) {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		try {
			CallableStatement cstmt = con.prepareCall("{call deleteRoomBill(?)}");
			cstmt.setString(1, room_billid);
			cstmt.execute();
			con.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}