package com.lgy.dao.action;

import java.util.List;
import java.util.Map;

import com.lgy.entiy.Users;
import com.lgy.server.UserServer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UsersAction extends ActionSupport {
	private UserServer userServer;
	private Users users;
	private String uname;
	private String upwd;

	public String getUname() {
		return uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public Users getUsers() {
		return users;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	/**
	 * 作者：陆广有 方法说明：用户登录 参数说明：@return 2011-10-16
	 */
	public String loginUsers() {
		String tag = ERROR;
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		List<Users> userList = userServer.LoginUser(uname, upwd);
		if (userList == null || userList.size() == 0) {
			return tag;
		}
		users = userList.get(0);
		if (users.getUname().equals(uname) && users.getUpwd().equals(upwd)) {
			session.put("users", users);
			tag = SUCCESS;

		} else {
			tag = ERROR;
		}
		return tag;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public String exitUsers() {
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		session.put("users", null);
		return SUCCESS;
	}
}
