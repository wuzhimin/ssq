package com.wzm.web.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.wzm.server.entity.User;
import com.wzm.server.service.UserService;
import com.wzm.util.BeanUtil;

//@ParentPackage(value = "struts-default")
@ParentPackage(value = "mydefault")
@Action(value = "user", results = {
		@Result(name = "success", location = "success.jsp"),
		@Result(name = "login_error", location = "login.jsp"),
		@Result(name = "register_error", location = "register.jsp") })
public class UserAction extends ActionSupport {

	// 登录失败
	private static final String LOGIN_ERROR = "login_error";
	
	// 注册失败
	private static final String REGISTER_ERROR = "register_error";

	private static final long serialVersionUID = -5745479657792199583L;

	// 用户名
	private String uname;
	
	// 密码
	private String pwd;
	
	// 提示信息
	private String txtinfo;
	
	// 检验userName是已被注册
	private ByteArrayInputStream userNameRegisterStream;

	/**
	 * 用户注册
	 * @return
	 */
	public String register() {
		User user = new User();
		user.setName(uname);
		user.setPwd(pwd);
		UserService userService = BeanUtil.getUserService();
		boolean isSuccess = userService.userRegister(user);
		
		if(!isSuccess) {
			txtinfo="注册失败，用户名已存在，请重新注册！";
		} else {
			Map<String, Object> session = BeanUtil.getSession();
			if(null != session) {
				session.put("username", uname);
			}
			
			txtinfo="注册成功，用户已登录！";
		}

		return isSuccess ? SUCCESS : REGISTER_ERROR;
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	 
	public String login() {
		User user = new User();
		user.setName(uname);
		user.setPwd(pwd);
		UserService userService = BeanUtil.getUserService();
		boolean isSuccess = userService.userLogin(uname, pwd);
		
		if(isSuccess) {
			// 把username保存到session中
			Map<String, Object> session = BeanUtil.getSession();
			if(null != session) {
				session.put("username", uname);
			}
			txtinfo="用户已登录！";
		} else {
			txtinfo="用户名或者密码错误，请重新输入！";
		}

		return isSuccess ? SUCCESS : LOGIN_ERROR;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = BeanUtil.getSession();
		if(session.containsKey(uname)) {
			return SUCCESS;
		} else {
			return LOGIN_ERROR;
		}
	}
	
	@Action(value = "checkUserName",  
			results = {@Result( name="checkUserNameResult", params={"contentType","text/html","inputName","userNameRegisterStream"}, type="stream")}) 
	public String checkUserName() throws IOException {
		
		UserService userService = BeanUtil.getUserService();
		
		User user = userService.findUserByName(uname);
		
		boolean isRegister = false;
		if(user!=null) {
			isRegister = true;
		}
		
		userNameRegisterStream = new ByteArrayInputStream((!isRegister+"").getBytes());     
		
		return "checkUserNameResult";
	}

	public String getTxtinfo() {
		return txtinfo;
	}

	public void setTxtinfo(String txtinfo) {
		this.txtinfo = txtinfo;
	}

	public ByteArrayInputStream getUserNameRegisterStream() {
		return userNameRegisterStream;
	}

	public void setUserNameRegisterStream(ByteArrayInputStream userNameRegisterStream) {
		this.userNameRegisterStream = userNameRegisterStream;
	}

}
