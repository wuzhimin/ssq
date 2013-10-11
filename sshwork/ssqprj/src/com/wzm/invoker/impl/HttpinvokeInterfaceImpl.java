package com.wzm.invoker.impl;

import java.util.List;

import com.wzm.invoker.HttpinvokeInterface;
import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.ssq.SsqRecord;

public class HttpinvokeInterfaceImpl implements HttpinvokeInterface {
	
	private SsqRecordDao ssqRecordDao;

	@Override
	public int getHello() {
		List<SsqRecord> list = ssqRecordDao.findSsqRecordsByIndexScope(2011001, 2011123);
		return list.size();
	}
	
	public HttpinvokeInterfaceImpl() {
		
	}

	public SsqRecordDao getSsqRecordDao() {
		return ssqRecordDao;
	}

	public void setSsqRecordDao(SsqRecordDao ssqRecordDao) {
		this.ssqRecordDao = ssqRecordDao;
	}

}
