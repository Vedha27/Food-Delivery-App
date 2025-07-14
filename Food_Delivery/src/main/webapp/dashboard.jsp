<!-- ✅ Final Fixed JSP Navbar Section with CSS and JS -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page import="com.model.User, com.model.Restaurant, java.util.List" %>
<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null) 
    {
        response.sendRedirect("login.html");
        return;
    }
    List<Restaurant> restList = (List<Restaurant>) request.getAttribute("allRestList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Dashboard - Vedlicious</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="dashboard.css" />
  <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
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
    <a href="cart.jsp">Cart</a>
    <a href="restaurants">Restaurants</a>
    <a href="myOrders">My Orders</a>
    <a href="profile">Profile</a>
  </div>
</nav>

<section class="greeting-section">
  <div class="greeting-box">
    <h2>Welcome, <%= loggedUser.getName() %>!</h2>
  </div>
</section>

  <div class="search-bar">
    <input type="text" placeholder="Search restaurants, cuisine..." />
    <button>🔍 Search</button>
  </div>
  
<section class="restaurant-section">
  <div class="restaurant-grid">
    <% if (restList != null && !restList.isEmpty()) {
         for (Restaurant rest : restList) { %>
      <div class="restaurant-card">
        <img class="restaurant-img" src="<%= rest.getImgPath() %>" alt="<%= rest.getName() %>" />
        <div class="restaurant-info">
          <div class="rest-header">
            <h3><%= rest.getName() %></h3>
            <span class="rating">⭐ <%= rest.getRating() %></span>
          </div>
          <div class="rest-details">
            <p><strong>Cuisine:</strong> <%= rest.getCuisineType() %></p>
            <p><strong>Delivery:</strong> <%= rest.getDeliveryTime() %> mins</p>
          </div>
          <a href="menu?restId=<%= rest.getRestId() %>" class="go-to-menu-btn">Go to Menu</a>
        </div>
      </div>
    <% } } else { %>
      <p class="no-restaurants">No restaurants available right now.</p>
    <% } %>
  </div>
</section>

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

<script src="dashboard.js">
 
</script>

</body>
</html>
