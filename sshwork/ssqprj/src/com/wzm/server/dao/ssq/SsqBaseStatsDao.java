package com.wzm.server.dao.ssq;

import java.util.List;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.entity.ssq.SsqBaseStats;
import com.wzm.server.entity.ssq.SsqColRowStats;
import com.wzm.server.entity.ssq.SsqLastSameStats;
import com.wzm.server.entity.ssq.SsqPrimeStats;
import com.wzm.server.entity.ssq.SsqTailStats;

/**
 * 基本规律dao
 * @author Administrator
 *
 */
public interface SsqBaseStatsDao extends BaseDao {
	
	public List<SsqBaseStats> findSsqBaseStatsesByHql(String hql);
	
	public List<SsqBaseStats> findSsqBaseStatsesByHql(String hql, Object[] params);
	
	public List<SsqBaseStats> findSsqBaseStatsesByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	public SsqBaseStats findSsqBaseStatsesBySsqIndex(int ssqIndex);
	
	public List<SsqBaseStats> findSsqBaseStatsesBySumScope(String sumType, int sum1, int sum2);
	
	public List<SsqTailStats> findSsqTailStatsesByHql(String hql);
	
	public List<SsqTailStats> findSsqTailStatsesByHql(String hql, Object[] params);
	
	public List<SsqTailStats> findSsqTailStatsesByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	public SsqTailStats findSsqTailStatsesBySsqIndex(int ssqIndex);
	
	public List<SsqColRowStats> findSsqColRowStatsesByHql(String hql, Object[] params);
	
	public List<SsqColRowStats> findSsqColRowStatsesByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	public SsqColRowStats findSsqColRowStatsesBySsqIndex(int ssqIndex);
	
	/**
	 * 获取上期相同规律list
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<SsqLastSameStats> findSsqLastSameStatsesByHql(String hql, Object[] params);
	
	/**
	 * 获取上期相同规律list
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @return
	 */
	public List<SsqLastSameStats> findSsqLastSameStatsesByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	/**
	 * 获取上期相同规律
	 * @param ssqIndex
	 * @return
	 */
	public SsqLastSameStats findSsqLastSameStatsesBySsqIndex(int ssqIndex);
	
	/**
	 * 获取质数统计信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<SsqPrimeStats> findSsqPrimeStatsesByHql(String hql, Object[] params);
	
	public List<SsqPrimeStats> findSsqPrimeStatsesByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	public SsqPrimeStats findSsqPrimeStatsesBySsqIndex(int ssqIndex);
}
