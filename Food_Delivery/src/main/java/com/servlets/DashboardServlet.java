package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dao.RestaurantDAO;
import com.daoimpl.RestaurantDAOImpl;
import com.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) 
        {
            response.sendRedirect("login.html");
            return;
        }

        RestaurantDAO restDAO = new RestaurantDAOImpl();
        try
        {
            List<Restaurant> restList = restDAO.getAllActiveRestaurants();
            if (restList == null) restList = new ArrayList<>();

            request.setAttribute("allRestList", restList);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
