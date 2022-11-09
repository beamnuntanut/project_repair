<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.springmvc.model.*"%>
<%@ page import="com.springmvc.controller.*"%>
<% 
 	Users user = (Users)session.getAttribute("user");
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
				href="${pageContext.request.contextPath}/users">ITSCI</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a
				href="${pageContext.request.contextPath}/users">Room</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					<%= user.getFullname() %></a></li>
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
				<th colspan="5"><center><%= "รายการค่าใช่จ่ายห้องพัก" %></center></th>
			</tr>
			<tr class="info">
				<th><center>หมายเลขห้อง</center></th>
				<th><center>ชั้น</center></th>
				<th><center>ประเภทห้อง</center></th>
				<th><center>ค่าห้องพัก</center></th>
			</tr>
			<tr class="active">
				<td><center><%= user.getRoom().getRoomid() %></center></td>
				<td><center><%= user.getRoom().getFloor() %></center></td>
				<td><center><%= user.getRoom().getType().getTypename() %></center></td>
				<td>
					<center>
						<a
							href="${pageContext.request.contextPath}/roombilluser?roomid=<%= user.getRoom().getRoomid() %>">
							<button type="button" class="btn btn-success">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</a>
					</center>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>