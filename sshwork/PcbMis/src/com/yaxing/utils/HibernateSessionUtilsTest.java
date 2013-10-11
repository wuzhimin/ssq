package com.yaxing.utils;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateSessionUtilsTest {

	@Test
	public void testOpenSession() {
		Session session = HibernateSessionUtils.openSession();
		System.out.println(session!=null);
		
		Session session1 = HibernateSessionUtils.getCurrentSession();
		System.out.println(session==session1);
		
		HibernateSessionUtils.closeAndRemoveCurrentSession();
		
		Session session2 = HibernateSessionUtils.getCurrentSession();
		System.out.println(session2==null);
	}

}
