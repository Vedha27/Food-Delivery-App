package com.model;

public class Menu {

	private int menuId;
	private int restId;
	private String itemName;
	private String description;
	private int price;
	private String rating;
	private String imagePath;
	
	public Menu() {

	}

	public Menu(int menuId, int restId, String itemName, String description, int price, String rating,
			String imagePath) {
		super();
		this.menuId = menuId;
		this.restId = restId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.imagePath = imagePath;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public String toString() {
		
		return "Menu Id:"+menuId+" Restaurant Id: "+restId+" Item Name: "+itemName+"Description: "+description+" Price: "+price+" Ratings: "+rating+" Image Path:"+imagePath;
	}
	
}
