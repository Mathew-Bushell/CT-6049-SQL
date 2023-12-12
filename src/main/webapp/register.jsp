<%--
  Created by IntelliJ IDEA.
  User: mathe
  Date: 11/11/2023
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="/CT-6049-NoSQL-1.0-SNAPSHOT/register" method="post">
    First Name <input type="text" name="fname"><br>
    Last Name <input type="text" name="lname"><br>
    Student Number <input type="text" name="snum"><br>
    Password <input type="password" name="pword"><br>
    <input type="submit" name="register" value="Register">
</form>
</body>
</html>
