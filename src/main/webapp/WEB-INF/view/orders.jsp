<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.example.demo.model.Purchase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.example.demo.model.Category"%>
<%@ page import="com.example.demo.model.Product"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>View Orders</title>
</head>
<body>
	<a href="/user/cart">Back</a>
	<a href="/user/logout">Logout</a>
	
	<table>
		<tr>
			<th>Purchase Id</th>
			<th>Purchase Amount</th>
			<th>Purchase Date</th>
		</tr>
		<c:forEach items="${orders}" var="purItems" varStatus="status">


			<tr>

				<td>${purItems.getPurchaseId()}</td>
				<td>${purItems.getTotalAmt()}</td>
				<td>${purItems.getPurchaseDate()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>