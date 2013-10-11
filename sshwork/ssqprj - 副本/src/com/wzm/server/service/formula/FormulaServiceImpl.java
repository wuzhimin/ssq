package com.wzm.server.service.formula;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import bsh.Interpreter;

import com.wzm.server.dao.formula.FormulaDao;
import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.base.BaseEntity;
import com.wzm.server.entity.formula.RedFormula;
import com.wzm.server.entity.formula.RedFormulaCacl;
import com.wzm.server.entity.formula.RedFormulaCaclMulForcast;
import com.wzm.server.entity.formula.RedFormulaCaclMulStats;
import com.wzm.server.entity.formula.RedFormulaCaclVerify;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.server.service.base.BaseServiceImpl;
import com.wzm.util.FileUtil;
import com.wzm.util.FormulaUtil;

/**
 * UserService实现类
 * 
 * @author wzm
 * 
 */
public class FormulaServiceImpl extends BaseServiceImpl implements FormulaService {
	
	private static Map<String, Interpreter> expMap = new HashMap<String, Interpreter>();
	
	private static Map<String, Method> methodMap = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap1 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap2 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap3 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap4 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap5 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap6 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap7 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap8 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap9 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap10 = new HashMap<String, Method>();
	
	private static Map<String, Method> methodMap11 = new HashMap<String, Method>();
	
	
	
	
	// 用户目录
	public static final String USERHOME = System.getProperties().getProperty(
			"user.home")
			+ "/ddd/";

	// 红球公式记录文件
	public static final String RED_FORMULA_FILE = "公式/杀红/杀单个/formula_杀红.txt";
	
	private SsqRecordDao ssqRecordDao;

	public FormulaServiceImpl() {

	}
	
	public SsqRecordDao getSsqRecordDao() {
		return ssqRecordDao;
	}

	public void setSsqRecordDao(SsqRecordDao ssqRecordDao) {
		this.ssqRecordDao = ssqRecordDao;
	}

	/**
	 * 返回FormulaDao
	 * @return
	 */
	public FormulaDao getFormulaDao() {
		return ((FormulaDao) getBaseDao());
	}

	@Override
	public void initAllRedFormula() throws IOException {
		
		// 从开奖记录文件中获取开奖记录集合
		List<String> strRecords = FileUtil.readLineFile(USERHOME  + RED_FORMULA_FILE);
		
		List<BaseEntity> records = new ArrayList<BaseEntity>();
		
		int index = 1;
		for (String str : strRecords) {
			
			RedFormula rFormula = new RedFormula();
			rFormula.setDesc("");
			rFormula.setName("formula"+index);
			rFormula.setValue(str.trim());

			records.add(rFormula);
			index++;
		}

		// 保存所有开奖记录
		getBaseDao().batchAddBaseEntityes(records);
	}

	@Override
	public Serializable addARedFormula(String name, String formulaValue,
			String desc) {
		
		RedFormula rFormula = new RedFormula();
		rFormula.setDesc(desc);
		rFormula.setName(name);
		rFormula.setValue(formulaValue);
		
		return getBaseDao().add(rFormula);
	}

	@Override
	public void caclAllRedFormula() throws Exception {
		
		// 获取所有公式
		List<RedFormula> redFormulaes = getFormulaDao().findRedFormulasByHql("from RedFormula");
		
		//获取还未计算过的开奖记录
		List<SsqRecord> ssqRecords = ssqRecordDao.findSsqRecordsByHql("from SsqRecord where ssqIndex > ( select max(ssqIndex) from RedFormulaCacl )");
		
		List<BaseEntity> caclVerifies = new ArrayList<BaseEntity>();
		
		for(int i=0;i<ssqRecords.size();i++) {
			RedFormulaCacl cacl = new RedFormulaCacl();
			cacl.setSsqIndex(ssqRecords.get(i).getSsqIndex());
			cacl.setTargetSsqIndex(ssqRecords.get(i).getNextSsqIndex());
			
			for(int j=0;j<redFormulaes.size();j++) {
				RedFormula redFormula = redFormulaes.get(j);
				String formulaStr = redFormula.getValue();
				String formulaName = redFormula.getName();
				
				// 计算公式
				int result = FormulaUtil.calculate(formulaStr, formulaName, ssqRecords.get(i), expMap);
				
				formulaName = formulaName.substring(0,1).toUpperCase()+formulaName.substring(1);
				
				// 根据公式名称反射赋值到RedFormulaCacl对应字段
				String methodName = "set"+formulaName+"Value";
				Method method = null;
				if(methodMap.get(methodName)==null) {
					method = RedFormulaCacl.class.getMethod(methodName, int.class);
					methodMap.put(methodName, method);
				} else {
					method = methodMap.get(methodName);
				}
				method.invoke(cacl, result);
			}
			
			caclVerifies.add(cacl);
		}
		
		getBaseDao().batchAddBaseEntityes(caclVerifies);
		
	}

