package com.wzm.server.dao.ssq;

import java.util.List;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.entity.ssq.SsqRecord;

public interface SsqRecordDao extends BaseDao {
	
	public List<SsqRecord> findSsqRecordsByHql(String hql);
	
	public List<SsqRecord> findSsqRecordsByIndexScope(int fromSsqIndex, int toSsqIndex);
	
	public SsqRecord findSsqRecordBySsqIndex(int ssqIndex);
	
}
