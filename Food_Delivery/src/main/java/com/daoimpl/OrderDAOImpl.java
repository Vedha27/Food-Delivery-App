package com.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.connector.Connector;
import com.dao.OrderDAO;
import com.model.Order;

public class OrderDAOImpl implements OrderDAO {

	private static final String INSERT_QUERY = 
	    "INSERT INTO `order` (restId, userId, orderDate, totalAmt, status, paymentMode, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_BY_ID_QUERY = 
	    "SELECT * FROM `order` WHERE orderId = ?";

	private static final String SELECT_ALL_QUERY = 
	    "SELECT * FROM `order`";

	private static final String UPDATE_QUERY = 
	    "UPDATE `order` SET restId = ?, userId = ?, orderDate = ?, totalAmt = ?, status = ?, paymentMode = ?, address = ? WHERE orderId = ?";

	private static final String DELETE_QUERY = 
	    "DELETE FROM `order` WHERE orderId = ?";

	@Override
	public void addOrder(Order order) {
		try (Connection con = Connector.getConnection();
		     PreparedStatement stmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setInt(1, order.getRestId());
			stmt.setInt(2, order.getUserId());
			stmt.setDate(3, order.getOrderDate());
			stmt.setInt(4, order.getTotalAmt());
			stmt.setString(5, order.getStatus());
			stmt.setString(6, order.getPaymentMode());
			stmt.setString(7, order.getAddress());

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int orderId = rs.getInt(1);
					order.setOrderId(orderId); // Set generated ID
					System.out.println("✅ Order inserted successfully with ID: " + orderId);
				}
			} else {
				System.out.println("❌ Order insertion failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order getOrder(int orderId) {
		Order order = null;
		try (Connection con = Connector.getConnection();
		     PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID_QUERY)) {

			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				order = new Order(
					rs.getInt("orderId"),
					rs.getInt("restId"),
					rs.getInt("userId"),
					rs.getDate("orderDate"),
					rs.getInt("totalAmt"),
					rs.getString("status"),
					rs.getString("paymentMode"),
					rs.getString("address")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> list = new ArrayList<>();
		try (Connection con = Connector.getConnection();
		     PreparedStatement stmt = con.prepareStatement(SELECT_ALL_QUERY);
		     ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Order order = new Order(
					rs.getInt("orderId"),
					rs.getInt("restId"),
					rs.getInt("userId"),
					rs.getDate("orderDate"),
					rs.getInt("totalAmt"),
					rs.getString("status"),
					rs.getString("paymentMode"),
					rs.getString("address")
				);
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateOrderr(Order order) {
		try (Connection con = Connector.getConnection();
		     PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY)) {

			stmt.setInt(1, order.getRestId());
			stmt.setInt(2, order.getUserId());
			stmt.setDate(3, order.getOrderDate());
			stmt.setInt(4, order.getTotalAmt());
			stmt.setString(5, order.getStatus());
			stmt.setString(6, order.getPaymentMode());
			stmt.setString(7, order.getAddress());
			stmt.setInt(8, order.getOrderId());

			int rows = stmt.executeUpdate();
			if (rows > 0) {
				System.out.println("✅ Order updated successfully.");
			} else {
				System.out.println("⚠️ Order update failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(int orderId) {
		try (Connection con = Connector.getConnection();
		     PreparedStatement stmt = con.prepareStatement(DELETE_QUERY)) {

			stmt.setInt(1, orderId);
			int rows = stmt.executeUpdate();
			if (rows > 0) {
				System.out.println("✅ Order deleted successfully.");
			} else {
				System.out.println("⚠️ Order deletion failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
