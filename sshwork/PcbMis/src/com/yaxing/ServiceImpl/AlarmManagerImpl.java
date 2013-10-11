package com.yaxing.ServiceImpl;

import java.util.List;

import com.yaxing.Dao.AlarmDao;
import com.yaxing.Service.AlarmManager;
import com.yaxing.model.Alarm;
import com.yaxing.utils.PageModel;

public class AlarmManagerImpl implements AlarmManager {
	private AlarmDao alarmDao;

	public void setAlarmDao(AlarmDao alarmDao) {
		this.alarmDao = alarmDao;
	}

	@Override
	public void add(Alarm alarm) {
		alarmDao.add(alarm);

	}

	@Override
	public void delete(Integer id) {
		alarmDao.delete(id);

	}

	@Override
	public Alarm getAlarm(Integer id) {
		
		return alarmDao.getAlarm(id);
	}

	@Override
	public List getAlarmList() {
		// TODO Auto-generated method stub
		return alarmDao.getAlarmList();
	}

	@Override
	public void update(Alarm alarm) {
		alarmDao.update(alarm);
	}

	@Override
	public PageModel findAllAlarm(int offset, int pagesize) {
		// TODO Auto-generated method stub
		return alarmDao.findAllAlarm(offset, pagesize);
	}

}
