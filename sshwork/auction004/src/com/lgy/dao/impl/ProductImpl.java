package com.lgy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lgy.entiy.Product;

public class ProductImpl extends HibernateDaoSupport {
	
	public void addProduct(Product product) {
		this.getHibernateTemplate().save(product);
		//this.getHibernateTemplate().saveOrUpdate(product);
	}

	public void updateProduct(Product product) {
		this.getHibernateTemplate().update(product);
	}

	public void delProduct(Product product) {
		System.out.println(product.getPid());
			this.getHibernateTemplate().delete(product);
		
		
	}

	public List<Product> showProductList() {
		List<Product> list = new ArrayList<Product>();
		list=this.getHibernateTemplate().find("from  Product");
			return list;
	}
	public List<Product> showProductByName() {
		List<Product> list = new ArrayList<Product>();
		
		return list;
	}
	public Product showProductById(int id) {
		Product product = new Product();
		product=(Product)this.getHibernateTemplate().get(Product.class, id);
		System.out.println(product.getPname());
		//System.out.println(product.getBid());
		/*Set<Bid> list =product.getBid();
		 for (Iterator listIterator = list.iterator(); listIterator.hasNext();) {
			Bid bid = (Bid) listIterator.next();
		    System.out.println(bid.getBprice());
		    System.out.println(bid.getUsers().getUname());
		}*/
		return product;
	}


}
