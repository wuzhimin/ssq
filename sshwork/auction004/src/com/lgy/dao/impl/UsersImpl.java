package com.lgy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lgy.entiy.Users;

public class UsersImpl extends HibernateDaoSupport {
	public void addUsers(Users users) {
	}

	public void updateUsers(Users users) {

	}

	public void delUsers(Users[] users) {
	}

	public List<Users> showUsers() {
		List<Users> list = new ArrayList<Users>();
		return list;
	}

	public List<Users> showUsersByName() {
		List<Users> list = new ArrayList<Users>();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Users> loginUsers(String uname, String upwd) {

		List<Users> list = new ArrayList<Users>();
		Object[] object = { uname, upwd };
		list = this.getHibernateTemplate().find(
				"from Users where uname=? and upwd=?", object);

		return list;
	}
}
