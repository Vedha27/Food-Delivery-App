<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.model.CartItem"%>
<%@ page import="com.daoimpl.Cart"%>
<%@ page import="com.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Cart - Vedlicious</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" href="cart.css"/>
  <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
</head>
<body>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) 
    {
        cart = new Cart();
        session.setAttribute("cart", cart);
    }
    
    Map<Integer, CartItem> items = cart.getItems();
    Integer restId = (Integer) session.getAttribute("restaurantId");
    User user = (User) session.getAttribute("loggedUser");
    String userAddress = (user != null && user.getAddress() != null) ? user.getAddress() : "";
%>

<nav class="navbar">
  <div class="logo">
    <img src="images/logo.png" alt="Vedlicious Logo" />
    <span class="app-name-gradient">VEDLICIOUS</span>
  </div>
  <div class="toggle-icon" onclick="toggleMenu()">☰</div>
  <div class="nav-links" id="navLinks">
    <span class="close-btn">✕</span>
    <a href="register.html">Register</a>
    <a href="login.html">Login</a>
    <a href="myOrders">My Orders</a>
    <a href="profile">Profile</a>
  </div>
</nav>

<div class="page-container">
  <div class="content">
    <div class="cart-container fadeIn">
      <h2>Your Cart</h2>

      <%
        if (!items.isEmpty()) 
        {
            double grandTotal = 0;
      %>
      <div class="table-responsive">
        <table class="cart-table slideUp">
          <thead>
            <tr>
              <th>Item</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>Total</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <%
              for (CartItem item : items.values()) 
              {
                double total = item.getQuantity() * item.getPrice();
                grandTotal += total;
            %>
            <tr>
              <td><%=item.getName()%></td>
              <td><%=item.getQuantity()%></td>
              <td>₹<%=item.getPrice()%></td>
              <td>₹<%=total%></td>
              <td>
               <div class="cart-actions" data-item-id="<%=item.getItemId()%>" data-restaurant-id="<%= restId %>">
                  <button class="cart-btn" data-action="increase">+</button>
                  <button class="cart-btn" data-action="decrease">−</button>
                  <button class="cart-btn" data-action="remove">Remove</button>
                </div>
              </td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>

      <div class="cart-buttons fadeIn">
        <h3>Grand Total: ₹<%=grandTotal%></h3>
        <form action="checkout" method="post">
          <input type="hidden" name="restId" value="<%=restId%>"/>

          <label for="address">Delivery Address:</label><br>
          <textarea name="address" id="address" required rows="3" cols="40"><%= userAddress.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;") %></textarea><br><br>

          <label for="paymentMode">Payment Mode:</label><br>
          <select name="paymentMode" id="paymentMode">
            <option value="COD">Cash on Delivery</option>
            <option value="Online">Online</option>
          </select><br><br>

          <div class="btn-group">
            <a href="menu?restId=<%=restId%>" class="btn add-items">Add More Items</a>
            <button type="submit" class="btn proceed">Proceed to Checkout</button>
          </div>
        </form>
      </div>

      <% } 
        else 
        { %>
        <p class="empty-cart">
          Your cart is empty. <a href="restaurants">Browse Restaurants</a>
        </p>
      <% } %>
    </div>
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
</div>

<script src="cart.js"></script>
</body>
</html>
