<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation Sucessfull</title>
<link rel="stylesheet" href="css/reservationsucessful.css">
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
                <li><a href="login_customer.jsp">Log In</a></li>
            </ul>
        </nav>
    </header>

    <center>
        <div class="card">
            <div class="card-header">
                <h2>Reservation Successful</h2>
            </div>
            <div class="card-content">
                <p><strong>Name:</strong> <%= request.getAttribute("Name") %></p>
                <p><strong>Phone Number:</strong> <%= request.getAttribute("PhoneNo") %></p>
                <p><strong>Table:</strong> <%= request.getAttribute("Table") %></p>
                <p><strong>Date:</strong> <%= request.getAttribute("Date") %></p>
                <p><strong>Time:</strong> <%= request.getAttribute("Time") %></p>
                <p><strong>Party Size:</strong> <%= request.getAttribute("partysize") %></p>
                <p><strong>ID:</strong> <%= request.getAttribute("id") %></p>
            </div>
        </div>
    </center>
<footer>
    <p>&copy; 2023 Your Restaurant. All rights reserved.</p>
</footer>
</body>
</html>