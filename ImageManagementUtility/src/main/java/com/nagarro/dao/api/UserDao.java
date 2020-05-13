package com.nagarro.dao.api;

import java.util.List;

import com.nagarro.model.User;

public interface UserDao {
	 
	  boolean resetPassword(String username,String newPassword);
	  User getUser(String username);
	  boolean isUser(String username,String password);
}
