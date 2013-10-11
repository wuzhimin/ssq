package com.wzm.biz;

import java.util.ArrayList;
import java.util.List;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.dao.ssq.SsqBaseStatsDao;
import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.server.entity.ssq.SsqTailStats;
import com.wzm.util.ClientBeanUtil;

public class TailHotLawBuild {

	public static void main(String[] args) {
//		buildTailHotLawWithRepeat(2013102);
//		for(int tailNumber=0;tailNumber<=9;tailNumber++)
//		buildTailHotLaw2(tailNumber,20);
		buildTailHotLaw2(1,20);
	}

	/**
	 * 产生尾数冷热遗失、连续规律
	 */
	public static List<String> buildTailHotLaw(int ssqIndex, int count) {
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");

		List<String> result = new ArrayList<String>();
		
		result.add("双色球当前期："+ssqIndex);
		
		for(int tailHotNumber = 0;tailHotNumber<=9;tailHotNumber++) {
			String field = "t"+tailHotNumber+"count";
		
			String hql = "from SsqTailStats s where "+field+">="+count+" and s.ssqIndex<=? order by s.ssqIndex";
			List<SsqTailStats> listSsqRecords = ssqBaseStatsDao.findSsqTailStatsesByHql(hql, new Integer[]{ssqIndex});
	
			result.addAll(build(ssqBaseStatsDao, listSsqRecords, tailHotNumber, ssqIndex));
		}
		
		return result;
	}
	
	public static List<String> buildTailHotLaw2(int tailNumber, int baseN) {
		SsqRecordDao ssqRecordDao = (SsqRecordDao)ClientBeanUtil.getDao("ssqRecordDao");
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");
		
		String hql = "from SsqRecord order by ssqIndex desc";
		
		String field = "t"+tailNumber+"count";
		String hql1 = "select count(ssqIndex) from SsqTailStats where "+field+">=2 and ssqIndex>=? and ssqIndex<=?";
		List<SsqRecord> records = ssqRecordDao.findSsqRecordsByHql(hql);
		System.out.println("基数："+baseN+"，尾数"+tailNumber+"的个数大于等于2的期数统计");
		long maxCount =-1;
		String maxCountStr ="";
		for(int i=0;i<records.size() && i+baseN-1<records.size();i++) {
			int ssqIndex1 = records.get(i).getSsqIndex();
			int ssqIndex2 = records.get(i+baseN-1).getSsqIndex();
			long count = ssqBaseStatsDao.getFunctionLongValue(hql1, new Integer[]{ssqIndex2, ssqIndex1});
			if(count>maxCount) {
				maxCount = count;
				maxCountStr= ssqIndex2+"----------"+ssqIndex1;
			}
			if(count>=0)
			System.out.println(ssqIndex2+"----------"+ssqIndex1+"--------"+count);
		}
		System.out.println(maxCountStr+"--------"+maxCount);
		
		List<String> result = new ArrayList<String>();
		
		return result;
	}
	
	public static List<String> buildTailHotLawWithRepeat(boolean isRepeat, int ssqIndex) {
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");

		List<String> result = new ArrayList<String>();
		
		result.add("双色球当前期："+ssqIndex);
		
		String conditionStr = "( ft0count<=1 and ft1count<=1 and ft2count<=1 and ft3count<=1 and ft4count<=1 and ft5count<=1 " +
				" and ft6count<=1 and ft7count<=1 and ft8count<=1 and ft9count<=1 )";
		
		if(isRepeat) {
			conditionStr = "( ft0count>=2 or ft1count>=2 or ft2count>=2 or ft3count>=2 or ft4count>=2 or ft5count>=2 " +
					" or ft6count>=2 or ft7count>=2 or ft8count>=2 or ft9count>=2 )";
		}
	
		String hql = "from SsqTailStats s where "+conditionStr+" and s.ssqIndex<=? order by s.ssqIndex";
		List<SsqTailStats> listSsqRecords = ssqBaseStatsDao.findSsqTailStatsesByHql(hql, new Integer[]{ssqIndex});

		result.addAll(build1(ssqBaseStatsDao, listSsqRecords, isRepeat, ssqIndex));
		
		return result;
	}

