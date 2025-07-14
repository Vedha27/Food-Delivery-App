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

@WebServlet("/update-profile")
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
    {

        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("loggedUser") == null) 
        {
            res.sendRedirect("login.html");
            return;
        }

        try
        {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String name = req.getParameter("name");
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");

            User user = (User) session.getAttribute("loggedUser");
            user.setName(name);
            user.setUserName(username);
            user.setEmail(email);
            user.setPhoneNumber(phone);
            user.setAddress(address);

            UserDAO userDAO = new UserDAOImpl();
            userDAO.updateUser(user);

            session.setAttribute("loggedUser", user);

            res.sendRedirect("profile.jsp");

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            res.getWriter().println("Something went wrong while updating profile.");
        }
    }
}
