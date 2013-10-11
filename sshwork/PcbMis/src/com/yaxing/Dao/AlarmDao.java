package com.yaxing.Dao;

import java.util.List;

import com.yaxing.model.Alarm;
import com.yaxing.utils.PageModel;

public interface AlarmDao {
	public void add(Alarm alarm);

	public Alarm getAlarm(Integer id);

	public List getAlarmList();

	public void update(Alarm alarm);

	public void delete(Integer id);
	/**  
     *   
     * @param offset  从第几条记录开始查询  
     * @param pagesize  每页显示多少条记录  
     * @return  
     */  
    public PageModel findAllAlarm(int offset, int pagesize); 


}
