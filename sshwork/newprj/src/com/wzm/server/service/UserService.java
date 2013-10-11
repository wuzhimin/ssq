package com.wzm.server.service;

import java.io.Serializable;
import java.util.List;

import com.wzm.server.entity.User;

public interface UserService extends BaseService {
	public List<User> getUserList(String hql);
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return User
	 */
	public User findUserById(Serializable id);
	
	
	/**
	 * 根据用户名获取用户
	 * @param id
	 * @return
	 */
	public User findUserByName(String name);
	
	/**
	 * 登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	public boolean userLogin(String name, String pwd);
	
	/**
	 * 注册
	 * @param user
	 * @return boolean
	 */
	public boolean userRegister(User user);
	
}
