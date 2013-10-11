package com.wzm.server.dao.ssq;

import java.util.List;

import com.wzm.server.dao.base.BaseDaoImpl;
import com.wzm.server.entity.ssq.SsqRecord;

public class SsqRecordDaoImpl extends BaseDaoImpl implements SsqRecordDao {

	@Override
	public List<SsqRecord> findSsqRecordsByHql(String hql) {
		@SuppressWarnings("unchecked")
		List<SsqRecord> result = getHibernateTemplate().find(hql);
		return result;
	}
	
	@Override
	public List<SsqRecord> findSsqRecordsByHql(String hql, Object[] param) {
		@SuppressWarnings("unchecked")
		List<SsqRecord> result = getHibernateTemplate().find(hql, param);
		return result;
	}

	@Override
	public List<SsqRecord> findSsqRecordsByIndexScope(int fromSsqIndex,
			int toSsqIndex) {
		String hql = " from SsqRecord s where s.ssqIndex >= ? and s.ssqIndex <= ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqRecord> result =(List<SsqRecord>)this.getHibernateTemplate().find(hql,  new Object[] { fromSsqIndex, toSsqIndex });
		
		return result;
	}

	@Override
	public SsqRecord findSsqRecordBySsqIndex(int ssqIndex) {
		String hql = " from SsqRecord s where s.ssqIndex = ? ";
		
		@SuppressWarnings("unchecked")
		List<SsqRecord> result = (List<SsqRecord>) this.getHibernateTemplate()
				.find(hql, new Object[] { ssqIndex });
		
		if(result.size()==0) {
			return null;
		}
		
		return result.get(0);
	}

}
