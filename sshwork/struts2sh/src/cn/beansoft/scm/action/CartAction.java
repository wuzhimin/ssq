package cn.beansoft.scm.action;

import java.io.File;
import java.util.Collection;
import java.util.List;

import util.StringUtil;
import cn.beansoft.scm.biz.*;
import cn.beansoft.scm.entity.*;


/**
 * 购物车 Struts 2 Action.
 * @author BeanSoft
 */
public class CartAction extends BaseActionSupport {
	// 供应商业务层类
	private VendorManager vendorManager;
	// 产品业务层类
	private ProductManager productManager;
	// 订单业务层类
	private OrderManager orderManager;


	// 图片上传相关属性
	private File photo;// 上传文件临时存储路径
	private String photoFileName;// 被上传的文件名

	/**
	 * 从Session中获取购物车对象, 如果没有, 就生成一个.
	 * @return Cart
	 */
	public static Cart getCart() {
		Cart cart = (Cart)getSession("cart");

		if(cart == null) {
			cart = new Cart();
			setSession("cart", cart);
		}

		return cart;
	}

	/**
	 * 购物车测试数据
	 */
//	void testCart() {
//		Product p = productManager.findById(1L);
//		OrderItem item =  new OrderItem();
//		item.setProduct(p);
//		item.setAmount(2);
//		getCart().addItem(item);
//	}

	/**
	 * 根据ID查找单个商品
	 * @param productId
	 * @return
	 */
	private Product findProductById(long productId) {
		Product p = productManager.findById(productId);
		// 预先读取, 避免 lazy 异常
		p.getVendor().getName();

		return p;
	}

	/**
	 * 使用AJAX方式添加购物车商品.
	 * @return
	 */
	public String ajaxAddProduct() {
		setMessage(null);

		String productId = getParameter("product_id");
		try {
			long pid = Long.parseLong(productId);

			if(getCart().getItem(pid) == null) {
				Product p = findProductById(pid);

				if(p == null) {
					setMessage("添加商品失败: 此商品不存在!");
				} else {
					OrderItem item = new OrderItem();
					item.setAmount(1);
					// 保存添加时商品定价
					item.setPrice(p.getPrice());
					item.setRate(p.getRate());
					item.setRebate(p.getRebate());

					item.setAddDate(new java.util.Date());
					item.setProduct(p);
					getCart().addItem(item);
					setMessage("添加商品成功: 购物车中共有此商品1件!");
				}
			} else {
				OrderItem item = getCart().getItem(pid);
				item.setAmount(item.getAmount() + 1);
				setMessage("添加商品成功: 购物车中共有此商品" + item.getAmount() + "件!");
			}
		} catch(Exception ex) {
		}

		return SUCCESS;
	}

	/**
	 * AJAX 方式修改商品数目
	 * @return
	 */
	public String ajaxChangeAmout() {
		setMessage(null);

		String productId = getParameter("product_id");
		String amountStr = getParameter("amount");
		try {
			long pid = Long.parseLong(productId);
			int amount = Integer.parseInt(amountStr);

			if(getCart().getItem(pid) == null) {
				Product p = findProductById(pid);

				if(p == null) {
					setMessage("商品不存在!");
				} else {
					OrderItem item = new OrderItem();
					item.setAmount(amount);
					item.setPrice(p.getPrice());
					item.setRate(p.getRate());
					item.setRebate(p.getRebate());

					item.setAddDate(new java.util.Date());
					item.setProduct(p);
					getCart().addItem(item);
				}
			} else {
				OrderItem item = getCart().getItem(pid);
				item.setAmount(amount);
			}
		} catch(Exception ex) {
		}

		return SUCCESS;
	}


	/**
	 * 使用AJAX方式从购物车清除商品.
	 * @return
	 */
	public String ajaxRemoeOutProduct() {
		setMessage(null);

		String productId = getParameter("product_id");
		try {
			long pid = Long.parseLong(productId);

			getCart().removeItemByProductId(pid);
		} catch(Exception ex) {
		}

		return SUCCESS;
	}

	/**
	 * 清空购物车
	 * @return
	 */
	public String emtpyCart() {
		getCart().empty();
		return null;
	}

