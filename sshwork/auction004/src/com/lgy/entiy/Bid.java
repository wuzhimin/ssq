package com.lgy.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者：陆广有
 * 类的说明：出价实体类
 * 电子邮件：952117701@qq.com
 */

@Entity
@Table(name="t_bid",catalog="test1")
public class Bid {
	
	
	private int bid;
	
	private Product product;
	
	private Integer bindex;  //顺序号
	
	private Users users;
	
	private String bcreate_time;//出价时间
	
	private Integer bprice; //出价金额
	
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "b_id", unique = true, nullable = false)
	public int getBid() {
		return bid;
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Column(name = "b_index")
	public Integer getBindex() {
		return bindex;
	}
	public void setBindex(Integer bindex) {
		this.bindex = bindex;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "u_id")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@Column(name = "b_create_time", length = 100)
	public String getBcreate_time() {
		return bcreate_time;
	}
	public void setBcreate_time(String bcreate_time) {
		this.bcreate_time = bcreate_time;
	}
	
	@Column(name = "b_price")
	public Integer getBprice() {
		return bprice;
	}
	public void setBprice(Integer bprice) {
		this.bprice = bprice;
	}
	
	
}
