<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.example.demo.model.Category"%>
<%@ page import="com.example.demo.model.Product"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
</head>
<body>
<a href="/user/orders">Orders</a>
<a href="/user/logout">Logout</a>
<br/><br/>
	<form:form method="post" action="/user/cart" modelAttribute="cartform">
		<table>
			<tr>
				<th>ProductId</th>
				<th>ProductName</th>
				<th>Price</th>
				
				<th>Quantity</th>
			</tr>
			<c:forEach items="${cartform.cartItems}" var="cartItems"
				varStatus="status">
				<tr>
					<td><input name="cartItems[${status.index}].productId"
						value="${cartItems.productId}" /></td>
					<td><input name="cartItems[${status.index}].productName"
						value="${cartItems.productName}" /></td>
					<td><input name="cartItems[${status.index}].price"
						value="${cartItems.price}" /></td>
					<td><input name="cartItems[${status.index}].qty"
						value="${cartItems.qty}" type="number" step="1" /></td>
				</tr>
			</c:forEach>
		</table>
		<br />
		
<form:button name="submit">Submit</form:button>
	</form:form>
</body>
</html>