package com.wzm.server.entity.ssq;

import java.util.List;

/**
 * 多期统计信息计算接口
 * @author wzm
 *
 */
public interface MulStatsCompute {
	
	/**
	 * 根据对应的统计信息记录，构造多期统计信息接口
	 * @param record
	 */
	public void buildStats(List<StatsCompute> records);
	
}
