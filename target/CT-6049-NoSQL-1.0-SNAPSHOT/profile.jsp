<%@ page import="java.util.List" %>
<%@ page import="com.mongodb.client.MongoClient" %>
<%@ page import="org.json.JSONString" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="org.bson.types.ObjectId" %>
<%--
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
<%--Hi this is Profile page--%>
<%
    String SNum = (String)request.getAttribute("SNum");
%>
<nav>
    <ul style="list-style-type: none; margin: 0; padding: 0;">
        <li><form action="books" method="post"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"><input type="submit" value="Home"></form></li>
        <li><form action="profile" method="post"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"><input type="submit" value="Profile"></form></li>
    </ul>

</nav>
<br>
<br>
<br>
<div style="width:100%;">
    <div style="align-content: center; width: 30%; float: left; padding-right: 5px;">
        <table style="width: 100%;">
            <tbody style="width: 100%;">
            <tr>
                <th style="width: 15%;">Name</th>
                <td><%=request.getAttribute("FName")+" "+request.getAttribute("LName")%></td>
            </tr>
            <tr>
                <th style="width: 10%;">Student Number</th>
                <td><%=request.getAttribute("SNum")%></td>
            </tr>
            <tr>
                <th style="width: 30%;">Password</th>
                <td><%=request.getAttribute("Password")%></td>
            </tr>
            </tbody>
        </table>
        <br>
        <br>
        <form action="Report" method="post">View report for the <select name="Month">
            <option value="1" selected>January</option>
            <option value="2">Febuary</option>
            <option value="3">March</option>
            <option value="4">April</option>
            <option value="5">May</option>
            <option value="6">June</option>
            <option value="7">July</option>
            <option value="8">August</option>
            <option value="9">September</option>
            <option value="10">October</option>
            <option value="11">November</option>
            <option value="12">December</option>
            <input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>">
        </select> month : <input type="submit" value="View">
        </form>


    </div>
<div style="align-content: center; width: 58%; float: right; padding-left: 5px;">
    <table style="width: 100%;">
      <tbody style="width: 100%;">
        <tr>
            <th style="width: 15%;">Title</th>
            <th style="width: 30%;">Taken Out</th>
            <th style="width: 30%;">Return By</th>
            <th style="width: 15%;">Fine</th>
            <th style="width: 10%;"></th>
        </tr>
      <%

          JSONArray Loans = (JSONArray)request.getAttribute("loans");


          if (Loans != null){
              if (!Loans.isEmpty()){
                  for (int i = 0; i < Loans.length(); i++) {
                      JSONObject Loan = Loans.getJSONObject(i);
                      if (Loan != null) {
                          String Title = Loan.getString("TITLE");
                          String LOut = Loan.getString("LOUT");
                          String LReturn = Loan.getString("LRETURN");
                          Integer Fine= Loan.getInt("AFINE");
                          String ISBN = Loan.getString("ISBN");
                          String Status = Loan.getString("STATUS");



      %>
        <tr>
            <td><%=Title%></td>
<%--            <td>placeholder</td>--%>
            <td><%=LOut%></td>
            <td><%=LReturn%></td>
<%--            <td>placeholder</td>--%>
            <td><%=Fine%></td>
            <%
                if (Status.equals("Rented") || Status.equals("Paid")){
//                    style="visibility: hidden; width:1px;"
          %><td><form action="Return" method="post"><input  type="submit" value="Return"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"><input style="visibility: hidden; width:1px;" type="text" name="ISBN" value="<%=ISBN%>"><input style="visibility: hidden; width:1px;" type="text" name="LOut" value="<%=LOut%>"></form></td>
            <%}else if((Status.equals("Overdue"))){
            %><td><form action="payFine" method="post"><input type="submit" value="Pay"><input style="visibility: hidden; width:1px;" type="text" name="SNum" value="<%=SNum%>"><input style="visibility: hidden; width:1px;" type="text" name="ISBN" value="<%=ISBN%>"><input style="visibility: hidden; width:1px;" type="text" name="LOut" value="<%=LOut%>"></form></td>
            <% }else{ %>
            <td>Returned</td>
            <% } %>
        </tr>
        <%
                        }else{
                          %><p><%=Loan+"Failure"%></p> <%
                        }
                }
              }
          }
        %>
      </tbody>
    </table>
</div>
</div>
</body>
</html>
