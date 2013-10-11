package com.wzm.server.dao.base;

import java.util.List;

import com.wzm.server.entity.base.User;

public interface UserDao extends BaseDao {
	
	public List<User> findUsersByHql(String hql);

}
