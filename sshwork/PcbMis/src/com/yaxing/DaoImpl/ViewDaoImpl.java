package com.yaxing.DaoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yaxing.Dao.ViewDao;
import com.yaxing.model.View;

public class ViewDaoImpl extends HibernateDaoSupport implements ViewDao {

	@Override
	public List<View> getView(Long id) {
		// TODO Auto-generated method stub
		return (List<View>) getHibernateTemplate().find("from View where id.id="+id);
	}

	@Override
	public List<View> getViewList() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from View");
	}

}
