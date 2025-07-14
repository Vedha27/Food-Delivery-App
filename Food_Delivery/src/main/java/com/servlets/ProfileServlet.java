package com.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        HttpSession session = request.getSession(); 

        if (session != null && session.getAttribute("loggedUser") != null) 
        {
          
            RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            dispatcher.forward(request, response);
        } 
        else
        {

            response.sendRedirect("please-login.jsp");
        }
    }
}
