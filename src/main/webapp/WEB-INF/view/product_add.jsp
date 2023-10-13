
<%@page import="java.util.List"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.example.demo.service.CategoryService"%>
<%@page import="com.example.demo.model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<a href="/admin/manageprod">Manage Products</a>
<a href="/admin/manageprod">Logout</a>
<br/>
<c:if test="${not empty message}">
<span>${message}</span>
</c:if>
<form:form modelAttribute="prod" action="/admin/product/add" method="post">
<label>
Product Name
</label>
<form:input path="productName" type="text"/>
<label>
Product Price
</label>
<form:input path="price" type="number"/>
<label>
Category
</label>
<form:select path="categoryIds">
<c:forEach items="${category}" var="catName" varStatus="loop">

    <form:option value="${catName.getCategoryId()}">
        ${catName.getCategoryName()}
    </form:option>
  </c:forEach>
</form:select>
<form:button>submit</form:button>
</form:form>
</body>
</html>