package com.wzm.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ssq.common.util.FileUtil;
import com.wzm.server.dao.ssq.SsqBaseStatsDao;
import com.wzm.server.entity.ssq.SsqPrimeStats;
import com.wzm.util.ClientBeanUtil;

/**
 * 质数规律创建
 * @author Administrator
 *
 */
public class PrimeHotLawBuild {

	public static void main(String[] args) {
		List<String> list = buildPrimeCountHotLaw(2013104);
		try {
			FileUtil.writeToFile("c:\temp1.txt", list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 产生质数个数遗失、连续规律
	 */
	public static List<String> buildPrimeCountHotLaw(int currentSsqIndex) {
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");
		
		List<String> result = new ArrayList<String>();
		
		result.add("双色球当前期："+currentSsqIndex);
		
		for(int i=0;i<=5;i++) {
			int primeCount = i;
			
			String hql = "from SsqPrimeStats s where s.primeCount= ? and s.ssqIndex<=? order by s.ssqIndex";
			
			List<SsqPrimeStats> listSsqRecords = ssqBaseStatsDao.findSsqPrimeStatsesByHql(hql, new Integer[]{primeCount, currentSsqIndex});
	
			result.addAll(build(ssqBaseStatsDao, listSsqRecords, primeCount, currentSsqIndex));
		}
		
		return result;
	}
	
	private static List<String> build(SsqBaseStatsDao ssqRecordDao, List<SsqPrimeStats> list, int primeHotNumber, int currentSsqIndex) {
		List<String> result = new ArrayList<String>();
		
		String hql = " select count(s.ssqIndex) from SsqRecord s where s.ssqIndex>=? and s.ssqIndex<=?";

		long max = -1;
		long min = 10000;
		String maxBeginStr = "";
		int maxContinue = -1;
		int continueCount = 1;
		
		int ssqIndex2 =0;
		for (int i = 1; i < list.size(); i++) {
			int ssqIndex1 = list.get(i - 1).getSsqIndex();
			ssqIndex2 = list.get(i).getSsqIndex();
			if(ssqIndex2==ssqIndex1+1) {
				continueCount++;
			} else {
				if(maxContinue<continueCount) {
					maxContinue = continueCount;
				}
				continueCount=1;
			}
			long count = ssqRecordDao.getFunctionLongValue(hql,
					new Integer[] { ssqIndex1, ssqIndex2 }) - 2;

			if (count > max) {
				max = count;
				maxBeginStr = String.valueOf(ssqIndex1);
			}

			if (count < min) {
				min = count;
			}
		}

		if(max == -1) {
			max =0;
		}
		
		if(min == 10000) {
			min =0;
		}
		
		long currentLoseCount = ssqRecordDao.getFunctionLongValue(hql,
				new Integer[] { ssqIndex2, currentSsqIndex }) -1;
		
		if(ssqIndex2!=currentSsqIndex) {
			continueCount = 0;
		}
		
		String str = "质数个数"+(primeHotNumber<10?"0"+primeHotNumber:""+primeHotNumber)+"："+"    最大连续期数："+maxContinue + "     " 
				+"最大遗漏期数："+ max+"     当前遗漏期数："+currentLoseCount
				+"    当前连续期数："+continueCount;
		System.out.println(str);
		result.add(str);
		
		return result;
	}
}
