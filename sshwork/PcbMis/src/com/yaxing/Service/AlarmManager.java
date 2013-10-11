package com.yaxing.Service;

import java.util.List;

import com.yaxing.model.Alarm;
import com.yaxing.utils.PageModel;

public interface AlarmManager {
	public void add(Alarm alarm);

	public Alarm getAlarm(Integer id);

	public List getAlarmList();

	public void update(Alarm alarm);

	public void delete(Integer id);
	
	public PageModel findAllAlarm(int offset, int pagesize);

}
