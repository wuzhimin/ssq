package com.wzm.server.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.StringUtils;

import com.wzm.server.dao.UserDao;
import com.wzm.server.entity.BaseEntity;
import com.wzm.server.entity.User;

/**
 * UserService实现类
 * @author wzm
 *
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	public UserServiceImpl() {
		
	}

	@Override
	public List<User> getUserList(String hql) {
		return ((UserDao)getBaseDao()).findUsersByHql(hql);
	}

	public UserDao getUserDao() {
		return ((UserDao)getBaseDao());
	}

	@Override
	public User findUserById(Serializable id) {
		if(null != id) {
			return (User) getBaseDao().find(id, User.class);
		}
		return null;
	}

	@Override
	public User findUserByName(String name) {
		if(StringUtils.hasLength(name)) {
			
			List<BaseEntity> users = getBaseDao().find("from User u where u.name = ?", new String[]{name} );
			if(users.size()>0) {
				return (User)users.get(0);
			}
		}
		return null;
	}

	@Override
	/*
	 * 用户登录
	 * @see com.wzm.server.service.UserService#userLogin(java.lang.String, java.lang.String)
	 */
	 
	public boolean userLogin(String name, String pwd) {
		User user = findUserByName(name);
		if(null != user && user.getPwd().equals(pwd)) {
			return true;
		}
		return false;
	}

	@Override
	/*
	 * 用户注册
	 * @see com.wzm.server.service.UserService#userRegister(com.wzm.server.entity.User)
	 */
	public boolean userRegister(User user) {
		
		// 1.检查用户是否已经存在
		User tmp = findUserByName(user.getName());
		if(tmp!=null) {
			return false;
		}
		
		// 2.保存用户
		Serializable id = add(user);
		if(id==null) {		
			return false;
		}
		
		return true;
	}

}
