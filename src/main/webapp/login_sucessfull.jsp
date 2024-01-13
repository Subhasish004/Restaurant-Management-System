<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="com.model.*" %>
    <%@ page import="com.controller.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/login_sucessful.css">
<title>Login Sucessfull</title>
</head>
<body>
    <header>
        <div class="logo">
            <img src="assets/imgs/food.jpg" alt="Restaurant Logo">
            <h1>Hotel Megha</h1>
        </div>
        <nav>
            <ul>
                <li><a href="Restaurant.jsp">Home</a></li>
                <li><a href="reservation.jsp">Make a Reservation</a></li>
                <li><a href="login_customer.jsp">Log In</a></li>
            </ul>
        </nav>
    </header>
    
    <%
    List<Reservation> customerDataList = (List<Reservation>) request.getAttribute("customerDataList");
      
    for (Reservation reservation : customerDataList) {
    
%>
<div class="card">

    <div class="card-content">
        <p><strong>ID:</strong> <%= reservation.getId() %></p>
        <p><strong>Customer Name: </strong><%= reservation.getCustomerName() %></p>
        <p><strong>Phone Number:</strong> <%= reservation.getPhoneNumber() %></p>
        <p><strong>Date:</strong> <%= reservation.getDate() %></p>
        <p><strong>Time:</strong>  <%= reservation.getTime() %></p>
        <p><strong>Party Size:</strong>  <%= reservation.getPartySize() %></p>
        <p><strong>Table:</strong> <%= reservation.getTableId() %></p>

        <ul>
            <a href="edit.jsp?reservationId=<%= reservation.getId() %>" class="btn" style="background-color: orange; color: white; font-weight: bold;">Edit</a>
            <a href="delete?reservationId=<%= reservation.getId() %>&customerName=<%= reservation.getCustomerName() %>" class="btn" style="background-color: red; color: white; font-weight: bold;">Delete</a>
            
        </ul>

    </div>
</div>
<%
    }
%>

<footer>
    <p>&copy; 2023 Your Restaurant. All rights reserved.</p>
</footer>
</body>
</html>