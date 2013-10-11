package com.yaxing.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	protected Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) pt.getActualTypeArguments()[0];
		System.out.println("--->"+clazz);
	}

	public void save(T object) {
		getSession().save(object);
	}

	public void delete(Long id) {
		Object object = getSession().get(clazz, id);
		getSession().delete(object);
	}

	public void update(T object) {
		getSession().update(object);
	}

	public T getById(Long id) {

		return null;
	}

	public List<T> getByIdList(Long[] idList) {
		if (idList == null || idList.length == 0) {
			return Collections.EMPTY_LIST;
		}
		return getSession().createQuery(//
				"FROM " + clazz.getName() + "WHERE id in(:idList)")//
				.setParameterList("idList", idList)//
				.list();
	}

	public List<T> findAll() {

		return getSession().createQuery("FROM " + clazz.getName()).list();// getSimpleName()全限定名
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 * */
	@SuppressWarnings("unused")
	private Session getSession() {
		throw new UnsupportedOperationException();
	}

}
