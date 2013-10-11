package com.lgy.entiy;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者：陆广有
 * 类的说明：商品实体类
 * 电子邮件：952117701@qq.com
 */

@Entity
@Table(name="t_product",catalog="test1")
public class Product {
	
	private String pbegin_date;    	// 开始时间
	
	private String pdesc;         	// 描述
	
	private String pend_date;		// 结束时间
	
	private int pid;                // ID
	 
	private String pname;			// 名称
	
	private int preserve_price;
	
	private int pstart_price;
	
	private int pstatus;
	
	private Set<Bid> bid;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bid")
	public Set<Bid> getBid() {
		return bid;
	}
	
	public void setBid(Set<Bid> bid) {
		this.bid = bid;
	}
	
	@Column(name = "p_begin_date", length = 100)
	public String getPbegin_date() {
		return pbegin_date;
	}
	public void setPbegin_date(String pbegin_date) {
		this.pbegin_date = pbegin_date;
	}
	
	@Column(name = "p_desc", length = 100)
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	
	@Column(name = "p_end_date", length = 100)
	public String getPend_date() {
		return pend_date;
	}
	public void setPend_date(String pend_date) {
		this.pend_date = pend_date;
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "p_id", unique = true, nullable = false)
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	@Column(name = "p_name", length = 100)
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

	@Column(name = "p_reserve_price")
	public int getPreserve_price() {
		return preserve_price;
	}
	public void setPreserve_price(int preserve_price) {
		this.preserve_price = preserve_price;
	}
	
	@Column(name = "p_start_price")
	public int getPstart_price() {
		return pstart_price;
	}
	public void setPstart_price(int pstart_price) {
		this.pstart_price = pstart_price;
	}
	
	@Column(name = "p_status")
	public int getPstatus() {
		return pstatus;
	}
	public void setPstatus(int pstatus) {
		this.pstatus = pstatus;
	}
	
}
