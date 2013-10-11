package com.wzm.biz;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.SsqUtils;
import com.wzm.server.dao.ssq.SsqBaseStatsDao;
import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.base.BaseEntity;
import com.wzm.server.entity.formula.RedFormulaCacl;
import com.wzm.server.entity.formula.RedFormulaCaclMulStats;
import com.wzm.server.entity.formula.RedFormulaCaclVerify;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.util.ClientBeanUtil;

public class MulFormulaLawBuild2 {

	/**
	 * 
	 * @param ssqIndex 当前期
	 * @param count1  50期内预测正确最小期数
	 * @param count2  50期内预测正确最大期数
	 * @return
	 */
	public static List<String> buildMulFormula(int ssqIndex, int count1,
			int count2) {
		List<String> result = new ArrayList<String>();

		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao) ClientBeanUtil
				.getDao("ssqBaseStatsDao");

		String hql1 = "from RedFormulaCaclMulStats s where s.toSsqIndex = ? and s.spaceNum = 50";
		List<BaseEntity> statsList = ssqBaseStatsDao.find(hql1,
				new Integer[] { ssqIndex });
		RedFormulaCaclMulStats stats = (RedFormulaCaclMulStats) statsList
				.get(0);

		String hql2 = "from RedFormulaCacl s where s.ssqIndex = ? order by s.ssqIndex";
		List<BaseEntity> caclList = ssqBaseStatsDao.find(hql2,
				new Integer[] { ssqIndex });
		RedFormulaCacl cacl = (RedFormulaCacl) caclList.get(0);

		Map<Integer, Method> methodMap = new HashMap<Integer, Method>();

