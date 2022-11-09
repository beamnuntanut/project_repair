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
		<center>Error</center>
	</div>
</body>
</html>