package com.nagarro.service;

import com.nagarro.dao.UserDaoImp;
import com.nagarro.dao.api.UserDao;
import com.nagarro.model.User;
import com.nagarro.service.api.LoginService;

public class LoginServiceImp implements LoginService {

	public boolean resetPassword(String username, String newPassword) {
		UserDao dao = new UserDaoImp();
		boolean isSuccessfulReset = dao.resetPassword(username, newPassword);
		if (isSuccessfulReset) {
			System.out.println("Password reset successful!");
			return true;
		} else {
			System.out.println("Could not reset,an error occured.");
		}
		return false;
	}

	public User getUser(String username) {
		UserDao dao = new UserDaoImp();
		User user = dao.getUser(username);
		return user;
	}

	public boolean isUser(String username, String password) {
		UserDao dao = new UserDaoImp();
		boolean isUser = dao.isUser(username, password);
		if (isUser) {
			return true;
		}
		return false;
	}

}
