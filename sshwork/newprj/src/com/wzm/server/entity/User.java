package com.wzm.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author wzm
 * 类说明：用户
 *
 */

@Entity
@Table(name="t_user",catalog="newprj")
public class User extends BaseEntity {

	private static final long serialVersionUID = 5926091904040938229L;

	public User() {
		
	}
	
	private String name;      // 用户名
	 
	private String pwd;       // 密码
	
	private String loginIP;
	
	private Date loginInDate;
	
	private Date loginOutDate;

	@Column(name = "fpwd", length=100)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "floginInIP", length=100)
	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	@Column(name = "floginInDate")
	public Date getLoginInDate() {
		return loginInDate;
	}

	public void setLoginInDate(Date loginInDate) {
		this.loginInDate = loginInDate;
	}

	@Column(name = "floginOutDate", length=30)
	public Date getLoginOutDate() {
		return loginOutDate;
	}

	public void setLoginOutDate(Date loginOutDate) {
		this.loginOutDate = loginOutDate;
	}

	@Column(name = "fName", unique=true, length=30, nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
