package com.wzm.biz;

import java.util.ArrayList;
import java.util.List;

import com.ssq.common.util.SsqUtils;
import com.wzm.server.dao.ssq.SsqBaseStatsDao;
import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.ssq.SsqBaseStats;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.server.entity.ssq.SsqTailStats;
import com.wzm.util.ClientBeanUtil;

public class StatsLawBuild {


	
	
	public static List<String> buildStatsForWeixin(int ssqIndex) {
		List<String> result = new ArrayList<String>();

		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao) ClientBeanUtil.getDao("ssqBaseStatsDao");
		SsqRecordDao ssqRecordDao = (SsqRecordDao) ClientBeanUtil.getDao("ssqRecordDao");

		SsqBaseStats baseStats = ssqBaseStatsDao.findSsqBaseStatsesBySsqIndex(ssqIndex);
		SsqTailStats tailStats = ssqBaseStatsDao.findSsqTailStatsesBySsqIndex(ssqIndex);
		SsqRecord record = ssqRecordDao.findSsqRecordBySsqIndex(ssqIndex);
		
		int redSum = baseStats.getRedSum();
		int[] mm = SumHotLawBuild.getMaxYilouAndMinYilou(ssqBaseStatsDao, SumHotLawBuild.RED_SUM, redSum);
		
		String str = "和值：    "+redSum+"     和值"+redSum+"最小遗漏期数："+mm[0]+"      和值"+redSum+"最大遗漏期数："+mm[1];
		result.add(str+"\n");
		
		String str1 = "奇偶数：  奇数个数："+baseStats.getOddCount()+"  奇数和值："+baseStats.getOddSum()+"     偶数个数："+baseStats.getEvenCount()+"   偶数和值："+baseStats.getEvenSum();
		result.add(str1+"\n");
		
		String str2 = "大小数：  小数(1-16)个数:"+baseStats.getSmallCount()+"     小数和值:"+baseStats.getSmallSum()+"      大数(17-33)个数:"+baseStats.getBigCount()+"      大数和值:"+baseStats.getBigSum()+"";
		result.add(str2+"\n");
		
		String str3 = "3区数：   小区(1-11)个数:"+baseStats.getFirstZoneCount()+"     中区(12-22)个数:"+baseStats.getSecondZoneCount()+"       大区(23-33)个数:"+baseStats.getThirdZoneCount();
		result.add(str3+"\n");
		
		String str4 = "质数：    个数:"+baseStats.getPrimeCount()+"     和值:"+baseStats.getPrimeSum();
		result.add(str4+"\n");
		
		String tailStr = "";
		if(tailStats.getT0count()>0) {
			tailStr = tailStr+"0";
		}
		
		if(tailStats.getT1count()>0) {
			tailStr = tailStr+"1";
		}
		
		if(tailStats.getT2count()>0) {
			tailStr = tailStr+"2";
		}
		
		if(tailStats.getT3count()>0) {
			tailStr = tailStr+"3";
		}
		
		if(tailStats.getT4count()>0) {
			tailStr = tailStr+"4";
		}
		
		if(tailStats.getT5count()>0) {
			tailStr = tailStr+"5";
		}
		
		if(tailStats.getT6count()>0) {
			tailStr = tailStr+"6";
		}
		
		if(tailStats.getT7count()>0) {
			tailStr = tailStr+"7";
		}
		
		if(tailStats.getT8count()>0) {
			tailStr = tailStr+"8";
		}
		
		if(tailStats.getT9count()>0) {
			tailStr = tailStr+"9";
		}
		
		String str5 = "尾数：    "+tailStr;
		result.add(str5+"\n");
		
		int ac = SsqUtils.getACValue(new int[]{record.getR1(), record.getR2(), record.getR3(), record.getR4(), record.getR5(), record.getR6()});
		int sandu = SsqUtils.getSandu(new int[]{record.getR1(), record.getR2(), record.getR3(), record.getR4(), record.getR5(), record.getR6()});
		
		String str6 = "AC值：    "+ac;
		result.add(str6+"\n");

		String str7 = "散度：    "+sandu;
		result.add(str7+"\n");
		
		return result;
	}
	
	public static void main(String[] args) {
		List<String> list = buildStatsForWeixin(2013118);
		for(String str:list) {
			System.out.println(str);
		}
	}
}
