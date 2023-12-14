<%@ page import="java.util.List" %>
<%@ page import="com.mongodb.client.MongoClient" %>
<%@ page import="org.json.JSONString" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: Mathew
  Date: 21/11/2023
  Time: 15:53
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
    </style>
    <title>Books</title>
</head>
<body>
<%
    String SNum = (String)request.getAttribute("snum");
%>
<%--Hi this is books page--%>
<nav>
<ul style="list-style-type: none; margin: 0; padding: 0;">
    <li><form action="books" method="post"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"><input type="submit" value="Home"></form></li>
    <li><form action="profile" method="post"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"><input type="submit" value="Profile"></form></li>
</ul>

</nav>
<br>
<br>
<br>
<div style="align-content: center; width: 100%;">
    <table style="width: 100%;">
      <tbody style="width: 100%;">
        <tr>
            <th style="width: 15%;">Title</th>
            <th style="width: 10%;">Genre</th>
            <th style="width: 30%;">Abstract</th>
            <th style="width: 15%;">Author</th>
            <th style="width: 10%;">Publisher</th>
            <th style="width: 12%;">Published</th>
            <th style="width: 8%;"></th>
        </tr>
      <%

          JSONArray Books = (JSONArray)request.getAttribute("books");

          if (Books != null){
              if (!Books.isEmpty()){
                  for (int i = 0; i < Books.length(); i++) {
                      JSONObject Book = Books.getJSONObject(i);
                      if (Book != null) {
                          String Title = Book.getString("TITLE");
                          String Genre = Book.getString("GENRE");
                          String Abstract = Book.getString("ABSTRACT");
                          String Author = Book.getString("AUTHOR");
                          String Publisher = Book.getString("PUBLISHER");
                          String Published = Book.getString("PUBLISHED");
                          String ISBN = Book.getString("ISBN");

      %>
        <tr>
            <td><%=Title%></td>
            <td><%=Genre%></td>
            <td><%=Abstract%></td>
            <td><%=Author%></td>
            <td><%=Publisher%></td>
            <td><%=Published%></td>
          <td><form action="bookDetails" method="post"><input type="submit" value="View"><input style="visibility: hidden; width:1px;" type="text" name="ISBN" value="<%=ISBN%>"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"></form></td>
        </tr>
        <%
                        }else{
                          %><p><%=Book%></p> <%
                        }
                }
              }
          }
        %>
      </tbody>
    </table>
</div>
</body>
</html>
