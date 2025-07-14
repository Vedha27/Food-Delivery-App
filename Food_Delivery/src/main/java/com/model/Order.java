package com.model;

import java.sql.Date;

public class Order {

	private int orderId;
	private int restId;
	private int userId;
	private Date orderDate;
	private int totalAmt;
	private String status;
	private String paymentMode;
	private String address;
	
	public Order() {

	}

	public Order(int orderId, int restId, int userId, Date orderDate, int totalAmt, String status, String paymentMode,String address) {
		super();
		this.orderId = orderId;
		this.restId = restId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.totalAmt = totalAmt;
		this.status = status;
		this.paymentMode = paymentMode;
		this.address=address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(int totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Override
	public String toString() {
		
		return "Order Id: "+orderId+" Restaurant Id: "+restId+" User Id: "+userId+" Order Date: "+orderDate+" Total Amount: "+totalAmt+" Status: "+totalAmt+" Payment Mode:"+paymentMode;
	}
	
}
