package com.gcu.data;

import com.gcu.model.User;
import com.gcu.model.UserProfile;

public interface IUserDAO {
	public int createUser(UserProfile user);

	public int findByUser(User user);
}
