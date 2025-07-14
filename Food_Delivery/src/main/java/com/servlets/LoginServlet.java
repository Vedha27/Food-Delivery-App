package com.servlets;

import java.io.IOException;

import com.dao.UserDAO;
import com.daoimpl.UserDAOImpl;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();
        try 
        {
            User user = userDAO.getUserByEmailAndPassword(email, password);

            if (user != null) 
            {
                userDAO.updateLastLoginDate(user.getUserId());

                HttpSession session = request.getSession();
                session.setAttribute("loggedUser", user);
                session.setAttribute("userId", user.getUserId());

                response.sendRedirect("dashboard");
            } 
            else 
            {
                response.sendRedirect("login-error.html");
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            response.sendRedirect("login-error.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.sendRedirect("login.html");
    }
}
