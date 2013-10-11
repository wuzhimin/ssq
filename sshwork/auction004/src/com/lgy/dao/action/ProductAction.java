package com.lgy.dao.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.lgy.entiy.Bid;
import com.lgy.entiy.Product;
import com.lgy.entiy.Users;

import com.lgy.server.BidServer;
import com.lgy.server.ProductServer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {
	
	 private List<Product> list;
	 private Product product;
	 private int id;
	 private ProductServer productServer;

	 private Bid bid;
		private BidServer bidServer;

		public Bid getBid() {
			return bid;
		}

		public void setBid(Bid bid) {
			this.bid = bid;
		}

		public BidServer getBidServer() {
			return bidServer;
		}

		public void setBidServer(BidServer bidServer) {
			this.bidServer = bidServer;
		}

	   	
		

		public String addBid() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   	String time = df.format(new Date());
			Users users = (Users) ActionContext.getContext().getSession().get("users");
			bid.setBcreate_time(time);
			bid.setUsers(users);
			bid.setProduct(product);
			bidServer.addBid(bid);
			this.setId(product.getPid());
			return SUCCESS;
		}

		public String addProduct() {
			productServer.addProduct(product);
			return SUCCESS;		
		}

		public String delProduct() {
			
			return productServer.delProduct(product);
		}

	
	public List<Product> getList() {
		return list;
	}

	public Product getProduct() {
		return product;
	}

	public ProductServer getProductServer() {
		return productServer;
	}

	
	public void setList(List<Product> list) {
		this.list = list;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductServer(ProductServer productServer) {
		this.productServer = productServer;
	}

	/**
	 * 作者：陆广有
	 * 方法说明：根据id显示
	 * 参数说明：@return
	 * 2011-10-16
	 */
	public String showProductById() {
		product =(Product) productServer.showProductById(product.getPid());
		return SUCCESS;
	}
	/**
	 * 作者：陆广有 方法说明：显示全部信息 参数说明：@return 2011-10-16
	 */
	public String showProductList() {
		list = productServer.showProductList();
		Users users= (Users) ActionContext.getContext().getSession().get("users");
		
		if (users!=null) {
			if (users.getUstatus()==2) {
				return "admin";
			}
			
		} 
		return SUCCESS;
	}
	/**
	 * 作者：陆广有 方法说明：修改product 参数说明：@return 2011-10-16
	 */
	public String updateProduct() {
		return productServer.updateProduct(product);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
