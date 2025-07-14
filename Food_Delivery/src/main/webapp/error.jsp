<!-- error.jsp -->
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <title>Error</title>
  <link rel="stylesheet" href="error.css">
</head>
<body>
 <div class="error-box">
    <div class="error-icon"><i class="fas fa-exclamation-triangle"></i></div>
    <h1>Error</h1>
    <h2>Oops! Something went wrong.</h2>
    <p>We're sorry, the page you're looking for might have been moved or doesn't exist anymore.</p>
    <a href="restaurants" class="home-btn">Return to Home</a>
  </div>
</body>
</html>
