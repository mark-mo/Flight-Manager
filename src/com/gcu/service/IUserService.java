package com.gcu.service;

import com.gcu.model.User;
import com.gcu.model.UserProfile;
import com.gcu.model.UserToken;

public interface IUserService {
	public UserToken authenticate(User user);

	public UserToken register(UserProfile user);

}
