package com.wzm.server.service.ssq;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.base.BaseEntity;
import com.wzm.server.entity.ssq.MulStatsCompute;
import com.wzm.server.entity.ssq.SsqColdHotStats;
import com.wzm.server.entity.ssq.SsqMulColdHotForcast;
import com.wzm.server.entity.ssq.SsqMulColdHotStats;
import com.wzm.server.entity.ssq.SsqMulTailForcast;
import com.wzm.server.entity.ssq.SsqMulTailStats;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.server.entity.ssq.SsqTailStats;
import com.wzm.server.service.base.BaseServiceImpl;

public class SsqForcastServiceImpl extends BaseServiceImpl implements SsqForcastService {
	
	private SsqRecordDao ssqRecordDao;
	
	/**
	 * 根据双色球开始期和结束期写入红球尾数预测值
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void writeSsqTailForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum) {
		
		// 1.删除开始期和结束期范围内的预测数据
		getBaseDao().deleteByHql(
				"delete from SsqMulTailForcast " 
						+ " a where a.fromSsqIndex>=? and a.toSsqIndex<=? and a.spaceNum = ? and a.forcastSpaceNum = ?",
				new Object[] { fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum });

		// 2.取出双色球多期统计信息记录集合
		String hql = "from SsqMulTailStats a where a.fromSsqIndex>=? and a.toSsqIndex<=? and a.spaceNum = ? order by a.fromSsqIndex";
		List<BaseEntity> records = getBaseDao().find(hql, new Integer[]{fromSsqIndex, toSsqIndex, spaceNum });
		
		List<BaseEntity> forcastList = new ArrayList<BaseEntity>();
		
		for(int i=0;i<records.size()-forcastSpaceNum+1;i++) {
			int count=0;
			
			List<MulStatsCompute> tmp = new ArrayList<MulStatsCompute>();
			
			for(int j=i;count<forcastSpaceNum;j++,count++) {
				tmp.add((MulStatsCompute) records.get(j));
			}
			
			SsqMulTailForcast forcast= buildTailForcast(tmp,forcastSpaceNum);
			forcastList.add(forcast);
			
		}
		
		// 批量保存预测数据
		getBaseDao().batchAddBaseEntityes(forcastList);
		
	}
	
	/**
	 * 根据双色球多期尾数统计信息写入到目前期数为止，不存在的多期尾数预测信息
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void writeNotExistSsqTailForcast(int spaceNum, int forcastSpaceNum) {
		
		// 获取formSsqIndex，即要写入的统计表信息的开始期
		String hql = "select max(toSsqIndex) from SsqMulTailForcast where spaceNum = ? and forcastSpaceNum = ?";
		
		hql = "select fromSsqIndex from SsqMulTailForcast where toSsqIndex = ("+hql+") and spaceNum = ? and forcastSpaceNum = ?";
		
		hql = " from SsqRecord  where ssqIndex = ("+hql+") ";
		
		Integer[] params = new Integer[] { spaceNum, forcastSpaceNum, spaceNum, forcastSpaceNum };
		List<BaseEntity> records = getBaseDao().find(hql, params);
		
		int fromSsqIndex = ((SsqRecord)records.get(0)).getNextSsqIndex();
		
		hql = "select max(toSsqIndex) from SsqMulTailStats where spaceNum = ?";
		params = new Integer[] { spaceNum};
		int toSsqIndex = getBaseDao().getFunctionIntValue(hql, params);
		
		writeSsqTailForcast(fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum);
	}
	
	/**
	 * 根据双色球多期尾数统计信息，验证到目前期数为止未验证过的红球尾数预测值
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void verifyNotExistSsqTailForcast(int spaceNum, int forcastSpaceNum) {
		
		// 获取formSsqIndex，即要写入的统计表信息的开始期
		String hql = "select min(toSsqIndex) from SsqMulTailForcast where spaceNum = ? and forcastSpaceNum = ? and verified = false";
		
		hql = "select fromSsqIndex from SsqMulTailForcast where toSsqIndex = ("+hql+") and spaceNum = ? and forcastSpaceNum = ?";
		
		hql = " from SsqRecord  where ssqIndex = ("+hql+") ";
		
		Integer[] params = new Integer[] { spaceNum, forcastSpaceNum, spaceNum, forcastSpaceNum };
		List<BaseEntity> records = getBaseDao().find(hql, params);
		
		int fromSsqIndex = ((SsqRecord)records.get(0)).getSsqIndex();
		
		hql = "select max(toSsqIndex) from SsqMulTailStats where spaceNum = ?";
		params = new Integer[] { spaceNum};
		int toSsqIndex = getBaseDao().getFunctionIntValue(hql, params);
		
		verifySsqTailForcast(fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum);
	}
	
	
	/**
	 * 根据双色球开始期和结束期及间隔，验证红球尾数预测值，写入数据库
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void verifySsqTailForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum) {
		
		// 1.获取开始期和结束期范围内的预测数据
		List<BaseEntity> forcasts = getBaseDao().find(" from SsqMulTailForcast a where a.fromSsqIndex>=? and a.toSsqIndex<=? and a.spaceNum = ? and a.forcastSpaceNum = ?", 
				new Integer[]{fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum});
		
		for(int i=0;i<forcasts.size();i++){
			SsqMulTailForcast forcast = (SsqMulTailForcast) forcasts.get(i);
			int targetSsqIndex = forcast.getTargetSsqIndex();
			
			SsqRecord record = ssqRecordDao.findSsqRecordBySsqIndex(targetSsqIndex);
			if(record == null) {
				return;
			}
			
			String careNums = forcast.getCareNums();
			String killNums = forcast.getKillNums();
			String posibleSltNums = forcast.getPossibleSelectedNums();
			String certainSltnums = forcast.getCertainSelectedNums();
			
			Map<String, String> tailMap = new HashMap<String, String>();
			
			tailMap.put(String.valueOf(record.getR1() % 10) ,null);
			tailMap.put(String.valueOf(record.getR2() % 10) ,null);
			tailMap.put(String.valueOf(record.getR3() % 10) ,null);
			tailMap.put(String.valueOf(record.getR4() % 10) ,null);
			tailMap.put(String.valueOf(record.getR5() % 10) ,null);
			tailMap.put(String.valueOf(record.getR6() % 10) ,null);
			
			String careNumsResult = "";
			String killNumsResult_right = "";
			String killNumsResult_error = "";
			String posibleNumsResult = "";
			String certainNumsResult = "";
			
			if(StringUtils.hasLength(careNums)) {
				String[] strs = careNums.split(",");
				for(int j=0;j<strs.length;j++) {
					if(tailMap.containsKey(strs[j])) {
						careNumsResult = careNumsResult+strs[j]+",";
					} 
				}
			}
			
			if(StringUtils.hasLength(killNums)) {
				String[] strs = killNums.split(",");
				for(int j=0;j<strs.length;j++) {
					if(tailMap.containsKey(strs[j])) {
						killNumsResult_error = killNumsResult_error + strs[j]+",";
					} else {
						killNumsResult_right = killNumsResult_right + strs[j]+",";
					}
				}
			}
			
			
			if(StringUtils.hasLength(posibleSltNums)) {
				String[] strs = posibleSltNums.split(",");
				for(int j=0;j<strs.length;j++) {
					if(tailMap.containsKey(strs[j])) {
						posibleNumsResult = posibleNumsResult + strs[j]+",";
					} 
				}
			}
			
			if(StringUtils.hasLength(certainSltnums)) {
				String[] strs = certainSltnums.split(",");
				for(int j=0;j<strs.length;j++) {
					if(tailMap.containsKey(strs[j])) {
						certainNumsResult = certainNumsResult + strs[j]+",";
					} 
				}
			}
			
			forcast.setCareNumsResult(careNumsResult);
			
			String tmp = "";
			if(StringUtils.hasLength(killNumsResult_right)) {
				tmp = tmp+"杀对："+killNumsResult_right;
			}
			if(StringUtils.hasLength(killNumsResult_error)) {
				tmp = tmp+"  杀错："+killNumsResult_error;
			}
			forcast.setKillNumsResult(tmp);
			
			forcast.setPossibleSelectedNumsResult(posibleNumsResult);
			forcast.setCertainSelectedNumsResult(certainNumsResult);
			
			forcast.setVerified(true);
			
			// 修改保存
			getBaseDao().update(forcast);
		}
		
	}
	
	/**
	 * * 根据双色球多期冷热数统计信息，验证到目前期数为止未验证过的红球冷热数预测值
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void verifyNotExistSsqCHForcast(int spaceNum, int forcastSpaceNum) {

		// 获取formSsqIndex，即要写入的统计表信息的开始期
		String hql = "select min(toSsqIndex) from SsqMulColdHotForcast where spaceNum = ? and forcastSpaceNum = ? and verified = false";
		
		hql = "select fromSsqIndex from SsqMulColdHotForcast where toSsqIndex = ("+hql+") and spaceNum = ? and forcastSpaceNum = ?";
		
		hql = " from SsqRecord  where ssqIndex = ("+hql+") ";
		
		Integer[] params = new Integer[] { spaceNum, forcastSpaceNum, spaceNum, forcastSpaceNum };
		List<BaseEntity> records = getBaseDao().find(hql, params);
		
		int fromSsqIndex = ((SsqRecord)records.get(0)).getSsqIndex();
		
		hql = "select max(toSsqIndex) from SsqMulColdHotStats where spaceNum = ?";
		params = new Integer[] { spaceNum };
		int toSsqIndex = getBaseDao().getFunctionIntValue(hql, params);
		
		verifySsqCHForcast(fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum);
		
	}
	
	/**
	 * 根据双色球开始期和结束期及间隔，验证红球冷热数预测值，写入数据库
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void verifySsqCHForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum) {
		
		// 1.获取开始期和结束期范围内的预测数据
		List<BaseEntity> forcasts = getBaseDao().find(" from SsqMulColdHotForcast a where a.fromSsqIndex>=? and a.toSsqIndex<=? and a.spaceNum = ? and a.forcastSpaceNum = ?", 
				new Integer[]{fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum});
		
		for(int i=0;i<forcasts.size();i++){
			SsqMulColdHotForcast forcast = (SsqMulColdHotForcast) forcasts.get(i);
			int targetSsqIndex = forcast.getTargetSsqIndex();
			
			SsqRecord record = ssqRecordDao.findSsqRecordBySsqIndex(targetSsqIndex);
			if(record == null) {
				return;
			}
			
			String careNums = forcast.getCareNums();
			String killNums = forcast.getKillNums();
			String selectNums = forcast.getSelectNums();
			
			Map<String, String> coldHotMap = new HashMap<String, String>();
			
			coldHotMap.put(String.valueOf(record.getR1()) ,null);
			coldHotMap.put(String.valueOf(record.getR2()) ,null);
			coldHotMap.put(String.valueOf(record.getR3()) ,null);
			coldHotMap.put(String.valueOf(record.getR4()) ,null);
			coldHotMap.put(String.valueOf(record.getR5()) ,null);
			coldHotMap.put(String.valueOf(record.getR6()) ,null);
			
			String careNumsResult = "";
			String killNumsResult_right = "";
			String killNumsResult_error = "";
			String selectNumsResult = "";
			
			if(StringUtils.hasLength(careNums)) {
				String[] strs = careNums.split(",");
				for(int j=0;j<strs.length;j++) {
					if(coldHotMap.containsKey(strs[j])) {
						careNumsResult = careNumsResult+strs[j]+",";
					} 
				}
			}
			
			if(StringUtils.hasLength(killNums)) {
				String[] strs = killNums.split(",");
				for(int j=0;j<strs.length;j++) {
					if(coldHotMap.containsKey(strs[j])) {
						killNumsResult_error = killNumsResult_error + strs[j]+",";
					} else {
						killNumsResult_right = killNumsResult_right + strs[j]+",";
					}
				}
			}
			
			
			if(StringUtils.hasLength(selectNums)) {
				String[] strs = selectNums.split(",");
				for(int j=0;j<strs.length;j++) {
					if(coldHotMap.containsKey(strs[j])) {
						selectNumsResult = selectNumsResult + strs[j]+",";
					} 
				}
			}
			
			forcast.setCareNumsResult(careNumsResult);
			
			String tmp = "";
			if(StringUtils.hasLength(killNumsResult_right)) {
				tmp = tmp+"杀对："+killNumsResult_right;
			}
			if(StringUtils.hasLength(killNumsResult_error)) {
				tmp = tmp+"  杀错："+killNumsResult_error;
			}
			forcast.setKillNumsResult(tmp);
			
			forcast.setSelectNumsResult(selectNumsResult);
			
			forcast.setVerified(true);
			
			// 修改保存
			getBaseDao().update(forcast);
		}
		
	}
	
	/**
	 * 根据双色球开始期和结束期写入红球冷热数预测值
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void writeSsqChForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum) {
		
		// 1.删除开始期和结束期范围内的预测数据
		getBaseDao().deleteByHql(
				"delete from SsqMulColdHotForcast " 
						+ " a where a.fromSsqIndex>=? and a.toSsqIndex<=? and a.spaceNum = ? and a.forcastSpaceNum = ?",
				new Object[] { fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum });

		// 2.取出双色球多期统计信息记录集合
		String hql = "from SsqMulColdHotStats a where a.fromSsqIndex>=? and a.toSsqIndex<=? and a.spaceNum = ? order by a.fromSsqIndex";
		List<BaseEntity> records = getBaseDao().find(hql, new Integer[]{fromSsqIndex, toSsqIndex, spaceNum });
		
		List<BaseEntity> forcastList = new ArrayList<BaseEntity>();
		
		for(int i=0;i<records.size()-forcastSpaceNum+1;i++) {
			int count=0;
			
			List<MulStatsCompute> tmp = new ArrayList<MulStatsCompute>();
			
			for(int j=i;count<forcastSpaceNum;j++,count++) {
				tmp.add((MulStatsCompute) records.get(j));
			}
			
			SsqMulColdHotForcast forcast= buildColdHotForcast(tmp,forcastSpaceNum);
			forcastList.add(forcast);
			
		}
		
		// 批量保存预测数据
		getBaseDao().batchAddBaseEntityes(forcastList);
		
	}
	
	/**
	 * 根据双色球多期冷热数统计信息写入到目前期数为止，不存在的多期冷热数预测信息
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	@Override
	public void writeNotExistSsqChForcast(int spaceNum, int forcastSpaceNum) {
		
		// 获取formSsqIndex，即要写入的统计表信息的开始期
		String hql = "select max(toSsqIndex) from SsqMulColdHotForcast where spaceNum = ? and forcastSpaceNum = ?";
		
		hql = "select fromSsqIndex from SsqMulColdHotForcast where toSsqIndex = ("+hql+") and spaceNum = ? and forcastSpaceNum = ?";
		
		hql = " from SsqRecord  where ssqIndex = ("+hql+") ";
		
		Integer[] params = new Integer[] { spaceNum, forcastSpaceNum, spaceNum, forcastSpaceNum };
		List<BaseEntity> records = getBaseDao().find(hql, params);
		
		int fromSsqIndex = ((SsqRecord)records.get(0)).getNextSsqIndex();
		
		hql = "select max(toSsqIndex) from SsqMulColdHotStats where spaceNum = ?";
		params = new Integer[] { spaceNum};
		int toSsqIndex = getBaseDao().getFunctionIntValue(hql, params);
		
		writeSsqChForcast(fromSsqIndex, toSsqIndex, spaceNum, forcastSpaceNum);
	}
	
	/* 以上接口实现区*/
	
