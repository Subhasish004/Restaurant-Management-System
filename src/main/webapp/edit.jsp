<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.dao.*" %> 
    <%@ page import="com.controller.*" %> 
    <%@ page import="com.model.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/reservation.css">
<title>Reservation</title>
</head>
<body>
  <%
// Retrieve reservationId from the URL
int reservationId = Integer.parseInt(request.getParameter("reservationId"));

// Use the reservationId to get the Reservation object
ReservationDAO reservationDao = new ReservationDAO();
Reservation editReservation=null;
try{
 editReservation = reservationDao.ShowReservationData(reservationId);
}catch(Throwable e){}
%>
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
    <h2>Edit Reservation</h2>
    <h1><br><p><%= editReservation.getCustomerName() %></p></h1>
    <form action="UpdateController" method="post">
    <input type="hidden" name="reservationId" value="<%= reservationId %>">

   
      <div class="input-box">
        <label for="date">Reservation Date:</label>
        <input type="date" id="date" name="date" placeholder="Enter your date" value="<%= editReservation.getDate() %>" required>
    </div>
    <br>
      <div> <select class="form-control" name="time"style="width: 365px; height: 45px;" required >
        <option value="<%= editReservation.getTime() %>">Update Party Time</option>
        <option value="12-2">12:00am - 2:00pm</option>
        <option value="2-4">2:00pm - 4:00pm</option>
        <option value="6-8">6:00pm - 8:00pm</option>
        <option value="8-10">8.00pm - 10.00pm</option>
      </select></div> <br>

       <div > <select class="form-control" id="partySize" name="partySize" style="width: 365px; height: 45px;" required  >
            
            <option value="<%= editReservation.getPartySize() %>" selected="selected">Update Party Size</option>
    </select></div><br>

      
      
        <div><select class="form-control" id="tableId" name="tableId"  style="width: 365px; height: 45px;" required  >
            <!-- <option value="<%= editReservation.getTableId() %>">Update Table ID</option> -->
            <option value="<%= editReservation.getTableId() %>" selected="selected">Select Table Number</option>
          </select><br>
        
      </div>
      
      <div class="input-box button">
        <input type="Submit" value="Update Now" onclick="checker()">
        
      </div>
    </form>
  </div>
  
  <script>
  function checker(){
	   var FailMsg = "<%= (String)request.getAttribute("Fail") %>";
	  if(FailMsg == "Fail"){
		  Failmsg=null;
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