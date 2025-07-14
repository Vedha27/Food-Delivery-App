package com.model;

import java.sql.Date;

public class User {

	private int userId;
	private String name;
	private String userName;
	private String password;
	private String email;
	private String phoneNumber;
	private String address;
	private Role role;
	private Date createdDate;
	private Date lastLoginDate;
	
	public User() {

	}

	public User(int userId, String name, String userName, String password, String email, String phoneNumber,
			String address,  Role role, Date createdDate, Date lastLoginDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}

	public User(int userId, String  name,String userName, String password, String email, String phoneNumber, String address,
			 Role role) {
		super();
		this.userId = userId;
		this.name=name;
		this.userName = userName;
		this.password = password;
		this.email = email;   
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
	} 

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public  Role getRole() {
		return role;
	}

	public void setRole( Role role) {
		this.role = role;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lostLoginDate) {
		this.lastLoginDate = lostLoginDate;
	}
	
	@Override
	public String toString() {

		return "User Id: "+userId+" Name: "+name+" User Name: "+userName+" Password: "+password+" Email: "+email+" Phone NUmber: "+phoneNumber+" Address: "+address+" Role: "+role+" Created Date: "+createdDate+" Last Login Date: "+lastLoginDate;
	}
}