	@SuppressWarnings("unchecked")
	public SsqMulTailForcast buildTailForcast(List<MulStatsCompute> records,int forcastSpaceNum) {
		
		if (!(records.get(0) instanceof SsqMulTailStats)) {
			return null;
		}
		
		SsqMulTailForcast forcast = new SsqMulTailForcast();

		int size = records.size();

		forcast.setFromSsqIndex(((SsqMulTailStats) records.get(0)).getFromSsqIndex());

		int toSsqIndex = ((SsqMulTailStats) records.get(size - 1)).getToSsqIndex();
		
		forcast.setToSsqIndex(toSsqIndex);
		
		Object obj = getBaseDao().find("select nextSsqIndex from SsqRecord a where a.ssqIndex = ? ", new Integer[]{toSsqIndex});
		
		forcast.setTargetSsqIndex(((List<Integer>)obj).get(0));

		forcast.setSpaceNum(((SsqMulTailStats) records.get(0)).getSpaceNum());
		
		forcast.setForcastSpaceNum(forcastSpaceNum);

		// 获得尾数最大个数和最小个数
		for (int j = 0; j < size; j++) {
			SsqMulTailStats stats = ((SsqMulTailStats)records.get(j));
			
			forcast.setT0MaxCount(stats.getT0count()>forcast.getT0MaxCount()?stats.getT0count():forcast.getT0MaxCount());
			forcast.setT0MinCount(stats.getT0count()<forcast.getT0MinCount()?stats.getT0count():forcast.getT0MinCount());
			
			forcast.setT1MaxCount(stats.getT1count()>forcast.getT1MaxCount()?stats.getT1count():forcast.getT1MaxCount());
			forcast.setT1MinCount(stats.getT1count()<forcast.getT1MinCount()?stats.getT1count():forcast.getT1MinCount());
			
			forcast.setT2MaxCount(stats.getT2count()>forcast.getT2MaxCount()?stats.getT2count():forcast.getT2MaxCount());
			forcast.setT2MinCount(stats.getT2count()<forcast.getT2MinCount()?stats.getT2count():forcast.getT2MinCount());
			
			forcast.setT3MaxCount(stats.getT3count()>forcast.getT3MaxCount()?stats.getT3count():forcast.getT3MaxCount());
			forcast.setT3MinCount(stats.getT3count()<forcast.getT3MinCount()?stats.getT3count():forcast.getT3MinCount());
			
			forcast.setT4MaxCount(stats.getT4count()>forcast.getT4MaxCount()?stats.getT4count():forcast.getT4MaxCount());
			forcast.setT4MinCount(stats.getT4count()<forcast.getT4MinCount()?stats.getT4count():forcast.getT4MinCount());
			
			forcast.setT5MaxCount(stats.getT5count()>forcast.getT5MaxCount()?stats.getT5count():forcast.getT5MaxCount());
			forcast.setT5MinCount(stats.getT5count()<forcast.getT5MinCount()?stats.getT5count():forcast.getT5MinCount());
			
			forcast.setT6MaxCount(stats.getT6count()>forcast.getT6MaxCount()?stats.getT6count():forcast.getT6MaxCount());
			forcast.setT6MinCount(stats.getT6count()<forcast.getT6MinCount()?stats.getT6count():forcast.getT6MinCount());
			
			forcast.setT7MaxCount(stats.getT7count()>forcast.getT7MaxCount()?stats.getT7count():forcast.getT7MaxCount());
			forcast.setT7MinCount(stats.getT7count()<forcast.getT7MinCount()?stats.getT7count():forcast.getT7MinCount());
			
			forcast.setT8MaxCount(stats.getT8count()>forcast.getT8MaxCount()?stats.getT8count():forcast.getT8MaxCount());
			forcast.setT8MinCount(stats.getT8count()<forcast.getT8MinCount()?stats.getT8count():forcast.getT8MinCount());
			
			forcast.setT9MaxCount(stats.getT9count()>forcast.getT9MaxCount()?stats.getT9count():forcast.getT9MaxCount());
			forcast.setT9MinCount(stats.getT9count()<forcast.getT9MinCount()?stats.getT9count():forcast.getT9MinCount());
			
		}
		
		SsqMulTailStats lastMulTailStats = ((SsqMulTailStats)records.get(size-1));
		
		// 处理关注的尾数
		dealCareTailNums(forcast, lastMulTailStats);
		
		// 处理可选尾数、定选尾数和杀尾数
		dealKillAndSelectTailNums(forcast, lastMulTailStats);
		
		return forcast;
	}
	
