<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User Profile - Vedlicious</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="profile.css">
  <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" crossorigin="anonymous">
</head>
<body>

<!-- ✅ Navbar -->
<nav class="navbar">
  <div class="logo">
    <img src="images/logo.png" alt="Vedlicious Logo" />
    <span class="app-name-gradient">VEDLICIOUS</span>
  </div>
  <div class="toggle-icon" onclick="toggleMenu()">☰</div>
  <div class="nav-links" id="navLinks">
    <span class="close-btn">✕</span>
    <a href="restaurants">Restaurants</a>
    <a href="myOrders">My Orders</a>
    <a href="cart.jsp">Cart</a>
  </div>
</nav>

<%
  User user = (User) session.getAttribute("loggedUser");
  if (user != null) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
%>

<div class="profile-wrapper">
  <h2 class="profile-heading">Welcome, <%= user.getName() %> 👋</h2>
  <div class="profile-card">
    <div class="profile-row">
      <div><span>User ID:</span> <%= user.getUserId() %></div>
      <div><span>Name:</span> <%= user.getName() %></div>
    </div>
    <div class="profile-row">
      <div><span>Username:</span> <%= user.getUserName() %></div>
      <div><span>Email:</span> <%= user.getEmail() %></div>
    </div>
    <div class="profile-row">
      <div><span>Phone:</span> <%= user.getPhoneNumber() %></div>
      <div><span>Address:</span> <%= user.getAddress() %></div>
    </div>
    <div class="profile-row">
      <div><span>Role:</span> <%= user.getRole() %></div>
      <div><span>Account Created:</span> <%= sdf.format(user.getCreatedDate()) %></div>
    </div>
    <div class="profile-row">
      <div><span>Last Login:</span> <%= sdf.format(user.getLastLoginDate()) %></div>
    </div>

    <!-- ✅ Buttons -->
    <div class="profile-actions">
      <form action="update-profile.jsp" method="get" style="display:inline;">
  	  <button type="submit" class="btn edit-btn">Edit Profile</button>
</form>

      <a href="logout" class="btn logout-btn">Logout</a>
    </div>
  </div>
</div>

<%
  } else {
%>
<div class="profile-wrapper">
  <div class="please-login-card">
    <h2>You must be logged in to view your profile.</h2>
    <p><a href="login.html">Click here to login</a></p>
  </div>
</div>
<%
  }
%>

<!-- ✅ Footer -->
<footer class="footer">
  <div class="footer-content">
    <div class="footer-links">
      <h4>Contact</h4>
      <a href="#">+91-9876543210</a>
      <a href="#">support@vedlicious.com</a>
      <a href="#">Bangalore, India</a>
      <a href="#">About Us</a>
    </div>
    <div class="footer-socials">
      <a href="#"><i class="fab fa-facebook-f"></i></a>
      <a href="#"><i class="fab fa-instagram"></i></a>
      <a href="#"><i class="fab fa-twitter"></i></a>
      <a href="#"><i class="fab fa-youtube"></i></a>
    </div>
    <div class="footer-copy">
      <p>&copy; 2025 Vedlicious | Powered by Flavor & Passion 🍽️</p>
    </div>
  </div>
</footer>

<script src="profile.js"></script>
</body>
</html>
