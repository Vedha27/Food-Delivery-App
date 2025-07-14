package com.servlets;

import java.io.IOException;
import com.daoimpl.Cart;
import com.model.CartItem;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        String action = req.getParameter("action");
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        int newRestId = Integer.parseInt(req.getParameter("restaurantId"));
       
        User u=(User)session.getAttribute("loggedUser");
        session.setAttribute("loggedUser", u);
        
        if (cart == null) 
        {
            cart = new Cart();
            session.setAttribute("cart", cart);
            session.setAttribute("restaurantId", newRestId);
        }

        Integer oldRestId = (Integer) session.getAttribute("restaurantId");
        if (oldRestId != null && oldRestId != newRestId && action.equals("add"))
        {
            cart = new Cart();
            session.setAttribute("cart", cart);
            session.setAttribute("restaurantId", newRestId);
        }

        switch (action) {
            case "add":
                addItemToCart(req, cart);
                break;
            case "increase":
                cart.increaseQuantity(itemId);
                break;
            case "decrease":
                cart.decreaseQuantity(itemId);
                break;
            case "remove":
                cart.removeItemById(itemId);
                break;
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart.jsp");
    }

    private void addItemToCart(HttpServletRequest req, Cart cart)
    {
        try {
            int itemId = Integer.parseInt(req.getParameter("itemId"));
            int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            CartItem item = new CartItem(itemId, restaurantId, name, quantity, price);
            cart.addItem(item);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