	@SuppressWarnings("unchecked")
	public SsqMulColdHotForcast buildColdHotForcast(List<MulStatsCompute> records,int forcastSpaceNum) {
		
		if (!(records.get(0) instanceof SsqMulColdHotStats)) {
			return null;
		}
		
		SsqMulColdHotForcast forcast = new SsqMulColdHotForcast();

		int size = records.size();

		forcast.setFromSsqIndex(((SsqMulColdHotStats) records.get(0)).getFromSsqIndex());

		int toSsqIndex = ((SsqMulColdHotStats) records.get(size - 1)).getToSsqIndex();
		
		forcast.setToSsqIndex(toSsqIndex);
		
		Object obj = getBaseDao().find("select nextSsqIndex from SsqRecord a where a.ssqIndex = ? ", new Integer[]{toSsqIndex});
		
		forcast.setTargetSsqIndex(((List<Integer>)obj).get(0));

		forcast.setSpaceNum(((SsqMulColdHotStats) records.get(0)).getSpaceNum());
		
		forcast.setForcastSpaceNum(forcastSpaceNum);

		// 获得尾数最大个数和最小个数
		for (int j = 0; j < size; j++) {
			SsqMulColdHotStats stats = ((SsqMulColdHotStats)records.get(j));
			
			for (int i = 1; i <= 33; i++) {

				try {
					int count = ((Integer) stats.getClass()
							.getMethod("getCh" + i + "count").invoke(stats))
							.intValue();
					int max = ((Integer) forcast.getClass()
							.getMethod("getCh" + i + "MaxCount")
							.invoke(forcast)).intValue();
					int min = ((Integer) forcast.getClass()
							.getMethod("getCh" + i + "MinCount")
							.invoke(forcast)).intValue();

					forcast.getClass()
							.getMethod("setCh" + i + "MaxCount", int.class)
							.invoke(forcast, count > max ? count : max);
					forcast.getClass()
							.getMethod("setCh" + i + "MinCount", int.class)
							.invoke(forcast, count < min ? count : min);

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}

			}
			
//			forcast.setCh1MaxCount(stats.getCh1count()>forcast.getCh1MaxCount()?stats.getCh1count():forcast.getCh1MaxCount());
//			forcast.setCh1MinCount(stats.getCh1count()<forcast.getCh1MinCount()?stats.getCh1count():forcast.getCh1MinCount());
	
		}
		
		// 获得关注的冷热数
		SsqMulColdHotStats lastMulCHStats = ((SsqMulColdHotStats)records.get(size-1));
		
		dealCareColdHotNums(forcast, lastMulCHStats);
		
		// 处理可选尾数、定选尾数和杀尾数
		dealKillAndSelectColdHotNum(forcast, lastMulCHStats);
		
		return forcast;
	}

