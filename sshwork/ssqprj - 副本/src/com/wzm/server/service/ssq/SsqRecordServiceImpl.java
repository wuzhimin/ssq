package com.wzm.server.service.ssq;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import bsh.Interpreter;

import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.base.BaseEntity;
import com.wzm.server.entity.ssq.MulStatsCompute;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.server.entity.ssq.SsqStatsType;
import com.wzm.server.entity.ssq.StatsCompute;
import com.wzm.server.service.base.BaseServiceImpl;
import com.wzm.util.FileUtil;
import com.wzm.util.FormulaUtil;

/**
 * UserService实现类
 * 
 * @author wzm
 * 
 */
public class SsqRecordServiceImpl extends BaseServiceImpl implements SsqService {

	// 用户目录
	public static final String USERHOME = System.getProperties().getProperty(
			"user.home")
			+ "/ddd/";

	// 双色球开奖记录文件
	public static final String HISTORY_FILE = "caipiao-history.txt";

	public SsqRecordServiceImpl() {

	}

	public SsqRecordDao getSsqRecordDao() {
		return ((SsqRecordDao) getBaseDao());
	}

	@Override
	public void writeAllSsqRecord(String ssqRecordFile) {
		try {
			
			int maxSsqIndex = getSsqRecordDao().getFunctionIntValue(" select max(ssqIndex) from SsqRecord");

			// 从开奖记录文件中获取开奖记录集合
			List<String> strRecords = FileUtil.readLineFile(USERHOME
					+ HISTORY_FILE);
			List<BaseEntity> records = new ArrayList<BaseEntity>();
			for (String str : strRecords) {

				SsqRecord record = buildASSqRecordFromARecordStr(str);
				if(record.getSsqIndex()>maxSsqIndex) {
					records.add(record);
				}
			}
			
			// 删除数据库所有开奖记录
//			getSsqRecordDao().deleteByHql("delete from SsqRecord");

			// 保存所有开奖记录
			getSsqRecordDao().batchAddBaseEntityes(records);

		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}

	}

	/**
	 * 从一条开奖记录str组装成一个SSqRecord对象
	 * 
	 * @param str
	 * @return
	 */
	private SsqRecord buildASSqRecordFromARecordStr(String str) {
		String[] strs = str.split(" ");

		// 期数
		int ssqIndex = Integer.parseInt(strs[0]);

		// 红球
		int r1 = Integer.parseInt(strs[1]);
		int r2 = Integer.parseInt(strs[2]);
		int r3 = Integer.parseInt(strs[3]);
		int r4 = Integer.parseInt(strs[4]);
		int r5 = Integer.parseInt(strs[5]);
		int r6 = Integer.parseInt(strs[6]);

		// 篮球
		int b1 = Integer.parseInt(strs[7]);

		SsqRecord record = new SsqRecord();

		record.setSsqIndex(ssqIndex);

		record.setR1(r1);
		record.setR2(r2);
		record.setR3(r3);
		record.setR4(r4);
		record.setR5(r5);
		record.setR6(r6);

		record.setB1(b1);
		
		initNextSsqIndex(record);
		
		return record;
	}
	
	private void initNextSsqIndex(SsqRecord record) {
		if(record.getSsqIndex()==2004122) {
			record.setNextSsqIndex(2005001);
		} else if(record.getSsqIndex()==2005153) {
			record.setNextSsqIndex(2006001);
		} else if(record.getSsqIndex()==2006154) {
			record.setNextSsqIndex(2007001);
		} else if(record.getSsqIndex()==2007153) {
			record.setNextSsqIndex(2008001);
		} else if(record.getSsqIndex()==2008154) {
			record.setNextSsqIndex(2009001);
		} else if(record.getSsqIndex()==2009154) {
			record.setNextSsqIndex(2010001);
		} else if(record.getSsqIndex()==2010153) {
			record.setNextSsqIndex(2011001);
		} else if(record.getSsqIndex()==2011153) {
			record.setNextSsqIndex(2012001);
		} else if(record.getSsqIndex()==2012154) {
			record.setNextSsqIndex(2013001);
		} else {
			record.setNextSsqIndex(record.getSsqIndex()+1);
		}
		
	}

