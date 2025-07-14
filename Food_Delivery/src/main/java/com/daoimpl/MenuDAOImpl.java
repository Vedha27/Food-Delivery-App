package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connector.Connector;
import com.dao.MenuDAO;
import com.model.Menu;

public class MenuDAOImpl implements MenuDAO {

    private static final String INSERT_QUERY =
        "INSERT INTO menu (restId, itemName, description, price, rating, imagePath) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_QUERY =
        "SELECT * FROM menu WHERE menuId = ?";

    private static final String SELECT_ALL_QUERY =
        "SELECT * FROM menu";

    private static final String UPDATE_QUERY =
        "UPDATE menu SET restId = ?, itemName = ?, description = ?, price = ?, rating = ?, imagePath = ? WHERE menuId = ?";

    private static final String DELETE_QUERY =
        "DELETE FROM menu WHERE menuId = ?";

    @Override
    public void addMenu(Menu menu) {
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_QUERY)) {

            stmt.setInt(1, menu.getRestId());
            stmt.setString(2, menu.getItemName());
            stmt.setString(3, menu.getDescription());
            stmt.setInt(4, menu.getPrice());
            stmt.setString(5, menu.getRating());
            stmt.setString(6, menu.getImagePath());

            stmt.executeUpdate();
            System.out.println("Menu added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        Menu menu = null;
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_QUERY)) {

            stmt.setInt(1, menuId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                menu = new Menu(
                    rs.getInt("menuId"),
                    rs.getInt("restId"),
                    rs.getString("itemName"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getString("rating"),
                    rs.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> list = new ArrayList<>();
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Menu menu = new Menu(
                    rs.getInt("menuId"),
                    rs.getInt("restId"),
                    rs.getString("itemName"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getString("rating"),
                    rs.getString("imagePath")
                );
                list.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateMenu(Menu menu) {
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY)) {

            stmt.setInt(1, menu.getRestId());
            stmt.setString(2, menu.getItemName());
            stmt.setString(3, menu.getDescription());
            stmt.setInt(4, menu.getPrice());
            stmt.setString(5, menu.getRating());
            stmt.setString(6, menu.getImagePath());
            stmt.setInt(7, menu.getMenuId());

            stmt.executeUpdate();
            System.out.println("Menu updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_QUERY)) {

            stmt.setInt(1, menuId);
            stmt.executeUpdate();
            System.out.println("Menu deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Menu> getAllMenuByRestId(int restId)
    {
        List<Menu> list = new ArrayList<>();
        String query = "SELECT * FROM menu WHERE restId = ?";
        
        try (Connection con = Connector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, restId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Menu menu = new Menu(
                    rs.getInt("menuId"),
                    rs.getInt("restId"),
                    rs.getString("itemName"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getString("rating"),
                    rs.getString("imagePath")
                );
                list.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