	private void dealCareTailNums(SsqMulTailForcast forcast, SsqMulTailStats lastMulTailStats) {
		
		String careNums = "";
		
		if(lastMulTailStats.getT0count()==forcast.getT0MaxCount() || lastMulTailStats.getT0count()==forcast.getT0MinCount()) {
			careNums = careNums+"0,";
		}
		
		if(lastMulTailStats.getT1count()==forcast.getT1MaxCount() || lastMulTailStats.getT1count()==forcast.getT1MinCount()) {
			careNums = careNums+"1,";
		}
		
		if(lastMulTailStats.getT2count()==forcast.getT2MaxCount() || lastMulTailStats.getT2count()==forcast.getT2MinCount()) {
			careNums = careNums+"2,";
		}
		
		if(lastMulTailStats.getT3count()==forcast.getT3MaxCount() || lastMulTailStats.getT3count()==forcast.getT3MinCount()) {
			careNums = careNums+"3,";
		}
		
		if(lastMulTailStats.getT4count()==forcast.getT4MaxCount() || lastMulTailStats.getT4count()==forcast.getT4MinCount()) {
			careNums = careNums+"4,";
		}
		
		if(lastMulTailStats.getT5count()==forcast.getT5MaxCount() || lastMulTailStats.getT5count()==forcast.getT5MinCount()) {
			careNums = careNums+"5,";
		}
		
		if(lastMulTailStats.getT6count()==forcast.getT6MaxCount() || lastMulTailStats.getT6count()==forcast.getT6MinCount()) {
			careNums = careNums+"6,";
		}
		
		if(lastMulTailStats.getT7count()==forcast.getT7MaxCount() || lastMulTailStats.getT7count()==forcast.getT7MinCount()) {
			careNums = careNums+"7,";
		}
		
		if(lastMulTailStats.getT8count()==forcast.getT8MaxCount() || lastMulTailStats.getT8count()==forcast.getT8MinCount()) {
			careNums = careNums+"8,";
		}
		
		if(lastMulTailStats.getT9count()==forcast.getT9MaxCount() || lastMulTailStats.getT9count()==forcast.getT9MinCount()) {
			careNums = careNums+"9,";
		}
		forcast.setCareNums(careNums);
	}

