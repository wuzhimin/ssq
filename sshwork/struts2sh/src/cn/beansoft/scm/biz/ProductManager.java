/**
 * 
 */
package cn.beansoft.scm.biz;

import java.util.List;

import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.Product;

/**
 * 商品业务模块.
 * @author BeanSoft
 */

public class ProductManager {
	// DAO 实例
	private ProductDAO dao;

	/**
	 * @return the cn.beansoft.scm.dao
	 */
	public ProductDAO getDao() {
		return dao;
	}

	/**
	 * @param cn.beansoft.scm.dao the cn.beansoft.scm.dao to set
	 */
	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}
	
    
    /**
     * 执行添加用户到数据库的操作, 首先检查此用户是否已经存在.
     * @param user 用户对象
     */
    public boolean add(Product bean) {
        dao.save(bean);
        return true;
    }
    
     /**
     * 根据用户名在数据库中查找用户对象.
     * @param username 用户名
     * @return User 用户对象
     */
    public List<Product> findByName(String name) {
        List<Product> results = dao.findByName("Product", name);
        
        return results;
    }
    
    /**
     * 根据包含名字和是否有库存查找商品信息.
     * @param name - 商品名
     * @param available - 是否有库存
     * @return - 查询结果, 产品列表
     */
    public List<Product> findAllByNameIncludeAmount(String name, boolean available, int... pageParams) {
    	String hql = "from Product where name like '%" + name + "%' and audited = true";
    	if(available == true) {
    		hql += " and amount > 0";
    	}
    	
    	System.out.println("hql=" + hql);
    	
        List<Product> results = dao.findByHQL(hql, pageParams);
        
        return results;
    }
    /**
     * 计算包含名字和是否有库存的商品的记录数.
     * @param name - 商品名
     * @param available - 是否有库存
     * @return - 记录数
     */    
    public long countFindAllByNameIncludeAmount(String name, boolean available) {
    	String hql = "select count(id) from Product where name like '%" + name + "%' and audited = true";
    	if(available == true) {
    		hql += " and amount > 0";
    	}
    	
    	System.out.println("hql=" + hql);
    	
    	return dao.queryForCount(hql);
    }
    
    /**
     * 根据审核状态查找所有商品对象(带分页功能)
     * @return List<Product>
     * @param pageParams - 参数1: 当前页码, 参数2: 显示数据数
     */
    public List<Product> findAllByAudited(boolean audited, int... pageParams) {
    	if(pageParams.length == 0) {
    		return dao.findByAudited(audited);
    	} else {
    		return dao.pagedQuery("from Product where audited = " + audited, pageParams[0], pageParams[1]);
    	}
    }
    
    /**
     * 获得已审核的产品记录数.
     * @param audited
     * @return
     */
    public long getProductCountByAudited(boolean audited) {
    	return dao.queryForCount("select count(id) from Product where audited = " + audited);
    }
    
    /**
     * 根据ID在数据库中查找对象.
     * @param ID 编号
     * @return 找到的对象
     */
    public Product findById(long id) {
        return (Product) dao.findById(Product.class, id);
    }
    
    /**
     * 获取所有产品列表.
     * @return 所有产品列表
     */
    public List<Product> getAll() {
        return getDao().findAll("Product");
    }
    
    /**
     * 更新对象信息.
     */
    public void update(Product bean) {
        dao.update(bean);
    }

}