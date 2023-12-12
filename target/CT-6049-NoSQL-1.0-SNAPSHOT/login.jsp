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
    <title>Login</title>
</head>
<body>
<form action="login" method="post">
    Student Number <input type="text" name="snum" id="snum"><br>
    Password <input type="password" name="pword" id="pword"><br>

    <input type="submit" name="login" value="Login"><br>

</form>
<%
    //recives login details from login.java
    String reply = (String)request.getAttribute("reply");
    if (reply != null){
        if (!reply.isEmpty()) {

%>
    <p><%=reply%><p><br>
<%
        }
    }
        %>

<a href="register.jsp">Register</a>
</body>
</html>