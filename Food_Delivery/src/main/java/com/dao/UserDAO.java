package com.dao;

import java.util.List;

import com.model.User;

public interface UserDAO {

	void addUser(User user);
	
	User getUser(int userId);
	
	List<User> getAllUser();
	
	void updateUser(User user);
	
	void deleteUser(int  userId);
	
	public User getUserByEmailAndPassword(String email, String password) throws Exception;
	
	void updateLastLoginDate(int userId) throws Exception;
	
}

