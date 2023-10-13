<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchase Report By Category</title>
</head>
<body>
	<a href="/admin/dashboard">Dashboard</a>
	<a href="/admin/logout">Logout</a>
	<br />
	<form action="/admin/purchasereport/category" method="post">
		<select name="catename">
			<c:forEach items="${category}" var="catName" varStatus="loop">

				<option value="${catName.getCategoryName()}">
					${catName.getCategoryName()}</option>
			</c:forEach>
		</select> <input type="submit" value="submit" />
	</form>
	<c:if test="${fn:length(purchase) > 0}">
		<table>
			<tr>
				<th>Purchase Id</th>
				<th>Purchase Date</th>
				<th>User Id</th>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Qty</th>
			</tr>
			<c:forEach items="${purchase}" var="purItems" varStatus="status">
			<c:forEach items="${purItems.getPurchaseItems()}" var="pur" varStatus="status">
				<tr>

					<td>${purItems.getPurchaseId()}</td>
					<td>${purItems.getPurchaseDate()}</td>
					<td>${purItems.getUserId()}</td>
					<td>${pur.getProductId()}</td>
					<td>${pur.getProductName()}</td>
					<td>${pur.getQty()}</td>
				</tr>
			</c:forEach>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>