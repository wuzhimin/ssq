package com.wzm.server.dao.ssq;

import java.util.List;

import com.wzm.server.dao.base.BaseDaoImpl;
import com.wzm.server.entity.ssq.SsqBaseStats;
import com.wzm.server.entity.ssq.SsqColRowStats;
import com.wzm.server.entity.ssq.SsqLastSameStats;
import com.wzm.server.entity.ssq.SsqPrimeStats;
import com.wzm.server.entity.ssq.SsqTailStats;

public class SsqBaseStatsDaoImpl extends BaseDaoImpl implements SsqBaseStatsDao {

	@Override
	public List<SsqBaseStats> findSsqBaseStatsesByHql(String hql) {
		@SuppressWarnings("unchecked")
		List<SsqBaseStats> result = getHibernateTemplate().find(hql);
		return result;
	}
	
	@Override
	public List<SsqBaseStats> findSsqBaseStatsesByHql(String hql, Object[] params) {
		@SuppressWarnings("unchecked")
		List<SsqBaseStats> result = getHibernateTemplate().find(hql, params);
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

	@Override
	public List<SsqTailStats> findSsqTailStatsesByHql(String hql) {
		@SuppressWarnings("unchecked")
		List<SsqTailStats> result =(List<SsqTailStats>)this.getHibernateTemplate().find(hql);
		
		return result;
	}

	@Override
	public List<SsqTailStats> findSsqTailStatsesByHql(String hql, Object[] params) {
		@SuppressWarnings("unchecked")
		List<SsqTailStats> result =(List<SsqTailStats>)this.getHibernateTemplate().find(hql, params);
		
		return result;
	}

	@Override
	public List<SsqTailStats> findSsqTailStatsesByIndexScope(int fromSsqIndex,
			int toSsqIndex) {
		String hql = " from SsqTailStats s where s.ssqIndex >= ? and s.ssqIndex <= ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqTailStats> result =(List<SsqTailStats>)this.getHibernateTemplate().find(hql,  new Object[] { fromSsqIndex, toSsqIndex });
		
		return result;
	}

	@Override
	public SsqTailStats findSsqTailStatsesBySsqIndex(int ssqIndex) {
		String hql = " from SsqTailStats s where s.ssqIndex = ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqTailStats> result =(List<SsqTailStats>)this.getHibernateTemplate().find(hql,  new Object[] { ssqIndex});
		if(result != null && result.size()>0) {
			return result.get(0);
		}
		
		return null;
	}

	@Override
	public List<SsqColRowStats> findSsqColRowStatsesByHql(String hql,
			Object[] params) {
		@SuppressWarnings("unchecked")
		List<SsqColRowStats> result =(List<SsqColRowStats>)this.getHibernateTemplate().find(hql, params);
		
		return result;
	}

	@Override
	public List<SsqColRowStats> findSsqColRowStatsesByIndexScope(
			int fromSsqIndex, int toSsqIndex) {
		String hql = " from SsqColRowStats s where s.ssqIndex >= ? and s.ssqIndex <= ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqColRowStats> result =(List<SsqColRowStats>)this.getHibernateTemplate().find(hql,  new Object[] { fromSsqIndex, toSsqIndex });
		
		return result;
	}

	@Override
	public SsqColRowStats findSsqColRowStatsesBySsqIndex(int ssqIndex) {
		String hql = " from SsqColRowStats s where s.ssqIndex = ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqColRowStats> result =(List<SsqColRowStats>)this.getHibernateTemplate().find(hql,  new Object[] { ssqIndex});
		if(result != null && result.size()>0) {
			return result.get(0);
		}
		
		return null;
	}

	@Override
	public List<SsqPrimeStats> findSsqPrimeStatsesByHql(String hql,
			Object[] params) {
		@SuppressWarnings("unchecked")
		List<SsqPrimeStats> result =(List<SsqPrimeStats>)this.getHibernateTemplate().find(hql, params);
		
		return result;
	}

	@Override
	public List<SsqPrimeStats> findSsqPrimeStatsesByIndexScope(
			int fromSsqIndex, int toSsqIndex) {
		String hql = " from SsqPrimeStats s where s.ssqIndex >= ? and s.ssqIndex <= ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqPrimeStats> result =(List<SsqPrimeStats>)this.getHibernateTemplate().find(hql,  new Object[] { fromSsqIndex, toSsqIndex });
		
		return result;
	}

	@Override
	public SsqPrimeStats findSsqPrimeStatsesBySsqIndex(int ssqIndex) {
		String hql = " from SsqPrimeStats s where s.ssqIndex = ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqPrimeStats> result =(List<SsqPrimeStats>)this.getHibernateTemplate().find(hql,  new Object[] {ssqIndex});
		if(result != null && result.size()>0) {
			return result.get(0);
		}
		
		return null;
	}

	@Override
	public List<SsqLastSameStats> findSsqLastSameStatsesByHql(String hql,
			Object[] params) {
		
		@SuppressWarnings("unchecked")
		List<SsqLastSameStats> result =(List<SsqLastSameStats>)this.getHibernateTemplate().find(hql, params);
				
		return result;
	}

	@Override
	public List<SsqLastSameStats> findSsqLastSameStatsesByIndexScope(
			int fromSsqIndex, int toSsqIndex) {
		String hql = " from SsqLastSameStatsSsqLastSameStats s where s.ssqIndex >= ? and s.ssqIndex <= ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqLastSameStats> result =(List<SsqLastSameStats>)this.getHibernateTemplate().find(hql,  new Object[] { fromSsqIndex, toSsqIndex });
		
		return result;
	}

	@Override
	public SsqLastSameStats findSsqLastSameStatsesBySsqIndex(int ssqIndex) {
		String hql = " from SsqLastSameStats s where s.ssqIndex = ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqLastSameStats> result =(List<SsqLastSameStats>)this.getHibernateTemplate().find(hql,  new Object[] {ssqIndex});
		if(result != null && result.size()>0) {
			return result.get(0);
		}
		
		return null;
	}

}
