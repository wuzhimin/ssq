package com.wzm.server.dao.ssq;

import java.util.List;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.entity.ssq.SsqBaseStats;
import com.wzm.server.entity.ssq.SsqRecord;

/**
 * 基本规律dao
 * @author Administrator
 *
 */
public interface SsqBaseStatsDao extends BaseDao {
	
	public List<SsqBaseStats> findSsqBaseStatsesByHql(String hql);
	
	public List<SsqBaseStats> findSsqBaseStatsesByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	public SsqBaseStats findSsqBaseStatsesBySsqIndex(int ssqIndex);
	
	public List<SsqBaseStats> findSsqBaseStatsesBySumScope(String sumType, int sum1, int sum2);
	
}
