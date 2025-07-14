package com.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connector.Connector;
import com.dao.UserDAO;
import com.model.Role;
import com.model.User;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_QUERY =
			"INSERT INTO `user`(`name`, `username`, `password`, `email`, `phoneNumber`, `address`, `role`, `createdDate`, `lastLoginDate`) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_QUERY =
			"SELECT * FROM `user` WHERE `userId` = ?";
	private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ? AND password = ?";

	private static final String UPDATE_QUERY =
			"UPDATE `user` SET `name` = ?, `password` = ?, `email` = ?, `phoneNumber` = ?, `address` = ? WHERE `userId` = ?";

	private static final String DELETE_QUERY =
			"DELETE FROM `user` WHERE `userId` = ?";

	private static final String SELECT_ALL_QUERY =
			"SELECT * FROM `user`";

	@Override
	public void addUser(User user) {
		try (Connection con = Connector.getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) 
		{

			ps.setString(1, user.getName());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhoneNumber());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getRole().toString());
			ps.setDate(8, user.getCreatedDate());
			ps.setDate(9, user.getLastLoginDate());

			int rows = ps.executeUpdate();
			System.out.println(rows + " user(s) inserted.");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userId) {
		User user = null;

		try (Connection con = Connector.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_QUERY)) {

			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) 
			{
				String name = rs.getString("name");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				String address = rs.getString("address");
				Role role = Role.valueOf(rs.getString("role"));
				Date createdDate = rs.getDate("createdDate");
				Date lastLoginDate = rs.getDate("lastLoginDate");

				user = new User(userId, name, userName, password, email, phone, address, role, createdDate, lastLoginDate);
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void updateUser(User user)
	{
		try (Connection con = Connector.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) 
		{

			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhoneNumber());
			ps.setString(5, user.getAddress());
			ps.setInt(6, user.getUserId());

			int rows = ps.executeUpdate();
			System.out.println(rows + " user(s) updated.");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) 
	{
		try (Connection con = Connector.getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_QUERY))
		{

			ps.setInt(1, userId);
			int rows = ps.executeUpdate();
			System.out.println(rows + " user(s) deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<>();

		try (Connection con = Connector.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_ALL_QUERY);
				ResultSet rs = ps.executeQuery()) 

		{

			while (rs.next()) {
				int userId = rs.getInt("userId");
				String name = rs.getString("name");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				String address = rs.getString("address");
				Role role = Role.valueOf(rs.getString("role"));
				Date createdDate = rs.getDate("createdDate");
				Date lastLoginDate = rs.getDate("lastLoginDate");

				User user = new User(userId, name, userName, password, email, phone, address, role, createdDate, lastLoginDate);
				userList.add(user);
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password)
	{
		try (Connection con = Connector.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_EMAIL))
		{	
		
				ps.setString(1, email);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();
				if (rs.next()) 
				{
					return new User(
							rs.getInt("userId"),
							rs.getString("name"),
							rs.getString("userName"),
							rs.getString("password"),
							rs.getString("email"),
							rs.getString("phoneNumber"),
							rs.getString("address"),
							com.model.Role.valueOf(rs.getString("role")),
							rs.getDate("createdDate"),
							rs.getDate("lastLoginDate")
							);
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;	
	}
	@Override
	public void updateLastLoginDate(int userId) throws Exception {
	    String sql = "UPDATE user SET lastLoginDate = NOW() WHERE userId = ?";
	    try (Connection conn = Connector.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, userId);
	        stmt.executeUpdate();
	    }
	}

}
