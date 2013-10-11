package cn.beansoft.scm.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.beansoft.scm.entity.Product;


@Transactional
public class ProductDAO extends BaseDAO {
	

	
	/**
	 * 执行 HQL 并返回查询结果. 
	 * @param hql
	 * @return
	 */
	public List<Product> findByHQL(String hql) {
		return super.findByHQL(hql);
	}

	public List<Product> findByAudited(boolean audited) {
		return super.findByProperty("Product", "audited", audited);
	}
	
//	public List findAllByAudited() {
//		log.debug("finding all Product instances");
//		try {
//			String queryString = "from Product where audited =" + audited;
//			return getHibernateTemplate().find(queryString);
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}
	

}