	private void dealKillAndSelectTailNums(SsqMulTailForcast forcast,
			SsqMulTailStats lastMulTailStats) {
		
		int beginSsqIndex = lastMulTailStats.getFromSsqIndex();
		String hql = " from SsqTailStats a where a.ssqIndex= ?"; // 获取一期尾数统计表
		List<BaseEntity> tailStatses = getBaseDao().find(hql,
				new Integer[] { beginSsqIndex });
		SsqTailStats tail = (SsqTailStats) tailStatses.get(0);
		
		// 获得杀的尾数
		String killNums = "";
		
		String possibleSelectedNums = "";   // 可选的尾数
		String possibleSelectedNumsCount = "";   // 可选的尾数数量（最多几个）
		
		String certainSelectedNums = "";   // 定选的尾数
		String certainSelectedNumsCount = "";   // 定选的尾数数量（最少几个）
		
		// 0
		if (lastMulTailStats.getT0count() == forcast.getT0MaxCount()) {
			if(tail.getT0count()==0) {    // 杀
				killNums = killNums + "0,";
			} else if(tail.getT0count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "0,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数0：1个,";
				
			} else if(tail.getT0count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "0,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数0：2个,";
				
			} else if(tail.getT0count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "0,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数0：3个,";
			} 
			
		} else if (lastMulTailStats.getT0count() == forcast.getT0MinCount()) {
			if(tail.getT0count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "0,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数0：3个,";
			} else if(tail.getT0count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "0,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数0：1个,";
				
			} else if(tail.getT0count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "0,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数0：2个,";
				
			} else if(tail.getT0count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "0,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数0：3个,";
			} 
		}
		
		// 1
		if (lastMulTailStats.getT1count() == forcast.getT1MaxCount()) {
			if(tail.getT1count()==0) {    // 杀
				killNums = killNums + "1,";
			} else if(tail.getT1count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "1,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数1：1个,";
			} else if(tail.getT1count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "1,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数1：2个,";
			} else if(tail.getT1count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "1,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数1：3个,";
			} 
			
		} else if (lastMulTailStats.getT1count() == forcast.getT1MinCount()) {
			if(tail.getT1count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "1,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数1：3个,";
			} else if(tail.getT1count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "1,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数1：1个,";
			} else if(tail.getT1count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "1,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数1：2个,";
			} else if(tail.getT1count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "1,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数1：3个,";
			} 
		}
		
		// 2
		if (lastMulTailStats.getT2count() == forcast.getT2MaxCount()) {
			if(tail.getT2count()==0) {    // 杀
				killNums = killNums + "2,";
			} else if(tail.getT2count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "2,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数2：1个,";
			} else if(tail.getT2count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "2,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数2：2个,";
			} else if(tail.getT2count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "2,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数2：3个,";
			} 
			
		} else if (lastMulTailStats.getT2count() == forcast.getT2MinCount()) {
			if(tail.getT2count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "2,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数2：3个,";
			} else if(tail.getT2count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "2,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数2：1个,";
			} else if(tail.getT2count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "2,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数2：2个,";
			} else if(tail.getT2count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "2,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数2：3个,";
			} 
		}
		
		// 3
		if (lastMulTailStats.getT3count() == forcast.getT3MaxCount()) {
			if(tail.getT3count()==0) {    // 杀
				killNums = killNums + "3,";
			} else if(tail.getT3count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "3,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数3：1个,";
			} else if(tail.getT3count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "3,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数3：2个,";
			} else if(tail.getT3count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "3,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数3：3个,";
			} 
			
		} else if (lastMulTailStats.getT3count() == forcast.getT3MinCount()) {
			if(tail.getT3count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "3,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数3：3个,";
			} else if(tail.getT3count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "3,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数3：1个,";
			} else if(tail.getT3count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "3,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数3：2个,";
			} else if(tail.getT3count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "3,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数3：3个,";
			} 
		}
		
		// 4
		if (lastMulTailStats.getT4count() == forcast.getT4MaxCount()) {
			if(tail.getT4count()==0) {    // 杀
				killNums = killNums + "4,";
			} else if(tail.getT4count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "4,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数4：1个,";
			} else if(tail.getT4count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "4,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数4：2个,";
			} else if(tail.getT4count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "4,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数4：3个,";
			} 
			
		} else if (lastMulTailStats.getT4count() == forcast.getT4MinCount()) {
			if(tail.getT4count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "4,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数4：3个,";
			} else if(tail.getT4count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "4,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数4：1个,";
			} else if(tail.getT4count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "4,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数4：2个,";
			} else if(tail.getT4count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "4,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数4：3个,";
			} 
		}
		
		// 5
		if (lastMulTailStats.getT5count() == forcast.getT5MaxCount()) {
			if(tail.getT5count()==0) {    // 杀
				killNums = killNums + "5,";
			} else if(tail.getT5count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "5,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数5：1个,";
			} else if(tail.getT5count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "5,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数5：2个,";
			} else if(tail.getT5count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "5,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数5：3个,";
			} 
			
		} else if (lastMulTailStats.getT5count() == forcast.getT5MinCount()) {
			if(tail.getT5count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "5,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数5：3个,";
			} else if(tail.getT5count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "5,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数5：1个,";
			} else if(tail.getT5count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "5,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数5：2个,";
			} else if(tail.getT5count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "5,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数5：3个,";
			} 
		}
		
		// 6
		if (lastMulTailStats.getT6count() == forcast.getT6MaxCount()) {
			if(tail.getT6count()==0) {    // 杀
				killNums = killNums + "6,";
			} else if(tail.getT6count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "6,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数6：1个,";
			} else if(tail.getT6count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "6,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数6：2个,";
			} else if(tail.getT6count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "6,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数6：3个,";
			} 
			
		} else if (lastMulTailStats.getT6count() == forcast.getT6MinCount()) {
			if(tail.getT6count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "6,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数6：3个,";
			} else if(tail.getT6count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "6,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数6：1个,";
			} else if(tail.getT6count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "6,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数6：2个,";
			} else if(tail.getT6count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "6,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数6：3个,";
			} 
		}
		
		// 7
		if (lastMulTailStats.getT7count() == forcast.getT7MaxCount()) {
			if(tail.getT7count()==0) {    // 杀
				killNums = killNums + "7,";
			} else if(tail.getT7count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "7,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数7：1个,";
			} else if(tail.getT7count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "7,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数7：2个,";
			} else if(tail.getT7count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "7,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数7：3个,";
			} 
			
		} else if (lastMulTailStats.getT7count() == forcast.getT7MinCount()) {
			if(tail.getT7count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "7,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数7：3个,";
			} else if(tail.getT7count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "7,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数7：1个,";
			} else if(tail.getT7count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "7,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数7：2个,";
			} else if(tail.getT7count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "7,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数7：3个,";
			} 
		}
		
		// 8
		if (lastMulTailStats.getT8count() == forcast.getT8MaxCount()) {
			if(tail.getT8count()==0) {    // 杀
				killNums = killNums + "8,";
			} else if(tail.getT8count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "8,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数8：1个,";
			} else if(tail.getT8count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "8,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数8：2个,";
			} else if(tail.getT8count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "8,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数8：3个,";
			} 
			
		} else if (lastMulTailStats.getT8count() == forcast.getT8MinCount()) {
			if(tail.getT8count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "8,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数8：3个,";
			} else if(tail.getT8count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "8,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数8：1个,";
			} else if(tail.getT8count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "8,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数8：2个,";
			} else if(tail.getT8count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "8,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数8：3个,";
			} 
		}
		
		// 9
		if (lastMulTailStats.getT9count() == forcast.getT9MaxCount()) {
			if(tail.getT9count()==0) {    // 杀
				killNums = killNums + "9,";
			} else if(tail.getT9count()==1) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "9,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数9：1个,";
			} else if(tail.getT9count()==2) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "9,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数9：2个,";
			} else if(tail.getT9count()==3) {   // 可选
				possibleSelectedNums = possibleSelectedNums + "9,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数9：3个,";
			} 
			
		} else if (lastMulTailStats.getT9count() == forcast.getT9MinCount()) {
			if(tail.getT9count()==0) {    // 可选
				possibleSelectedNums = possibleSelectedNums + "9,";
				possibleSelectedNumsCount = possibleSelectedNumsCount + "尾数9：3个,";
			} else if(tail.getT9count()==1) {   // 定选
				certainSelectedNums = certainSelectedNums + "9,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数9：1个,";
			} else if(tail.getT9count()==2) {   // 定选
				certainSelectedNums = certainSelectedNums + "9,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数9：2个,";
			} else if(tail.getT9count()==3) {   // 定选
				certainSelectedNums = certainSelectedNums + "9,";
				certainSelectedNumsCount = certainSelectedNumsCount + "尾数9：3个,";
			} 
		}
		
		forcast.setKillNums(killNums);
		forcast.setCertainSelectedNums(certainSelectedNums);
		forcast.setCertainSelectedNumsCount(certainSelectedNumsCount);
		forcast.setPossibleSelectedNums(possibleSelectedNums);
		forcast.setPossibleSelectedNumsCount(possibleSelectedNumsCount);
		
	}

	private void dealCareColdHotNums(SsqMulColdHotForcast forcast, SsqMulColdHotStats lastMulCHStats) {
		String careNums = "";
		
		for(int i=1;i<=33;i++) {
			int count = 0;
			int maxCount = 0;
			int minCount = 0;
			try {
				count = ((Integer)lastMulCHStats.getClass().getMethod("getCh"+i+"count").invoke(lastMulCHStats)).intValue();
				maxCount = ((Integer)forcast.getClass().getMethod("getCh"+i+"MaxCount").invoke(forcast)).intValue();
				minCount = ((Integer)forcast.getClass().getMethod("getCh"+i+"MinCount").invoke(forcast)).intValue();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			if( count == maxCount || count == minCount) {
				careNums = careNums+i+",";
			}
		}
		forcast.setCareNums(careNums);
	}
	
	private void dealKillAndSelectColdHotNum(SsqMulColdHotForcast forcast, SsqMulColdHotStats lastMulCHStats) {
		
		int beginSsqIndex = lastMulCHStats.getFromSsqIndex();
		String hql = " from SsqColdHotStats a where a.ssqIndex= ?"; // 获取一期冷热数统计表
		List<BaseEntity> coldHotStatses = getBaseDao().find(hql,
				new Integer[] { beginSsqIndex });
		SsqColdHotStats ch = (SsqColdHotStats) coldHotStatses.get(0);
		
		// 获得杀的冷热数
		String killNums = "";
		
		String selectNums = "";
		
		for(int i=1;i<=33;i++) {
			int count = 0;
			int maxCount = 0;
			int minCount = 0;
			boolean isHasTheNum = false;
			try {
				count = ((Integer)lastMulCHStats.getClass().getMethod("getCh"+i+"count").invoke(lastMulCHStats)).intValue();
				maxCount = ((Integer)forcast.getClass().getMethod("getCh"+i+"MaxCount").invoke(forcast)).intValue();
				minCount = ((Integer)forcast.getClass().getMethod("getCh"+i+"MinCount").invoke(forcast)).intValue();
				isHasTheNum = ((Boolean)ch.getClass().getMethod("isHasCh"+i).invoke(ch)).booleanValue();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			if( count == maxCount) {
				if(!isHasTheNum) {
					killNums = killNums + i+ ",";
				}
			} else if( count == minCount) {
				if(isHasTheNum) {
					selectNums = selectNums + i +",";
				}
			}
			 
		}
		
		forcast.setKillNums(killNums);
		forcast.setSelectNums(selectNums);
	}

	public SsqRecordDao getSsqRecordDao() {
		return ssqRecordDao;
	}


	public void setSsqRecordDao(SsqRecordDao ssqRecordDao) {
		this.ssqRecordDao = ssqRecordDao;
	}

}
