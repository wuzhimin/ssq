package com.laodong.pub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 得到当前日期字串
	 * D-8位年月日(yyyyMMdd)
	 */
	public static String getDateTime(){
		return getDateTime("D");
	}
	/**
	 * 根据参数得到当前日期字串
	 * String DorTorDT取值范围：D、T、DT
	 * D-8位年月日(yyyyMMdd) 
	 * T-6位时分秒(HHmmss) 
	 * DT-14位年月日时分秒(yyyyMMddHHmmss)
	 * 默认：D 型日期
	 */
	public static String getDateTime(String D_T_DT){
		return getDateTime(D_T_DT, null);
	}
	/**
	 * 
	 * @名称 得到DT格式时间
	 * @描述 
	 * @日期 2006-7-26
	 * @时间 21:25:56
	 * @param D_T_DT
	 * @return
	 */
	public static String getDT_DateTime(){
		return getDateTime("DT", null);
	}
	public static String getBZ_DT_DateTime(){
		return getDateTime("DT", null);
	}
	/**
	 * 
	 * @名称 根据给定日期和格式得到日期字串
	 * @描述 
	 * @日期 2006-6-4
	 * @时间 12:37:12
	 * @param DorTorDT
	 * @param date
	 * @return
	 */
    public static String getDateTime(String DorTorDT, Date date){
    	String str = null;
        if(DorTorDT==null)DorTorDT="D";
        if(date==null)
        	date = new Date();
		SimpleDateFormat lFormatDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat lFormatTime = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat lFormatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(DorTorDT.equals("DT")){
			String rtn2 = lFormatTimestamp.format(date);
		
			rtn2 = rtn2.substring(0, 4) + rtn2.substring(5, 7) + rtn2.substring(8, 10)
	        + rtn2.substring(11, 13) + rtn2.substring(14, 16)+ rtn2.substring(17, 19);
            str = rtn2;
		}
		else if(DorTorDT.equals("T")){
			String rtn1 = lFormatTime.format(date);
			rtn1 = rtn1.substring(0, 2) + rtn1.substring(3, 5) + rtn1.substring(6, 8);
            str = rtn1;
		}
		else{ //默认：D 型日期
			String rtn = lFormatDate.format(date);
			rtn = rtn.substring(0, 4) + rtn.substring(5, 7) + rtn.substring(8);
            str = rtn;
			
		}
		
		return str; 
		
    }
    public static String getShowTime(){
    	String bzstr = getBZ_DT_DateTime();
    	return zhuanhuanLDDateStrToXSDateStr(bzstr);
    }
    
    /**
     * 
     * @名称 转换劳动DT时间格式为标准DT时间格式
     * @描述 20060604123428  ->  2006-06-04 12:34:28
     * @日期 2006-6-4
     * @时间 12:39:48
     * @return
     */
    public static String zhuanhuanLDDateStrToBZDateStr(String datestr){
    	if(StringUtil.sfWz(datestr))
    		return datestr;
    	if(datestr.length()<14){
    		int cha = 14 - datestr.length();
    		for(int i=0;i<cha;i++)
    			datestr += "0";
    	}
    	String str = datestr.substring(0, 4) + "-" + datestr.substring(4, 6) + "-" + datestr.substring(6, 8)
    	+ " " + datestr.substring(8, 10) + ":" + datestr.substring(10, 12) + ":" + datestr.substring(12, 14);
    	return str;
    }
    /**
     * @方法名称
     * @业务描述 20060604123428  ->  12:34:28
     *
     * @author lhh
     * @时间 2008-2-27 21:32:52
     */
    public static String zhuanhuanLDDateStrToHhmmtt(String datestr){
    	if(StringUtil.sfWz(datestr))
    		return datestr;
    	if(datestr.length()<14){
    		int cha = 14 - datestr.length();
    		for(int i=0;i<cha;i++)
    			datestr += "0";
    	}
    	String str = datestr.substring(8, 10) + ":" + datestr.substring(10, 12) + ":" + datestr.substring(12, 14);
    	return str;
    }
    /**
     * 
     * @名称 转换劳动DT时间格式为显示时间格式
     * @描述 20060604123428  ->  2006-06-04 12:34
     * @日期 2006-6-4
     * @时间 12:39:48
     * @return
     */
    public static String zhuanhuanLDDateStrToXSDateStr(String datestr){
    	if(StringUtil.sfWz(datestr))
    		return datestr;
    	if(datestr.length()<14){
    		int cha = 14 - datestr.length();
    		for(int i=0;i<cha;i++)
    			datestr += "0";
    	}
    	String str = datestr.substring(0, 4) + "-" + datestr.substring(4, 6) + "-" + datestr.substring(6, 8)
    	+ " " + datestr.substring(8, 10) + ":" + datestr.substring(10, 12);
    	return str;
    }
    public static String BZ_zhuanhuanLDDateStrToXSDateStr(String datestr){
    	return zhuanhuanLDDateStrToXSDateStr(datestr);
    }
    /**
     * 
     * @名称 转换劳动DT时间格式为简单显示时间格式
     * @描述 20060604123428  ->  2006-06-04
     * @日期 2006-6-4
     * @时间 12:39:48
     * @return
     */
    public static String zhuanhuanLDDateStrToJDXSDateStr(String datestr){
    	if(StringUtil.sfWz(datestr))
    		return datestr;
    	if(datestr.length()<8){
    		int cha = 8 - datestr.length();
    		for(int i=0;i<cha;i++)
    			datestr += "0";
    	}
    	String str = datestr.substring(0, 4) + "-" + datestr.substring(4, 6) + "-" + datestr.substring(6, 8);
    	return str;
    }
    /**
     * 
     * @名称 转换标准DT时间格式为劳动DT时间格式
     * @描述 2006-07-11 23:05:00 -> 20060711230500
     * @日期 2006-7-16
     * @时间 13:07:30
     * @param datastr
     * @return
     */
    public static String zhuanhuanBZDateStrToLDDateStr(String datastr){
    	if(StringUtil.sfWz(datastr))
    		return datastr;
    	if(datastr.length()<19)
    		return null;
    	datastr = datastr.substring(0, 4) + datastr.substring(5, 7) + datastr.substring(8, 10)
        + datastr.substring(11, 13) + datastr.substring(14, 16)+ datastr.substring(17, 19);
        return datastr;
    }
    /**
     * 日期字串由存储格式转换成显示格式
     * 20280706 -> 2028-07-06
     * @param datestr
     * @return
     * 2006-8-21
     */
    public static String zhNDateToBDate(String datestr){
    	if(datestr==null||datestr.length()<8)
    		return null;
    	String str = datestr.substring(0, 4) + "-" + datestr.substring(4, 6) + "-" + datestr.substring(6, 8);
        return str;
    }
    /**
     * 日期字串由显示格式转换成存储格式
     * 2028-07-06 -> 20280706
     * @param datestr
     * @return
     * 2006-8-21
     */
    public static String zhBDateToNDate(String datestr){
    	if(datestr==null||datestr.length()<10)
    		return null;
    	String str = datestr.substring(0, 4) + datestr.substring(5, 7) + datestr.substring(8, 10);
        return str;
    }
    /**
     * 
     * @名称 转换劳动DT时间格式为时间
     * @描述 
     * @日期 2006-6-4
     * @时间 12:47:29
     * @param datestr
     * @return
     */
    public static Date zhuanhuanLDDateStrToDate(String datestr){
    	
    	Date date = null;
    	try {
    		String str = datestr.substring(0, 4) + "-" + datestr.substring(4, 6) + "-" + datestr.substring(6, 8)
        	+ " " + datestr.substring(8, 10) + ":" + datestr.substring(10, 12) + ":" + datestr.substring(12, 14);
        	SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = formatTimestamp.parse(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("格式化日期字串错误！");
		}
    	return date;
    }
    
    public static void main(String[] args){
//    	Date date = zhuanhuanLDDateStrToDate("2006-07-11 23:05:00");
//    	System.out.println("2006-07-11 23:05:00".length());
    	Date d1 = zhuanhuanLDDateStrToDate("20070604123428");
    	Date d2 = zhuanhuanLDDateStrToDate("20060604123428");
    	//d1>d2时返回true
    	if(d1.after(d2))
    		System.out.println("ok");
//    	String str = zhuanhuanLDDateStrToBZDateStr("20060604123428");
//    	System.out.println(str);
//    	String str = zhuanhuanBZDateStrToLDDateStr("2006-07-11 23:05:00");
//    	System.out.println(str);
//    	Calendar cal = Calendar.getInstance();
//    	cal.add(Calendar.MINUTE, -10);
//    	Date date = cal.getTime();
//    	System.out.println(date);
//    	String str = DateUtil.getDateTime("DT");
//    	System.out.print(str);
    	//Date d = new Date();
    	//d.setSeconds()
    }
}