	@Override
	public Serializable addASsqRecord(String recordStr) {
		SsqRecord record = buildASSqRecordFromARecordStr(recordStr);
		return getBaseDao().add(record);
	}

	@Override
	public void writeAllSsqBaseStats() {
		writeAllSsqStats("baseStats");
	}

	@Override
	public void writeSsqBaseStats(int fromSsqIndex, int toSsqIndex) {
		writeSsqStats("baseStats", fromSsqIndex, toSsqIndex);
	}

	@Override
	public void writeAllSsqBlueStats() {
		writeAllSsqStats("blueStats");

	}

	@Override
	public void writeSsqBlueStats(int fromSsqIndex, int toSsqIndex) {
		writeSsqStats("blueStats", fromSsqIndex, toSsqIndex);
	}

	/**
	 * 生成所有双色球红球质数统计信息
	 */
	public void writeAllSsqPrimeStats() {
		writeAllSsqStats("primeStats");
	}

	/**
	 * 根据双色球开始期和结束期写入红球质数统计信息
	 * 
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqPrimeStats(int fromSsqIndex, int toSsqIndex) {
		writeSsqStats("primeStats", fromSsqIndex, toSsqIndex);
	}

	/**
	 * 生成所有双色球红球尾数统计信息
	 */
	@Override
	public void writeAllSsqTailStats() {
		writeAllSsqStats("tailStats");
	}

	/**
	 * 根据双色球开始期和结束期写入红球尾数统计信息
	 * 
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	@Override
	public void writeSsqTailStats(int fromSsqIndex, int toSsqIndex) {
		writeSsqStats("tailStats", fromSsqIndex, toSsqIndex);
	}
	
	/**
	 * 生成所有双色球红球冷热数统计信息
	 */
	public void writeAllSsqColdHotStats() {
		writeAllSsqStats("coldHotStats");
	}
	
	/**
	 * 根据双色球开始期和结束期写入红球冷热数统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqColdHotStats(int fromSsqIndex, int toSsqIndex) {
		writeSsqStats("coldHotStats", fromSsqIndex, toSsqIndex);
	}

	/**
	 * 生成所有双色球多期红球尾数统计信息
	 */
	@Override
	public void writeAllSsqMulTailStats(int spaceNum) {
		writeAllSsqMulStats("mulTailStats", spaceNum);
	}

	/**
	 * 根据双色球开始期和结束期写入红球质数统计信息
	 * 
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	@Override
	public void writeSsqMulTailStats(int fromSsqIndex, int toSsqIndex,
			int spaceNum) {
		writeSsqMulStats("mulTailStats", fromSsqIndex, toSsqIndex, spaceNum);
	}
	
	/**
	 * 生成所有双色球多期红球冷热数统计信息
	 */
	public void writeAllSsqMulColdHotStats(int spaceNum) {
		writeAllSsqMulStats("mulColdHotStats", spaceNum);
	}
	
	
	/**
	 * 根据双色球开始期和结束期写入红球冷热数多期统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqMulColdHotStats(int fromSsqIndex, int toSsqIndex, int spaceNum) {
		writeSsqMulStats("mulColdHotStats", fromSsqIndex, toSsqIndex, spaceNum);
	}
	
	/**
	 * 生成所有双色球多期红球质数统计信息
	 */
	public void writeAllSsqMulPrimeStats(int spaceNum) {
		writeAllSsqMulStats("mulPrimeStats", spaceNum);
	}
	
	/**
	 * 根据双色球开始期和结束期写入红球质数多期统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqMulPrimeStats(int fromSsqIndex, int toSsqIndex, int spaceNum) {
		writeSsqMulStats("mulPrimeStats", fromSsqIndex, toSsqIndex, spaceNum);
	}
	
	
	/**
	 * 生成所有双色球多期蓝球统计信息
	 */
	public void writeAllSsqMulBlueStats(int spaceNum) {
		writeAllSsqMulStats("mulBlueStats", spaceNum);
	}
	
