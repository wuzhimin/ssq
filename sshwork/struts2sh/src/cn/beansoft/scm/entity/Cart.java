package cn.beansoft.scm.entity;

import java.io.Serializable;
import java.util.*;

import cn.beansoft.scm.dao.*;

/**
 * 购物车实体类
 * 
 * @author BeanSoft
 * 
 */
public class Cart implements Serializable {
	// Map<商品主键, 订单项>
	private Map<Long, OrderItem> items = new HashMap<Long, OrderItem>();
	private double cost;

	/**
	 * 添加订单项
	 * @param item
	 */
	public void addItem(OrderItem item) {
		items.put(item.getProduct().getId(), item);
	}
	
	/**
	 * 根据商品编号获取订单项
	 * @param productId
	 * @return
	 */
	public OrderItem getItem(long productId) {
		return items.get(productId);
	}

	/**
	 * 更改商品数目
	 * @param productId
	 * @param num
	 */
	public void modifyItemNumber(long productId, int num) {
		OrderItem oldItem = items.get(productId);
		oldItem.setAmount(num);
	}

	/**
	 * 根据编号删除商品
	 * @param pid
	 */
	public void removeItemByProductId(long pid) {
		items.remove(pid);
	}

	/**
	 * 清空购物车
	 */
	public void empty() {
		items.clear();
	}

	/**
	 * 购物车是否为空
	 * @return
	 */
	public boolean isEmpty() {
		if (items.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 物品总价格
	 * @return
	 */
	public double getCost() {
		double totalCost = 0;
		
		Iterator<OrderItem> it = items.values().iterator();
		
		while (it.hasNext()) {
			OrderItem item = it.next();
			
			totalCost += item.getCost();
		}
		
		return totalCost;
	}

	/**
	 * 获得购物车项目的Map列表.
	 * @return
	 */

	public Map<Long, OrderItem> getItems() {
		return items;
	}
	
	/**
	 * 获得购物车中的所有订单项(物品列表)
	 * @return
	 */
	public Collection<OrderItem> getOrderItems() {
		return items.values();
	}

	public void setItems(Map<Long, OrderItem> items) {
		this.items = items;
	}
	
	/**
	 * 商品件数(不是商品总个数)
	 * @return
	 */
	public int getItemCount() {
		if(items != null) {
			return items.size();
		}
		
		return 0;
	}

	public static void main(String[] args) {
		Cart cart = new Cart();
		Map items = cart.getItems();
		Iterator it = items.values().iterator();
		while (it.hasNext()) {
			it.next();
		}
	}
}
