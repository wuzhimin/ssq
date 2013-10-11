package com.yaxing.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yaxing.Dao.AlarmDao;
import com.yaxing.model.Alarm;
import com.yaxing.utils.PageModel;

public class AlarmDaoImpl extends HibernateDaoSupport implements AlarmDao {

	@Override
	public void add(Alarm alarm) {
		getHibernateTemplate().save(alarm);

	}

	@Override
	public void delete(Integer id) {

		getHibernateTemplate().delete(
				getHibernateTemplate().get(Alarm.class, id));
System.out.print("123");
	}

	@Override
	public Alarm getAlarm(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Alarm.class, id);
	}

	@Override
	public List getAlarmList() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Alarm");
	}

	@Override
	public void update(Alarm alarm) {
		getHibernateTemplate().update(alarm);

	}

	@Override
	public PageModel findAllAlarm(int offset, int pagesize) {
	
		 //得到总记录数   
        String queryCountHql = "select count(*) from Alarm";   
           
        Query query = getSession().createQuery(queryCountHql);   
        int total = ((Long)query.uniqueResult()).intValue();   
           
        List datas = getSession().createQuery("from Alarm")   
                    .setFirstResult(offset)   
                    .setMaxResults(pagesize)   
                    .list();   
        //得到结果集   
        PageModel pm = new PageModel();   
        pm.setTotal(total);   
        pm.setDatas(datas);   
        return pm;   

	}

}
