package com.laodong.pub.pubinf.control;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

/**
 * 每隔3分钟执行一次的任务
 * 
 * 该任务引擎有２个属性：
 * １、第一次执行时间　　
 * ２、执行周期
 * @author lhh
 * 这里仍然存在问题，当添加、删除任务时，会影响run方法中的Iterator运行
 */
public class StatTashfor3fz extends TimerTask {
	private static HashMap objMap = new HashMap();

	private static HashMap taskMap = new HashMap();
    /**添加自动执行任务*/
	public synchronized static void addTask(String key, DingshiInfo dsinfo, Dingshi ds) {
		objMap.put(key, dsinfo);
		taskMap.put(key, ds);
	}
	/**删除自动执行任务*/
	public synchronized static void removeTask(String key) {
		objMap.remove(key);
		taskMap.remove(key);
	}

	/**
	 * 本DaemonTask执行的实际任务。
	 */
	public void run() {
		try {
			/**
			 * 2006/12/17 添加此同步关健字，主要避免在迭代过程中objMap结构发生变化
			 */
			synchronized(this){
			Set entries = objMap.entrySet();
			Iterator iter = entries.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String taskKey = (String) entry.getKey();
				DingshiInfo info = (DingshiInfo) entry.getValue();
				Dingshi ds = (Dingshi) taskMap.get(taskKey);
				ds.autolaodong(info);
			}
			}
		} catch (Exception e) {
			System.out.println("StatTask.run()发生异常:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 任务的执行周期。
	 * 
	 * @return long
	 */
	public long getPeriod() {
		// 本任务每天执行一次。
		// long PERIOD = 1000 * 60 * 60 * 24;

		// 每3分钟执行一次
		long PERIOD = 1000 * 60 * 3;

		return PERIOD;
	}

	/**
	 * 第一次执行时间。
	 * 
	 * @return Date
	 */
	public Date getFirstTime() {
		// 本任务的第一次执行时间每天00:00:00。
		int hour = 0, minute = 0, second = 0;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.HOUR_OF_DAY, hour);
		calendar.set(GregorianCalendar.MINUTE, minute);
		calendar.set(GregorianCalendar.SECOND, second);
		return new Date(calendar.getTimeInMillis());
	}

}
