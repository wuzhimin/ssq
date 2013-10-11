package com.wzm.server.dao.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wzm.server.entity.base.BaseEntity;

/**
 * BaseDao实现类,实现最基本的实体增、删、改、查操作
 * 
 * @author wzm
 * @date 2012.4.24
 */
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	
	private final static int BATCH_COUNT = 500; 

	public BaseDaoImpl() {
		super();
	}

	@Override
	/*
	 * 增加一个新的实体
	 * @see com.wzm.server.dao.BaseDao#add(com.wzm.server.entity.BaseEntity)
	 */
	public Serializable add(BaseEntity obj) {
		
		fillEntity(obj);
		
		return this.getHibernateTemplate().save(obj);
	}

	/**
	 * 补充一些默认属性值
	 * @param obj
	 */
	private void fillEntity(BaseEntity obj) {
		if(obj.getCreateTime()==null) {
			obj.setCreateTime(new Date(System.currentTimeMillis()));
		}
		
		if(obj.getLastUpdateTime()==null) {
			obj.setLastUpdateTime(new Date(System.currentTimeMillis()));
		}
	}

	@Override
	/*
	 * 保存一个实体（新增或修改）
	 * @see com.wzm.server.dao.BaseDao#save(com.wzm.server.entity.BaseEntity)
	 */
	public Serializable save(BaseEntity obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
		return obj.getId();
	}

	@Override
	/*
	 * 保存一个修改的实体
	 * @see com.wzm.server.dao.BaseDao#update(com.wzm.server.entity.BaseEntity)
	 */
	public void update(BaseEntity obj) {
		this.getHibernateTemplate().update(obj);
	}

	@Override
	/*
	 * 删除一个实体
	 * @see com.wzm.server.dao.BaseDao#delete(com.wzm.server.entity.BaseEntity)
	 */
	public void delete(BaseEntity obj) {
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	/*
	 * 获取一个实体
	 * @see com.wzm.server.dao.BaseDao#find(java.io.Serializable, java.lang.Class)
	 */
	public BaseEntity find(Serializable id,Class<?> cls) {
		BaseEntity entity = (BaseEntity)this.getHibernateTemplate().get(cls, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseEntity> find(String hql, Object[] params) {
		return (List<BaseEntity>)this.getHibernateTemplate().find(hql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseEntity> find(String hql, Map<String, ?> params) {
		int length = params.keySet().size();
		String[] keys = new String[length];
		Object[] values = new Object[length];
		int i=0;
		for(String key:params.keySet()) {
			keys[i] = key;
			values[i] = params.get(key);
		}
		return (List<BaseEntity>)this.getHibernateTemplate().findByNamedParam(hql, keys, values);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseEntity> find(String hql) {
		return (List<BaseEntity>)this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<BaseEntity> batchAddBaseEntityes(final List<BaseEntity> records) {
		
		@SuppressWarnings("unchecked")
		List<BaseEntity> result = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				for (int i = 0; i < records.size(); i++) {
                    session.save(records.get(i));
                    if (i % BATCH_COUNT == 0) {
                        session.flush();
                        session.clear();
                    }
                }

				return records;
			}
		});
		
		return result;
	}

	@Override
	public void deleteByHql(String hql) {
		this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	@Override
	public void deleteByHql(String hql, Object[] params) {
		this.getHibernateTemplate().bulkUpdate(hql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getFunctionIntValue(String hql) {
		Object obj = this.getHibernateTemplate().find(hql);
		if(obj==null) {
			return 0;
		}
		return ((List<Integer>)obj).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getFunctionIntValue(String hql, Object[] params) {
		Object obj = this.getHibernateTemplate().find(hql,params);
		if((obj==null) || (((List)obj).size()==0) ||  ( ((List)obj).size()==1 && ((List)obj).get(0)==null ) ) {
			return 0;
		}
		return ((List<Integer>)obj).get(0);
	}
		
	@SuppressWarnings("unchecked")
	public long getFunctionLongValue(String hql) {
		Object obj = this.getHibernateTemplate().find(hql);
		if(obj==null) {
			return 0;
		}
		return ((List<Long>)obj).get(0);
	}
	
	/**
	 * 获取聚合函数的值，hql必须是一个查询聚合函数的hql
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long getFunctionLongValue(String hql, Object[] params) {

		Object obj = this.getHibernateTemplate().find(hql,params);
		if((obj==null) || (((List)obj).size()==0) ||  ( ((List)obj).size()==1 && ((List)obj).get(0)==null ) ) {
			return 0;
		}
		return ((List<Long>)obj).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Float getFunctionFloatValue(String hql) {
		Object obj = this.getHibernateTemplate().find(hql);
		if(obj==null) {
			return 0f;
		}
		return ((List<Float>)obj).get(0);
	}

}
