package com.yaxing.Dao;

import com.yaxing.base.BaseDao;
import com.yaxing.model.User;

public interface UserDao extends BaseDao<User>{
	User getByNameAndPassword();
}
