package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.IUserDAO;
import com.gcu.data.UserDAO;
import com.gcu.model.User;
import com.gcu.model.UserProfile;
import com.gcu.model.UserToken;

public class UserService implements IUserService {
	IUserDAO dao;

	public UserToken authenticate(User user) {
		int id = dao.findByUser(user);
		UserToken token = new UserToken(id, user);
		return token;
	}

	public UserToken register(UserProfile user) {
		int id = dao.createUser(user);
		UserToken token = new UserToken(id, user);
		return token;
	}

	@Autowired
	public void setUserService(IUserDAO dao) {
		this.dao = dao;
	}
}
