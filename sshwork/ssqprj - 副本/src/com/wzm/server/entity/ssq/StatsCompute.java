package com.wzm.server.entity.ssq;
/**
 * 统计信息计算接口
 * @author wzm
 *
 */
public interface StatsCompute {
	
	/**
	 * 根据开奖记录，构造统计信息接口
	 * @param record
	 */
	public void buildStats(SsqRecord aRecord);
	
}
