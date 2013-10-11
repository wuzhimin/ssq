package com.wzm.biz;

import java.util.ArrayList;
import java.util.List;

import com.ssq.common.util.SsqUtils;
import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.util.ClientBeanUtil;

public class BlueHotLawBuild {

	public static void main(String[] args) {
		buildBlueHotLaw(2013102);
	}

	/**
	 * 产生蓝球冷热遗失、连续规律
	 */
	public static List<String> buildBlueHotLaw(int currentSsqIndex) {
		SsqRecordDao ssqRecordDao = (SsqRecordDao)ClientBeanUtil.getDao("ssqRecordDao");
		
		List<String> result = new ArrayList<String>();
		
		result.add("双色球当前期："+currentSsqIndex);

		for(int blueHotNumber = 1;blueHotNumber<=16;blueHotNumber++) {
		
			String hql = "from SsqRecord s where s.b1=? and s.ssqIndex<=? order by s.ssqIndex";
			List<SsqRecord> listSsqRecords = ssqRecordDao.findSsqRecordsByHql(hql, new Integer[]{blueHotNumber, currentSsqIndex});
	
			result.addAll(build(ssqRecordDao, listSsqRecords, blueHotNumber, currentSsqIndex));
		}
		
		return result;
	}
	
	public static List<String> buildBlueHotIsEvenLaw(boolean isEven, int currentSsqIndex) {
		SsqRecordDao ssqRecordDao = (SsqRecordDao)ClientBeanUtil.getDao("ssqRecordDao");
		
		List<String> result = new ArrayList<String>();
		
		result.add("双色球当前期："+currentSsqIndex);
		
		String hql = "from SsqRecord s where s.b1 % 2=0 and s.ssqIndex<=? order by s.ssqIndex";
		if(!isEven) {
			hql = "from SsqRecord s where s.b1 % 2=1 and s.ssqIndex<=? order by s.ssqIndex";
		}
		List<SsqRecord> listSsqRecords = ssqRecordDao.findSsqRecordsByHql(hql, new Integer[]{currentSsqIndex});


		result.addAll(build1(ssqRecordDao, listSsqRecords, isEven, currentSsqIndex));
		
		return result;
	}

	private static List<String> build(SsqRecordDao ssqRecordDao, List<SsqRecord> list, int blueHotNumber, int currentSsqIndex) {
		List<String> result = new ArrayList<String>();
		
		String hql = " select count(s.ssqIndex) from SsqRecord s where s.ssqIndex>=? and s.ssqIndex<=?";

		long maxLose = -1;
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

			if (count > maxLose) {
				maxLose = count;
				maxBeginStr = String.valueOf(ssqIndex1);
			}

			if (count < min) {
				min = count;
			}
		}

		if(maxLose == -1) {
			maxLose =0;
		}
		
		if(min == 10000) {
			min =0;
		}
		
		long currentLoseCount = ssqRecordDao.getFunctionLongValue(hql,
				new Integer[] { ssqIndex2, currentSsqIndex }) -1;
		
		
		String str1 = "蓝球"+SsqUtils.build2BitIntStr(blueHotNumber)+"：";
		String str2 = "     最大连续期数：" + SsqUtils.build2BitIntStr(maxContinue);
		String str3 = "     最大遗漏期数：" + SsqUtils.buildBitIntStr(3, (int)maxLose);
		String str4 = "     当前遗漏期数：" + SsqUtils.buildBitIntStr(3, (int)currentLoseCount);
		String str = str1 + str2 + str3 + str4;
//		System.out.println(str);
		result.add(str);
		
		return result;
	}
	
	private static List<String> build1(SsqRecordDao ssqRecordDao, List<SsqRecord> list, boolean isEven, int currentSsqIndex) {
		List<String> result = new ArrayList<String>();
		
		String hql = " select count(s.ssqIndex) from SsqRecord s where s.ssqIndex>=? and s.ssqIndex<=?";

		long max = -1;
		long min = 10000;
		String maxLoseBeginStr = "";
		String maxContinueBeginStr = "";
		String tmpContinueBeginStr = "";
		int maxContinue = -1;
		int continueCount = 1;
		
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
				tmpContinueBeginStr = "";
				continueCount=1;
			}
			long count = ssqRecordDao.getFunctionLongValue(hql,
					new Integer[] { ssqIndex1, ssqIndex2 }) - 2;

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
		
		long currentLoseCount = ssqRecordDao.getFunctionLongValue(hql,
				new Integer[] { ssqIndex2, currentSsqIndex }) -1;
		
		String tmp = "蓝球尾数为偶数："; 
		if(!isEven) {
			tmp = "蓝球尾数为奇数："; 
		}
		String str = tmp+"    最大连续期数："+maxContinue + "     " +"最大遗漏期数："+ max+"     当前遗漏期数："+currentLoseCount+"     最大连续开始期："+maxContinueBeginStr+"     最大遗漏开始期："+maxLoseBeginStr;
		System.out.println(str);
		result.add(str);
		
		return result;
	}

}
