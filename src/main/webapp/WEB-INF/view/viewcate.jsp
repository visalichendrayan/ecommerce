<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@page import="com.example.demo.model.Category"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Category</title>
</head>
<body>
<a href="/admin/dashboard">Dashboard</a>
<a href="/admin/logout">Logout</a>
<br/>
<c:forEach items="${category}" var="cate" varStatus="loop">
<a href="/admin/category/${cate.getCategoryName()}">${cate.getCategoryName()}</a>
<br/>
</c:forEach>
</body>
</html>