package cn.beansoft.scm.dao;

import java.util.List;

/**
 * 资源管理DAO.
 * 
 * @see cn.beansoft.scm.entity.Resource
 * @author BeanSoft
 */
public class ResourceDAO extends BaseDAO {

	/**
	 * 查找是否存在资源的权限限制
	 * 
	 * @param uri -
	 *            URI 地址
	 * @return
	 */
	public boolean hasUrlResources(String uri) {
		String queryString = "select count(model.id) from Resource as model where model.uri = ?";
		List results = getHibernateTemplate().find(queryString, uri);
		long count = 0;

		if (results != null && results.size() > 0) {
			count = (Long) results.get(0);
		}

		return count > 0;
	}

}