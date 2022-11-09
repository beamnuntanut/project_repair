<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.springmvc.model.*"%>
<%@ page import="com.springmvc.controller.*"%>
<% 
	Admin admin = (Admin)session.getAttribute("admin");
	Vector<RoomBill> bill = (Vector<RoomBill>)request.getAttribute("bill");
	String roomid = (String)request.getParameter("roomid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
@import url('https://fonts.googleapis.com/css?family=Kanit');
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body style="background-color: #99ccff; font-family: 'Kanit', sans-serif;">
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/admin">ITSCI</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a
				href="${pageContext.request.contextPath}/admin">Room</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					<%= admin.getFullname() %></a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><span
					class="glyphicon glyphicon-cog"></span><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span>
							ออกจากระบบ</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>

	<div class="container">
		<table class="table table-hover">
			<tr class="success">
				<th colspan="10"><center><%= "รายการค่าใช้จ่ายห้องพัก ห้อง "+roomid %></center></th>
			</tr>
			<tr class="info">
				<th><center>วันที่</center></th>
				<th><center>หมายเลขห้อง</center></th>
				<th><center>ประเภทห้อง</center></th>
				<th><center>ค่าเช่าห้องพัก (บาท)</center></th>
				<th><center>ค่าน้ำ (บาท)</center></th>
				<th><center>ค่าไฟฟ้า (บาท)</center></th>
				<th><center>รวมราคา (บาท)</center></th>
				<th><center>สถานะการชำระเงิน</center></th>
				<th><center>ยืนยันการชำระเงิน</center></th>
				<th><center>ลบ</center></th>
			</tr>
			<% int count = 0; %>
			<% for(RoomBill b : bill) { %>
			<tr class="active">
				<td><center><%= b.getDate() %></center></td>
				<td><center><%= b.getRoom_id() %></center></td>
				<td><center>
						<% if("1".equals(b.getTypeid())) { %>
						<%= "แอร์" %>
						<% } else if("2".equals(b.getTypeid())) { %>
						<%= "พัดลม" %>
						<% } %>
					</center></td>
				<td><center><%= b.getRoom_cost() %></center></td>
				<td><center>
						<a href="#waterbill<%= count %>" data-toggle="modal">
							<%= b.getWater_bill().getPrice() %>
						</a>
					</center></td>
					
				<td><center>
						<a href="#electricbill<%= count %>" data-toggle="modal" onclick="select(<%= count %>)">
							<%= b.getElectric_bill().getPrice() %>
						</a></center></td>
				<td><center><%= b.getPrice() %></center></td>
				<td><center><%= b.getStatus() %></center></td>
				<td><center>
					<% if("ชำระแล้ว".equals(b.getStatus())) { %>
						-
					<% } else { %>
						<a
							href="${pageContext.request.contextPath}/confirmbill?billid=<%= b.getRoom_billid() %>&roomid=<%= b.getRoom_id() %>">
							<button type="button" class="btn btn-success">
								<span class="glyphicon glyphicon-ok"></span>
							</button>
						</a>
					<% } %>
				</center></td>
				<td><center>
					<a
						href="${pageContext.request.contextPath}/deleteroombill?billid=<%= b.getRoom_billid() %>&roomid=<%= b.getRoom_id() %>">
						<button type="button" class="btn btn-danger">
							<span class="glyphicon glyphicon-trash"></span>
						</button>
					</a> 
				</center></td>
			</tr>
			<% count++; %>
			<% } %>
		</table>
	</div>
	
	<% for(int i = 0; i < count; i++) { %>
		<div class="modal fade" id="waterbill<%= i %>" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">รายละเอียดค่าน้ำห้อง <%= bill.get(0).getRoom_id() %></h4>
					</div>
						<div class="modal-body">
							<table class="table">
								<tr>
									<th>วันที่</th>
									<td><%= bill.get(i).getWater_bill().getDates() %></td>
								</tr>
								<tr>
									<th>เลขในมาตรครั้งก่อน</th>
									<td><%= bill.get(i).getWater_bill().getLast_unit() %></td>
								</tr>
								<tr>
									<th>เลขในมาตรครั้งปัจจุบัน</th>
									<td><%= bill.get(i).getWater_bill().getCurrent_unit() %></td>
								</tr>
								<tr>
									<th>จำนวนมาตรที่ใช้</th>
									<td><%= bill.get(i).getWater_bill().getAmount() %></td>
								</tr>
								<tr>
									<th>รวมเป็นเงิน (บาท)</th>
									<td><%= bill.get(i).getWater_bill().getPrice() %></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-danger" data-dismiss="modal" value="ปิด">
						</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="electricbill<%= i %>" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">รายละเอียดค่าไฟฟ้าห้อง <%= bill.get(0).getRoom_id() %></h4>
					</div>
						<div class="modal-body">
							<table class="table">
								<tr>
									<th>วันที่</th>
									<td><%= bill.get(i).getElectric_bill().getDates() %></td>
								</tr>
								<tr>
									<th>เลขอ่านครั้งก่อน</th>
									<td><%= bill.get(i).getElectric_bill().getLast_unit() %></td>
								</tr>
								<tr>
									<th>เลขอ่านครั้งปัจจุบัน</th>
									<td><%= bill.get(i).getElectric_bill().getCurrent_unit() %></td>
								</tr>
								<tr>
									<th>จำนวนที่ใช้</th>
									<td><%= bill.get(i).getElectric_bill().getAmount() %></td>
								</tr>
								<tr>
									<th>รวมเป็นเงิน (บาท)</th>
									<td><%= bill.get(i).getElectric_bill().getPrice() %></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-danger" data-dismiss="modal" value="ปิด">
						</div>
				</div>
			</div>
		</div>
	<% } %>
</body>
</html>