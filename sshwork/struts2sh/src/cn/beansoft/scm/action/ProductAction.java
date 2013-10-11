package cn.beansoft.scm.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import util.BeanDebugger;
import util.FileOperate;
import util.PageBean;
import util.StringUtil;
import cn.beansoft.scm.biz.ProductManager;
import cn.beansoft.scm.biz.VendorManager;
import cn.beansoft.scm.entity.Product;
import cn.beansoft.scm.entity.User;
import cn.beansoft.scm.entity.Vendor;


/**
 * 商品 Struts 2 Action.
 * @author BeanSoft
 */
public class ProductAction extends BaseActionSupport {
	private Product product;

	
	// 供应商业务层类
	private VendorManager vendorManager;
	// 产品业务层类
	private ProductManager productManager;
	

	// 图片上传相关属性
	private File photo;// 上传文件临时存储路径
	private String photoFileName;// 被上传的文件名
	
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
			// TODO Auto-generated catch block
			setMessage("此用户没有提交任何商家信息");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 添加商品
	 */
	public String add() {
		System.out.println(product.getVendor().getId());
		
		// 复制临时文件夹中的文件到 /upload 目录下
		if (getPhotoFileName() != null) {
			// 随机文件名
			String outputFile = System.currentTimeMillis()
					+ new java.util.Random().nextInt(10000) + "."
					+ FileOperate.getExtension(this.getPhotoFileName());
			FileOperate.copyFile(getPhoto().getAbsolutePath(),
					getApplication().getRealPath(
							"/upload")
							+ "/" + outputFile);
			product.setPhoto("/upload/" + outputFile);

		}
		// 设置注册日期
		product.setAddDate(new java.util.Date());
		product.setAudited(false);
		// 设置销量为0
		product.setTotalSold(0);
		
		// 根据ID查找供应商信息
		Vendor vendor = vendorManager.findById(product.getVendor().getId());
		if(vendor != null) {
			product.setVendor(vendor);
		} else {
			setMessage("请提供商品的供应商信息");
			return INPUT;
		}
		
		BeanDebugger.dump(product);

		// 保存用户
		if (productManager.add(product)) {
			setMessage("新商品" + product.getName() + "添加成功,请等待审批后发布商品");
			return SUCCESS;
		} else {
			setMessage("商品添加失败,请检查您输入的信息是否有误");
		}

		return INPUT;
	}

	// 所有商品列表(已审核)
	public String list() {
		setMessage(null);
		setTitle("所有商品列表");
		int currentPage = getParameterInt("page");
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageCount(2);
		pageBean.setRecordCount((int)productManager.getProductCountByAudited(true));
		pageBean.setPageUrl("product/list.action");
		setAttribute("pageBean", pageBean);
		
		setAttribute("products", 
				productManager.findAllByAudited(true, currentPage, pageBean.getPageCount()) );
		
		if("true".equalsIgnoreCase(getParameter("ajax"))) {
			return "ajax";
		}
		
		return SUCCESS;
	}
	
	// 我的供应商列表
	public String myList() {
		setMessage(null);
		setTitle("我的供应商列表");
		try {
			long user_id = getParameterLong("user_id");
			List<Vendor> vendors = vendorManager.findAllByUserId(user_id);
			
			if(vendors == null) {
				throw new Exception();
			}
			
			setAttribute("vendors", vendors);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			setMessage("此用户没有提交任何商家信息");
		}
		
		return SUCCESS;
	}
	
	// 根据ID查找单个产品
	public String findById() {
		setMessage(null);
		setTitle("查看商品信息");
		try {
			long id = getParameterLong("id");
			product = productManager.findById(id);
			
			if(product == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			setMessage("此商品信息不存在");
		}
		
		return SUCCESS;
	}
	
	// 根据名字查找商品列表, 包括参数是否有库存
	public String findProductByName() {
		setMessage(null);
		setTitle("商品查找结果");
		System.out.println("mergeParamsAsURI=" + mergeParamsAsURI());
		
		try {
			String name = getParameter("keyword");
			String availableStr = getParameter("available");
			
			boolean available = false;
			
			try {
				available = Boolean.parseBoolean(availableStr);
			} catch(Exception ex) {
				
			}
			
			if(StringUtil.isEmpty(name)) {
				setMessage("请输入商品名字");
			} else {
				setTitle("所有名字包含" + name + "的商品列表");
				List<Product> products = productManager.findAllByNameIncludeAmount(name, available);
				
				int currentPage = getParameterInt("page");
				PageBean pageBean = new PageBean();
				pageBean.setCurrentPage(currentPage);
				pageBean.setPageCount(2);
				pageBean.setRecordCount((int)productManager.countFindAllByNameIncludeAmount(name, available));
				pageBean.setPageUrl("product/findByName.action?" + mergeParamsAsURI());
				setAttribute("pageBean", pageBean);
				
				if(products == null || products.size() == 0) {
					throw new Exception();
				}
				
				setAttribute("products", products);				
			}

		} catch (Exception e) {
			setMessage("没有符合条件的商品信息");
		}
		
		if("true".equalsIgnoreCase(getParameter("ajax"))) {
			return "ajax";
		}
		
		return SUCCESS;
	}
	
	// 根据审核状态查找商品列表
	public String findAllByAudited() {
		setMessage(null);
		boolean audited = false;
		
		try {
			audited = Boolean.parseBoolean(getParameter("audited"));
		} catch (Exception e) {
		}
		
		List<Product> products = productManager.findAllByAudited(audited);
		
		if(products == null || products.size() == 0) {
			setMessage("没有符合条件的商品列表");
		}
		
		setAttribute("products", products);
		
		setTitle(audited ? "已审核商品列表" : "未审核商品列表");
		
		return SUCCESS;
	}
	
	// 审批单个商品
	public String auditById() {
		setTitle("商品审批结果");
		setMessage(null);
		try {
			long id = Long.parseLong(getParameter("id"));
			Product p  = productManager.findById(id);
			if(p == null) {
				throw new Exception();
			}
			p.setAudited(true);
			productManager.update(p);
			setMessage("编号为" + id + ",名为 " + p.getName() + " 的商品审批成功");
		} catch (Exception e) {
			setMessage("此商品信息不存在");
		}

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
}
