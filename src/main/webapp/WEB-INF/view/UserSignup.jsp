<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.example.demo.model.User"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Signup</title>
</head>
<body>
<form:form modelAttribute="user" action="/user/signup" method="post">
<label>
Username
</label>
<form:input path="username" type="text" />
<label>
Password
</label>
<form:input path="password" type="password"/>
<label>
Email
</label>
<form:input path="email" type="email"/>
<label>
Phone Number
</label>
<form:input path="phoneNumber" type="tel"/>
<form:button>submit</form:button>
</form:form>
</body>
</html>