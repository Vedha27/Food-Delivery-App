package com.servlets;

import com.daoimpl.OrderDAOImpl;
import com.daoimpl.RestaurantDAOImpl;
import com.model.Order;
import com.model.Restaurant;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/myOrders")
public class MyOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        User user = (User) request.getSession().getAttribute("loggedUser");
        if (user == null) 
        {
            response.sendRedirect("login.html");
            return;
        }

        OrderDAOImpl orderDAO = new OrderDAOImpl();
        RestaurantDAOImpl restDAO = new RestaurantDAOImpl();

        List<Order> orders = orderDAO.getAllOrders();
        List<Map<String, Object>> enrichedOrders = new ArrayList<>();

        for (Order o : orders) 
        {
            if (o.getUserId() == user.getUserId()) 
            {
                Map<String, Object> data = new HashMap<>();
                data.put("order", o);

                Restaurant r = restDAO.getRestaurantById(o.getRestId());
                data.put("restaurantName", r != null ? r.getName() : "Unknown");

                enrichedOrders.add(data);
            }
        }

        request.setAttribute("enrichedOrders", enrichedOrders);
        request.getRequestDispatcher("my-orders.jsp").forward(request, response);
    }
}
