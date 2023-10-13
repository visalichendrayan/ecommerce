<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
<c:if test="${not empty message}">
<span>${message}</span>
</c:if>
	<form:form modelAttribute="user" action="/user/login">
		<label>Username</label>
		<form:input path="username" type="text" />
		<label>Password</label>
		<form:input path="password" type="password" />
		<form:button>Submit</form:button>
	</form:form>
</body>
</html>