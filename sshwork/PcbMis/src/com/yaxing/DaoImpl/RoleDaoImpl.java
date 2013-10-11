package com.yaxing.DaoImpl;

import com.yaxing.Dao.RoleDao;
import com.yaxing.base.BaseDaoImpl;
import com.yaxing.model.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
	public RoleDaoImpl() {
		clazz = Role.class;
	}
}
