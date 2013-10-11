package com.wzm.server.entity.base;

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
@Table(name="t_user",catalog="ssq")
public class User extends BaseEntity {

	private static final long serialVersionUID = 5926091904040938229L;

	public User() {
		
	}
	
	private String name;      // 用户名
	 
	private String pwd;       // 密码
	
	private String email;     // email

	@Column(name = "fpwd", length=100)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "fName", unique=true, length=30, nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "femail", length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
