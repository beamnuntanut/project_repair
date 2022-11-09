package com.springmvc.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.springmvc.model.TypeRoom;
import com.springmvc.model.Users;
import com.springmvc.model.Admin;
import com.springmvc.model.LoginBean;
import com.springmvc.model.Room;
import com.springmvc.model.RoomBill;

public class UsersManager {
	
	public Admin doAdminDetail(String adminid) {
		Admin admin = new Admin();
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select fullname from admin where username = '"+adminid+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				admin.setFullname(rs.getString(1));
				admin.setRoom(getAllRoomDetail());
			}
		
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return admin;
	}
	
	public Users doUsersDetail(String userid) {
		Users user = new Users();
		 
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select users.fullname,invite.roomid from users,invite where users.username = invite.username and invite.username = '"+userid+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				user.setFullname(rs.getString(1));
				user.setRoom(doRoomDetail(rs.getString(2)));
			}
		
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return user;
	}
	
	public Room doRoomDetail(String roomid) {
		Room room = new Room();
		
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select roomid,floor,typeid from room where roomid = '"+roomid+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				room.setRoomid(rs.getString(1));
				room.setFloor(rs.getString(2));
				room.setType(doTypeRoomDetail(rs.getString(3)));
			}
		
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return room;
	}
	
	public Vector<Room> getAllRoomDetail() {
		Vector<Room> listroom = new Vector<Room>();
		RoomBillManager bill = new RoomBillManager();
		
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select roomid,floor,typeid from room";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				Room room = new Room();
				room.setRoomid(rs.getString(1));
				room.setFloor(rs.getString(2));
				room.setType(doTypeRoomDetail(rs.getString(3)));
				room.setUsers(doUsersInvite(room.getRoomid()));
				room.setRoombill(bill.getRoomBill(room.getRoomid()));
				listroom.add(room);
			}
		
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return listroom;
	}
	
	public Vector<Users> doUsersInvite(String roomid) {
		Vector<Users> listuser = new Vector<Users>();
		 
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select users.fullname from users,invite where users.username = invite.username and invite.roomid = '"+roomid+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Users user = new Users();
				user.setFullname(rs.getString(1));
				listuser.add(user);
			}
		
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return listuser;
	}
	
	public TypeRoom doTypeRoomDetail(String typeid) {
		TypeRoom typeroom = new TypeRoom();
		
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select typeid,typename from typeroom where typeid = '"+typeid+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				typeroom.setTypeid(rs.getString(1));
				typeroom.setTypename(rs.getString(2));	
			}
		
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return typeroom;
	}
	
	public void addUser(LoginBean login, Users user, String roomid) throws Exception {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
			CallableStatement cstmt = con.prepareCall("{call addUser(?,?,?,?)}");
			cstmt.setString(1, login.getUsername());
			cstmt.setString(2, login.getPassword());
			cstmt.setString(3, user.getFullname());
			cstmt.setString(4, roomid);
			cstmt.execute();
			con.close();
	}
}