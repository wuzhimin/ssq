package com.yaxing.utils;

import java.util.Hashtable;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionUtils {
	private static SessionFactory sessionFactory ;
	private static Map<Thread, Session> sessionMap = new Hashtable<Thread, Session>();
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	/**
	 * 打开一个新的Session，并自动与当前线程关联
	 * 
	 * */
	public static Session openSession(){
		Session session = sessionFactory.openSession();
		sessionMap.put(Thread.currentThread(), session);
		return session;
	}
	/**
	 * 获取当前线程关联的session，如果没有，返回null
	 * 
	 * */
	public static Session getCurrentSession(){
		return sessionMap.get(Thread.currentThread());
	}
	/**
	 * 关闭当前线程对应的session,并自动移除与当前线程的关联
	 * 
	 * */
	public static void closeAndRemoveCurrentSession(){
		Session session = sessionMap.get(Thread.currentThread());
		session.close();
		sessionMap.remove(Thread.currentThread());
	}
}
