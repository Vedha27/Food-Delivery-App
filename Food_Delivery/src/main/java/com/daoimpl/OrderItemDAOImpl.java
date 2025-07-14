package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connector.Connector;
import com.dao.OrderItemDAO;
import com.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_QUERY = "INSERT INTO orderitem (orderId, menuId, quantity, totalAmount) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM orderitem WHERE orderItemId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM orderitem";
    private static final String UPDATE_QUERY = "UPDATE orderitem SET orderId = ?, menuId = ?, quantity = ?, totalAmount = ? WHERE orderItemId = ?";
    private static final String DELETE_QUERY = "DELETE FROM orderitem WHERE orderItemId = ?";

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_QUERY)) {

            stmt.setInt(1, orderItem.getOderId());
            stmt.setInt(2, orderItem.getMenuId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setInt(4, orderItem.getTotalAmount());

            stmt.executeUpdate();
            System.out.println("Order item inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        OrderItem item = null;
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_QUERY)) {

            stmt.setInt(1, orderItemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                item = new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("menuId"),
                        rs.getInt("quantity"),
                        rs.getInt("totalAmount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> list = new ArrayList<>();
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OrderItem item = new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("menuId"),
                        rs.getInt("quantity"),
                        rs.getInt("totalAmount")
                );
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY)) {

            stmt.setInt(1, orderItem.getOderId());
            stmt.setInt(2, orderItem.getMenuId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setInt(4, orderItem.getTotalAmount());
            stmt.setInt(5, orderItem.getOrderItemId());

            stmt.executeUpdate();
            System.out.println("Order item updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_QUERY)) {

            stmt.setInt(1, orderItemId);
            stmt.executeUpdate();
            System.out.println("Order item deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
