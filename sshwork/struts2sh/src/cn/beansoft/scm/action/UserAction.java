package cn.beansoft.scm.action;

import java.io.File;
import java.util.List;

import util.BeanDebugger;
import util.FileOperate;
import util.PageBean;
import util.StringUtil;
import util.mail.MailSender;


import cn.beansoft.scm.biz.UserManager;
import cn.beansoft.scm.entity.User;

/**
 * 用户 Struts 2 Action.
 * @author BeanSoft
 */
public class UserAction extends BaseActionSupport {
	private User user;

	// 用户业务层类
	private UserManager userManager;

	// 图片上传相关属性
	private File photo;// 上传文件临时存储路径
	private String photoFileName;// 被上传的文件名
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * 登录
	 */
	public String login() {
		setMessage(null);
		System.out
				.println("用户名:" + user.getName() + ",密码" + user.getPassword());

		try {
			if (getUserManager().checkLogin(user.getName(), user.getPassword(),
					user.getUserType())) {
				// 更新登录次数
				user = getUserManager().findByName(
						user.getName());
				// 修正手工插入数据后导致int为null的问题
				if(user.getLoginCount() == null) {
					user.setLoginCount(0);
				}
				
				user.setLoginCount(user.getLoginCount() + 1);
				userManager.update(user);
				
				// 将登录用户信息存入 session
				setSession("username", user.getName());
				setSession("loginedUser", user);
				
				System.out.println("登录成功");

				// 管理员跳转到管理首页
				// TODO 修正为Role中配置首页
				if(user.getUserType() == 4) {
					return "adminSuccess";
				}
				return SUCCESS;
			} else {
				setMessage("您输入的用户名或者密码或者用户类型不正确,请重试");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMessage(e.getMessage());
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * 注册, 保存头像.
	 */
	public String reg() {
		resetMessages();
		// 1. 检查验证码
		String regcode = (String) getSession("regcode");
		// 2. 检查输入的验证码
		String regcodeInput = getParameter("keycode");
		// TODO 用户唯一后台验证 1. 可用SQL的unique 2. 用java检验
		// TODO 密码一致验证
		// 用户名敏感词禁止注册
		if (util.BadWordFilter.hasBadWords(getParameter("user.username"))) {
			setMessage("您的用户名包含有中国法律所不允许的敏感词汇");
			return "success";
		}

		if (regcodeInput == null || regcodeInput.length() == 0) {
			setMessage("请输入验证码");
			return "success";
		} else if (regcodeInput.equals(regcode)) {
			// 3. 验证码检查成功, 继续向下
			// 密码MD5保存
			user.setPassword(util.MD5Bean.md5(user.getPassword().getBytes()));
			// 保存注册日期
			user.setRegDate(new java.util.Date());
			// 设置登录次数
			user.setLoginCount(0);			
			// 设置激活状态为1
			user.setActive(true);
			getUserManager().save(user);

			System.out.println("getPhotoFileName()=" + getPhotoFileName());

			// 重命名文件并保存上传的图片, 复制临时文件夹中的文件到 /upload 目录下
			if (getPhotoFileName() != null && getPhotoFileName().length() > 0) {
				// 上传目录
				String uploadFolder = getApplication()
						.getRealPath("/upload");
				String uploadPhotoFileName = System.currentTimeMillis() + "_"// 推荐改成年_月_日
						+ new java.util.Random().nextInt(10000) + "_"
						+ util.Counter.getInstance().nextValue() + "."
						+ util.FileOperate.getExtension(getPhotoFileName());
				
				util.FileOperate.copyFile(getPhoto().getAbsolutePath(),
						uploadFolder + java.io.File.separator
								+ uploadPhotoFileName);
				// 更新头像的存储路径
				user.setPhoto("/upload/" +uploadPhotoFileName);
				getUserManager().update(user);
			}
			
			// 发送注册通知邮件
			String message = "<html><body>您注册了新用户, 用户密码是:" + user.getName()
					+ ", 欢迎使用我们的系统! 此邮件用来确认您的邮箱是否正确. " + "</body></html>";
			;

			MailSender mailSender = new MailSender();
			mailSender.setFrom("admin@beansoft.cn");
			mailSender.setTo(user.getEmail());
			mailSender.setSubject("注册用户成功");
			mailSender.setBody(message);
			mailSender.setHtmlFormat(true);

			if (mailSender.sendMail()) {
				setMessage("新用户" + user.getName() + "注册成功,请点击右上角进行登录, 并请您检查邮箱接收通知邮件");
			} else {
				setMessage("注册成功, 但邮箱可能无法正常接受邮件, 建议您与管理员联系!");
			}
			
		}
		return INPUT;
	}

	// 用户注册 AJAX 验证功能
	public String ajaxValidate() {
		setMessage(null);
		String value = getParameter("value");
		String what = getParameter("what");
		
		// 用户名敏感词禁止注册和使用
		if (util.BadWordFilter.hasBadWords(value)) {
			setMessage("您的用户名包含有中国法律所不允许的敏感词汇");
		}
		
		if ("user.name".equals(what)) {

			User user = userManager.findByName(value);
			if (user != null) {
				setMessage("用户名已存在");
			}
			// 没加Filter应该手工专门
			// System.out.println("value1=" +
			// util.StringUtil.changeEncoding(value, "ISO8859-1", "UTF-8"));
			System.out.println("value=" + value);


		} else if ("user.email".equals(what)) {
			System.out.println("user.email");
			System.out.println("value=" + value);
			User user = userManager.findByEmail(value);
			if (user != null) {
				setMessage("邮箱地址已被注册");
			}
		} else if ("keycode".equals(what)) {
			System.out.println("keycode");
			System.out.println("value=" + value);
			// 1. 检查验证码
			String regcode = (String) getSession("regcode");

			if (value == null || value.length() == 0) {
				setMessage("验证码不能为空");
			} else if (!value.equals(regcode)) {
				setMessage("验证码不正确");
			}
		}else if ("keycode".equals(what)) {
			System.out.println("keycode");
			System.out.println("value=" + value);
			// 1. 检查验证码
			String regcode = (String) getSession("regcode");

			if (value == null || value.length() == 0) {
				setMessage("验证码不能为空");
			} else if (!value.equals(regcode)) {
				setMessage("验证码不正确");
			}
		} else if ("roleName".equals(what)) {
			// 检查角色名是否重复
			System.out.println("roleName");
			System.out.println("value=" + value);
			List roles = getBaseDAO().findByProperty("Role", "roleName", value);

			if (roles != null && roles.size() > 0) {
				setMessage("此角色已存在");
			}
		}
			
		return "success";

	}

	// 注册用户列表

	// 分页列表功能
	public String list() {
		resetMessages();
		// 1. 把当前页从表单参数取出来
		int currentPage = getParameterInt("page");
		// 2. 取出来记录总数
		int totalRecord = getUserManager().getTotalUsers();
		// 3. 根据当前页和每页显示数据从业务层取得数据
		List users = getUserManager().pageUsers(currentPage, 5);
		// 4. 配置分页Bean
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageCount(5);
		pageBean.setRecordCount(totalRecord);
		pageBean.setPageUrl("user/list.action");
		// 5. 将数据和分页bean存入request的属性中返回给前台进行显示
		setAttribute("title", "注册用户列表");
		setAttribute("users", users);
		setAttribute("pageBean", pageBean);
		
		System.out.println("总页数:" + pageBean.getTotalPage());

		// 转向excel视图层
		if ("true".equals(getParameter("excel"))) {
			return "excel";
		}

		return SUCCESS;
	}
	
	/**
	 * 根据编号查找用户信息
	 * @return
	 * @throws Exception - 查找时出错
	 */
	public String findById()throws Exception {
		setMessage(null);
			long id = Long.parseLong(getParameter("id"));
			user = userManager.findById(id);
			
			if(user == null) {
				setMessage("此用户信息不存在");
				throw new Exception("此用户信息不存在");
			}

		
		return SUCCESS;
	}

	// 修改密码
	public String changePassword() {
		setMessage(null);
		
		String oldPassword = getParameter("password");// 旧密码
		String newPassword = getParameter("password_new");// 修密码
		String newPasswordRepeat = getParameter("password_new_repeat");// 重复新密码
		
		if(StringUtil.isEmpty(oldPassword)) {
			setMessage("请输入旧密码!");
			return SUCCESS;
		}
		
		if(StringUtil.isEmpty(newPassword)) {
			setMessage("请输入新密码!");
			return SUCCESS;
		}
		
	    if(!newPassword.equals(newPasswordRepeat)) {
	    	setMessage("两次输入的密码不一致!");
	    	return SUCCESS;
	    }		
		
		// 从 session 读取当前用户信息

		
		User currentUser = getSessionLoginedUser();
		
		if(currentUser == null) {
			setMessage("您尚未登录!");
			return SUCCESS;
		}
		
		currentUser.setPassword(getParameter("password_new"));
		
		try {
			if(userManager.changeUserPassword(oldPassword, currentUser)) {
				setMessage("密码修改成功!");
				return SUCCESS;
			} else {
				setMessage("密码修改失败, 请检查输入是否正确!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}
	
	/**
	 * 注册, 保存头像.
	 */
	public String update() {
		setMessage(null);
		// 从 session 读取当前用户信息
		User currentUser = getSessionLoginedUser();
		
		if(currentUser == null) {
			setMessage("您尚未登录!");
			return INPUT;
		}
		
		// 更新必要信息
		currentUser.setAddress(user.getAddress());
		currentUser.setPostCode(user.getPostCode());
		currentUser.setHomePhone(user.getHomePhone());
		currentUser.setCellPhone(user.getCellPhone());
		currentUser.setOfficePhone(user.getOfficePhone());
		currentUser.setIm(user.getIm());
		currentUser.setWebsite(user.getWebsite());
		currentUser.setNote(user.getNote());
		
		
		System.out.println("getPhotoFileName=" + getPhotoFileName());
		System.out.println("user.getBirthday()=" + user.getBirthday());

		// 复制临时文件夹中的文件到 /upload 目录下
		if (getPhotoFileName() != null) {
			// 删除老头像文件
			if(!StringUtil.isEmpty(currentUser.getPhoto())) {
				FileOperate.delFile(getApplication().getRealPath(currentUser.getPhoto()));
			}
			
			// 随机文件名
			String outputFile = System.currentTimeMillis()
					+ new java.util.Random().nextInt(10000) + "."
					+ FileOperate.getExtension(this.getPhotoFileName());
			FileOperate.copyFile(getPhoto().getAbsolutePath(),
					getApplication().getRealPath(
							"/upload")
							+ "/" + outputFile);
			currentUser.setPhoto("/upload/" + outputFile);

		}

		BeanDebugger.dump(currentUser);

		// 保存用户
		getUserManager().update(currentUser);
		setMessage("用户" + currentUser.getName() + "资料修改成功");


		return INPUT;
	}
	
	/**
	 * 取回密码, 生成随机新密码并发送到用户注册时提供的邮箱.
	 * @return
	 */
	public String retrievePassword() {
/**
		1.	根据Email找到用户信息
		2.	生成一个随机的密码并更新到数据库
		3.	发送一封邮件告诉用户新密码
*/
		String email = getParameter("email");
		
		User user = userManager.findByEmail(email);
		
		if(user == null) {
			setMessage("密码取回失败: 您输入的邮件无效");
			
			return SUCCESS;
		} else {
			String password = new java.util.Random().nextInt(1000000000) + "";
			
			user.setPassword(util.MD5Bean.md5(password));
			
			userManager.update(user);
			
	        MailSender sender = new MailSender();

	        sender.setFrom("\"Admin\" <admin@earth.org>");
	        sender.setTo(email);
	        sender.setSubject("SCM系统取回密码通知邮件");
	        sender.setBody("应您的要求, 系统重置了您的帐户密码, 系统生成的新密码是" + password + ", 请立即登录后修改此密码!");

	        System.out.println(sender.sendMail());
	        
	        setMessage("您的新密码已经发送到了您注册时提供的邮箱, 请检查邮件并重新登录");
		}
		
		return SUCCESS;
	}
	
	

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}



	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}


}
