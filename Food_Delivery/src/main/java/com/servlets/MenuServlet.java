package com.servlets;

import java.io.IOException;
import java.util.List;

import com.dao.MenuDAO;
import com.daoimpl.MenuDAOImpl;
import com.model.Menu;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException
    {

        String restIdParam = req.getParameter("restId");

        if (restIdParam == null || restIdParam.isEmpty()) 
        {
            resp.sendRedirect("dashboard");  // fallback to main page
            return;
        }

        try 
        {
            int restId = Integer.parseInt(restIdParam);

            HttpSession session = req.getSession();
            User u=(User)session.getAttribute("loggedUser");
            session.setAttribute("loggedUser", u);
            
            session.setAttribute("restaurantId", restId);
            

            MenuDAO menuDao = new MenuDAOImpl();
            List<Menu> menuList = menuDao.getAllMenuByRestId(restId);

            req.setAttribute("menuList", menuList);
            req.getRequestDispatcher("menu.jsp").forward(req, resp);

        } 
        catch (NumberFormatException e) 
        {
            e.printStackTrace();
            resp.sendRedirect("dashboard"); 
        }
    }
}
