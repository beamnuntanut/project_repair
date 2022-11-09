<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function check(frm){
		if(frm.username.value=="" || frm.username.value==null){
			alert("กรุณากรอกชื่อผู้ใช้");
			frm.username.focus();
			return false;
		}
		if(frm.password.value=="" || frm.password.value==null){
			alert("กรุณากรอกรหัสผ่าน");
			frm.password.focus();
			return false;
		}
	}
</script>
<style>
@import url('https://fonts.googleapis.com/css?family=Kanit');
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Information Tecnology</title>
</head>
<body style="background-color:#99ccff; font-family: 'Kanit', sans-serif;">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">ITSCI</a>
			</div>
		</div>
	</nav>
<div class="container">
	<center><h3>ระบบจัดการหอพัก</h3></center>
	<form name="frm" action="${pageContext.request.contextPath}/login" method="post">
		<table class="table">
			<tr>
				<th colspan="2"><center>เข้าสู่ระบบ</center></th>
			</tr>
			<tr>
				<td><label for="username">ชื่อผู้ใช้ :</label></td>
				<td><input type="text" class="form-control" name="username" id="username"></td>
			</tr>
			<tr>
				<td><label for="password">รหัสผ่าน :</label></td>
				<td><input type="password" name="password" id="password" class="form-control"></td>
			</tr>
		</table>
		<center><label style="color:red;">${errorlogin}</label></center>
		<center><input type="submit" value="เข้าสู่ระบบ" onclick="return check(frm)"></center>
	</form>
</div>
</body>
</html>