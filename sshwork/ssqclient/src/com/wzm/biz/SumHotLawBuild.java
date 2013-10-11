package com.wzm.biz;

import java.util.ArrayList;
import java.util.List;

import com.wzm.server.dao.ssq.SsqBaseStatsDao;
import com.wzm.server.entity.ssq.SsqBaseStats;
import com.wzm.util.ClientBeanUtil;

public class SumHotLawBuild {
	
	// 红球和值
	public static final String RED_SUM = "redSum";
	
	// 红球偶数和值
	public static final String EVEN_SUM = "evenSum";
	
	// 红球奇数和值
	public static final String ODD_SUM = "oddSum";
	
	// 红球小数和
	public static final String SMALL_SUM = "smallSum";
	
	// 红球大数和
	public static final String BIG_SUM = "bigSum";
	
	// 红球小区数和
	public static final String FIRST_ZONE_SUM = "firstZoneSum";
	
	// 红球中区数和
	public static final String SECOND_ZONE_SUM = "secondZoneSum";
	
	// 红球大区数和
	public static final String THIRD_ZONE_SUM = "thirdZoneSum";
	
	// 红球质数和
	public static final String PRIME_SUM = "primeSum";
	
	public static void main(String[] args) {
		buildSum(BIG_SUM, 2013104);
	}

	public static List<String> buildSum(String sumType, int ssqIndex) {
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");

		List<String> result = new ArrayList<String>();
		result.add("和值类型："+sumType);
		
		String hql = "select distinct "+sumType+" from SsqBaseStats s where s.ssqIndex<=? order by "+sumType;
		List listSum = ssqBaseStatsDao.find(hql, new Integer[]{ssqIndex});

		for (Object baseStats : listSum) {
			int sum = ((Integer) baseStats).intValue();

			result.addAll(build(ssqBaseStatsDao, sumType, sum));
		}
		
		return result;
	}
	
	public static List<String> buildSum(String sumType, int ssqIndex,int sum) {
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");

		List<String> result = new ArrayList<String>();
		result.add("和值类型："+sumType);
		
		result.addAll(build(ssqBaseStatsDao, sumType, sum));
		
		return result;
	}

	private static List<String> build(SsqBaseStatsDao ssqBaseStatsDao, String sumType, int sum) {
		List<String> result = new ArrayList<String>();
		result.add("\n-----------" + sum + "-----------");
		
		String hql = "from SsqBaseStats s where s."+sumType+" = ?";
		List<SsqBaseStats> list = ssqBaseStatsDao
				.findSsqBaseStatsesBySumScope(sumType, sum, sum);

		hql = " select count(s.ssqIndex) from SsqBaseStats s where s.ssqIndex>=? and s.ssqIndex<=?";

		long max = -1;
		long min = 10000;
		for (int i = 1; i < list.size(); i++) {
			int ssqIndex1 = list.get(i - 1).getSsqIndex();
			int ssqIndex2 = list.get(i).getSsqIndex();
			long count = ssqBaseStatsDao.getFunctionLongValue(hql,
					new Integer[] { ssqIndex1, ssqIndex2 }) - 2;

			if (count > max) {
				max = count;
			}

			if (count < min) {
				min = count;
			}
			result.add(ssqIndex1 + "----->" + ssqIndex2 + "       遗漏期数：" + count);
		}

		if(max == -1) {
			max =0;
		}
		
		if(min == 10000) {
			min =0;
		}
		result.add("最小遗漏期数："+min + "       " +"最大遗漏期数："+ max);
		
		return result;
		
	}
	
	public static int[] getMaxYilouAndMinYilou(SsqBaseStatsDao ssqBaseStatsDao, String sumType, int sum) {
		
		String hql = "from SsqBaseStats s where s."+sumType+" = ?";
		List<SsqBaseStats> list = ssqBaseStatsDao.findSsqBaseStatsesBySumScope(sumType, sum, sum);

		hql = " select count(s.ssqIndex) from SsqBaseStats s where s.ssqIndex>=? and s.ssqIndex<=?";

		long max = -1;
		long min = 10000;
		for (int i = 1; i < list.size(); i++) {
			int ssqIndex1 = list.get(i - 1).getSsqIndex();
			int ssqIndex2 = list.get(i).getSsqIndex();
			long count = ssqBaseStatsDao.getFunctionLongValue(hql,
					new Integer[] { ssqIndex1, ssqIndex2 }) - 2;

			if (count > max) {
				max = count;
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
		
		
		
		return new int[]{(int)min, (int)max};
		
	}

}
