<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="ISO-8859-1">

    <title>File Upload...</title>
</head>
<body>
    <h1>${msg}</h1>
    <img alt="profile image" src="<c:url value="/resources/image/${filename}"/> " style="width: 100%; height: 100%;"/>
    <h2>File Name: ${filename}</h2>
</body>
</html> 