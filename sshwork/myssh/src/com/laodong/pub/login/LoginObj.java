package com.laodong.pub.login;

import com.laodong.pub.product.spring.NllDObj;

public class LoginObj extends NllDObj{
	public String userid;

	public String password;

	public String xb;

	public String account;//’ ∫≈

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

}
