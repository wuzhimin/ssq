/**
 * 
 */
package cn.beansoft.scm.biz;

import java.util.List;

import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.Vendor;

/**
 * 供应商业务模块.
 * @author BeanSoft
 */

public class VendorManager {
	// DAO 实例
	private VendorDAO dao;

	/**
	 * @return the cn.beansoft.scm.dao
	 */
	public VendorDAO getDao() {
		return dao;
	}

	/**
	 * @param cn.beansoft.scm.dao the cn.beansoft.scm.dao to set
	 */
	public void setDao(VendorDAO dao) {
		this.dao = dao;
	}
	
    
    /**
     * 执行添加用户到数据库的操作, 首先检查此用户是否已经存在.
     * @param user 用户对象
     */
    public boolean add(Vendor bean) {
        dao.save(bean);
        return true;
    }
    
     /**
     * 根据用户名在数据库中查找用户对象.
     * @param username 用户名
     * @return User 用户对象
     */
    public List<Vendor> findByName(String name) {
        List<Vendor> results = dao.findByName(name);
        
        return results;
    }
    
    public List<Vendor> findAllByNameInclude(String name) {
        List<Vendor> results = dao.findAllByNameInclude(name);
        
        return results;
    }    
    
    /**
     * 根据用户ID查找所有供应商对象.
     * @param id 用户ID
     * @return List<Vendor>
     */
    public List<Vendor> findAllByUserId(long id) {
        return dao.findAllByUserId(id);
    }
    
    /**
     * 根据审核状态查找所有供应商对象.
     * @param 
     * @return List<Vendor>
     */
    public List<Vendor> findAllByAudited(boolean audited) {
        return dao.findByAudited(audited);
    }
    
    /**
     * 根据ID在数据库中查找对象.
     * @param ID 编号
     * @return 找到的对象
     */
    public Vendor findById(long id) {
        return dao.findById(id);
    }
    
    /**
     * 获取所有用户列表.
     * 委托到 DAO 层完成.
     * @return 所有用户列表
     */
    public List<Vendor> getAll() {
        return getDao().findAll("Vendor");
    }
    
    
    /**
     * 更新对象信息.
     */
    public void update(Vendor bean) {
        dao.update(bean);
    }

	/**
	 * 获取给定用户的分成总额.
	 * @param userId
	 * @return
	 */    
    public double getDenductSum(long userId) {
    	return dao.getDenductSum(userId);
    }
    
}
