package com.yaxing.DaoImpl;

import com.yaxing.Dao.UserDao;
import com.yaxing.base.BaseDaoImpl;
import com.yaxing.model.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	public UserDaoImpl() {
		clazz = User.class;
	}

	public User getByNameAndPassword() {

		return null;
	}

}
