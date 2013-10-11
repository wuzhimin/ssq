package cn.beansoft.scm.entity;

import java.util.Date;

/**
 * User cn.beansoft.scm.entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String realName;
	private String password;
	private String address;
	private String postCode;
	private String homePhone;
	private Integer gender;
	private String cellPhone;
	private String officePhone;
	private Date birthday;
	private Integer loginCount;
	private Integer buyCount;
	private Double payNum;
	private String email;
	private String website;
	private String im;
	private Integer userType;
	private String note;
	private String photo;
	private Date regDate;
	private boolean active;// ÊÇ·ñ¼¤»î

	// Constructors

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String name, String realName, String password, String address,
			String postCode, Integer gender, Date birthday, Integer userType,
			Date regDate) {
		this.name = name;
		this.realName = realName;
		this.password = password;
		this.address = address;
		this.postCode = postCode;
		this.gender = gender;
		this.birthday = birthday;
		this.userType = userType;
		this.regDate = regDate;
	}

	/** full constructor */
	public User(String name, String realName, String password, String address,
			String postCode, String homePhone, Integer gender,
			String cellPhone, String officePhone, Date birthday,
			Integer loginCount, Integer buyCount, Double payNum, String email,
			String website, String im, Integer userType, String note,
			String photo, Date regDate) {
		this.name = name;
		this.realName = realName;
		this.password = password;
		this.address = address;
		this.postCode = postCode;
		this.homePhone = homePhone;
		this.gender = gender;
		this.cellPhone = cellPhone;
		this.officePhone = officePhone;
		this.birthday = birthday;
		this.loginCount = loginCount;
		this.buyCount = buyCount;
		this.payNum = payNum;
		this.email = email;
		this.website = website;
		this.im = im;
		this.userType = userType;
		this.note = note;
		this.photo = photo;
		this.regDate = regDate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getBuyCount() {
		return this.buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Double getPayNum() {
		return this.payNum;
	}

	public void setPayNum(Double payNum) {
		this.payNum = payNum;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIm() {
		return this.im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}