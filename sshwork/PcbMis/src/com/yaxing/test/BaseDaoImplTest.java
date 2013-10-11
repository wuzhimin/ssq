package com.yaxing.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.yaxing.Dao.RoleDao;
import com.yaxing.Dao.UserDao;
import com.yaxing.DaoImpl.RoleDaoImpl;
import com.yaxing.DaoImpl.UserDaoImpl;

public class BaseDaoImplTest extends TestCase {
	@Test
	public void test() {
		UserDao userDao = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}
}
