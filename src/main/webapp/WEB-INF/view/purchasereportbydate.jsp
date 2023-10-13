<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchase Report By Date</title>
</head>
<body>
	<a href="/admin/dashboard">Dashboard</a>
	<a href="/admin/logout">Logout</a>
	<br />
	<form action="/admin/purchasereport/date" method="post">
		<label>Purchase Date</label><input type="date" name="purchasedate" />
		<input type="submit" />
	</form>

	<c:if test="${fn:length(purchase) > 0}">
		<table>
			<tr>
				<th>Purchase Id</th>
				<th>Purchase Amount</th>
				<th>Purchase Date</th>
				<th>User Id</th>
			</tr>
			<c:forEach items="${purchase}" var="purItems" varStatus="status">
				<tr>

					<td>${purItems.getPurchaseId()}</td>
					<td>${purItems.getTotalAmt()}</td>
					<td>${purItems.getPurchaseDate()}</td>
					<td>${purItems.getUserId()}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


</body>
</html>