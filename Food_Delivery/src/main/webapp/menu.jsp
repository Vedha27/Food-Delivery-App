88<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Menu List</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="menu.css"/>
  <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet">
 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<!-- ✅ Navbar -->
<nav class="navbar">
  <div class="logo">
    <img src="images/logo.png" alt="Vedlicious Logo" />
    <span class="app-name-gradient">VEDLICIOUS</span>
  </div>

  <!-- ✅ Toggle icon for mobile only -->
  <div class="toggle-icon" onclick="toggleMenu()">☰</div>

  <!-- ✅ Nav links -->
  <div class="nav-links" id="navLinks">
    <span class="close-btn">✕</span> <!-- ✅ Required for close to work -->
    <a href="register.html">Register</a>
    <a href="login.html">Login</a>
    <a href="myOrders">My Orders</a>
    <a href="cart.jsp">Cart</a>
    <a href="profile">Profile</a>
  </div>
</nav>


  <!-- Search -->
  <div class="search-bar">
    <input type="text" placeholder="Search Menus..." />
    <button>🔍 Search</button>
  </div>

 <!-- ✅ Menu Title -->
<h2 class="menu-title">Our Special Menu</h2>

<%
  List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
  if (menuList != null && !menuList.isEmpty()) {
%>
<div class="menu-grid">
  <% for (Menu menu : menuList) { %>
    <div class="menu-card">
      <img src="<%= menu.getImagePath() %>" alt="<%= menu.getItemName() %>" />
      <h3><%= menu.getItemName() %></h3>
      <p><%= menu.getDescription() %></p>
      <div class="menu-info">
        <span class="price">₹<%= menu.getPrice() %></span>
       <span class="rating"><%= menu.getRating() %><span id="r">⭐</span></span>
      </div>
      <form action="cart">
        <input type="hidden" name="itemId" value="<%= menu.getMenuId() %>" />
        <input type="hidden" name="restaurantId" value="<%= menu.getRestId() %>" />
        <input type="hidden" name="name" value="<%= menu.getItemName() %>" />
        <input type="hidden" name="price" value="<%= menu.getPrice() %>" />
        <input type="hidden" name="quantity" value="1" />
        <input type="hidden" name="action" value="add" />
        <button type="submit" class="add-btn">Add to Cart</button>
      </form>
    </div>
  <% } %>
</div>
<% } else { %>
  <h3 style="text-align:center; color:red;">No menu items available for this restaurant.</h3>
<% } %>
<!-- Footer -->
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

  <script src="menu.js">
  
</script>
</body>
</html>
