package com.wzm.server.service;

import java.io.Serializable;

import com.wzm.server.entity.BaseEntity;

public interface BaseService {
	
	/**
	 * 增加一个实体
	 * @param entity
	 * @return
	 */
	public Serializable add(BaseEntity entity);
	
	
	/**
	 * 修改一个实体
	 * @param entity
	 * @return
	 */
	public void update(BaseEntity entity);
	
	/**
	 * 删除一个实体
	 * @param entity
	 */
	public void delete(BaseEntity entity);
	
	
	/**
	 * 保存一个实体
	 * @param user
	 * @return
	 */
	public Serializable save(BaseEntity entity);
}
