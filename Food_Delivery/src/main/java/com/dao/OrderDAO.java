package com.dao;

import java.util.List;

import com.model.Order;

public interface OrderDAO {

	void addOrder(Order order);

	Order getOrder(int orderId);

	List<Order> getAllOrders();

	void updateOrderr(Order order);

	void deleteOrder(int  orderId);
}
