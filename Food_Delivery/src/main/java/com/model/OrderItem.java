package com.model;

public class OrderItem {
	
	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private int totalAmount;
	
	public OrderItem() {
		
	}

	
	public OrderItem(int orderItemId, int orderId, int menuId, int quantity, int totalAmount) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}


	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOderId() {
		return orderId;
	}

	public void setOderId(int oderId) {
		this.orderId = oderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "OrderItemId: "+orderItemId+" Order Id: "+orderId+" Menu Id: "+menuId+" Quantity: "+quantity+" Total Amount: "+totalAmount;
	}
}
