<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.model.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>My Orders</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="my-orders.css"/>
  <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet">
 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

<nav class="navbar">
  <div class="logo">
    <img src="images/logo.png" alt="Vedlicious Logo" />
    <span class="app-name-gradient">VEDLICIOUS</span>
  </div>

 
  <div class="toggle-icon" onclick="toggleMenu()">☰</div>

  <div class="nav-links" id="navLinks">
    <span class="close-btn">✕</span> 
    <a href="restaurants">Restaurants</a>
    <a href="logout">Logout</a>
    <a href="cart.jsp">Cart</a>
    <a href="profile">Profile</a>
  </div>
</nav>

<%
  List<Map<String, Object>> enrichedOrders = (List<Map<String, Object>>) request.getAttribute("enrichedOrders");
%>

<div class="orders-wrapper">
  <h1 class="orders-heading">📦 Your Orders</h1>

  <% if (enrichedOrders != null && !enrichedOrders.isEmpty()) 
  {
       for (Map<String, Object> map : enrichedOrders) 
       {
         com.model.Order order = (com.model.Order) map.get("order");
         String restaurantName = (String) map.get("restaurantName");
  %>
  <div class="order-card">
    <div class="order-top">
      <span class="order-id">Order ID: #<%= order.getOrderId() %></span>
      <span class="status <%= order.getStatus().toLowerCase() %>"><%= order.getStatus() %></span>
    </div>
    <div class="order-info">
      <p><strong>Restaurant:</strong> <%= restaurantName %></p>
      <p><strong>Order Date:</strong> <%= order.getOrderDate() %></p>
      <p><strong>Total Amount:</strong> ₹<%= order.getTotalAmt() %></p>
      <p><strong>Payment Mode:</strong> <%= order.getPaymentMode() %></p>
      <p><strong>Address:</strong> <%= order.getAddress() %></p>
    </div>
  </div>
  <% } }
  else
  { %>
    <div class="no-orders">You have no orders yet.</div>
  <% } %>
</div>

 
<footer class="footer">
  <div class="footer-content">
    <div class="footer-links">
      <div>
        <h4>Contact</h4>
        <a href="#">+91-9876543210</a>
        <a href="#">support@vedlicious.com</a>
        <a href="#">Bangalore, India</a>
        <a href="#">About Us</a>
      </div>
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

  <script src="index.js">
  
</script>
</body>
</html>
