<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Order Successful - Vedlicious</title>
<link rel="stylesheet" href="order-success.css">
</head>
<body>

	<%
	String customerName = (String) session.getAttribute("customerName");
	if (customerName == null)
		customerName = "Customer";
	%>

	<audio autoplay>
		<source src="audio/success-tone.mp3" type="audio/mpeg">
	</audio>

	<canvas id="confetti-canvas"></canvas>

	<div class="success-container">
		<div class="message">
			<h1>
				<span class="emoji">🎉</span> <span class="gradient-text">Dear
					<%=customerName%>,
				</span>
			</h1>
			<p>Your order has been successfully placed!</p>
			<p class="quote">
				Thank you for choosing Vedlicious. Your food is being prepared with
				love and care. <br /> Enjoy your meal, savor every bite, and don’t
				forget—good food is happiness served on a plate. 😊🍽️
			</p>
		</div>

		<div class="delivery-scene">
			<div class="bike-wrapper">
				<img src="images/delivery-bike.png" alt="Delivery Bike"
					class="bike bounce" />
				<div class="speech-bubble">I'm on the way 🛵</div>
			</div>
		</div>

		<a href="restaurants" class="back-link">← Back to Restaurants</a>
	</div>

	<script src="order-success.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/canvas-confetti@1.5.1/dist/confetti.browser.min.js"></script>

</body>
</html>
