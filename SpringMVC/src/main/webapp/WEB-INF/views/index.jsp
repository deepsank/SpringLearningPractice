<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
    <h1>This is my Home Page !!! </h1>
    <h1>Called from Home Controller </h1>
    <h1>url /home </h1>

    <%
     String name = (String)request.getAttribute("name");
     Integer id = (Integer)request.getAttribute("id");
     List<String> pvc = (List<String>)request.getAttribute("pvc");
     %>

     <h1>Name is <%=name%></h1>
     <h1>Id is <%=id%></h1>
     <h1>
     Param Veers :-- <%
     for(String s: pvc){
     //out.println(s);
     %>
     <h1><%=s%></h1>
     <%
     }

     %>


     </h1>
</body>
</html>