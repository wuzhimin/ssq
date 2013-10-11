package cn.beansoft.scm.action;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import util.BeanDebugger;
import util.FileOperate;
import util.StringUtil;


import cn.beansoft.scm.biz.UserManager;
import cn.beansoft.scm.biz.VendorManager;
import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.User;
import cn.beansoft.scm.entity.Vendor;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 供应商 Struts 2 Action.
 * @author BeanSoft
 */
public class VendorAction extends BaseActionSupport {
	private Vendor vendor;
	// 供应商业务层类
	private VendorManager vendorManager;

	// 图片上传相关属性
	private File photo;// 上传文件临时存储路径
	private String photoFileName;// 被上传的文件名
	
	/**
	 * 添加供应商
	 */
	public String add() {
		System.out.println("getPhotoFileName=" + getPhotoFileName());

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
			vendor.setPhoto("/upload/" + outputFile);

		}
		// 设置注册日期
		vendor.setRegDate(new java.util.Date());
		vendor.setAudited(false);
		// 从 session 读取当前用户信息
		
		vendor.setUser(getSessionLoginedUser());

		BeanDebugger.dump(vendor);

		// 保存用户
		if (vendorManager.add(vendor)) {
			setMessage("新供应商" + vendor.getName() + "添加成功,请等待审批后发布商品");
			return INPUT;
		} else {
			setMessage("添加失败,请检查您输入的信息是否有误");
		}

		return INPUT;
	}

	// 所有供应商列表
	public String list() {
		setMessage(null);
		setTitle("所有供应商列表");
		setAttribute("vendors", vendorManager.getAll());
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
	
	// 根据ID查找单个供应商
	public String findById() {
		setMessage(null);
		try {
			long id = getParameterLong("id");
			vendor = vendorManager.findById(id);
			
			if(vendor == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			setMessage("此商家信息不存在");
		}
		
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
		try {
			long id = getParameterLong("id");
			vendor = vendorManager.findById(id);
			if(vendor == null) {
				throw new Exception();
			}
			vendor.setAudited(true);
			vendorManager.update(vendor);
			setMessage("编号为" + id + ",名为 " + vendor.getName() + " 的供应商审批成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMessage("此供应商信息不存在");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查看当前用户的分成总额.
	 * @return
	 */
	public String viewDenductSum() {
		setTitle("查看您的分成总金额");
		
		User currentUser = getSessionLoginedUser();
		
		if(currentUser == null) {
			setMessage("您尚未登录!");
			return SUCCESS;
		} else {
			setMessage(vendorManager.getDenductSum(currentUser.getId()) + " 元");
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

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}
