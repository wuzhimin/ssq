package com.wzm.server.dao.ssq;

import java.util.List;

import com.wzm.server.dao.base.BaseDaoImpl;
import com.wzm.server.entity.ssq.SsqBaseStats;

public class SsqBaseStatsDaoImpl extends BaseDaoImpl implements SsqBaseStatsDao {

	@Override
	public List<SsqBaseStats> findSsqBaseStatsesByHql(String hql) {
		@SuppressWarnings("unchecked")
		List<SsqBaseStats> result = getHibernateTemplate().find(hql);
		return result;
	}

	@Override
	public List<SsqBaseStats> findSsqBaseStatsesByIndexScope(int fromSsqIndex,
			int toSsqIndex) {
		String hql = " from SsqBaseStats s where s.ssqIndex >= ? and s.ssqIndex <= ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqBaseStats> result =(List<SsqBaseStats>)this.getHibernateTemplate().find(hql,  new Object[] { fromSsqIndex, toSsqIndex });
		
		return result;
	}

	@Override
	public SsqBaseStats findSsqBaseStatsesBySsqIndex(int ssqIndex) {
		String hql = " from SsqBaseStats s where s.ssqIndex = ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqBaseStats> result =(List<SsqBaseStats>)this.getHibernateTemplate().find(hql,  new Object[] { ssqIndex});
		if(result != null && result.size()>0) {
			return result.get(0);
		}
		
		return null;
	}

	@Override
	public List<SsqBaseStats> findSsqBaseStatsesBySumScope(String sumType, int sum1, int sum2) {
		String hql = " from SsqBaseStats s where s."+sumType+" >= ? and s."+sumType+" <= ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqBaseStats> result =(List<SsqBaseStats>)this.getHibernateTemplate().find(hql,  new Object[] { sum1, sum2});
		
		return result;
	}

	

	

}