	/**
	 * 根据双色球开始期和结束期写入蓝球多期统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 */
	public void writeSsqMulBlueStats(int fromSsqIndex, int toSsqIndex, int spaceNum) {
		writeSsqMulStats("mulBlueStats", fromSsqIndex, toSsqIndex, spaceNum);
	}
	
	
	/**
	 * 生成所有双色球红球ac值和散度统计信息
	 */
	public void writeAllSsqAcAndSanduStats() {
		writeAllSsqStats("acAndSanduStats");
	}
	
	/**
	 * 根据双色球开始期和结束期写入红球ac值和散度统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqAcAndSanduStats(int fromSsqIndex, int toSsqIndex) {
		writeSsqStats("acAndSanduStats", fromSsqIndex, toSsqIndex);
	}
	
	@Override
	public void writeNotExistSsqStatsByNow(String typeName) {
		String[] statsEntityClassNames = getStatsEntityClassName(typeName);
		if (!StringUtils.hasLength(statsEntityClassNames[0])) {
			return;
		}
		
		// 获取formSsqIndex，即要写入的统计表信息的开始期
		String hql = "select max(ssqIndex) from " + statsEntityClassNames[0];
		int maxStatsSsqIndex = getBaseDao().getFunctionIntValue(hql);
		
		hql = "from SsqRecord where ssqIndex = ?";
		SsqRecord record = getSsqRecordDao().findSsqRecordBySsqIndex(maxStatsSsqIndex);
		int fromSsqIndex = record.getNextSsqIndex();
		
		hql = "select max(ssqIndex) from SsqRecord";
		int toSsqIndex = getBaseDao().getFunctionIntValue(hql);
		
		if(fromSsqIndex>toSsqIndex) {
			return; 
		}
		
		writeSsqStats(typeName, fromSsqIndex, toSsqIndex);
		
	}
	
	@Override
	public void writeNotExistSsqMulStatsByNow(String typeName, int spaceNum) {

		String[] statsEntityClassNames = getStatsEntityClassName(typeName);
		if (!StringUtils.hasLength(statsEntityClassNames[0])
				|| !StringUtils.hasLength(statsEntityClassNames[1])) {
			return;
		}
		
		// 获取formSsqIndex，即要写入的统计表信息的开始期
		String hql = "select max(toSsqIndex) from " + statsEntityClassNames[0]+" where spaceNum= ?";
		
		hql = "select fromSsqIndex from "+statsEntityClassNames[0]+" where toSsqIndex = ("+hql+") and spaceNum = ?";
		
		hql = " from SsqRecord  where ssqIndex = ("+hql+") ";
		
		List<BaseEntity> records = getBaseDao().find(hql, new Integer[]{spaceNum,spaceNum});
		
		int fromSsqIndex = ((SsqRecord)records.get(0)).getNextSsqIndex();
		
		hql = "select max(ssqIndex) from "+statsEntityClassNames[1];
		int toSsqIndex = getBaseDao().getFunctionIntValue(hql);
		
		if(fromSsqIndex+spaceNum-1>toSsqIndex) {
			return; 
		}
		
		writeSsqMulStats(typeName, fromSsqIndex, toSsqIndex, spaceNum);
	}
	
	/* 以上为接口实现区 */
	
	
	

	public void writeSsqMulStats(String typeName, int fromSsqIndex,
			int toSsqIndex, int spaceNum) {

		String[] statsEntityClassNames = getStatsEntityClassName(typeName);
		if (!StringUtils.hasLength(statsEntityClassNames[0])
				|| !StringUtils.hasLength(statsEntityClassNames[1])) {
			return;
		}

		// 1.删除部分的某种类型的多期统计表
		getSsqRecordDao()
				.deleteByHql(
						"delete from "
								+ statsEntityClassNames[0]
								+ " a where a.spaceNum = ? and a.fromSsqIndex>=? and a.toSsqIndex<=?",
						new Integer[] { spaceNum, fromSsqIndex, toSsqIndex });

		// 2.取出所有双色球某种单期统计信息
		List<BaseEntity> statses = getBaseDao().find(
				"from " + statsEntityClassNames[1]
						+ " a where a.ssqIndex>=? and a.ssqIndex<=?",
				new Integer[] { fromSsqIndex, toSsqIndex });

		List<StatsCompute> statsRecord = new ArrayList<StatsCompute>();
		for (BaseEntity a : statses) {
			statsRecord.add((StatsCompute) a);
		}

		writeSsqMulStatsFromSSqStatses(statsRecord, statsEntityClassNames[0],
				spaceNum);
	}

