package cn.beansoft.scm.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.beansoft.scm.entity.User;
import cn.beansoft.scm.entity.Vendor;

@Transactional
public class VendorDAO extends BaseDAO {
	
	/**
	 * 获取给定用户的分成总额.
	 * @param userId
	 * @return
	 */
	public double getDenductSum(long userId) {
		String hql = "select sum(o.deduct) from OrderItem o where o.product.scmUser.id = " + userId;
        List results = getHibernateTemplate().find(hql);
        
        if(results != null && results.size() > 0) {
        	return (Double)results.get(0);
        }
        
        return 0;
	}

    /**
     * 根据用户ID查找所有供应商对象.
     * @param id 用户ID
     * @return List<Vendor>
     */
    public List<Vendor> findAllByUserId(long id) {
    	String hql = "from Vendor v where v.user.id = " + id;
        List<Vendor> results = getHibernateTemplate().find(hql);
        
        return results;
    }
    
    /**
     * 根据名字模糊查找供应商列表.
     * @param name
     * @return
     */
	public List<Vendor> findAllByNameInclude(String name) {

		try {
			String queryString = "from Vendor as model where model.name like '%" + name + "%'";
			System.out.println(queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	


	public Vendor findById(java.lang.Long id) {
		try {
			Vendor instance = (Vendor) getHibernateTemplate().get("cn.beansoft.scm.entity.Vendor",
					id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Vendor> findByName(String name) {
		String hql = "from Vendor where name = ?";
        List<Vendor> result = super.findByHQL(hql, name);
        return result;
	}

	public List<Vendor> findByAudited(boolean audited) {
		String hql = "from Vendor where audited = ?";
        List<Vendor> result = super.findByHQL(hql, audited);
        return result;
	}


}