package cn.beansoft.scm.entity;

import java.util.Date;

/**
 * Vendor cn.beansoft.scm.entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Vendor implements java.io.Serializable {

	// Fields

	private Long id;
	private User user;
	private User auditor;
	private String name;
	private String address;
	private Date auditDate;
	private boolean audited;
	private String catalog;
	private String note;
	private String photo;
	private String website;
	private Date regDate;

	// Constructors

	/** default constructor */
	public Vendor() {
	}

	/** minimal constructor */
	public Vendor(Long id, String name, String address, Date auditDate,
			Date regDate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.auditDate = auditDate;
		this.regDate = regDate;
	}



	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getAuditor() {
		return auditor;
	}

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public boolean getAudited() {
		return this.audited;
	}

	public void setAudited(boolean audited) {
		this.audited = audited;
	}

	public String getCatalog() {
		return this.catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}