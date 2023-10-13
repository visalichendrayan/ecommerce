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
<title>Payment Gateway</title>
</head>
<body>
	<%
Purchase purchase=(Purchase)request.getAttribute("purchase");
session.setAttribute("purchase", purchase);
%>
	<a href="/user/orders">Orders</a>
	<a href="/user/logout">Logout</a>
	<br />
	<form action="/user/payment" method="post">


		<p>Total Amount ${purchase.getTotalAmt() }</p>
		<table>
			<tr>

				<th>ProductId</th>
				<th>ProductName</th>
				<th>Quantity</th>
			</tr>
			<c:forEach items="${purchase.purchaseItems}" var="purItems"
				varStatus="status">


				<tr>

					<td>${purItems.getProductId()}</td>
					<td>${purItems.getProductName()}</td>
					<td>${purItems.getQty()}</td>
				</tr>
			</c:forEach>
		</table>

		<label>CardNumber</label> <input name="cardnumber" type="tel"
			inputmode="numeric" pattern="[0-9\s]{13,19}" autocomplete="cc-number"
			maxlength="19" placeholder="xxxx xxxx xxxx xxxx" /> <label>CardHolder
			Name</label> <input name="holdername" type="text" /> <input type="submit"
			name="submit" value="submit" />
	</form>
</body>
</html>