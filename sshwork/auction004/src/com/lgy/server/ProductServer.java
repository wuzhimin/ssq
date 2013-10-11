package com.lgy.server;

import java.util.List;

import com.lgy.dao.impl.ProductImpl;
import com.lgy.entiy.Product;

public class ProductServer {
	private ProductImpl productImpl;

	public ProductImpl getProductImpl() {
		return productImpl;
	}

	public void setProductImpl(ProductImpl productImpl) {
		this.productImpl = productImpl;
	}

	/**
	 * 作者：陆广有
	 * 方法说明：显示全部信息
	 * 参数说明：@return
	 * 2011-10-16
	 */
	public List<Product> showProductList() {
		return productImpl.showProductList();
	}

	/**
	 * 作者：陆广有
	 * 方法说明：根据di显示信息
	 * 参数说明：@param id
	 * 参数说明：@return
	 * 2011-10-16
	 */
	public Product showProductById(int id) {

		return productImpl.showProductById(id);
	}
	/**
	 * 作者：陆广有
	 * 方法说明：删除
	 * 参数说明：@param product
	 * 参数说明：@return
	 * 2011-10-16
	 */
	public String delProduct(Product product) {
		String fag ="error";
		try {
			productImpl.delProduct(product);
			fag="success";
		} catch (Exception e) {
			fag="error";
		}
		
		return fag;
	}
	/**
	 * 作者：陆广有
	 * 方法说明：修改
	 * 参数说明：@param product
	 * 参数说明：@return
	 * 2011-10-16
	 */
	public String updateProduct(Product product) {
	      
		String fag ="error";
		try {
			productImpl.updateProduct(product);
			fag="success";
		} catch (Exception e) {
			fag="error";
		}
		return fag;
	}
	public void addProduct(Product product) {
		productImpl.addProduct(product);		
	}
}
