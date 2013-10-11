package cn.beansoft.scm.action;
import java.util.ArrayList;
import java.util.List;

import util.BeanDebugger;
import util.PageBean;
import util.StringUtil;
import util.mail.MailSender;
import cn.beansoft.scm.dao.BaseDAO;
import cn.beansoft.scm.entity.*;



/**
 * 系统管理员模块.
 * 
 * @author BeanSoft
 * 
 */
public class AdminAction extends UserAction {
	
	/**
	 * 根据编号删除实体.
	 * 需要的参数: 
	 * entity=实体名
	 * id=主键
	 * 可选参数:entityDesc=实体中文描述
	 * @return
	 */
	public String deleteByID() {
		resetMessages();
		String entityName = getParameter("entity");// 实体名
		String entityDesc = getParameter("entityDesc");// 实体中文描述
		
		System.out.println(entityDesc);
		
		String id = getParameter("id");// 主键
		if(id != null && entityName != null) {
			getBaseDAO().deleteById(entityName, id);
			if(entityDesc == null) {
				entityDesc = "数据";
			}
			
			setMessage("编号为 " + id + " 的" + entityDesc + " 删除成功");
			
			setAttribute("returnURL", getRequest().getHeader("referer"));
		}
		
		
		return "message";

	}
	
	/**
	 * 根据用户名搜索 ==> 显示用户信息的结果页面
	 */
	public String searchByUserName() {

		User u = getUserManager().findByName(getUser().getName());

		setAttribute("user", u);

		return "viewUser";
	}

	/**
	 * 根据ID查找用户信息并显示.
	 * @return
	 * @throws Exception
	 */
	public String findUserById() throws Exception {
		super.findById();// 委托父类方法
		return "viewUser";
	}
	
	/**
	 * 转向用户修改页面
	 * @return
	 * @throws Exception 
	 */
	public String toEditUser() throws Exception {
		findUserById();
		return "editUser";
	}

	/**
	 * 修改用户资料.
	 */
	public String updateUser() {
		setMessage(null);
		// 从 session 读取当前用户信息
		User userInDB = getUserManager().findById(getParameterLong("user.id"));
		
		if(userInDB == null) {
			setMessage("更新失败, 此用户信息不存在!");
			return "editUser";
		}
		
		// 更新必要信息
		userInDB.setRealName(getUser().getRealName());
		userInDB.setAddress(getUser().getAddress());
		userInDB.setPostCode(getUser().getPostCode());
		userInDB.setHomePhone(getUser().getHomePhone());
		userInDB.setCellPhone(getUser().getCellPhone());
		userInDB.setOfficePhone(getUser().getOfficePhone());
		userInDB.setIm(getUser().getIm());
		userInDB.setWebsite(getUser().getWebsite());
		userInDB.setNote(getUser().getNote());
		// 帐号启用设置
		userInDB.setActive(getUser().isActive());
		
		// 密码非空时更改密码并通知用户
		String password = getParameter("password");
		
		if(!StringUtil.isEmpty(password)) {
			userInDB.setPassword(util.MD5Bean.md5(password));
			
	        MailSender sender = new MailSender();
	
	        sender.setFrom("\"Admin\" <admin@beansoft.cn>");
	        sender.setTo(userInDB.getEmail());
	        sender.setSubject("SCM系统修改密码通知邮件");
	        sender.setBody("管理员修改了您的帐户密码, 新密码是" + password + ", 请立即登录后修改此密码!");
	
	        System.out.println(sender.sendMail());
		}
        
		BeanDebugger.dump(getUser());

		// 保存用户
		getUserManager().update(userInDB);
		setMessage("用户" + userInDB.getName() + "资料修改成功");


		return "message";
	}
	
	/**
	 * 添加用户==> 直接激活, 不检查注册码, 不需要实现用户邮件通知, 其他功能和User模块的注册是相同的
	 */
	public String addUser() {
		// TODO 用户唯一后台验证 1. 可用SQL的unique 2. 用java检验
		// 用户名敏感词禁止注册
		// 2008-10-29 fix bug: getParameter(getUser().getName())
		if (util.BadWordFilter.hasBadWords(getUser().getName())) {
			setMessage("您的用户名包含有中国法律所不允许的敏感词汇");
			return "message";
		}

		// 密码MD5保存
		getUser().setPassword(
				util.MD5Bean.md5(getUser().getPassword().getBytes()));
		// 保存注册日期
		getUser().setRegDate(new java.util.Date());
		// 设置激活状态
		getUser().setActive(true);

		getUserManager().save(getUser());

		// 重命名文件并保存上传的图片, 复制临时文件夹中的文件到 /upload 目录下
		if (getPhotoFileName() != null && getPhotoFileName().length() > 0) {
			// 上传目录
			String uploadFolder = getApplication()
					.getRealPath("/upload");
			String uploadPhotoFileName = System.currentTimeMillis() + "_"
					+ new java.util.Random().nextInt(10000) + "_"
					+ util.Counter.getInstance().nextValue() + "."
					+ util.FileOperate.getExtension(getPhotoFileName());
			
			util.FileOperate.copyFile(getPhoto().getAbsolutePath(),
					uploadFolder + java.io.File.separator
							+ uploadPhotoFileName);
			// 更新头像的存储路径
			getUser().setPhoto("/upload/" +uploadPhotoFileName);
			getUserManager().update(getUser());
		}

		setMessage("用户添加成功");
		return "message";
	}
	