	private void writeAllSsqMulStats(String typeName, int spaceNum) {

		String[] statsEntityClassNames = getStatsEntityClassName(typeName);
		if (!StringUtils.hasLength(statsEntityClassNames[0])
				|| !StringUtils.hasLength(statsEntityClassNames[1])) {
			return;
		}

		// 1.删除所有的某种类型的多期统计表
		getSsqRecordDao()
				.deleteByHql(
						"delete from " + statsEntityClassNames[0]
								+ " a where a.spaceNum = ?",
						new Integer[] { spaceNum });

		// 2.取出所有双色球某种单期统计信息
		List<BaseEntity> statses = getBaseDao().find(
				"from " + statsEntityClassNames[1]);

		List<StatsCompute> statsRecord = new ArrayList<StatsCompute>();
		for (BaseEntity a : statses) {
			statsRecord.add((StatsCompute) a);
		}

		writeSsqMulStatsFromSSqStatses(statsRecord, statsEntityClassNames[0],
				spaceNum);
	}

	/**
	 * 写入双色球各种统计表（间隔期数为1）
	 * 
	 * @param typeName
	 */
	private void writeAllSsqStats(String typeName) {

		// 获取统计信息实体类名称
		String[] statsEntityClassNames = getStatsEntityClassName(typeName);
		if (!StringUtils.hasLength(statsEntityClassNames[0])) {
			return;
		}

		// 1.删除所有的某种类型的统计表
		getSsqRecordDao()
				.deleteByHql("delete from " + statsEntityClassNames[0]);

		// 2.取出所有双色球开奖记录
		List<SsqRecord> records = getSsqRecordDao().findSsqRecordsByHql(
				"from SsqRecord");

		writeSsqStatsFromSSqRecord(records, statsEntityClassNames[0]);
	}

	private String[] getStatsEntityClassName(String typeName) {
		// 根据类型获取统计类型信息
		List<BaseEntity> types = getBaseDao().find(
				" from SsqStatsType a where a.name = ?",
				new String[] { typeName });
		if (types.size() != 1) {
			return null;
		}

		// 统计实体名称
		String statsEntityClassName = ((SsqStatsType) types.get(0))
				.getStatsEntityClassName();

		// 依赖统计实体名称
		String dependedSstatsEntityClassName = ((SsqStatsType) types.get(0))
				.getDependedStatsEntityClassName();

		return new String[] { statsEntityClassName,
				dependedSstatsEntityClassName };
	}

	private void writeSsqStats(String typeName, int fromSsqIndex, int toSsqIndex) {
		String[] statsEntityClassNames = getStatsEntityClassName(typeName);
		if (!StringUtils.hasLength(statsEntityClassNames[0])) {
			return;
		}

		// 1.删除开始期和结束期范围内的统计表
		getSsqRecordDao().deleteByHql(
				"delete from " + statsEntityClassNames[0]
						+ " a where a.ssqIndex>=? and a.ssqIndex<=?",
				new Object[] { fromSsqIndex, toSsqIndex });

		// 2.取出双色球开奖记录集合
		List<SsqRecord> records = getSsqRecordDao().findSsqRecordsByIndexScope(
				fromSsqIndex, toSsqIndex);

		// 3.写入基本统计信息
		writeSsqStatsFromSSqRecord(records, statsEntityClassNames[0]);
	}