	private static List<String> build(BaseDao baseDao, List<SsqTailStats> list, int tailHotNumber, int ssqIndex) {
		
		String hql = " select count(s.ssqIndex) from SsqTailStats s where s.ssqIndex>=? and s.ssqIndex<=?";
		
		List<String> result= new ArrayList<String>();

		long maxLose = -1;
		long minLose = 10000;
		
		String maxLoseBeginStr = "";
		String maxContinueEndStr = "";
		
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
					maxContinueEndStr = String.valueOf(ssqIndex1);
				}
				continueCount=1;
			}
			
			long count = baseDao.getFunctionLongValue(hql, new Integer[] { ssqIndex1, ssqIndex2 }) - 2;

			if (count > maxLose) {
				maxLose = count;
				maxLoseBeginStr = String.valueOf(ssqIndex1);
			}

			if (count < minLose) {
				minLose = count;
			}
		}

		if(maxLose == -1) {
			maxLose =0;
		}
		
		if(minLose == 10000) {
			minLose =0;
		}
		
		long currentLoseCount = baseDao.getFunctionLongValue(hql, new Integer[] { ssqIndex2, ssqIndex }) -1;
		
		
		String str1 = "尾数"+getFormatStr(tailHotNumber)+"：";
		String str2 = "      最大连续期数：" + getFormatStr(maxContinue);
		String str3 = "      最大遗漏期数：" + getFormatStr(maxLose);
		String str4 = "      当前遗漏期数：" + getFormatStr(currentLoseCount);
		String str5 = "      最大连续结束期：" + maxContinueEndStr;
		
		result.add(str1 + str2 + str3 + str4 + str5);
		
		return result;
	}
	
	private static List<String> build1(BaseDao baseDao, List<SsqTailStats> list, boolean isRepeat, int ssqIndex) {
		
		String hql = " select count(s.ssqIndex) from SsqTailStats s where s.ssqIndex>=? and s.ssqIndex<=?";
		
		List<String> result= new ArrayList<String>();

		long max = -1;
		long min = 10000;
		String maxLoseBeginStr = "";
		int maxContinue = -1;
		int continueCount = 1;
		
		String maxContinueBeginStr = "";
		String tmpContinueBeginStr = "";
		
		int ssqIndex2 =0;
		for (int i = 1; i < list.size(); i++) {
			int ssqIndex1 = list.get(i - 1).getSsqIndex();
			ssqIndex2 = list.get(i).getSsqIndex();
			if(ssqIndex2==ssqIndex1+1) {
				continueCount++;
				if(tmpContinueBeginStr.trim().length()==0) {
					tmpContinueBeginStr = String.valueOf(ssqIndex1);
				}
			} else {
				if(maxContinue<continueCount) {
					maxContinue = continueCount;
					maxContinueBeginStr = tmpContinueBeginStr;
				}
				continueCount=1;
				tmpContinueBeginStr = "";
			}
			
			long count = baseDao.getFunctionLongValue(hql, new Integer[] { ssqIndex1, ssqIndex2 }) - 2;

			if (count > max) {
				max = count;
				maxLoseBeginStr = String.valueOf(ssqIndex1);
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
		
		
		// 当前遗漏期数
		long currentLoseCount = baseDao.getFunctionLongValue(hql, new Integer[] { ssqIndex2, ssqIndex }) -1;
		
		String tmp = "尾数重复:";
		if(!isRepeat) {
			tmp = "尾数不重复:";
		}
		
		result.add(tmp+"    最大连续期数："+getFormatStr(maxContinue) + "     " +"最大遗漏期数："+ 
				getFormatStr(max)+"     当前遗漏期数："+getFormatStr(currentLoseCount)+
				"     最大连续期数开始期："+maxContinueBeginStr + "     最大遗漏期数开始期："+maxLoseBeginStr);
		
		return result;
	}

	private static String getFormatStr(int tailHotNumber) {
		return tailHotNumber<10?"0"+tailHotNumber:""+tailHotNumber;
	}
	
	private static String getFormatStr(long tailHotNumber) {
		return tailHotNumber<10?"0"+tailHotNumber:""+tailHotNumber;
	}

}
