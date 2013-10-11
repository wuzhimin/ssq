package com.lgy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lgy.entiy.Bid;
import com.lgy.entiy.Product;

public class BidImpl extends HibernateDaoSupport {

	public void addBid(Bid bid) {
		//Product product=(Product)this.getHibernateTemplate().load(Product.class, bid.getBid());
		//product.setPstatus(2);
		//bid.setProduct(product);
		this.getHibernateTemplate().save(bid);
	}

	public void updateBid(Bid bid) {
		
	}

	public void delBid(Bid[] bids) {
	}

	public List<Bid> showBid() {
		List<Bid> list = new ArrayList<Bid>();
		list = this.getHibernateTemplate().find("from Bid");
		return list;
	}
	public List<Bid> showBidByProductId(int id) {
		List<Bid> list = new ArrayList<Bid>();
		list = this.getHibernateTemplate().find("from Bid b where b.product.pid=?",id);
		return list;
	}

	public List<Bid> showBidByName() {
		List<Bid> list = new ArrayList<Bid>();
		return list;
	}
}
