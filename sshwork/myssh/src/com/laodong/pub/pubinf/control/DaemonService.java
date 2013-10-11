package com.laodong.pub.pubinf.control;

import java.util.Timer;

public class DaemonService {
    private static DaemonService service;

    /**
     * 使用Singleton模式，保持一个实例，通过修改这个实例，达到通知修改的目的。
     * @return DaemonService DaemonService实例。
     */
    public static DaemonService getInstance()
    {
        if (service == null)
        {
            service = new DaemonService();
        }
        return service;

    }

    /**
     * 私有构造方法，只能由getInstance()方法调用，是启动定时器的东东。
     */
    private DaemonService()
    {
        run();
    }

    /**
     * 设置Timer和Task。
     * 把第一次执行时间和执行周期不同的任务引擎加入到此方法
     */
    private void run()
    {
    	
        Timer timer = new Timer();
        
        //每天执行一次的任务
        StatTaskforDay statTask = new StatTaskforDay();
        timer.scheduleAtFixedRate(statTask, statTask.getFirstTime(), statTask.getPeriod());
        
        //每隔3分钟执行一次的任务
        StatTashfor3fz stat3 = new StatTashfor3fz();
        timer.scheduleAtFixedRate(stat3, stat3.getFirstTime(), stat3.getPeriod());
        
//      每隔15分钟执行一次的任务
        StatTashfor15fz stat15 = new StatTashfor15fz();
        timer.scheduleAtFixedRate(stat15, stat15.getFirstTime(), stat15.getPeriod());
//
//        //执行ValidChecker的递增操作。
//        ValidTask validTask = new ValidTask();
//        timer.scheduleAtFixedRate(validTask, validTask.getFirstTime(), validTask.getPeriod());
//        //执行日准确率的统计。
//        DayNicetyTask dayTask = new DayNicetyTask();
//        timer.scheduleAtFixedRate(dayTask, dayTask.getFirstTime(), dayTask.getPeriod());
////		执行地方电厂日准确率的统计。
//		PlantNicetyTask plantTask = new PlantNicetyTask();
//		timer.scheduleAtFixedRate(plantTask, plantTask.getFirstTime(), plantTask.getPeriod());
        //执行统计分析。
        


//        //      超短期预测
//        SSTLFTask sstlf = new SSTLFTask();
//        timer.schedule(sstlf, sstlf.getFirstTime(), sstlf.getPeriod());
//        
////      执行LoadFromCurTask的递增操作。北京局地区负荷
//        LoadFromCurTask loadTask = new LoadFromCurTask();
//        timer.scheduleAtFixedRate(loadTask, loadTask.getFirstTime(), loadTask.getPeriod());
//        
////      执行LoadFromCurWGTask的递增操作。北京局网供负荷
//        LoadFromCurWGTask loadWGTask = new LoadFromCurWGTask();
//        timer.scheduleAtFixedRate(loadWGTask, loadWGTask.getFirstTime(), loadWGTask.getPeriod());
//        
////      执行DecadeDayTask的递增操作。统计旬最大负荷
//        DecadeDayTask decade = new DecadeDayTask();
//        timer.scheduleAtFixedRate(decade, decade.getFirstTime(), decade.getPeriod());
//
////      执行WeatherConditionTask的递增操作。统计实况气象
//        WeatherConditionTask weather = new WeatherConditionTask();
//        timer.scheduleAtFixedRate(weather, weather.getFirstTime(), weather.getPeriod());
//
//        
////      执行WeatherEachHourTask的递增操作。统计逐时气象
//        WeatherEachHourTask weatherEH = new WeatherEachHourTask();
//        timer.scheduleAtFixedRate(weatherEH, weatherEH.getFirstTime(), weatherEH.getPeriod());
//
////      执行WeatherToSenTask的递增操作。统计灵敏度分析的气象
//        WeatherToSenTask weatherSen = new WeatherToSenTask();
//        timer.scheduleAtFixedRate(weatherSen, weatherSen.getFirstTime(), weatherSen.getPeriod());
//
////      执行LoadFromCurYesterDayTask的递增操作。修正前一天的负荷
//        LoadFromCurYesterDayTask curYesterday = new LoadFromCurYesterDayTask();
//        timer.scheduleAtFixedRate(curYesterday, curYesterday.getFirstTime(), curYesterday.getPeriod());
//        
////      执行DayEnergyTask的递增操作。统计电量
//        DayEnergyTask energy = new DayEnergyTask();
//        timer.scheduleAtFixedRate(energy, energy.getFirstTime(), energy.getPeriod());

    }
}