	/**
	 * 根据开奖记录及统计实体类名称，生成统计表
	 * 
	 * @param records
	 * @param statsEntityClassName
	 */
	@SuppressWarnings("rawtypes")
	private void writeSsqStatsFromSSqRecord(List<SsqRecord> records,
			String statsEntityClassName) {

		List<BaseEntity> baseStatses = new ArrayList<BaseEntity>();

		// 根据开奖记录生成统计信息
		for (SsqRecord record : records) {

			Class cls = null;
			try {
				cls = Class.forName(statsEntityClassName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			StatsCompute stats = null;

			try {
				stats = (StatsCompute) cls.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			// 计算统计值
			stats.buildStats(record);

			baseStatses.add((BaseEntity) stats);
		}

		// 4.保存统计表信息
		getSsqRecordDao().batchAddBaseEntityes(baseStatses);
	}

	/**
	 * 根据对应单期统计信息，生成多期统计信息
	 * 
	 * @param records
	 * @param statsEntityClassName
	 */
	@SuppressWarnings({ "rawtypes" })
	private void writeSsqMulStatsFromSSqStatses(List<StatsCompute> records,
			String statsEntityClassName, int spaceNum) {

		// 计算多期统计总记录数
		int mulCount = records.size() - spaceNum + 1;

		List<BaseEntity> mulStatses = new ArrayList<BaseEntity>();

		for (int i = 0; i < mulCount; i++) {

			List<StatsCompute> tmp = new ArrayList<StatsCompute>();

			for (int j = i, index = 0; index < spaceNum; index++, j++) {
				tmp.add((StatsCompute) records.get(j));
			}

			// 生成一条多期尾数统计信息

			Class cls = null;
			try {
				cls = Class.forName(statsEntityClassName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
			MulStatsCompute stats = null;

			try {
				stats = (MulStatsCompute) cls.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			// 计算统计值
			stats.buildStats(tmp);

			mulStatses.add((BaseEntity) stats);
		}

		// 保存统计表信息
		getSsqRecordDao().batchAddBaseEntityes(mulStatses);
	}

	@Override
	public Map<String, Object[]> testRed(List<String> formulas) {
		Map<String, Object[]> result = new HashMap<String, Object[]>();
		
		Map<String, Interpreter> expMap = new HashMap<String, Interpreter>();
		
		List<SsqRecord> list = getSsqRecordDao().findSsqRecordsByHql(" from SsqRecord where ssqIndex >= 2010001 order by ssqIndex");
		
		Map<String, Integer> totalForcastRightMap = new HashMap<String, Integer>();
		
		
		for(String formula:formulas) {
			Map<String, Integer> forcastRightMap = new HashMap<String, Integer>();
			
			List<String> outs = new ArrayList<String>();
			List<String> rights = new ArrayList<String>();
			
			int index =0;
			for(SsqRecord currentRecord:list) {
				
				SsqRecord nextRecord = getSsqRecordDao().findSsqRecordBySsqIndex(currentRecord.getNextSsqIndex());
				
				int forcast = FormulaUtil.calculate(formula, formula, currentRecord, expMap);
				
				if(index==list.size()-1) {
					rights.add("当前期预测："+currentRecord.redString()+"   -----------  , 预测："+forcast);
				}
				
				if(nextRecord==null) {
					index++;
					continue;
				}
				Map<Integer, String> nextRedMap = new HashMap<Integer, String>();
				
				nextRedMap.put(nextRecord.getR1() ,null);
				nextRedMap.put(nextRecord.getR2() ,null);
				nextRedMap.put(nextRecord.getR3() ,null);
				nextRedMap.put(nextRecord.getR4() ,null);
				nextRedMap.put(nextRecord.getR5() ,null);
				nextRedMap.put(nextRecord.getR6() ,null);
				
				
				
				if(forcast>33) {
					outs.add("超过33， "+currentRecord.redString()+"   "+nextRecord.redString()+ " , 预测："+forcast);
					continue;
				}
				
				if(nextRedMap.containsKey(forcast)) {
					rights.add(currentRecord.redString()+"   "+nextRecord.redString()+ " , 预测："+forcast);
					
					forcastRightMap.put(nextRecord.getSsqIndex()+" "+forcast, 1);
				}
				
				index++;
			}
			
			if( (float)(rights.size()-1)/(float)list.size()<=0.15) {
				result.put(formula, new Object[]{list.size(),outs,rights});
				
				for(Iterator<String> it = forcastRightMap.keySet().iterator();it.hasNext();) {
					String key = it.next();
					if(totalForcastRightMap.containsKey(key)) {
						totalForcastRightMap.put(key, totalForcastRightMap.get(key)+1);
					} else {
						totalForcastRightMap.put(key, 1);
					}
				}
			}
			
			forcastRightMap.clear();
		}
		
		System.out.println("---------------------------------------------------------------");
		
		List<String> keys = new ArrayList<String>();
		for(Iterator<String> it = totalForcastRightMap.keySet().iterator();it.hasNext();) {
			String key = it.next();
			keys.add(key);
		}
		Collections.sort(keys);
		
		for(String key:keys){
//		for(Iterator<String> it = totalForcastRightMap.keySet().iterator();it.hasNext();) {
//			String key = it.next();
			int value = totalForcastRightMap.get(key);
			
			if(value>=1 && (key.indexOf("2012")>=0 || key.indexOf("2013")>=0) ) {
				System.out.println(key+"----"+value);
			}
		}
		System.out.println("---------------------------------------------------------------");
		
		return result;
		
	}
	
	@Override
	public Map<String, Object[]> testBlue(List<String> formulas) {
		Map<String, Object[]> result = new HashMap<String, Object[]>();
		
		Map<String, Interpreter> expMap = new HashMap<String, Interpreter>();
		
		List<SsqRecord> list = getSsqRecordDao().findSsqRecordsByHql(" from SsqRecord where ssqIndex >= 2010001 order by ssqIndex");
		
		Map<String, Integer> totalForcastRightMap = new HashMap<String, Integer>();
		
		
		for(String formula:formulas) {
			Map<String, Integer> forcastRightMap = new HashMap<String, Integer>();
			
			List<String> outs = new ArrayList<String>();
			List<String> rights = new ArrayList<String>();
			
			int index =0;
			for(SsqRecord currentRecord:list) {
				
				SsqRecord nextRecord = getSsqRecordDao().findSsqRecordBySsqIndex(currentRecord.getNextSsqIndex());
				
				int forcast = FormulaUtil.calculate(formula, formula, currentRecord, expMap);
				
				if(index==list.size()-1) {
					rights.add("当前期预测："+currentRecord.redString()+"   -----------  , 预测："+forcast);
				}
				
				if(nextRecord==null) {
					index++;
					continue;
				}
				
				int blueValue = nextRecord.getB1();
				
				if(forcast>16) {
					outs.add("超过16， "+currentRecord.redString()+"   "+nextRecord.redString()+ " , 预测："+forcast);
					continue;
				}
				
				if(blueValue ==forcast) {
					rights.add(currentRecord.toString()+"   "+nextRecord.toString()+ " , 预测："+forcast);
					
					forcastRightMap.put(nextRecord.getSsqIndex()+" "+forcast, 1);
				}
				
				index++;
			}
			
			if( (float)(rights.size()-1)/(float)list.size()<=0.048) {
				result.put(formula, new Object[]{list.size(),outs,rights});
				
				for(Iterator<String> it = forcastRightMap.keySet().iterator();it.hasNext();) {
					String key = it.next();
					if(totalForcastRightMap.containsKey(key)) {
						totalForcastRightMap.put(key, totalForcastRightMap.get(key)+1);
					} else {
						totalForcastRightMap.put(key, 1);
					}
				}
			}
			
			forcastRightMap.clear();
		}
		
		System.out.println("---------------------------------------------------------------");
		
		List<String> keys = new ArrayList<String>();
		for(Iterator<String> it = totalForcastRightMap.keySet().iterator();it.hasNext();) {
			String key = it.next();
			keys.add(key);
		}
		Collections.sort(keys);
		
		for(String key:keys){
//		for(Iterator<String> it = totalForcastRightMap.keySet().iterator();it.hasNext();) {
//			String key = it.next();
			int value = totalForcastRightMap.get(key);
			
			if(value>=1 && ( key.indexOf("2012")>=0 || key.indexOf("2013")>=0) ) {
				System.out.println(key+"----"+value);
			}
		}
		System.out.println("---------------------------------------------------------------");
		
		return result;
		
	}

}
