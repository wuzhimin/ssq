/**
 * 
 */
package cn.beansoft.scm.biz;

import java.util.List;

import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.Order;

/**
 * 订单和订单项业务模块
 * 
 * @author BeanSoft
 * 
 */
public class OrderManager {
	// DAO 实例
	private OrderDAO orderDao;
	// OrderItem 的 DAO 实例
	private OrderItemDAO orderItemDao;

	/**
	 * 创建订单项
	 * @param order
	 * @return
	 */
	public boolean createOrder(Order order) {
		try {
			orderDao.save(order);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * 列出指定用户的订单.
	 * @param userId
	 * @param pageParams
	 * @return
	 */
	public List<Order> listMyOrders(long userId, int... pageParams ) {
		String hql = " from Order o where o.scmUser.id = " + userId;
		
		if(pageParams.length > 0) {
			return orderDao.findByHQL(hql, pageParams[0], pageParams[1]);
		} else {
			return orderDao.findByHQL(hql);
		}
	}
	
	/**
	 * 列出指定用户的订单, 根据是否支付来查找.
	 * @param userId
	 * @param payed - 是否支付
	 * @param pageParams
	 * @return 订单列表
	 */
	public List<Order> listMyOrders(long userId, boolean payed, int... pageParams ) {
		String hql = " from Order o where o.scmUser.id = " + userId + " and o.status  ";
		if(payed) {
			hql += " > 0";
		} else {
			hql += " = 0";
		}
		
		if(pageParams.length > 0) {
			return orderDao.findByHQL(hql, pageParams[0], pageParams[1]);
		} else {
			return orderDao.findByHQL(hql);
		}
	}
	
	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderdao) {
		this.orderDao = orderdao;
	}

	public OrderItemDAO getOrderItemDao() {
		return orderItemDao;
	}

	public void setOrderItemDao(OrderItemDAO orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	

}
