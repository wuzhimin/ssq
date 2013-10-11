package com.laodong.pub.login;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm{
	public String userid;
	public String password;
	public String dzxx;
	public String newpassword;
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDzxx() {
		return dzxx;
	}
	public void setDzxx(String dzxx) {
		this.dzxx = dzxx;
	}
    
}