	/**
	 * 分页显示用户列表.
	 * @return
	 */
	public String listUser() {
		super.list();// 调用父类的列表功能
		
		PageBean pageBean =  (PageBean) getAttribute("pageBean");
		if(pageBean != null) {
			pageBean.setPageUrl("admin/listUser.action");
		}
		
		return "userList";
	}
	
	/**
	 * 根据 ID 删除用户.
	 * @return
	 */
	public String deleteUser() {
		int id = getParameterInt("id");
		
		if(id > 0) {
			User u = getUserManager().findById(id);
			getUserManager().delete(u);
			setMessage("删除成功");
			setAttribute("returnURL", getRequest().getHeader("referer"));
		}
		
		
		return "message";
	}
	
	public String advanceUserQuery() {
		setMessage("查询条件为:" + getParameter("condition"));
		return "message";
	}
	
	/**
	 * 更新数据库和Application缓存中的网站全局设置.
	 * @return
	 */
	public String updateAppConfig() {
		BaseDAO dao = getBaseDAO();
		AppConfig appConfig = (AppConfig) dao.findById(AppConfig.class, new Integer(1));
		
		appConfig.setAfficheContent(getParameter("appConfig.afficheContent"));
		appConfig.setAppTitle(getParameter("appConfig.appTitle"));
		appConfig.setBadwords(getParameter("appConfig.badwords"));
		appConfig.setAfficheTitle(getParameter("appConfig.afficheTitle"));
		appConfig.setCopyright(getParameter("appConfig.copyright"));
	
		BeanDebugger.dump(appConfig);
		
		// 更新系统配置		
		dao.update(appConfig);
		setTitle("系统管理");
		setMessage("网站配置更新成功");
		getApplication().setAttribute("appConfig", appConfig);

		// 分析并保存敏感词列表
		String words = appConfig.getBadwords();
		String[] wordsArray = words.split("\r\n");
		ArrayList<String> wordLists = new ArrayList<String>();
		for (String word : wordsArray) {
			System.out.println("处理敏感词 " + word);
			wordLists.add(word);
		}
		
		util.BadWordFilter.setWordList(wordLists);

		
		return "message";
	}
	
	/**
	 * 添加角色.
	 * @return
	 */
	public String addRole() {
		super.resetMessages();
		Role role = new Role();
		role.setRoleName(getParameter("roleName"));
		getBaseDAO().save(role);
		
		setTitle("管理");
		setMessage("角色添加成功");
		
		return "message";
	}
	
	/**
	 * 查看角色列表.
	 * @return
	 */
	public String roleList() {
		super.resetMessages();
		List roles = getBaseDAO().findAll("Role");
		setAttribute("roles", roles);
		
		return "roleList";
	}
	
	/**
	 * 转向添加资源页面.
	 * @return
	 */
	public String toAddResource() {
		roleList();
		return "addResource";
	}
	
	/**
	 * 添加资源访问许可.
	 * @return
	 */
	public String addResource() {
		super.resetMessages();
		setTitle("管理 - 添加资源许可");
		
		Resource r = null;
		
		String uri = getParameter("uri");
		
		if(StringUtil.isEmpty(uri)) {
			setMessage("资源不能为空");
			return "message";
		}
		
		int roleId = getParameterInt("roleId");
		
		String hql = "from Resource res where res.uri = ? and res.scmRole.id = ?";
		
		List<Resource> roles = getBaseDAO().findByHQL(hql, uri, roleId);
		
		if(roles != null && roles.size() > 0) {
			setMessage("该资源许可已存在, 因此我们未进行任何操作.");
		} else {
			r = new Resource();
			r.setUri(getParameter("uri"));
			r.setScmRole((Role) getBaseDAO().findById(Role.class, getParameterInt("roleId")));			
			r.setAddDate(new java.util.Date());
			r.setNote(getParameter("note"));
			
			getBaseDAO().save(r);
			setMessage("资源许可添加成功.");			
		}
		
		return "message";
	}
	
	public String resourceList() {
		List<Resource> resources = getBaseDAO().findByHQL("from Resource order by uri");
		setAttribute("resources", resources);
		return "resourceList";
		
	}
	
}
