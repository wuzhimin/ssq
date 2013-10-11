package cn.beansoft.scm.entity;

import java.util.Date;

/**
 * Product cn.beansoft.scm.entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Long id;
	private Vendor vendor;
	private User scmUser;
	private String name;
	private Integer amount;
	private Date auditDate;
	private boolean audited;
	private String catalog;
	private String description;
	private String photo;
	private Double price;
	private Double rate;
	private Double rebate;
	private Integer totalSold;
	private Date addDate;

	// Constructors

	/** default constructor */
	public Product() {
	}


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public User getScmUser() {
		return this.scmUser;
	}

	public void setScmUser(User scmUser) {
		this.scmUser = scmUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getRebate() {
		return this.rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public Integer getTotalSold() {
		return this.totalSold;
	}

	public void setTotalSold(Integer totalSold) {
		this.totalSold = totalSold;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

}