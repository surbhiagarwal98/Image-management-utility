package com.nagarro.dao;

import org.hibernate.Session;

import com.nagarro.dao.api.UserDao;
import com.nagarro.model.User;
import com.nagarro.util.HibernateUtil;

public class UserDaoImp implements UserDao {

	/**
	 * updates password in database
	 */
	public boolean resetPassword(String username, String newPassword) {
		User user = null;
		try {
			Session session = HibernateUtil.getSession();
			session.getTransaction().begin();
			user = session.get(User.class, username);
			user.setPassword(newPassword);
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (user == null) {
			return false;
		}
		return true;
	}

	public User getUser(String username) {
		User user = null;
		try {
			Session session = HibernateUtil.getSession();
			session.getTransaction().begin();
			user = session.get(User.class, username);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;

	}

	public boolean isUser(String username, String password) {
		Session session = HibernateUtil.getSession();
		try {
			session.getTransaction().begin();
			User user = session.get(User.class, username);
			System.out.println(user.getUserName());
			if (user.getUserName() != null && user.getPassword().equals(password))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

}
