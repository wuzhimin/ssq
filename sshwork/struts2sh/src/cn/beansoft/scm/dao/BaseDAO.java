/**
 * 
 */
package cn.beansoft.scm.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * 提供更新对象, 执行 HQL 语句, 分页等通用功能的DAO类.
 * 
 * @author BeanSoft
 * 
 */
@org.springframework.transaction.annotation.Transactional
public class BaseDAO extends HibernateDaoSupport {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void save(Object entity) {
		getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 根据ID和实体类型查找实体.
	 * 
	 * @param t -
	 *            实体类型
	 * @param id -
	 *            序列化ID
	 * @return - 实体对象
	 */
	public Object findById(Class type		, java.io.Serializable id) {
		return getHibernateTemplate().get(type, id);
	}

	/**
	 * 根据ID和实体名查找实体.
	 * 
	 * @param entityName -
	 *            实体名
	 * @param id -
	 *            序列化ID
	 * @return - 实体对象
	 */
	public Object findById(String entityName, java.io.Serializable id) {
		return getHibernateTemplate().get(entityName, id);
	}

	/**
	 * 删除实体类.
	 * 
	 * @param bean
	 */
	public void delete(Object bean) {
		getHibernateTemplate().delete(bean);
	}

	/**
	 * 根据ID删除实体类.
	 * 
	 * @param entityName
	 *            实体名
	 * @param id
	 *            编号
	 * @return 删除的数据记录数
	 */
	public Integer deleteById(String entityName, final String id) {
		final String hql = "delete " + entityName + " where id = " + id;

		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);

						return query.executeUpdate();

					}
				});
	}

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 列出所有数据.
	 * 
	 * @param entityName - 实体名
	 * @return
	 */
	public List findAll(String entityName) {
		return findByHQL("from " + entityName);
	}


	/**
	 * 根据名字查找.
	 * 
	 * @param entityName
	 * @param name
	 * @return
	 */
	public List findByName(String entityName, String name) {
		return findByProperty(entityName, "name", name);
	}

	/**
	 * 根据属性名和属性值查找.
	 * 
	 * @param entityName
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByProperty(String entityName, String propertyName,
			Object value) {
		String hql = "from " + entityName + " where " + propertyName + " = ?";
		return findByHQL(hql, value);
	}

	/**
	 * 将查询COUNT结果作为长整数来显示. HQL = select count(id) from Entity
	 * 
	 * @param hql 查询语句
	 * @param args 参数列表
	 * @return 整型数字
	 */
	public Integer queryForCount( final String hql, final Object... args) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			 public Object doInHibernate(Session session)
			 throws HibernateException, SQLException {
			 Query query = session.createQuery(hql);
			 
			 for(int i =0; i < args.length; i++) {
				 query.setParameter(i, args[i]);
			 }
			 

			 List list = query.list();
			 return list;
			 }
			});
		
		// Hibernate做count计算返回一般都是对象
		
		return Integer.parseInt(list.get(0) + "");		
	}

	/**
	 * 将查询SUM结果作为双精度数来显示. HQL = select sum(value) from Entity
	 * 
	 * @param queryString
	 *            查询语句
	 * @return double
	 */
	public double queryForSum(String queryString) {
		List results = findByHQL(queryString);
		long count = 0;

		if (results != null && results.size() > 0) {
			if (results.get(0) == null) {
				return 0;
			}

			return (Double) results.get(0);
		}

		return 0;
	}

	/**
	 * 根据 HQL 查询对象信息, 带0个或多个参数, 如: findByHQL(hql); findByHQL(hql, value);
	 * findByHQL(hql, values);
	 * 
	 * @param hql -
	 *            Hibernate 查询语言
	 * @param values -
	 *            可变参数
	 * @return 实体列表
	 */
	public List findByHQL(String hql, Object... values) {
		System.out.println("hql=" + hql);
		return getHibernateTemplate().find(hql, values);
	}

	/**
	 * 使用hql 语句进行分页查询操作
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param values
	 *            如果hql有多个个参数需要传入，values就是传入的参数数组
	 * @param offset
	 *            第一条记录索引
	 * @param pageSize
	 *            每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List pagedQuery(final String hql, int currentPage,
			final int pageSize, final Object... values) {
		if (currentPage == 0) {
			currentPage = 1;
		}

		final int curPage = currentPage;

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);

				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}

				List result = query.setFirstResult((curPage - 1) * pageSize)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

}
