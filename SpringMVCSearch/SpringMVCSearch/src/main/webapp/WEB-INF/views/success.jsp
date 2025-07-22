<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Success</title>
</head>
<body>
    <h1>Form Submitted Successfully</h1>
    <h1>Name is ${student.name}</h1>
    <h1>Student ID is ${student.id}</h1>
    <h1>Date of Birth is ${student.date}</h1>
    <h1>Subjects are ${student.subjects}</h1>
    <h1>Gender is ${student.gender}</h1>
    <h1>Student type is ${student.type}</h1>
    <h1>Address Details:</h1>
    <h1>Street is ${student.address.street}</h1>
    <h1>City is ${student.address.city}</h1>
</body>
</html>
