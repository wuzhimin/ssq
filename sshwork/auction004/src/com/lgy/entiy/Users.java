package com.lgy.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者：陆广有 类的说明：用户信息实体类 电子邮件：952117701@qq.com
 */

@Entity
@Table(name="t_users",catalog="test1")
public class Users {
	
	private int uid;
	
	private String uname;
	
	private String upwd;
	
	private String ucard;
	
	private String uphone;
	
	private String uaddress;
	
	private String upostcoed;
	
	private int ustatus;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "u_id", unique = true, nullable = false)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "u_name", length = 100)
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "u_pwd", length = 100)
	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	@Column(name = "u_card", length = 100)
	public String getUcard() {
		return ucard;
	}

	public void setUcard(String ucard) {
		this.ucard = ucard;
	}

	@Column(name = "u_phone", length = 100)
	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	@Column(name = "u_address", length = 100)
	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	@Column(name = "u_postcoed", length = 100)
	public String getUpostcoed() {
		return upostcoed;
	}

	public void setUpostcoed(String upostcoed) {
		this.upostcoed = upostcoed;
	}

	@Column(name = "u_status")
	public int getUstatus() {
		return ustatus;
	}

	public void setUstatus(int ustatus) {
		this.ustatus = ustatus;
	}

}
