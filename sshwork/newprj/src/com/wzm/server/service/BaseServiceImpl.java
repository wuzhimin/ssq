package com.wzm.server.service;

import java.io.Serializable;

import com.wzm.server.dao.BaseDao;
import com.wzm.server.entity.BaseEntity;

/**
 * BaseService实现类
 * @author wzm
 *
 */
public class BaseServiceImpl implements BaseService {
	
	private BaseDao baseDao;
	
	public BaseServiceImpl() {
		
	}

	@Override
	public Serializable add(BaseEntity entity) {
		if(entity!=null) {
			return baseDao.add(entity);
		}
		return null;
	}

	@Override
	public void update(BaseEntity entity) {
		if(entity!=null) {
			baseDao.update(entity);
		}
	}

	@Override
	public void delete(BaseEntity entity) {
		if(entity!=null) {
			baseDao.delete(entity);
		}
	}

	@Override
	public Serializable save(BaseEntity entity) {
		if(entity!=null) {
			return baseDao.save(entity);
		}
		return null;
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
