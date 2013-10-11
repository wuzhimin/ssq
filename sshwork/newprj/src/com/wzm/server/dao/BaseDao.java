package com.wzm.server.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wzm.server.entity.BaseEntity;

public interface BaseDao {
	
	/**
	 * 新增实体
	 * @param obj
	 * @return Serializable
	 */
	public Serializable add(BaseEntity obj);
	
	/**
	 * 保存一个实体（新增或修改）
	 * @param obj
	 * @return
	 */
	public Serializable save(BaseEntity obj);
	
	/**
	 * 修改一个实体
	 * @param obj
	 */
	public void update(BaseEntity obj);
	
	/**
	 * 删除一个实体
	 * @param obj
	 */
	public void delete(BaseEntity obj);
	
	/**
	 * 根据id，查找实体
	 * @param id
	 * @return
	 */
	public BaseEntity find(Serializable id,Class<?> cls);
	
	
	public List<BaseEntity> find(String hql, Object[] params);
	
	public List<BaseEntity> find(String hql, Map<String, ?> params);
}
