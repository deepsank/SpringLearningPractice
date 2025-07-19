<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Help Page</title>
</head>
<body>
    <h1>This is my Help Page !!! </h1>
    <h1>Deepak  Upadhyay </h1>
    <h1>url /help </h1>

    <%
    String skill_1 = (String) request.getAttribute("skill_1");
    String skill_2 = (String) request.getAttribute("skill_2");
    LocalDateTime date = (LocalDateTime) request.getAttribute("time");
    %>

    <h1>
        My skill 1 is :- <%=skill_1 %>
    </h1>
    <h1>
        My skill 2 is :- <%=skill_2 %>
    </h1>

    <h1>
            Time is :- <%=date %>
        </h1>
</body>
</html>