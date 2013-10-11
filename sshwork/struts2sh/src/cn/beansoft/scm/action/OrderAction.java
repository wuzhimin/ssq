package cn.beansoft.scm.action;

import java.util.List;

import util.StringUtil;

import cn.beansoft.scm.entity.Order;

/**
 * 订单Action.
 * @author BeanSoft
 *
 */
public class OrderAction extends CartAction {
	/**
	 *  我的订单列表
	 * @return
	 */
	public String myOrderList() {
		setMessage(null);
		setTitle("我的订单列表");
		try {
			long user_id = getSessionLoginedUser().getId();
			
			List<Order> orders = getOrderManager().listMyOrders(user_id);
			
			// 检查是否支付的参数
			if(!StringUtil.isEmpty(getParameter("payed"))) {
				boolean payed = Boolean.parseBoolean(getParameter("payed"));
				orders = getOrderManager().listMyOrders(user_id, payed);
				
				setTitle("我的订单列表 - " + (payed? "已支付" : "未支付") );
			}
			
			System.out.println(orders.size());

			if(orders == null || orders.size() == 0) {
				setMessage("没有符合条件的订单信息");
			}

			setAttribute("orders", orders);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("此用户没有任何订单信息");
		}

		return SUCCESS;
	}
	
	/**
	 * 查看单个订单的详细内容.
	 * @return
	 */
	public String viewOrderDetail() {
		resetMessages();

		setTitle("查看订单详情");
		try {
			long orderId = getParameterLong("id");
			
			Order order = (Order) getBaseDAO().findById(Order.class, orderId);
			
			System.out.println(order);

			setAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("此订单信息不存在");
		}
		
		return "viewOrder";
	}
	
	/**
	 *  我的我的购物总数
	 * @return
	 */
	public String myBuyCount() {
		setMessage(null);
		setTitle("我的购物总数");
		try {
			long user_id = getSessionLoginedUser().getId();
			
			String hql = "select sum(o.amount) from OrderItem o where o.scmOrder.scmUser.id = " + user_id;
			
			long count = getBaseDAO().queryForCount(hql);
			setMessage("当前共购物" + count + "件");
			
			hql = "select sum(o.cost) from Order o where o.scmUser.id = " + user_id;
			
			double cost = getBaseDAO().queryForSum(hql);
			
			setMessage(getMessage() + "<br>" + "购物总支出为:" + cost + "元");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("当前没有任何购物信息");
		}

		return "message";
	}	

}
