package com.wzm.server.dao;

import java.util.List;

import com.wzm.server.entity.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao{

	@Override
	public List<User> findUsersByHql(String hql) {
		@SuppressWarnings("unchecked")
		List<User> list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
