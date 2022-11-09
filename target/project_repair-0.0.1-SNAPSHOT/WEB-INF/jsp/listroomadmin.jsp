<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.springmvc.model.*"%>
<%@ page import="com.springmvc.controller.*"%>
<% 
	Admin admin = (Admin)session.getAttribute("admin");
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
				data-toggle = "dropdown" href="#"><span
					class="glyphicon glyphicon-cog"></span><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span>
							ออกจากระบบ</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>

	<div class="container">
		<table class="table table-hover table-bordered ">
			<tr class="success">
				<th colspan="7"><center><%= "รายการค่าใช่จ่ายห้องพัก" %></center></th>
			</tr>
			<tr class="info">
				<th><center>หมายเลขห้อง</center></th>
				<th><center>ชั้น</center></th>
				<th><center>ประเภทห้อง</center></th>
				<th colspan="2"><center>ผู้เช่า</center>	</th>
				<th colspan="2"><center>ใบแจ้งค่าเช่า</center></th>
			</tr>
			<tr class="info">
				<th colspan="3"></th>
				<th><center>ตรวจสอบ</center></th>
				<th><center>เพิ่ม</center></th>
				<th><center>ตรวจสอบ</center></th>
				<th><center>เพิ่ม</center></th>
			</tr>
			
			<% int count = 0; %>
			<% for(Room r : admin.getRoom()) { %>
				<tr class="active">
					<td><center><%= r.getRoomid() %></center></td>
					<td><center><%= r.getFloor() %></center></td>
					<td><center><%= r.getType().getTypename() %></center></td>
					<td>
						<center>
							<% if(r.getUsers().isEmpty()) { %>
								ไม่มีผู้เช่า
							<% } else { %>
								<button type="button" class="btn btn-success" data-toggle="modal" data-target="#viewUser<%= count %>">
									<span class="glyphicon glyphicon-user"></span>
								</button>
							<% } %>
						</center>
					</td>
					<!-- Add user -->
					<td>
						<center>
							<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addUser<%= count %>">
									<span class="glyphicon glyphicon-plus"></span>
							</button>
						</center>
					</td>
					<!-- end Add user -->
					<% if(r.getUsers().isEmpty()) { %>
						<td><center>-</center></td>
						<td><center>-</center></td>
					<% } else { %>
						<td><center>
							<a
								href="${pageContext.request.contextPath}/roombilladmin?roomid=<%= r.getRoomid() %>">
								<button type="button" class="btn btn-success">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</a> 
						</center></td>
						<td><center>
							<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addRoombill<%= count %>">
									<span class="glyphicon glyphicon-plus"></span>
							</button>
						</center></td>
					<% } %>
				</tr>
				<% count++; %>
			<% } %>
		</table>
	</div>
	<% int select = 0; %>
	<% for(Room r : admin.getRoom()) { %>
		<!-- Add user modal -->
		<div class="modal fade" id="addUser<%= select %>" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">เพิ่มผู้เช่าห้อง <%= r.getRoomid() %></h4>
					</div>
					<form name="frm" action="${pageContext.request.contextPath}/addUser" method="post">
						<div class="modal-body">
							<table class="table">
								<input type="hidden" name="roomid" value="<%= r.getRoomid() %>">
								<tr>
									<th colspan="2"><center>รายละเอียดผู้เช้า</center></th>
								</tr>
								<tr>
									<th>ชื่อผู้ใช้ :</th>
									<td><input type="text" name="username"></td>
								</tr>
								<tr>
									<th>รหัสผ่าน :</th>
									<td><input type="password" name="password"></td>
								</tr>
								<tr>
									<th>ยืนยันรหัสผ่าน :</th>
									<td><input type="password" name="confirm_password"></td>
								</tr>
								<tr>
									<th>ชื่อ - นามสกุล :</th>
									<td><input type="text" name="fullname"></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-success" value="บันทึก">
							<input type="reset" class="btn btn-danger" data-dismiss="modal" value="ยกเลิก">
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- end Add user modal -->
		<% if(r.getUsers().isEmpty()) { %>
			<% select++; %>
		<% } else { %>
			<div class="modal fade" id="viewUser<%= select %>" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">รายละเอียดผู้เช่าห้อง <%= r.getRoomid() %></h4>
						</div>
						<div class="modal-body">
							<table class="table">
							<% for(int i = 0; i < r.getUsers().size(); i++) { %>
								<tr>
									<th>ชื่อผู้เช่า</th>
									<td><%= r.getUsers().get(i).getFullname() %></td>
								</tr>
							<% } %>
							</table>
						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-danger" data-dismiss="modal" value="ปิด">
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal fade" id="addRoombill<%= select %>" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">เพิ่มใบชำระเงินห้อง <%= r.getRoomid() %></h4>
						</div>
						<form name="frm" action="${pageContext.request.contextPath}/addRoombill" method="post">
							<div class="modal-body">
								<table class="table">
									<tr>
										<th colspan="2"><center>ค่าเช่าห้องพัก</center></th>
									</tr>
									<tr>
										<th>หมายเลขห้อง :</th>
										<td><input type="text" name="roomid" value="<%= r.getRoomid() %>" readonly></td>
									</tr>
									<tr>
										<th>ประเภทห้องพัก :</th>
										<td><input type="text" value="<%= r.getType().getTypename() %>" readonly></td>
										<input type="hidden" name="typeid" value="<%= r.getType().getTypeid() %>">
									</tr>
									<tr>
										<th>ค่าเช่าห้องพัก  (บาท):</th>
										<td><input type="text" name="roomcost" value="<%= r.calRoomcost() %>" readonly></td>
									</tr>
									<tr>
										<th colspan="2"><center>ค่าน้ำ</center></th>
									</tr>
									<tr>
										<th>เลขมาตรครั้งก่อน :</th>
										<% if(r.getRoombill().isEmpty()) { %>
											<td><input type="text" name="waterbilllast"></td>
										<% } else { %>
											<td><input type="text" name="waterbilllast" value="<%= r.getRoombill().get(r.getRoombill().size()-1).getWater_bill().getCurrent_unit() %>" readonly></td>
										<% } %>
									</tr>
									<tr>
										<th>เลขมาตรปัจจุบัน :</th>
										<td><input type="text" name="waterbillcurrent"></td>
									</tr>
									<tr>
										<th colspan="2"><center>ค่าไฟฟ้า</center></th>
									</tr>
									<tr>
										<th>เลขอ่านครั้งก่อน :</th>
										<% if(r.getRoombill().isEmpty()) { %>
											<td><input type="text" name="electricbilllast"></td>
										<% } else { %>
											<td><input type="text" name="electricbilllast" value="<%= r.getRoombill().get(r.getRoombill().size()-1).getElectric_bill().getCurrent_unit() %>" readonly></td>
										<% } %>
									</tr>
									<tr>
										<th>เลขอ่านปัจจุบัน :</th>
										<td><input type="text" name="electricbillcurrent"></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<input type="submit" class="btn btn-success" value="บันทึก">
								<input type="reset" class="btn btn-danger" data-dismiss="modal" value="ยกเลิก">
							</div>
						</form>
					</div>
				</div>
			</div>
			<% select++; %>
		<% } %>
	<% } %>
</body>
</html>