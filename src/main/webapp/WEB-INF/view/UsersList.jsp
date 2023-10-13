<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.example.demo.model.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users List</title>
</head>
<body>
	<a href="/admin/dashboard">Dashboard</a>
	<a href="/admin/logout">Logout</a>
	<table>
		<tr>
			<th>Username</th>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="loop">
			<tr>
				<td>${user.getUsername()}</td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>
