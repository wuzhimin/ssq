package com.yaxing.base;

import java.util.List;

public interface BaseDao<T> {
	/**
	 * 保存
	 * 
	 * @param object
	 * */
	void save(T object);
	/**
	 * 删除
	 * @param id
	 * */
	void delete(Long id);
	/**
	 * 更新
	 * @param object
	 * */
	void update(T object);
	/**
	 * 获取实体
	 * @param id
	 * @return 
	 * */
	T getById(Long id);
	/**
	 * 获取实体
	 * @param idList
	 * @return 
	 * */
	List<T> getByIdList(Long idList[]);
	/**
	 * 查询所有
	 * @return 
	 * */
	List<T> findAll();

}
