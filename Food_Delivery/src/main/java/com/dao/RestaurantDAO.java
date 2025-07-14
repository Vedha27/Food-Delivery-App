package com.dao;

import java.util.List;

import com.model.Restaurant;

public interface RestaurantDAO {

	

	    void addRestaurant(Restaurant restaurant);

	    Restaurant getRestaurantById(int restaurantId);

	    List<Restaurant> getAllActiveRestaurants();

	    void updateRestaurant(Restaurant restaurant);

	    void deactivateRestaurant(int restaurantId);

	    List<Restaurant> getAllRestaurants(int adminUserId);
	

}
