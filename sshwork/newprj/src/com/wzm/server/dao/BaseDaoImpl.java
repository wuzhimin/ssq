package com.wzm.server.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wzm.server.entity.BaseEntity;

/**
 * BaseDao实现类,实现最基本的实体增、删、改、查操作
 * 
 * @author wzm
 * @date 2012.4.24
 */
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

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

}
