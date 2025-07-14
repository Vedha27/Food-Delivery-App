package com.servlets;

import java.io.IOException;
import java.sql.Date;

import com.dao.UserDAO;
import com.daoimpl.UserDAOImpl;
import com.model.Role;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException 
	{

	    String name = request.getParameter("name");
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    String confirmPassword = request.getParameter("confirmPassword");
	    String email = request.getParameter("email");
	    String phoneNumber = request.getParameter("phoneNumber");
	    String address = request.getParameter("address");

	    String tempRole = request.getParameter("role"); 
	    if (tempRole == null || tempRole.isEmpty()) 
	    {
	        response.sendRedirect("error2.html");
	        return;
	    }

	    Role role;
	    try
	    {
	        role = Role.valueOf(tempRole.toUpperCase()); 
	    } 
	    catch (IllegalArgumentException e) 
	    {
	        e.printStackTrace();
	        response.sendRedirect("error2.html");
	        return;
	    }

	    if (!password.equals(confirmPassword))
	    {
	        response.sendRedirect("error2.html");
	        return;
	    }

	    Date currentDate = new Date(System.currentTimeMillis());
	    User user = new User(1, name, userName, password, email, phoneNumber, address, role, currentDate, currentDate);
 
	    UserDAO userDAO = new UserDAOImpl();
	    try
	    {
	        userDAO.addUser(user);
	        response.sendRedirect("success.html");
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        response.sendRedirect("error2.html");
	    }
	}
}
