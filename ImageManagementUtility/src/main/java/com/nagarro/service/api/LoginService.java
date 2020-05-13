package com.nagarro.service.api;


import com.nagarro.model.User;

public interface LoginService {
	boolean isUser(String username,String password);
	boolean resetPassword(String username,String newPassword);
	User getUser(String username);
}
