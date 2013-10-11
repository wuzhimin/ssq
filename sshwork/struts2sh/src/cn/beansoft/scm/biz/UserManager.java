/**
 * 
 */
package cn.beansoft.scm.biz;

import java.util.List;

import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.User;

/**
 * 用户业务模块, 包括登录, 注册, 修改等等.
 * @author BeanSoft
 *
 */
public class UserManager {
	// DAO 实例
	private UserDAO dao;

	/**
	 * @return the cn.beansoft.scm.dao
	 */
	public UserDAO getDao() {
		return dao;
	}

	/**
	 * @param cn.beansoft.scm.dao the cn.beansoft.scm.dao to set
	 */
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}
	
	   /**
     * 根据用户名和密码进行登录检查.
     * @param username 用户名
     * @param password 密码
     */
    public boolean checkLogin(String username, String password, int userType) throws Exception {
		// 1. 对密码进行MD5摘要计算
		password = util.MD5Bean.md5(password.getBytes());
		// 2. 是否存在给定用户
        User user = findByName(username);
        if (user == null) {
			// 3. 用户不存在
			throw new Exception("用户不存在");
		}
        
        // 4. 检查是否激活
        if (user.isActive() == false) {
			throw new Exception("用户帐号尚未激活, 请与管理员联系.");
		}        
        
        
        // 5. 检查密码, 用户类型是否匹配
        if(user != null && user.getPassword() !=  null && user.getPassword().equals(password)
        		&& user.getUserType() == userType) {
            return true;
        }
        
        return false;
    }
    
    
    /**
     * 执行添加用户到数据库的操作, 首先检查此用户是否已经存在.
     * @param user 用户对象
     */
    public boolean save(User user) {
        User userCheck = findByName(user.getName());
        
        if(userCheck != null) {
            return false;
        }
        
        dao.save(user);
        return true;
    }
    
    public void delete(User user) {
    	dao.delete(user);
    }
    
     /**
     * 根据用户名在数据库中查找用户对象.
     * @param username 用户名
     * @return User 用户对象
     */
    public User findByName(String username) {
        List<User> users = dao.findByName(username);
        if(users != null && users.size() > 0) {
        	return users.get(0);
        }
        
        return null;
    }
    
    /**
     * 根据ID在数据库中查找对象.
     * @param ID 编号
     * @return 找到的对象
     */
    public User findById(long id) {
        return (User) dao.findById(User.class, new Long(id));
    }    
    
    /**
     * 根据Email在数据库中查找用户对象.
     * @param username 用户名
     * @return User 用户对象
     */
    public User findByEmail(String email) {
        List<User> users = dao.findByEmail(email);
        if(users != null && users.size() > 0) {
        	return users.get(0);
        }
        
        return null;
    }
    
    /**
     * 获取所有用户列表.
     * 委托到 DAO 层完成.
     * @return 所有用户列表
     */
    public List<User> getAllUsers() {
        return getDao().findAll("User");
    }
    
    /**
     * 更改用户密码. 更改之前需要首先检查旧密码是否正确.
     * @param oldPassword 旧密码
     * @param user 带有新密码的用户对象
     */
    public boolean changeUserPassword(String oldPassword, User user) throws Exception {
        if(!checkLogin(user.getName(), oldPassword, user.getUserType())) {
            return false;
        }
        
        user.setPassword(util.MD5Bean.md5(user.getPassword()));
        
        dao.update(user);
        
        return true;
    }
    
    /**
     * 更改用户信息.
     * @param oldPassword 旧密码
     * @param user 带有新密码的用户对象
     */
    public void update(User user) {
        dao.update(user);
    }

	/**
	 * 获取用户的总数
	 * @return
	 */
	public int getTotalUsers() {
		return (int)( dao.queryForCount("select count(id) from User") );
	}
    
	/**
	 * 分页列出用户数据
	 * @param currentPage 当前页
	 * @param pageSize 每页显示条数
	 * @return 分页结果数据
	 */
	@SuppressWarnings("unchecked")
	public List<User> pageUsers(int currentPage, int pageSize) {
		return dao.pagedQuery("from User", currentPage, pageSize);
	}	
}
