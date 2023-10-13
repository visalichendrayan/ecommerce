<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@page import="com.example.demo.model.Category"%>
     <%@page import="com.example.demo.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Products</title>
</head>
<body>
<a href="/admin/manageprod">Manage Products</a>
<a href="/admin/logout">Logout</a>
<br/>
<table style="border: thick;">
<tr>
<th>Product Id</th>
<th>Product Name</th>
<th>Price</th>
<th>Category</th>
<th>Date Added</th>
</tr>

<c:forEach items="${prods}" var="prod" varStatus="loop">
<tr>
<td>${prod.getProductId()}</td>
<td>${prod.getProductName()}</td>
<td>${prod.getPrice()}</td>
<td>
<c:forEach items="${prod.getCategoryIds()}" var="cate" varStatus="loop">
<span>
${cate.getCategoryName()}
</span>
<br/>
</c:forEach>

</td>
<td>${prod.getDateAdded()}</td>
</tr>
</c:forEach>

</table>
</body>
</html>