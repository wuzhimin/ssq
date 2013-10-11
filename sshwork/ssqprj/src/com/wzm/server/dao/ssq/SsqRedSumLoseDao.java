package com.wzm.server.dao.ssq;

import java.util.List;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.entity.ssq.SsqRedSumLose;

public interface SsqRedSumLoseDao extends BaseDao {
	
	public List<SsqRedSumLose> findSsqRedSumLosesByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	public SsqRedSumLose findSsqRedSumLoseBySsqIndex(int ssqIndex);
	
}
