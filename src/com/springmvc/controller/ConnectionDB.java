package com.springmvc.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	Connection conn;
	String url = "jdbc:mysql://localhost:3307/project_repair?useSSL=false&characterEncoding=UTF-8";
	String uname = "root";
	String pwd = "1234";

	public Connection getConnection() {
		conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, uname, pwd);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
