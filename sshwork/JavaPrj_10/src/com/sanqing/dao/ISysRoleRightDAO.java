package com.sanqing.dao;

import java.util.List;

import com.sanqing.po.SysRoleRight;
import com.sanqing.util.PageResult;
import com.sanqing.util.RightList;

public interface ISysRoleRightDAO {

	public List<SysRoleRight> findRightExist(Long roleId, String rightCode);

	// 根据用户角色查询用户权限
	public abstract String[] findRight(Long roleId);


	public RightList findRightByRoleId(Long roleId);

	public abstract void save(SysRoleRight transientInstance);

	public abstract void delete(SysRoleRight persistentInstance);

	public abstract SysRoleRight findById(java.lang.Integer id);

	public abstract List findByExample(SysRoleRight instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract SysRoleRight merge(SysRoleRight detachedInstance);

	public abstract void attachDirty(SysRoleRight instance);

	public abstract void attachClean(SysRoleRight instance);

}