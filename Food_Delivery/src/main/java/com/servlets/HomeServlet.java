package com.servlets;

import java.io.IOException;
import java.util.List;

import com.daoimpl.RestaurantDAOImpl;
import com.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurants")
public class HomeServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		
		RestaurantDAOImpl daoImpl=new RestaurantDAOImpl();
		List<Restaurant> restList=daoImpl.getAllActiveRestaurants();
		
		req.setAttribute("allRestList", restList);
		
		RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
	}
}

