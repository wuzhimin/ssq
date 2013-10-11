package cn.beansoft.scm.dao;

import java.util.List;

import cn.beansoft.scm.entity.User;



public class UserDAO extends BaseDAO {

	public List<User> findByName(String username) {
		String hql = "from User where name = ?";
        List<User> users = super.findByHQL(hql, username);
        return users;
	}

	public List<User> findByEmail(String email) {
		String hql = "from User where email = ?";
        List<User> users = super.findByHQL(hql, email);
        return users;
	}

}