		for (int k = 1; k <= 91; k++) {
			try {
				String methodName = "getFormula" + k + "KillRightCount";
				int count = 0;
				if (!methodMap.containsKey(k)) {
					Method method = RedFormulaCaclMulStats.class
							.getMethod(methodName);
					count = (Integer) method.invoke(stats);
					methodMap.put(k, method);
				} else {
					count = (Integer) methodMap.get(k).invoke(stats);
				}

				if (count >= count1 && count <= count2) {
					methodName = "getFormula" + k + "Value";
					int tmp = (Integer) RedFormulaCacl.class.getMethod(
							methodName).invoke(cacl);
					String str = SsqUtils.build2BitIntStr(tmp);
					if(!result.contains(str)) {
						result.add(str);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		Collections.sort(result);

		return result;
	}
	
	
	public static List<String> buildMulFormula2(int targetSsqIndex1,int targetSsqIndex2, int count1, int count2) {
		List<String> result = new ArrayList<String>();

		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao) ClientBeanUtil.getDao("ssqBaseStatsDao");
		SsqRecordDao ssqRecordDao = (SsqRecordDao) ClientBeanUtil.getDao("ssqRecordDao");

		String hql2 = "from RedFormulaCacl s where s.targetSsqIndex >= ? and s.targetSsqIndex <= ? order by s.ssqIndex";
		
		List<BaseEntity> caclList = ssqBaseStatsDao.find(hql2, new Integer[] { targetSsqIndex1, targetSsqIndex2 });
		
		List<Integer> total = new ArrayList<Integer>();
		
		for(int i=0;i<caclList.size();i++) {
			RedFormulaCacl cacl = (RedFormulaCacl) caclList.get(i);
			
			SsqRecord record = ssqRecordDao.findSsqRecordBySsqIndex(cacl.getTargetSsqIndex());
			Map<Integer, String> ssqRedMap = new HashMap<Integer, String>();
			ssqRedMap.put(record.getR1(), null);
			ssqRedMap.put(record.getR2(), null);
			ssqRedMap.put(record.getR3(), null);
			ssqRedMap.put(record.getR4(), null);
			ssqRedMap.put(record.getR5(), null);
			ssqRedMap.put(record.getR6(), null);
			
			Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
			
			Map<Integer, Integer> forcastCountMap = new HashMap<Integer, Integer>();
	
			for (int k = 1; k <= 91; k++) {
				try {
					String methodName = "getFormula" + k + "Value";
					Method method = null;
					if (!methodMap.containsKey(k)) {
						method = RedFormulaCacl.class
								.getMethod(methodName);
						methodMap.put(k, method);
					} else {
						method = methodMap.get(k);
					}
					
					int tmp = (Integer) RedFormulaCacl.class.getMethod(methodName).invoke(cacl);
					
					if(forcastCountMap.containsKey(tmp)) {
						forcastCountMap.put(tmp, forcastCountMap.get(tmp)+1);
					} else {
						forcastCountMap.put(tmp, 1);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			for(int key:forcastCountMap.keySet()) {
				if(forcastCountMap.get(key) >=count1 && forcastCountMap.get(key) <=count2 && ssqRedMap.containsKey(key)) {
					System.out.println(cacl.getTargetSsqIndex()+"-------"+key);
					if(!total.contains(key)) {
						total.add(key);
					}
				}
			}
		}
		
		Collections.sort(total);
		System.out.println(total);
		return result;
	}
	
	public static List<String> buildMulFormulaMaxContinueRightCount(int targetSsqIndex1,int targetSsqIndex2) {
		
		List<Integer> fList = new ArrayList<Integer>();
		for(int i=1;i<=91;i++) {
			fList.add(i);
		}
		return buildMulFormulaMaxContinueRightCount(fList, targetSsqIndex1, targetSsqIndex2);
		
//		List<String> result = new ArrayList<String>();
//
//		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao) ClientBeanUtil.getDao("ssqBaseStatsDao");
////		SsqRecordDao ssqRecordDao = (SsqRecordDao) ClientBeanUtil.getDao("ssqRecordDao");
//
//		String hql = "from RedFormulaCaclVerify s where s.targetSsqIndex >= ? and s.targetSsqIndex <= ? order by s.ssqIndex";
//		
//		List<BaseEntity> veryList = ssqBaseStatsDao.find(hql, new Integer[] { targetSsqIndex1, targetSsqIndex2 });
//		
//		// 连续次数的次数
//		Map<Integer, Map<Integer, Integer>> countCountMap = new HashMap<Integer, Map<Integer, Integer>>();
//		Map<Integer, Map<Integer, String>> countCountInfoMap = new HashMap<Integer, Map<Integer, String>>();
//		
//		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
//		Map<Integer, String> countInfoMap = new HashMap<Integer, String>();
//		
//		Map<Integer, Integer> maxCountMap = new HashMap<Integer, Integer>();
//		Map<Integer, String> maxCountInfoMap = new HashMap<Integer, String>();
//		
//		for(int i=0;i<veryList.size();i++) {
//			RedFormulaCaclVerify caclVery = (RedFormulaCaclVerify) veryList.get(i);
//			
//			Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
//			
//			for (int k = 1; k <= 91; k++) {
//				try {
//					
//					String methodName = "isKillFormula"+k+"Right";
//					
//					Method method = null;
//					if (!methodMap.containsKey(k)) {
//						method = RedFormulaCaclVerify.class.getMethod(methodName);
//						methodMap.put(k, method);
//					} else {
//						method = methodMap.get(k);
//					}
//					
//					boolean isRight = (Boolean) RedFormulaCaclVerify.class.getMethod(methodName).invoke(caclVery);
//					
//					if(!isRight) {
//						if(countMap.containsKey(k)) {
//							countMap.put(k, countMap.get(k)+1);
//							countInfoMap.put(k, caclVery.getSsqIndex()+"");
//						} else {
//							countMap.put(k, 1);
//							countInfoMap.put(k, caclVery.getSsqIndex()+"");
//						}
//					} else {
//						if(countMap.containsKey(k)) {
//							if(!maxCountMap.containsKey(k)) { 
//								maxCountMap.put(k, countMap.get(k));
//								maxCountInfoMap.put(k, countInfoMap.get(k));
//							} else if(maxCountMap.get(k)<countMap.get(k)) {
//								maxCountMap.put(k, countMap.get(k));
//								maxCountInfoMap.put(k, countInfoMap.get(k));
//							}
//							
//							if(countCountMap.containsKey(k)) {
//								Map<Integer, Integer> t = countCountMap.get(k);
//								Map<Integer, String> s = countCountInfoMap.get(k);
//								
//								if(t.containsKey(countMap.get(k))) {
//									t.put(countMap.get(k), t.get(countMap.get(k))+1);
//									s.put(countMap.get(k), s.get(countMap.get(k))+","+countInfoMap.get(k));
//									
//								} else {
//									t.put(countMap.get(k), 1);
//									s.put(countMap.get(k), countInfoMap.get(k));
//								}
//								countCountMap.put(k, t);
//								countCountInfoMap.put(k, s);
//								
//							} else {
//								Map<Integer, Integer> t = new HashMap<Integer, Integer>();
//								Map<Integer, String> s = new HashMap<Integer, String>();
//								
//								t.put(countMap.get(k), 1);
//								s.put(countMap.get(k), countInfoMap.get(k));
//								
//								countCountMap.put(k, t);
//								countCountInfoMap.put(k, s);
//							}
//						}
//						
//						countMap.put(k, 0);
//					}
//					
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//		
//		for (int k = 1; k <= 91; k++) {
//			if(countMap.containsKey(k)) {
//				if(!maxCountMap.containsKey(k)) { 
//					maxCountMap.put(k, countMap.get(k));
//					maxCountInfoMap.put(k, countInfoMap.get(k));
//				} else if(maxCountMap.get(k)<countMap.get(k)) {
//					maxCountMap.put(k, countMap.get(k));
//					maxCountInfoMap.put(k, countInfoMap.get(k));
//				}
//				
//				if(countCountMap.containsKey(k)) {
//					Map<Integer, Integer> t = countCountMap.get(k);
//					Map<Integer, String> s = countCountInfoMap.get(k);
//					
//					if(t.containsKey(countMap.get(k))) {
//						t.put(countMap.get(k), t.get(countMap.get(k))+1);
//						s.put(countMap.get(k), s.get(countMap.get(k))+","+countInfoMap.get(k));
//						
//					} else {
//						t.put(countMap.get(k), 1);
//						s.put(countMap.get(k), countInfoMap.get(k));
//					}
//					countCountMap.put(k, t);
//					countCountInfoMap.put(k, s);
//					
//				} else {
//					Map<Integer, Integer> t = new HashMap<Integer, Integer>();
//					Map<Integer, String> s = new HashMap<Integer, String>();
//					
//					t.put(countMap.get(k), 1);
//					s.put(countMap.get(k), countInfoMap.get(k));
//					
//					countCountMap.put(k, t);
//					countCountInfoMap.put(k, s);
//				}
//			}
//		}
//		
//		for (int k = 1; k <= 91; k++) {
//			String tmpStr = "公式序号："+k+",最大连续期数："+maxCountMap.get(k)+",结束期数："+maxCountInfoMap.get(k)+",最大连续期数出现次数："+countCountMap.get(k).get(maxCountMap.get(k))+
//					", "+countCountInfoMap.get(k).get(maxCountMap.get(k));
//			result.add(tmpStr);
//			System.out.println(tmpStr);
//		}
//		
//		return result;
	}
	
	
	public static List<String> buildMulFormulaMaxContinueRightCount(List<Integer> formulaIndexList, int targetSsqIndex1,int targetSsqIndex2) {
		List<String> result = new ArrayList<String>();

		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao) ClientBeanUtil.getDao("ssqBaseStatsDao");

		String hql = "from RedFormulaCaclVerify s where s.targetSsqIndex >= ? and s.targetSsqIndex <= ? order by s.ssqIndex";
		
		List<BaseEntity> veryList = ssqBaseStatsDao.find(hql, new Integer[] { targetSsqIndex1, targetSsqIndex2 });
		
		// 连续次数的次数
		Map<Integer, Map<Integer, Integer>> countCountMap = new HashMap<Integer, Map<Integer, Integer>>();
		Map<Integer, Map<Integer, String>> countCountInfoMap = new HashMap<Integer, Map<Integer, String>>();
		
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		Map<Integer, String> countInfoMap = new HashMap<Integer, String>();
		
		Map<Integer, Integer> maxCountMap = new HashMap<Integer, Integer>();
		Map<Integer, String> maxCountInfoMap = new HashMap<Integer, String>();
		
		for(int i=0;i<veryList.size();i++) {
			RedFormulaCaclVerify caclVery = (RedFormulaCaclVerify) veryList.get(i);
			
			Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
			
			for (int k:formulaIndexList) {
				try {
					
					String methodName = "isKillFormula"+k+"Right";
					
					Method method = null;
					if (!methodMap.containsKey(k)) {
						method = RedFormulaCaclVerify.class.getMethod(methodName);
						methodMap.put(k, method);
					} else {
						method = methodMap.get(k);
					}
					
					boolean isRight = (Boolean) RedFormulaCaclVerify.class.getMethod(methodName).invoke(caclVery);
					
					if(!isRight) {
						if(countMap.containsKey(k)) {
							countMap.put(k, countMap.get(k)+1);
							countInfoMap.put(k, caclVery.getSsqIndex()+"");
						} else {
							countMap.put(k, 1);
							countInfoMap.put(k, caclVery.getSsqIndex()+"");
						}
					} else {
						if(countMap.containsKey(k)) {
							if(!maxCountMap.containsKey(k)) { 
								maxCountMap.put(k, countMap.get(k));
								maxCountInfoMap.put(k, countInfoMap.get(k));
							} else if(maxCountMap.get(k)<countMap.get(k)) {
								maxCountMap.put(k, countMap.get(k));
								maxCountInfoMap.put(k, countInfoMap.get(k));
							}
							
							if(countCountMap.containsKey(k)) {
								Map<Integer, Integer> t = countCountMap.get(k);
								Map<Integer, String> s = countCountInfoMap.get(k);
								
								if(t.containsKey(countMap.get(k))) {
									t.put(countMap.get(k), t.get(countMap.get(k))+1);
									s.put(countMap.get(k), s.get(countMap.get(k))+","+countInfoMap.get(k));
									
								} else {
									t.put(countMap.get(k), 1);
									s.put(countMap.get(k), countInfoMap.get(k));
								}
								countCountMap.put(k, t);
								countCountInfoMap.put(k, s);
								
							} else {
								Map<Integer, Integer> t = new HashMap<Integer, Integer>();
								Map<Integer, String> s = new HashMap<Integer, String>();
								
								t.put(countMap.get(k), 1);
								s.put(countMap.get(k), countInfoMap.get(k));
								
								countCountMap.put(k, t);
								countCountInfoMap.put(k, s);
							}
						}
						
						countMap.put(k, 0);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		for (int k:formulaIndexList) {
			if(countMap.containsKey(k)) {
				if(!maxCountMap.containsKey(k)) { 
					maxCountMap.put(k, countMap.get(k));
					maxCountInfoMap.put(k, countInfoMap.get(k));
				} else if(maxCountMap.get(k)<countMap.get(k)) {
					maxCountMap.put(k, countMap.get(k));
					maxCountInfoMap.put(k, countInfoMap.get(k));
				}
				
				if(countCountMap.containsKey(k)) {
					Map<Integer, Integer> t = countCountMap.get(k);
					Map<Integer, String> s = countCountInfoMap.get(k);
					
					if(t.containsKey(countMap.get(k))) {
						t.put(countMap.get(k), t.get(countMap.get(k))+1);
						s.put(countMap.get(k), s.get(countMap.get(k))+","+countInfoMap.get(k));
						
					} else {
						t.put(countMap.get(k), 1);
						s.put(countMap.get(k), countInfoMap.get(k));
					}
					countCountMap.put(k, t);
					countCountInfoMap.put(k, s);
					
				} else {
					Map<Integer, Integer> t = new HashMap<Integer, Integer>();
					Map<Integer, String> s = new HashMap<Integer, String>();
					
					t.put(countMap.get(k), 1);
					s.put(countMap.get(k), countInfoMap.get(k));
					
					countCountMap.put(k, t);
					countCountInfoMap.put(k, s);
				}
			}
		}
		
		for (int k:formulaIndexList) {
			String tmpStr = "公式序号："+k+",最大连续期数："+maxCountMap.get(k)+",结束期数："+maxCountInfoMap.get(k)+",最大连续期数出现次数："+countCountMap.get(k).get(maxCountMap.get(k))+
					", "+countCountInfoMap.get(k).get(maxCountMap.get(k));
			result.add(tmpStr);
			System.out.println(tmpStr);
		}
		
		return result;
	}
	
	public static List<String> buildMulFormulaMaxContinueRightCountDetail(List<Integer> formulaIndexList, int targetSsqIndex1,int targetSsqIndex2) {
		List<String> result = new ArrayList<String>();

		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao) ClientBeanUtil.getDao("ssqBaseStatsDao");

		String hql = "from RedFormulaCaclVerify s where s.targetSsqIndex >= ? and s.targetSsqIndex <= ? order by s.ssqIndex";
		
		List<BaseEntity> veryList = ssqBaseStatsDao.find(hql, new Integer[] { targetSsqIndex1, targetSsqIndex2 });
		
		// 连续次数的次数
		Map<Integer, Map<Integer, Integer>> countCountMap = new HashMap<Integer, Map<Integer, Integer>>();
		Map<Integer, Map<Integer, String>> countCountInfoMap = new HashMap<Integer, Map<Integer, String>>();
		
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		Map<Integer, String> countInfoMap = new HashMap<Integer, String>();
		
		Map<Integer, Integer> maxCountMap = new HashMap<Integer, Integer>();
		Map<Integer, String> maxCountInfoMap = new HashMap<Integer, String>();
		
		for(int i=0;i<veryList.size();i++) {
			RedFormulaCaclVerify caclVery = (RedFormulaCaclVerify) veryList.get(i);
			
			Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
			
			for (int k:formulaIndexList) {
				try {
					
					String methodName = "isKillFormula"+k+"Right";
					
					Method method = null;
					if (!methodMap.containsKey(k)) {
						method = RedFormulaCaclVerify.class.getMethod(methodName);
						methodMap.put(k, method);
					} else {
						method = methodMap.get(k);
					}
					
					boolean isRight = (Boolean) RedFormulaCaclVerify.class.getMethod(methodName).invoke(caclVery);
					
					if(!isRight) {
						if(countMap.containsKey(k)) {
							countMap.put(k, countMap.get(k)+1);
							countInfoMap.put(k, caclVery.getSsqIndex()+"");
						} else {
							countMap.put(k, 1);
							countInfoMap.put(k, caclVery.getSsqIndex()+"");
						}
					} else {
						if(countMap.containsKey(k)) {
							if(!maxCountMap.containsKey(k)) { 
								maxCountMap.put(k, countMap.get(k));
								maxCountInfoMap.put(k, countInfoMap.get(k));
							} else if(maxCountMap.get(k)<countMap.get(k)) {
								maxCountMap.put(k, countMap.get(k));
								maxCountInfoMap.put(k, countInfoMap.get(k));
							}
							
							if(countCountMap.containsKey(k)) {
								Map<Integer, Integer> t = countCountMap.get(k);
								Map<Integer, String> s = countCountInfoMap.get(k);
								
								if(t.containsKey(countMap.get(k))) {
									t.put(countMap.get(k), t.get(countMap.get(k))+1);
									s.put(countMap.get(k), s.get(countMap.get(k))+","+countInfoMap.get(k));
									
								} else {
									t.put(countMap.get(k), 1);
									s.put(countMap.get(k), countInfoMap.get(k));
								}
								countCountMap.put(k, t);
								countCountInfoMap.put(k, s);
								
							} else {
								Map<Integer, Integer> t = new HashMap<Integer, Integer>();
								Map<Integer, String> s = new HashMap<Integer, String>();
								
								t.put(countMap.get(k), 1);
								s.put(countMap.get(k), countInfoMap.get(k));
								
								countCountMap.put(k, t);
								countCountInfoMap.put(k, s);
							}
						}
						
						countMap.put(k, 0);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		for (int k:formulaIndexList) {
			if(countMap.containsKey(k)) {
				if(!maxCountMap.containsKey(k)) { 
					maxCountMap.put(k, countMap.get(k));
					maxCountInfoMap.put(k, countInfoMap.get(k));
				} else if(maxCountMap.get(k)<countMap.get(k)) {
					maxCountMap.put(k, countMap.get(k));
					maxCountInfoMap.put(k, countInfoMap.get(k));
				}
				
				if(countCountMap.containsKey(k)) {
					Map<Integer, Integer> t = countCountMap.get(k);
					Map<Integer, String> s = countCountInfoMap.get(k);
					
					if(t.containsKey(countMap.get(k))) {
						t.put(countMap.get(k), t.get(countMap.get(k))+1);
						s.put(countMap.get(k), s.get(countMap.get(k))+","+countInfoMap.get(k));
						
					} else {
						t.put(countMap.get(k), 1);
						s.put(countMap.get(k), countInfoMap.get(k));
					}
					countCountMap.put(k, t);
					countCountInfoMap.put(k, s);
					
				} else {
					Map<Integer, Integer> t = new HashMap<Integer, Integer>();
					Map<Integer, String> s = new HashMap<Integer, String>();
					
					t.put(countMap.get(k), 1);
					s.put(countMap.get(k), countInfoMap.get(k));
					
					countCountMap.put(k, t);
					countCountInfoMap.put(k, s);
				}
			}
		}
		
		for (int k:formulaIndexList) {
			String tmpStr = "公式序号："+k;
			result.add(tmpStr);
			Map<Integer, Integer> xx = countCountMap.get(k);
			for(Iterator<Integer> it = xx.keySet().iterator();it.hasNext();) {
				int key = it.next();
				if(key<2) {
					continue;
				}
				String str = "连续期数："+key+", 出现次数："+xx.get(key)+", 结束期数："+countCountInfoMap.get(k).get(key);
				result.add(str);
			}
			result.add("");
		}
		
		return result;
	}
	
	public static void main(String[] args) {
//		buildMulLaw(2013114);
//		buildMulFormula2(2013001, 2013116, 2, 2);
//		buildMulFormulaMaxContinueRightCount(2004001, 2013119);
		
		List<Integer> fList = new ArrayList<Integer>();
		fList.add(1);
		fList.add(2);
		fList.add(3);
		fList.add(4);
		fList.add(5);
		fList.add(6);
		fList.add(7);
		fList.add(8);
		
		List<String> tmp = buildMulFormulaMaxContinueRightCountDetail(fList, 2004001, 2013119);
		for(String str:tmp) {
			System.out.println(str);
		}
	}

	public static List<String> buildMulLaw(int ssqIndex) {
		List<String> result = new ArrayList<String>();
		
		List<String> list_36_38 = buildMulFormula(ssqIndex, 36, 38);
		List<String> list_44_44 = buildMulFormula(ssqIndex, 44, 44);
		List<String> list_45_50 = buildMulFormula(ssqIndex, 45, 50);
		List<String> list_44_50 = buildMulFormula(ssqIndex, 44, 50);
		List<String> list_43_43 = buildMulFormula(ssqIndex, 43, 43);
		
		Map<String, Integer> countMap = new HashMap<String, Integer>();
 		
		for(int i=1;i<=33;i++) {
			String tmp = SsqUtils.build2BitIntStr(i);
			if(list_36_38.contains(tmp)) {
				if(countMap.containsKey(tmp)) {
					countMap.put(tmp, countMap.get(tmp)+1);
				} else {
					countMap.put(tmp, 1);
				}
			} 
			
			if(list_44_44.contains(tmp)) {
				if(countMap.containsKey(tmp)) {
					countMap.put(tmp, countMap.get(tmp)+1);
				} else {
					countMap.put(tmp, 1);
				}
			}
			
			if(list_45_50.contains(tmp)) {
				if(countMap.containsKey(tmp)) {
					countMap.put(tmp, countMap.get(tmp)+1);
				} else {
					countMap.put(tmp, 1);
				}
			}
			
			if(list_43_43.contains(tmp)) {
				if(countMap.containsKey(tmp)) {
					countMap.put(tmp, countMap.get(tmp)+1);
				} else {
					countMap.put(tmp, 1);
				}
			}
		}
		
		List<String> listNo = new ArrayList<String>();
		List<String> listHas = new ArrayList<String>();
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		
		for(int i=1;i<=33;i++) {
			String tmp = SsqUtils.build2BitIntStr(i);
			if(countMap.get(tmp)==null) {
				listNo.add(tmp);
			}  else {
				listHas.add(tmp);
				if(countMap.get(tmp)==1) {
					list1.add(tmp);
				} else if(countMap.get(tmp)==2) {
					list2.add(tmp);
				} else if(countMap.get(tmp)>=3) {
					list3.add(tmp);
				}
			}
		}
		
		result.add("36-38次的: "+buildAStr(list_36_38, 0, 3));
		result.add("44-44次的: "+buildAStr(list_44_44, 0, 3));
		result.add("45-50次的: "+buildAStr(list_45_50, 0, 3));
		result.add("44-50次的: "+buildAStr(list_44_50, 3, 5));
		result.add("43-43次的: "+buildAStr(list_43_43, 1, 3));
		
		result.add("1次的: "+buildAStr(list1, 0, 3));
		result.add("2次的: "+buildAStr(list2, 0, 3));
		result.add("3次的: "+buildAStr(list3, 0, 3));
		result.add("has: "+buildAStr(listHas, 3, 5));
		result.add("no: "+buildAStr(listNo, 2, 3));
		
		return result;
	}
	
	public static String buildAStr(List<String> strList, int count1,int count2) { 
		String str = count1+":";
		for(String tmp:strList) {
			str = str+tmp+",";
		}
		
		if(strList.size()>0) {
			str = str.substring(0,str.length()-1);
		}
		
		str = str+":"+count2;
		return str;
	}
}
