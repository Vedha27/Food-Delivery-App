package com.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.connector.Connector;
import com.dao.RestaurantDAO;
import com.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT_QUERY = 
        "INSERT INTO restaurant (name, address, phNumber, cuisineType, adminUserId, deliveryTime, rating, isActive, imgPath) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID_QUERY = 
        "SELECT * FROM restaurant WHERE restId = ?";

    private static final String SELECT_ALL_ACTIVE_QUERY = 
        "SELECT * FROM restaurant WHERE isActive = 'yes'";

    private static final String UPDATE_QUERY = 
        "UPDATE restaurant SET name = ?, address = ?, phNumber = ?, cuisineType = ?, deliveryTime = ?, rating = ?, isActive = ?, imgPath = ? " +
        "WHERE restId = ?";

    private static final String DEACTIVATE_QUERY = 
        "UPDATE restaurant SET isActive = 'no' WHERE restId = ?";

    private static final String SELECT_BY_ADMIN_ID_QUERY = 
        "SELECT * FROM restaurant WHERE adminUserId = ?";

    @Override
    public void addRestaurant(Restaurant restaurant) {
        try (Connection con = Connector.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setLong(3, restaurant.getPhNumber());
            ps.setString(4, restaurant.getCuisineType());
            ps.setInt(5, restaurant.getAdminUserId());
            ps.setTime(6, restaurant.getDeliveryTime());
            ps.setDouble(7, restaurant.getRating());
            ps.setString(8, restaurant.getIsActive());
            ps.setString(9, restaurant.getImgPath());

            int rows = ps.executeUpdate();
            System.out.println(rows + " restaurant(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant restaurant = null;
        try (Connection con = Connector.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                restaurant = extractRestaurant(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllActiveRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        try (Connection con = Connector.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_ACTIVE_QUERY);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) 
            {
                list.add(extractRestaurant(rs));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try (Connection con = Connector.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setLong(3, restaurant.getPhNumber());
            ps.setString(4, restaurant.getCuisineType());
            ps.setTime(5, restaurant.getDeliveryTime());
            ps.setDouble(6, restaurant.getRating());
            ps.setString(7, restaurant.getIsActive());
            ps.setString(8, restaurant.getImgPath());
            ps.setInt(9, restaurant.getRestId());

            int rows = ps.executeUpdate();
            System.out.println(rows + " restaurant(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deactivateRestaurant(int restaurantId) {
        try (Connection con = Connector.getConnection();
             PreparedStatement ps = con.prepareStatement(DEACTIVATE_QUERY)) {

            ps.setInt(1, restaurantId);
            int rows = ps.executeUpdate();
            System.out.println(rows + " restaurant(s) deactivated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants(int restId) {
        List<Restaurant> list = new ArrayList<>();
        try (Connection con = Connector.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ADMIN_ID_QUERY)) {

            ps.setInt(1, restId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractRestaurant(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Restaurant extractRestaurant(ResultSet rs) throws SQLException {
        return new Restaurant(
            rs.getInt("restId"),
            rs.getString("name"),
            rs.getString("address"),
            rs.getLong("phNumber"),
            rs.getString("cuisineType"),
            rs.getInt("adminUserId"),
            rs.getTime("deliveryTime"),
            rs.getDouble("rating"),
            rs.getString("isActive"),
            rs.getString("imgPath")
        );
    }
}
