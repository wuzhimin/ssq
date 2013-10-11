package com.wzm.server.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wzm.server.entity.base.BaseEntity;

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
	
	public List<BaseEntity> find(String hql);
	
	
	/**
	 * 批量增加实体
	 * @param records
	 */
	public List<BaseEntity> batchAddBaseEntityes(final List<BaseEntity> records);
	
	/**
	 * 根据hql删除对象
	 * @param hql
	 */
	public void deleteByHql(final String hql);
	
	/**
	 * 根据hql删除对象,带参数
	 * @param hql
	 * @param params
	 */
	public void deleteByHql(String hql, Object[] params);
	
	/**
	 * 获取聚合函数的值，hql必须是一个查询聚合函数的hql
	 * @param hql
	 * @return
	 */
	public int getFunctionIntValue(String hql);
	
	/**
	 * 获取聚合函数的值，hql必须是一个查询聚合函数的hql
	 * @param hql
	 * @param params
	 * @return
	 */
	public int getFunctionIntValue(String hql, Object[] params);
	
	public long getFunctionLongValue(String hql);
	
	/**
	 * 获取聚合函数的值，hql必须是一个查询聚合函数的hql
	 * @param hql
	 * @param params
	 * @return
	 */
	public long getFunctionLongValue(String hql, Object[] params);

	public Float getFunctionFloatValue(String hql);
	
	
	
}
