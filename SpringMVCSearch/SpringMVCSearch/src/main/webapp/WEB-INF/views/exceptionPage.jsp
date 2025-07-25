<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Oops! Something Went Wrong</title>
    <style>
        body {
            background-color: #f8d7da;
            color: #721c24;
            font-family: Arial, sans-serif;
            text-align: center;
            padding-top: 100px;
        }
        .container {
            background: #fff;
            border: 1px solid #f5c6cb;
            display: inline-block;
            padding: 40px 60px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }
        h1 {
            font-size: 48px;
            margin-bottom: 10px;
        }
        p {
            font-size: 20px;
            margin-bottom: 30px;
        }
        a {
            color: #721c24;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Oops!</h1>
        <p>${msg}<br>
        Please try again later.</p>
        <a href="#!">Go to Home Page</a>
    </div>
</body>
</html>