package com.dao;

import java.util.List;

import com.model.OrderItem;

public interface OrderItemDAO {

	void addOrderItem(OrderItem orderItem);

	OrderItem getOrderItem(int orderItemId);

	List<OrderItem> getAllOrderItems();

	void updateOrderItem(OrderItem OrderItem);

	void deleteOrderItem(int  orderItemId);
	
}
