package com.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import com.dao.OrderDAO;
import com.daoimpl.Cart;
import com.daoimpl.OrderDAOImpl;
import com.daoimpl.OrderItemDAOImpl;
import com.model.CartItem;
import com.model.Order;
import com.model.OrderItem;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException 
    {
        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        System.out.println("CheckoutServlet: POST triggered");
        User u =null;
        try 
        {
            HttpSession session = request.getSession();
            
             u=(User)session.getAttribute("loggedUser");
             session.setAttribute("loggedUser", u);
           

            Cart cart = (Cart) session.getAttribute("cart");
            Integer userId = (Integer) session.getAttribute("userId");
            Integer restId = (Integer) session.getAttribute("restaurantId");

            if (cart == null || userId == null || restId == null) 
            {
                System.out.println("Missing cart/userId/restId in session");
                response.sendRedirect("login.html");
                return;
            }

            Map<Integer, CartItem> items = cart.getItems();

            if (items == null || items.isEmpty())
            {
                System.out.println("Cart is empty");
                response.sendRedirect("cart.jsp");
                return;
            }

            String address = request.getParameter("address");
            String paymentMode = request.getParameter("paymentMode");
            if (paymentMode == null) paymentMode = "COD"; // default

            int totalAmt = 0;
            for (CartItem item : items.values()) 
            {
                totalAmt += item.getQuantity() * item.getPrice();
            }

            // Create Order
            Order order = new Order();
            order.setRestId(restId);
            order.setUserId(userId);
            order.setOrderDate(Date.valueOf(LocalDate.now()));
            order.setTotalAmt(totalAmt);
            order.setStatus("Placed");
            order.setPaymentMode(paymentMode);
            order.setAddress(address);

            // Save Order
            orderDAO.addOrder(order);
            System.out.println("Order inserted: ID = " + order.getOrderId());

            // Save Order Items
            OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();

            for (CartItem item : items.values()) 
            {
                OrderItem orderItem = new OrderItem();
                orderItem.setOderId(order.getOrderId());
                orderItem.setMenuId(item.getItemId());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setTotalAmount((int) (item.getQuantity() * item.getPrice()));
                orderItemDAO.addOrderItem(orderItem);
                System.out.println("Order item added: " + item.getName());
            }

            // Clear cart
            session.removeAttribute("cart");
            session.setAttribute("customerName", u.getName());

            // Redirect to success
            response.sendRedirect("order-success.jsp");

        }
        catch (Exception e)
        {
            System.out.println("Exception in CheckoutServlet");
            e.printStackTrace();
            request.setAttribute("errorMsg", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
