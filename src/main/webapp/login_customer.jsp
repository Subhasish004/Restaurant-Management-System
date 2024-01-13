<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/login_customer.css">
<title>Log In</title>
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
          <li><a href="reservation.jsp">Make a reservation</a></li>
        </ul>
    </nav>
</header>
 <div class="wrapper">
    <h2>Login</h2>
    <form action="loginController" method="post">
      <div class="input-box">
        <input type="text" name="customerName" placeholder="Enter your name" required>
      </div>
      <div class="input-box">
        <input type="text" name="phoneNumber" placeholder="Enter your phone Number" required>
      </div>
      <div class="input-box button">
        <input type="Submit" value="Login Now">
        </div>
        </form>
 </div>
        <footer>
          <p>&copy; 2023 Your Restaurant. All rights reserved.</p>
      </footer>
</body>
</html>