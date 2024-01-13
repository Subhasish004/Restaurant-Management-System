<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.dao.TableDAO" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/reservation.css">
<title>Reservation</title>
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
<div class="wrapper">
    <h2>Reservation</h2>
    <form action="ReservationController" method="post">
      <div class="input-box">
        <input type="text" name="customerName" placeholder="Enter your name" required>
      </div>
      <div class="input-box">
        <input type="date" name="date" placeholder="Enter your date" required>
      </div>
      <div> <select class="form-control" name="time"style="width: 365px; height: 45px;" required >
        <option>Select Party Time</option>
        <option value="12-2">12:00am - 2:00pm</option>
        <option value="2-4">2:00pm - 4:00pm</option>
        <option value="6-8">6:00pm - 8:00pm</option>
        <option value="8-10">8.00pm - 10.00pm</option>
      </select></div> <br>

       <div > <select class="form-control" id="partySize" name="partySize" style="width: 365px; height: 45px;" required  >
            <option value="" selected="selected">Enter Party Size</option>
    </select></div><br>

      <div class="input-box">
        <input type="text"  name="phoneNumber" placeholder="Enter your phoneNumber" required>
      </div>
      
        <div><select class="form-control" id="tableId" name="tableId"  style="width: 365px; height: 45px;" required  >
            <option value="" selected="selected">Select Table Number</option>
          </select>
        
      </div>
      
      <div class="input-box button">
        <input type="Submit" value="Reserve Now" onclick="checker()">
        
      </div>
    </form>
  </div>
  <script>
  function checker(){
   var FailMsg = "<%= (String)request.getAttribute("Fail") %>";
  if(FailMsg == "Fail"){
	  Failmsg = null;
	  alert("Table Unavailable Reservation Failed");
  }
  }
    var Party = {
      "1": ["1", "12", "13"],
      "2": ["2", "6"],
      "3": ["3", "8"],
      "4": ["4", "10", "11"],
      "5": ["5", "7", "9"]
    }
  
    window.onload = function() {
      var PartySize = document.getElementById("partySize");
      var TableID = document.getElementById("tableId");
  
      for (var x in Party) {
        PartySize.options[PartySize.options.length] = new Option(x, x);
      }
  
      PartySize.onchange = function() {
        TableID.length = 1;
  
        for (var y in Party[this.value]) {
          TableID.options[TableID.options.length] = new Option(Party[this.value][y], Party[this.value][y]);
        }
      }
    }
  </script>
  <footer>
    <p>&copy; 2023 Your Restaurant. All rights reserved.</p>
</footer>
</body>
</html>