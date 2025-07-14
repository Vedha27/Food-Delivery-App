package com.model;

import java.sql.Time;

public class Restaurant {
	
	private int restId;
	private String name;
	private String address;
	private long phNumber;
	private String cuisineType;
	private int adminUserId;
	private Time deliveryTime;
	private Double rating;
	private String isActive;
	private String imgPath;
	
	public Restaurant() {

	}


	public Restaurant(int restId, String name, String address, long phNumber, String cusineType, int adminUserId,
			Time deliveryTime,  Double rating, String isActive, String imgPath) {
		super();
		this.restId = restId;
		this.name = name;
		this.address = address;
		this.phNumber = phNumber;
		this.cuisineType = cusineType;
		this.adminUserId = adminUserId;
		this.deliveryTime = deliveryTime;
		this.rating = rating;
		this.isActive = isActive;
		this.imgPath = imgPath;
	}



	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(long phNumber) {
		this.phNumber = phNumber;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cusineType) {
		this.cuisineType = cusineType;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public  Double getRating() {
		return rating;
	}

	public void setRating( Double rating) {
		this.rating = rating;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public Time getDeliveryTime() {
		return deliveryTime;
	}
	
	public void setDeliveryTime(Time deliveryTime) {
		this.deliveryTime=deliveryTime;
	}
	
	@Override
	public String toString() {

		return "Restaurant Id: "+restId+" Name: "+name+" Phone Numbe: "+phNumber+" Cuisine Type: "+cuisineType+" Delivery Time: "+deliveryTime+" Admin UserId: "+" Ratin: "+rating+" Image Path: "+imgPath;
	}
}
