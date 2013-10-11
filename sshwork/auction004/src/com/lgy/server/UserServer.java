package com.lgy.server;

import java.util.List;

import com.lgy.dao.impl.UsersImpl;
import com.lgy.entiy.Users;

public class UserServer {
	private UsersImpl usersImpl;




	public UsersImpl getUsersImpl() {
		return usersImpl;
	}

	public void setUsersImpl(UsersImpl usersImpl) {
		this.usersImpl = usersImpl;
	}
	public List<Users> LoginUser(String uname,String upwd){
		
		return usersImpl.loginUsers(uname, upwd);
		
	}
}
