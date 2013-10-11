package com.wzm.server.dao;

import java.util.List;

import com.wzm.server.entity.User;

public interface UserDao extends BaseDao {
	
	public List<User> findUsersByHql(String hql);

}