	/**
	 * 创建订单
	 * @return
	 */
	public String createOrder() {
		setMessage(null);
		// 从 session 读取当前用户信息
		User currentUser = getSessionLoginedUser();

		if(currentUser == null) {
			setMessage("您尚未登录,请登录后再创建!");
			return SUCCESS;
		}

		if(getCart().isEmpty()) {
			setMessage("购物车为空, 不能创建订单!");
			return SUCCESS;
		}

		// 创建订单
		Order order = new Order();
		order.setAddDate(new java.util.Date());
		order.setScmUser(currentUser);
		order.setStatus(0);
		order.setCost(getCart().getCost());
		// 处理订单项
		Collection<OrderItem> items = getCart().getOrderItems();

		for(OrderItem item : items) {
			item.setScmOrder(order);
			order.getOrderItems().add(item);
		}

		if(getOrderManager().createOrder(order)) {
			setMessage("订单创建成功!");
			// 修改库存和销量
			for(OrderItem item : items) {
				Product p = item.getProduct();
				p.setAmount(p.getAmount() - item.getAmount());
				if(p.getTotalSold() == null) {
					p.setTotalSold(0);
				}
				
				// 销量
				p.setTotalSold(p.getTotalSold() 
						+ item.getAmount());
				productManager.update(p);
			}
			//清空购物车
			getCart().empty();
		} else {
			setMessage("创建订单失败!");
		}

		return SUCCESS;
	}
	
	/**
	 * 购物车物品数
	 * @return
	 */
	public String count() {
		setMessage(getCart().getItemCount() + "");
		return SUCCESS;
	}

	// 查找我的供应商列表并转向添加商品页面
	public String toAddPage() {
		setMessage(null);
		setTitle(null);
		// 从 session 读取当前用户信息
		User currentUser = getSessionLoginedUser();

		if(currentUser == null) {
			setMessage("您尚未登录!");
			return SUCCESS;
		}

		try {
			long user_id = currentUser.getId();
			List<Vendor> vendors = vendorManager.findAllByUserId(user_id);

			if(vendors == null) {
				throw new Exception();
			}

			setAttribute("vendors", vendors);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("此用户没有提交任何商家信息");
		}

		return SUCCESS;
	}



	// 购物车所有商品列表
	public String list() {
		setMessage(null);
		setTitle("购物车商品列表");
//		testCart();
		setAttribute("orders", getCart().getOrderItems());
		return SUCCESS;
	}



	// 根据名字查找供应商列表
	public String findListByName() {
		setMessage(null);
		setTitle("根据名字查找的供应商列表");
		try {
			String name = getParameter("vend_name");

			if(StringUtil.isEmpty(name)) {
				setMessage("请输入供应商名字");
			} else {
				setTitle("所有名字包含" + name + "的供应商列表");
				List<Vendor> vendors = vendorManager.findAllByNameInclude(name);

				if(vendors == null || vendors.size() == 0) {
					throw new Exception();
				}

				setAttribute("vendors", vendors);
			}

		} catch (Exception e) {
			setMessage("没有符合条件的供应商信息");
		}

		return SUCCESS;
	}

	// 根据审核状态查找供应商列表
	public String findAllByAudited() {
		setMessage(null);
		boolean audited = false;

		try {
			audited = Boolean.parseBoolean(getParameter("audited"));
		} catch (Exception e) {
		}

		List<Vendor> vendors = vendorManager.findAllByAudited(audited);

		if(vendors == null || vendors.size() == 0) {
			setMessage("没有符合条件的供应商列表");
		}

		setAttribute("vendors", vendors);

		setTitle(audited ? "已审核供应商列表" : "未审核供应商列表");

		return SUCCESS;
	}

	// 审批单个供应商
	public String auditById() {
		setTitle("供应商审批结果");
		setMessage(null);
//		try {
//			long id = Long.parseLong(getParameter("id"));
//			vendor = vendorManager.findById(id);
//			if(vendor == null) {
//				throw new Exception();
//			}
//			vendor.setAudited(true);
//			vendorManager.update(vendor);
//			setMessage("编号为" + id + ",名为 " + vendor.getName() + " 的供应商审批成功");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			setMessage("此供应商信息不存在");
//		}
//
		return SUCCESS;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public VendorManager getVendorManager() {
		return vendorManager;
	}

	public void setVendorManager(VendorManager vendorManager) {
		this.vendorManager = vendorManager;
	}


	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}



	public ProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
}