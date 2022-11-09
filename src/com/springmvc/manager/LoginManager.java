package com.springmvc.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.springmvc.model.LoginBean;

public class LoginManager {
	public String doLogin(LoginBean login) throws SQLException {
		ConnectionDB db = new ConnectionDB();
		Connection con = db.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		Statement stmt2 = null;
		ResultSet rs2 = null;
		try {
			String sql = "select username,passwords from login where username = '"+login.getUsername()+"'";
			
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					if(login.getPassword().equals(rs.getString(2))) {
						String sql1 = "select username from users where username = '"+login.getUsername()+"'";
						stmt1 = con.createStatement();
						rs1 = stmt1.executeQuery(sql1);
						String sql2 = "select username from admin where username = '"+login.getUsername()+"'";
						stmt2 = con.createStatement();
						rs2 = stmt2.executeQuery(sql2);
						if(rs1.next()) {
							return "login success users";
						}
						else if(rs2.next()) {
							return "login success admin";
						}
						return "invalid login";
					}
					else {
						return "invalid login";
					}
				}
				else {
					return "error";
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			return "login error";
		}finally {
			rs.close();
			stmt.close();
			con.close();
		}
	}
}