	@Override
	public void caclARedFormula(String formulaName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void caclAllRedFormulaByNow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void caclARedFormulaByNow(String formulaName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyAllRedFormula() throws Exception {
		
		// 删除所有RedFormulaCaclVerify
//		getBaseDao().deleteByHql("delete from RedFormulaCaclVerify");
		
		List<RedFormula> redFormulaes = getFormulaDao().findRedFormulasByHql("from RedFormula");
		
		// 获取还未验证过的红球公式计算值
		List<BaseEntity> redFormulaCacles = getFormulaDao().find("from RedFormulaCacl where ssqIndex > ( select max(ssqIndex) from RedFormulaCaclVerify ) ");
		
		List<BaseEntity> caclVerifies = new ArrayList<BaseEntity>();
		
		for(int i=0;i<redFormulaCacles.size();i++) {
			
			RedFormulaCaclVerify caclVerify = new RedFormulaCaclVerify();
			
			RedFormulaCacl redFormulaCacl = (RedFormulaCacl)redFormulaCacles.get(i);
			
			caclVerify.setSsqIndex(redFormulaCacl.getSsqIndex());
			
			int targetSsqIndex = redFormulaCacl.getTargetSsqIndex();
			
			caclVerify.setTargetSsqIndex(targetSsqIndex);
			
			// 获取目标期的双色球开奖记录
			SsqRecord targetSsqRecord = ssqRecordDao.findSsqRecordBySsqIndex(targetSsqIndex);
			
			if(targetSsqRecord==null) {
				continue;
			}
			
			Map<Integer, String> redMap = new HashMap<Integer, String>();
			redMap.put(targetSsqRecord.getR1(), null);
			redMap.put(targetSsqRecord.getR2(), null);
			redMap.put(targetSsqRecord.getR3(), null);
			redMap.put(targetSsqRecord.getR4(), null);
			redMap.put(targetSsqRecord.getR5(), null);
			redMap.put(targetSsqRecord.getR6(), null);
			
			for(int j=0;j<redFormulaes.size();j++) {
				RedFormula redFormula = redFormulaes.get(j);
				
				String formulaName = redFormula.getName();
				
				// 获取计算值
				formulaName = formulaName.substring(0,1).toUpperCase()+formulaName.substring(1);
				
				// 根据公式名称反射赋值到RedFormulaCacl对应字段
				String methodName = "get"+formulaName+"Value";
				Method method = null;
				if(methodMap1.get(methodName)==null) {
					method = RedFormulaCacl.class.getMethod(methodName);
					methodMap1.put(methodName, method);
				} else {
					method = methodMap1.get(methodName);
				}
				
				int result = ((Integer)method.invoke(redFormulaCacl)).intValue();
				
				boolean killRight = false;
				if(!redMap.containsKey(result)) {
					killRight = true;
				}
				
				methodName = "setKill"+formulaName+"Right";
				Method method1 = null;
				if(methodMap2.get(methodName)==null) {
					method1 = RedFormulaCaclVerify.class.getMethod(methodName,boolean.class);
					methodMap2.put(methodName, method1);
				} else {
					method1 = methodMap2.get(methodName);
				}
				method1.invoke(caclVerify, killRight);
			}
			caclVerifies.add(caclVerify);
		}
		
		getBaseDao().batchAddBaseEntityes(caclVerifies);
		
	}
	
	/**
	 * 生成所有红球公式计算值正确性多期统计表
	 * @param spaceNum
	 * @throws Exception
	 */
	@Override
	public void writeAllRedFormulaCaclVerifyMulStats(int spaceNum) throws Exception {
		
		List<BaseEntity> mulStatsList = new ArrayList<BaseEntity>();
		
		// 取出所有红球公式
		List<RedFormula> redFormulaes = getFormulaDao().findRedFormulasByHql("from RedFormula");
		
		// 1.删除所有的红球公式计算值正确性多期统计表
//		getSsqRecordDao()
//				.deleteByHql(
//						"delete from RedFormulaCaclMulStats a where a.spaceNum = ?",
//						new Integer[] { spaceNum });

		// 2.取出所有红球公式计算值正确性单期统计信息
		int maxFromSsqIndex = ssqRecordDao.getFunctionIntValue("select max(fromSsqIndex) from RedFormulaCaclMulStats  where spaceNum = ? ", new Object[]{spaceNum});
		
		List<BaseEntity> statses = getBaseDao().find(
				"from RedFormulaCaclVerify where targetSsqIndex > ? order by targetSsqIndex", new Object[]{maxFromSsqIndex});
		
		// 计算多期统计总记录数
		int mulCount = statses.size() - spaceNum + 1;

		for (int i = 0; i < mulCount; i++) {
			
			List<RedFormulaCaclVerify> tmp = new ArrayList<RedFormulaCaclVerify>();

			for (int j = i, index = 0; index < spaceNum; index++, j++) {
				tmp.add((RedFormulaCaclVerify) statses.get(j));
			}
			
			// 生成一条红球公式计算值正确性多期统计表信息
			RedFormulaCaclMulStats mulStats = buildARedFormulaCaclMulStats(tmp, redFormulaes);
			
			mulStatsList.add(mulStats);
			
		}
		
		getSsqRecordDao().batchAddBaseEntityes(mulStatsList);
	}
	
	/**
	 * 生成所有红球公式计算值正确性多期预测表
	 * @param spaceNum
	 * @throws Exception
	 */
	@Override
	public void writeAllRedFormulaCaclVerifyMulForcast(int spaceNum, int forcastSpaceNum) throws Exception {
		
		List<BaseEntity> forcastList = new ArrayList<BaseEntity>();
		
		// 取出所有红球公式
		List<RedFormula> redFormulaes = getFormulaDao().findRedFormulasByHql("from RedFormula");
		
		String str = " select max(fromSsqIndex) from RedFormulaCaclMulForcast where spaceNum = ? and forcastSpaceNum = ?";
		int maxFromSsqIndex = ssqRecordDao.getFunctionIntValue(str,new Object[]{spaceNum, forcastSpaceNum});

		// 2.取出所有红球公式计算值正确性单期统计信息
		String hql = "from RedFormulaCaclMulStats a where a.fromSsqIndex>=2007001 and a.fromSsqIndex > ? and a.spaceNum = ? order by a.fromSsqIndex";
		
		
		List<BaseEntity> records = getBaseDao().find(hql, new Integer[]{ maxFromSsqIndex, spaceNum });
		
		int forcastCount = records.size() - forcastSpaceNum + 1;
		
		for (int i = 0; i < forcastCount; i++) {
			int count=0;
			
			List<RedFormulaCaclMulStats> tmp = new ArrayList<RedFormulaCaclMulStats>();
			
			for(int j=i;count<forcastSpaceNum;j++,count++) {
				tmp.add((RedFormulaCaclMulStats) records.get(j));
			}
			
			RedFormulaCaclMulForcast forcast = buildARedFormulaCaclMulForcast(
					tmp, redFormulaes, forcastSpaceNum);

			forcastList.add(forcast);
		}
		
		// 批量保存预测数据
		getBaseDao().batchAddBaseEntityes(forcastList);
		
	}
	
	/**
	 * 生成所有红球公式计算值正确性多期预测表
	 * @param spaceNum
	 * @throws Exception
	 */
//	@Override
	public void writeAllRedFormulaCaclForcast(int spaceNum, int forcastSpaceNum) throws Exception {
		
		List<BaseEntity> forcastList = new ArrayList<BaseEntity>();
		
		// 取出所有红球公式
		List<RedFormula> redFormulaes = getFormulaDao().findRedFormulasByHql("from RedFormula");
		
		String str = " select max(fromSsqIndex) from RedFormulaCaclMulForcast where spaceNum = ? and forcastSpaceNum = ?";
		int maxFromSsqIndex = ssqRecordDao.getFunctionIntValue(str,new Object[]{spaceNum, forcastSpaceNum});

		// 2.取出所有红球公式计算值正确性单期统计信息
		String hql = "from RedFormulaCaclMulStats a where a.fromSsqIndex>=2007001 and a.fromSsqIndex > ? and a.spaceNum = ? order by a.fromSsqIndex";
		
		List<BaseEntity> records = getBaseDao().find(hql, new Integer[]{ maxFromSsqIndex, spaceNum });
		
		int forcastCount = records.size() - forcastSpaceNum + 1;
		
		for (int i = 0; i < forcastCount; i++) {
			int count=0;
			
			List<RedFormulaCaclMulStats> tmp = new ArrayList<RedFormulaCaclMulStats>();
			
			for(int j=i;count<forcastSpaceNum;j++,count++) {
				tmp.add((RedFormulaCaclMulStats) records.get(j));
			}
			
			RedFormulaCaclMulForcast forcast = buildARedFormulaCaclMulForcast(
					tmp, redFormulaes, forcastSpaceNum);

			forcastList.add(forcast);
		}
		
		// 批量保存预测数据
		getBaseDao().batchAddBaseEntityes(forcastList);
		
	}

	@Override
	public void verifyAllRedFormulaCaclVerifyMulForcast(int spaceNum, int forcastSpaceNum) {
		
		// 1.获取开始期和结束期范围内的预测数据
		List<BaseEntity> forcasts = getBaseDao().find(" from RedFormulaCaclMulForcast a where a.verified = false and a.spaceNum = ? and a.forcastSpaceNum = ?", 
				new Integer[]{spaceNum, forcastSpaceNum});
		
		for(int i=0;i<forcasts.size();i++){
			RedFormulaCaclMulForcast forcast = (RedFormulaCaclMulForcast) forcasts.get(i);
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
			
			writeKillErrorFormula(forcast);
			writeSelectRightFormula(forcast);
			
			forcast.setVerified(true);
			
			// 修改保存
			getBaseDao().update(forcast);
		}
		
	}
	
	private RedFormulaCaclMulForcast buildARedFormulaCaclMulForcast(
			List<RedFormulaCaclMulStats> records, List<RedFormula> redFormulaes,
			int forcastSpaceNum) throws Exception {
		
		RedFormulaCaclMulForcast forcast = new RedFormulaCaclMulForcast();
		
		int size = records.size();

		int fromSsqIndex = records.get(0).getFromSsqIndex();
		forcast.setFromSsqIndex(fromSsqIndex);

		int toSsqIndex = records.get(size - 1).getToSsqIndex();
		forcast.setToSsqIndex(toSsqIndex);
		
		int targetSsqIndex = ssqRecordDao.getFunctionIntValue("select nextSsqIndex from SsqRecord a where a.ssqIndex = ? ", new Integer[]{toSsqIndex});
		
		forcast.setTargetSsqIndex(targetSsqIndex);

		forcast.setSpaceNum(records.get(0).getSpaceNum());
		forcast.setForcastSpaceNum(forcastSpaceNum);
		
		for (int j = 0; j < size; j++) {
			RedFormulaCaclMulStats stats = records.get(j);
			
			for(int i=0;i<redFormulaes.size();i++) {
				String formulaName = redFormulaes.get(i).getName();
				formulaName = formulaName.substring(0,1).toUpperCase()+formulaName.substring(1);
				
				// 获取原记录数
				String getKillRightCountMethodName = "get"+formulaName+"KillRightCount";
				Method m1 = null;
				if(methodMap3.get(getKillRightCountMethodName)==null) {
					m1 = RedFormulaCaclMulStats.class.getMethod(getKillRightCountMethodName);
					methodMap3.put(getKillRightCountMethodName, m1);
				} else {
					m1 = methodMap3.get(getKillRightCountMethodName);
				}
				int count = ((Integer)m1.invoke(stats)).intValue();
				
				// 获取最大、最小值
				String getFormulaKillRightMaxCountMethodName = "get"+formulaName+"KillRightMaxCount";
				Method m2 = null;
				if(methodMap6.get(getFormulaKillRightMaxCountMethodName)==null) {
					m2 = RedFormulaCaclMulForcast.class.getMethod(getFormulaKillRightMaxCountMethodName);
					methodMap6.put(getFormulaKillRightMaxCountMethodName, m2);
				} else {
					m2 = methodMap6.get(getFormulaKillRightMaxCountMethodName);
				}
				int maxCount = ((Integer)m2.invoke(forcast)).intValue();
				
				String getFormulaKillRightMinCountMethodName = "get"+formulaName+"KillRightMinCount";
				Method m3 = getMethodInCache(getFormulaKillRightMinCountMethodName, RedFormulaCaclMulForcast.class, methodMap7);
				int minCount = ((Integer)m3.invoke(forcast)).intValue();
				
				// 设置最大、最小值
				String setFormulaKillRightMaxCountMethodName = "set"+formulaName+"KillRightMaxCount";
				Method m4 = null;
				if(methodMap8.get(setFormulaKillRightMaxCountMethodName)==null) {
					m4 = RedFormulaCaclMulForcast.class.getMethod(setFormulaKillRightMaxCountMethodName,int.class);
					methodMap8.put(setFormulaKillRightMaxCountMethodName, m4);
				} else {
					m4 = methodMap8.get(setFormulaKillRightMaxCountMethodName);
				}
				m4.invoke(forcast,count>maxCount?count:maxCount);
				
				String setFormulaKillRightMinCountMethodName = "set"+formulaName+"KillRightMinCount";
				Method m5 = null;
				if(methodMap9.get(setFormulaKillRightMinCountMethodName)==null) {
					m5 = RedFormulaCaclMulForcast.class.getMethod(setFormulaKillRightMinCountMethodName,int.class);
					methodMap9.put(setFormulaKillRightMinCountMethodName, m5);
				} else {
					m5 = methodMap9.get(setFormulaKillRightMinCountMethodName);
				}
				m5.invoke(forcast, count < minCount ? count : minCount);
			}
		}	
		
		RedFormulaCaclMulStats lastMulStats = records.get(size-1);
		
		// 处理关注数
		dealCareNums(forcast, lastMulStats,redFormulaes);
		
		// 处理选择的和杀的
		dealKillAndSelectNums(forcast, lastMulStats,redFormulaes);
		
		return forcast;
	}
	
	private void dealKillAndSelectNums(RedFormulaCaclMulForcast forcast, RedFormulaCaclMulStats lastMulStats, 
			List<RedFormula> redFormulaes) throws Exception {
		String killNums = "";
		String killFormulas = "";
		
		String selectNums = "";
		String selectFormulas = "";
		
		
		for(int i=0;i<redFormulaes.size();i++) {
			
			String formulaName = redFormulaes.get(i).getName();
			formulaName = formulaName.substring(0,1).toUpperCase()+formulaName.substring(1);
			
			// 获取原记录数
			String getKillRightCountMethodName = "get"+formulaName+"KillRightCount";
			Method m1 = getMethodInCache(getKillRightCountMethodName, RedFormulaCaclMulStats.class, methodMap3);
			int count = ((Integer)m1.invoke(lastMulStats)).intValue();
			
			// 获取最大值
			String getFormulaKillRightMaxCountMethodName = "get"+formulaName+"KillRightMaxCount";
			Method m2 = getMethodInCache(getFormulaKillRightMaxCountMethodName, RedFormulaCaclMulForcast.class, methodMap6);
			int maxCount = ((Integer)m2.invoke(forcast)).intValue();
			
			// 获取最小值
			String getFormulaKillRightMinCountMethodName = "get"+formulaName+"KillRightMinCount";
			Method m3 = getMethodInCache(getFormulaKillRightMinCountMethodName, RedFormulaCaclMulForcast.class, methodMap7);
			int minCount = ((Integer)m3.invoke(forcast)).intValue();
			
			
			String isKillFormulaRightMethodName = "isKill"+formulaName+"Right";
			Method m5 = getMethodInCache(isKillFormulaRightMethodName, RedFormulaCaclVerify.class, methodMap11);
			int beginSsqIndex =lastMulStats.getFromSsqIndex();
//			RedFormulaCaclVerify formulaCaclVerify = getFormulaDao().findRedFormulaCaclVerifyBySsqIndex(beginSsqIndex);   //这里有问题  TODO
			RedFormulaCaclVerify formulaCaclVerify = getFormulaDao().findRedFormulaCaclVerifyByTargetSsqIndex(beginSsqIndex);   //这里有问题  TODO
			
			boolean isKillRight = ((Boolean)m5.invoke(formulaCaclVerify)).booleanValue();
			
			String getFormulaValueMethodName = "get"+formulaName+"Value";
			Method m4 = getMethodInCache(getFormulaValueMethodName, RedFormulaCacl.class, methodMap10);
			int toSsqIndex = lastMulStats.getToSsqIndex();
			RedFormulaCacl formulaCacle = getFormulaDao().findRedFormulaCaclBySsqIndex(toSsqIndex);
			int killValue = ((Integer)m4.invoke(formulaCacle)).intValue();
			
			// 设置杀的数字和选的数字
			if (count == maxCount) {
				if (isKillRight) {
					
				} else {
					selectNums = selectNums+killValue+",";
					selectFormulas = selectFormulas +"f"+formulaName.substring(7)+",";
				}
			} else if (count == minCount) {
				if (isKillRight) {
					killNums = killNums+killValue+",";
					killFormulas = killFormulas +"f"+formulaName.substring(7)+",";
				} else {

				}
			}
		}
		
		forcast.setKillNums(killNums);
		forcast.setKillFormulas(killFormulas);
		
		forcast.setSelectNums(selectNums);
		forcast.setSelectFormulas(selectFormulas);
		
	}

	private void dealCareNums(RedFormulaCaclMulForcast forcast,
			RedFormulaCaclMulStats lastMulStats, List<RedFormula> redFormulaes) throws Exception {
		String careNums = "";
		String careFormulas = "";
		Map<Integer, String> tmp = new HashMap<Integer, String>();
		for(int i=0;i<redFormulaes.size();i++) {
			
			String formulaName = redFormulaes.get(i).getName();
			formulaName = formulaName.substring(0,1).toUpperCase()+formulaName.substring(1);
			
			// 获取原记录数
			String getKillRightCountMethodName = "get"+formulaName+"KillRightCount";
			Method m1 = null;
			if(methodMap3.get(getKillRightCountMethodName)==null) {
				m1 = RedFormulaCaclMulStats.class.getMethod(getKillRightCountMethodName);
				methodMap3.put(getKillRightCountMethodName, m1);
			} else {
				m1 = methodMap3.get(getKillRightCountMethodName);
			}
			int count = ((Integer)m1.invoke(lastMulStats)).intValue();
			
			// 获取最大、最小值
			String getFormulaKillRightMaxCountMethodName = "get"+formulaName+"KillRightMaxCount";
			Method m2 = null;
			if(methodMap6.get(getFormulaKillRightMaxCountMethodName)==null) {
				m2 = RedFormulaCaclMulForcast.class.getMethod(getFormulaKillRightMaxCountMethodName);
				methodMap6.put(getFormulaKillRightMaxCountMethodName, m2);
			} else {
				m2 = methodMap6.get(getFormulaKillRightMaxCountMethodName);
			}
			int maxCount = ((Integer)m2.invoke(forcast)).intValue();
			
			String getFormulaKillRightMinCountMethodName = "get"+formulaName+"KillRightMinCount";
			Method m3 = getMethodInCache(getFormulaKillRightMinCountMethodName, RedFormulaCaclMulForcast.class, methodMap7);
			int minCount = ((Integer)m3.invoke(forcast)).intValue();
			
			// 设置关注的数字
			if(count==maxCount || count==minCount) {
				String getFormulaValueMethodName = "get"+formulaName+"Value";
				Method m4 = getMethodInCache(getFormulaValueMethodName, RedFormulaCacl.class, methodMap10);
				
				RedFormulaCacl formulaCacle = getFormulaDao().findRedFormulaCaclBySsqIndex(lastMulStats.getToSsqIndex());
				
				int killValue = ((Integer)m4.invoke(formulaCacle)).intValue();
				if(!tmp.containsKey(killValue)) {
					careNums = careNums+killValue+",";
					careFormulas = careFormulas+"f"+formulaName.substring(7)+",";
					tmp.put(killValue, null);
				} 
			}
		}
		
		forcast.setCareNums(careNums);
		forcast.setCareFormulas(careFormulas);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Method getMethodInCache(String methodName, Class cls, Map<String, Method> mapMethod)
			throws NoSuchMethodException {
		Method m = null;
		if(mapMethod.get(methodName)==null) {
			m = cls.getMethod(methodName);
			mapMethod.put(methodName, m);
		} else {
			m = mapMethod.get(methodName);
		}
		return m;
	}

	private RedFormulaCaclMulStats buildARedFormulaCaclMulStats(
			List<RedFormulaCaclVerify> records,List<RedFormula> formulas) throws Exception { 
		
		RedFormulaCaclMulStats mulStats = new RedFormulaCaclMulStats();
		
		int size = records.size();

		mulStats.setFromSsqIndex(records.get(0).getTargetSsqIndex());

		mulStats.setToSsqIndex(records.get(size-1).getTargetSsqIndex());

		mulStats.setSpaceNum(size);
		
		for (int j = 0; j < size; j++) {
			
			RedFormulaCaclVerify v = records.get(j);
			
			for(int i=0;i<formulas.size();i++) {
				String formulaName = formulas.get(i).getName();
				formulaName = formulaName.substring(0,1).toUpperCase()+formulaName.substring(1);
				
				// 获取原记录数
				String getKillRightCountMethodName = "get"+formulaName+"KillRightCount";
				Method m1 = null;
				if(methodMap3.get(getKillRightCountMethodName)==null) {
					m1 = RedFormulaCaclMulStats.class.getMethod(getKillRightCountMethodName);
					methodMap3.put(getKillRightCountMethodName, m1);
				} else {
					m1 = methodMap3.get(getKillRightCountMethodName);
				}
				int count = ((Integer)m1.invoke(mulStats)).intValue();
				
				// 获取是否杀正确
				String isKillRightMethodName = "isKill"+formulaName+"Right";
				Method m2 = null;
				if(methodMap4.get(isKillRightMethodName)==null) {
					m2 = RedFormulaCaclVerify.class.getMethod(isKillRightMethodName);
					methodMap4.put(isKillRightMethodName, m2);
				} else {
					m2 = methodMap4.get(isKillRightMethodName);
				}
				boolean killRight = ((Boolean)m2.invoke(v)).booleanValue();
				
				// 设置之后记录数
				String setKillRightCountMethodName = "set"+formulaName+"KillRightCount";
				Method m3 = null;
				if(methodMap5.get(setKillRightCountMethodName)==null) {
					m3 = RedFormulaCaclMulStats.class.getMethod(setKillRightCountMethodName, int.class);
					methodMap5.put(setKillRightCountMethodName, m3);
				} else {
					m3 = methodMap5.get(setKillRightCountMethodName);
				}
				m3.invoke(mulStats, killRight?(count+1):count);
			}
			
		}
		
		return mulStats;
	}
	
	public void writeKillErrorFormula(RedFormulaCaclMulForcast forcast) {
		String killNums = forcast.getKillNums();
		String killFormulas = forcast.getKillFormulas();
		String killNumRst = forcast.getKillNumsResult();
		
		String killErrorFormulas = "";
		
		
		if(StringUtils.hasLength(killNums) && StringUtils.hasLength(killFormulas) ) {
			if(killNumRst.indexOf("杀错：") >=0 ) {
				String[] tmp1 = killNums.split(",");
				String[] tmp2 = killFormulas.split(",");
				Map<String, String> map = new HashMap<String, String>();
				for(int i=0;i<tmp1.length;i++) {
					map.put(tmp2[i], tmp1[i]);
				}
				
				String[] tmp = killNumRst.substring(killNumRst.indexOf("杀错：")+3).split(",");
				for(int i=0;i<tmp.length;i++) {
					for(String key:map.keySet()) {
						String value = map.get(key);
						if(value.equals(tmp[i])) {
							killErrorFormulas = killErrorFormulas + key + ",";
							map.remove(key);
							break;
						}
					}
				}
 				
			}
		}
		forcast.setKillErrorFormulas(killErrorFormulas);
		
	}
	
	public void writeSelectRightFormula(RedFormulaCaclMulForcast forcast) {
		String selectNums = forcast.getSelectNums();
		String selectFormulas = forcast.getSelectFormulas();
		String selectNumRst = forcast.getSelectNumsResult();
		
		String selectRightFormulas = "";
		
		if(StringUtils.hasLength(selectNums) && StringUtils.hasLength(selectFormulas) && StringUtils.hasLength(selectNumRst) ) {
			String[] tmp1 = selectNums.split(",");
			String[] tmp2 = selectFormulas.split(",");
			Map<String, String> map = new HashMap<String, String>();
			for(int i=0;i<tmp1.length;i++) {
				map.put(tmp2[i], tmp1[i]);
			}
			
			String[] tmp = selectNumRst.split(",");
			for(int i=0;i<tmp.length;i++) {
				for(String key:map.keySet()) {
					String value = map.get(key);
					if(value.equals(tmp[i])) {
						selectRightFormulas = selectRightFormulas + key + ",";
						map.remove(key);
						break;
					}
				}
 				
			}
		}
		forcast.setSelectRightFormulas(selectRightFormulas);
		
	}
	
}
