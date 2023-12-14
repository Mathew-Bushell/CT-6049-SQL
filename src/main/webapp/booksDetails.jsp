<%@ page import="java.util.List" %>
<%@ page import="com.mongodb.client.MongoClient" %>
<%@ page import="org.json.JSONString" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: mathe
  Date: 26/11/2023
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }
        li {
            float:left;

        }
        table, th, td {
            border: 1px solid;
        }
        table{
            background-color: grey;
        }
        th, td{
            background-color: white;
        }
        div{
            align-self: center;
        }
    </style>
    <title>Book Details</title>
</head>
<body>
<nav>
    <ul style="list-style-type: none; margin: 0; padding: 0;">
        <li><form action="books" method="post"><input type="submit" value="Home"></form></li>
        <li><form><input type="submit" value="Profile"></form></li>
    </ul>

</nav><br>
<%
    JSONObject book = (JSONObject)request.getAttribute("book");

    if (book != null) {
        if (!book.isEmpty()){
            String Title = book.getString("TITLE");
            String Genre = book.getString("GENRE");
            String Abstract = book.getString("ABSTRACT");
            String Author = book.getString("AUTHOR");
            String Publisher = book.getString("PUBLISHER");
            String Published = book.getString("PUBLISHED");
            String ISBN = book.getString("ISBN");
            Integer Qunatity = book.getInt("QUANTITY");
            Integer Stock = (book.getInt("QUANTITY")-book.getInt("QOUT"));
            String SNum = (String)request.getAttribute("SNum");

%>
<div style="width: 90%">
    <h3><%=Title%></h3><p style="float: right">Author: <%=Author%></p>
    <p><%=Abstract%></p>
    <table style="width:100%">
        <tbody style="width:100%">
        <tr>
            <th style="width:25%;">Genre</th>
            <td style="width:75%;"><%=Genre%></td>
        </tr>
        <tr>
            <th>Publisher</th>
            <td><%=Publisher%></td>
        </tr>
        <tr>
            <th>Published</th>
            <td><%=Published%></td>
        </tr>
        <tr>
            <th>ISBN</th>
            <td><%=ISBN%></td>
        </tr>
        <tr>
            <th>Stock</th>
            <td><%=Stock%>/<%=Qunatity%></td>
        </tr>
        </tbody>
    </table><br>
    <form action="bookRent" method="post"><input type="submit" value="Rent"><input style="visibility: hidden; width:1px;" type="text" name="ISBN" value="<%=ISBN%>"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"></form>
</div>

<%
        }
    }
%>
</body>
</html>
