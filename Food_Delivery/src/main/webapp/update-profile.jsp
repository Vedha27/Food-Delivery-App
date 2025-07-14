<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.model.User"%>
<%@ page import="com.model.Role"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Update Profile</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="update-profile.css">
</head>
<body>

	<%
  User user = (User) session.getAttribute("loggedUser");
  if (user == null) 
  {
    response.sendRedirect("login.html");
    return;
  }
%>

	<div class="update-container">
		<h2>Update Your Profile</h2>
		<form action="update-profile" method="post">
			<input type="hidden" name="userId" value="<%= user.getUserId() %>" />

			<label>Name:</label> <input type="text" name="name"
				value="<%= user.getName() %>" required /> <label>Username:</label>
			<input type="text" name="username" value="<%= user.getUserName() %>"
				required /> <label>Email:</label> <input type="email" name="email"
				value="<%= user.getEmail() %>" required /> <label>Phone
				Number:</label> <input type="text" name="phone"
				value="<%= user.getPhoneNumber() %>" required /> <label>Address:</label>
			<textarea name="address" required><%= user.getAddress() %></textarea>

			<button type="submit">Update Profile</button>
		</form>
		<a href="profile.jsp" class="back-link">← Back to Profile</a>
	</div>

</body>
</html>
