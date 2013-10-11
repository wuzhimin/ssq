package com.wzm.biz;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

public class MulFormulaLawBuild {
	
	/**
	 * 
	 * @param ssqIndex  当前期
	 * @param continueRightCount1  连续预测正确最小期数
	 * @param continueRightCount2  连续预测正确最大期数
	 * @return
	 */
	public static List<String> buildMulFormulaContinueRight(int ssqIndex, int continueRightCount1, int continueRightCount2) {
		List<String> result= new ArrayList<String>();
		
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");
		
		String hql = "from RedFormulaCaclVerify s where s.targetSsqIndex <= ? order by s.targetSsqIndex";
		List<BaseEntity> verifyList = ssqBaseStatsDao.find(hql, new Integer[]{ssqIndex});
		
		hql = "from RedFormulaCacl s where s.ssqIndex = ? ";
		List<BaseEntity> caclList = ssqBaseStatsDao.find(hql, new Integer[]{ssqIndex});
		
		Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
		
		for(int i=verifyList.size()-1;i>=1400;i-- ) {
			RedFormulaCaclVerify v1 = (RedFormulaCaclVerify) verifyList.get(i);
			
			String hql3 = "from RedFormulaCacl s where s.targetSsqIndex = ? ";
			List<BaseEntity> tmpCaclList = ssqBaseStatsDao.find(hql3, new Integer[]{v1.getTargetSsqIndex()});
			RedFormulaCacl tmpCacl = (RedFormulaCacl) tmpCaclList.get(0);
			
			String str = "当前期："+v1.getSsqIndex()+"------"+"预测期："+v1.getTargetSsqIndex();
			
			Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
			List<Integer> extList = new ArrayList<Integer>();
			
			for(int j=i;j>=1;j--) {
				RedFormulaCaclVerify v2 = (RedFormulaCaclVerify) verifyList.get(j);
				
				
				for(int k=1;k<=91;k++) {
					if(extList.contains(k)) {
						continue;
					}
					
					try {
						Method m1 = null;
						if(methodMap.containsKey(k)) {
							m1 = methodMap.get(k);
						} else {
							String mName = "isKillFormula"+k+"Right";
							m1 = RedFormulaCaclVerify.class.getMethod(mName);
							methodMap.put(k, m1);
						}
						
						boolean isRight = (Boolean) m1.invoke(v2);
						
						if(isRight) {
							if(countMap.containsKey(k)) {
								countMap.put(k, countMap.get(k)+1);
							} else {
								countMap.put(k, 1);
							}
						} else {
							extList.add(k);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
				
				if(extList.size()>=91) {
					break;
				}
			}
			
			if(i==verifyList.size()-1) {
				RedFormulaCacl cacl = (RedFormulaCacl) caclList.get(0);
				String tmpStr = "";
				List<String> tmpList = new ArrayList<String>();
				for(int k=1;k<=91;k++) {
					if(countMap.containsKey(k) && countMap.get(k)>=continueRightCount1 && countMap.get(k)<=continueRightCount2) {
						try {
							boolean isRight = (Boolean) methodMap.get(k).invoke(v1);
							if(isRight) {
								String methodName = "getFormula"+k+"Value";
								int tmp = (Integer)RedFormulaCacl.class.getMethod(methodName).invoke(cacl);
								result.add("公式序号："+SsqUtils.build2BitIntStr(k)+"---"+"连续正确数："+SsqUtils.build2BitIntStr(countMap.get(k))+"---"+"预测值："+SsqUtils.build2BitIntStr(tmp));
								if(!tmpList.contains(SsqUtils.build2BitIntStr(tmp))) {
									tmpList.add(SsqUtils.build2BitIntStr(tmp));
								}
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						} 
					}
				}
				Collections.sort(tmpList);
				for(String str1:tmpList) {
					tmpStr = tmpStr+str1+" ";
				}
				result.add("连续"+continueRightCount1+"---"+continueRightCount2+"期正确："+tmpStr);
				result.add("\n");
				
			}
			List<String> tmp = new ArrayList<String>();
			boolean isAddStr = false;
			for(int k=1;k<=91;k++) {
				if(countMap.containsKey(k) && countMap.get(k)>=continueRightCount1 && countMap.get(k)<=continueRightCount2) {
					try {
						boolean isRight = (Boolean) methodMap.get(k).invoke(v1);
						if(!isRight) {
							isAddStr = true;
						}
						
						String mName = "getFormula"+k+"Value";
						Method m3 = RedFormulaCacl.class.getMethod(mName);
						int value = (Integer)m3.invoke(tmpCacl);
						
						tmp.add("公式序号："+SsqUtils.build2BitIntStr(k)+"------"+"连续正确数："+SsqUtils.build2BitIntStr(countMap.get(k))+"----"+", 预测值："+SsqUtils.build2BitIntStr(value)+", 预测是否正确："+isRight);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if(isAddStr) {
				result.add(str+"----预测有错");
			} else {
				result.add(str);
			}
			result.addAll(tmp);
			result.add("\n");
		}
		return result;
	}
	
	public static List<String> buildMulFormulaContinueRight1(int ssqIndex, int continueRightCount1, int continueRightCount2) {
		List<String> result= new ArrayList<String>();
		
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");
		SsqRecordDao ssqRecordDao = (SsqRecordDao)ClientBeanUtil.getDao("ssqRecordDao");
		
		String hql = "from SsqRecord s where s.ssqIndex <= ? order by s.ssqIndex";
		List<SsqRecord> recordList = ssqRecordDao.findSsqRecordsByHql(hql,new Integer[]{ssqIndex});
		
		String hql1 = "from RedFormulaCaclVerify s where s.targetSsqIndex = ? ";
		String hql2 = "from RedFormulaCaclVerify s where s.targetSsqIndex <= ? order by s.targetSsqIndex";
		
		Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
		
		int count = 0;
		for(int m=recordList.size()-1;count<=50;m-- ) {
			int currentSsqIndex = recordList.get(m).getSsqIndex();
			int nextSsqIndex = recordList.get(m).getNextSsqIndex();
			String str = "当前期：" + currentSsqIndex +"------"+"预测期：" + nextSsqIndex;
			
			List<BaseEntity> verifyList1 = ssqBaseStatsDao.find(hql1, new Integer[]{nextSsqIndex});
			
			if(verifyList1.size()<=0) {
				List<BaseEntity> verifyList = ssqBaseStatsDao.find(hql2, new Integer[]{currentSsqIndex});
				
				hql = "from RedFormulaCacl s where s.ssqIndex = ? ";
				List<BaseEntity> caclList = ssqBaseStatsDao.find(hql, new Integer[]{currentSsqIndex});
				RedFormulaCacl tmpCacl = (RedFormulaCacl) caclList.get(0);
				
				// 计数map
				Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
				
				List<Integer> extList = new ArrayList<Integer>();
				
				// 计算连续正确数
				for(int i=verifyList.size()-1;i>=0 && extList.size()<91;i-- ) {
					RedFormulaCaclVerify v = (RedFormulaCaclVerify) verifyList.get(i);
					for(int k=1;k<=91;k++) {
						if(extList.contains(k)) {
							continue;
						}
						
						try {
							Method m1 = null;
							if(methodMap.containsKey(k)) {
								m1 = methodMap.get(k);
							} else {
								String mName = "isKillFormula"+k+"Right";
								m1 = RedFormulaCaclVerify.class.getMethod(mName);
								methodMap.put(k, m1);
							}
							
							boolean isRight = (Boolean) m1.invoke(v);
							
							if(isRight) {
								if(countMap.containsKey(k)) {
									countMap.put(k, countMap.get(k)+1);
								} else {
									countMap.put(k, 1);
								}
							} else {
								extList.add(k);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
					
				}
				
				List<String> tmp = new ArrayList<String>();
				List<String> tmpList = new ArrayList<String>();
				
				for(int k=1;k<=91;k++) {
					if(countMap.containsKey(k) && countMap.get(k)>=continueRightCount1 && countMap.get(k)<=continueRightCount2) {
						try {
							String mName = "getFormula"+k+"Value";
							Method m3 = RedFormulaCacl.class.getMethod(mName);
							int value = (Integer)m3.invoke(tmpCacl);
							
							tmp.add("公式序号："+SsqUtils.build2BitIntStr(k)+"------"+"连续正确数："+SsqUtils.build2BitIntStr(countMap.get(k))+"----"
									+", 预测值："+SsqUtils.build2BitIntStr(value));
							
							if(!tmpList.contains(SsqUtils.build2BitIntStr(value))) {
								tmpList.add(SsqUtils.build2BitIntStr(value));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				Collections.sort(tmpList);
				String tmpStr = "";
				for(String str1:tmpList) {
					tmpStr = tmpStr+str1+" ";
				}
				result.add(str);
				result.addAll(tmp);
				result.add("连续"+continueRightCount1+"---"+continueRightCount2+"期正确："+tmpStr);
				result.add("\n");
				
			} else {
				RedFormulaCaclVerify v1 = (RedFormulaCaclVerify) verifyList1.get(0);

				List<BaseEntity> verifyList = ssqBaseStatsDao.find(hql2, new Integer[]{currentSsqIndex});
				
				hql = "from RedFormulaCacl s where s.ssqIndex = ? ";
				List<BaseEntity> caclList = ssqBaseStatsDao.find(hql, new Integer[]{currentSsqIndex});
				RedFormulaCacl tmpCacl = (RedFormulaCacl) caclList.get(0);
				
				// 计数map
				Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
				
				List<Integer> extList = new ArrayList<Integer>();
				
				// 计算连续正确数
				for(int i=verifyList.size()-1;i>=0 && extList.size()<91;i-- ) {
					RedFormulaCaclVerify v = (RedFormulaCaclVerify) verifyList.get(i);
					for(int k=1;k<=91;k++) {
						if(extList.contains(k)) {
							continue;
						}
						
						try {
							Method m1 = null;
							if(methodMap.containsKey(k)) {
								m1 = methodMap.get(k);
							} else {
								String mName = "isKillFormula"+k+"Right";
								m1 = RedFormulaCaclVerify.class.getMethod(mName);
								methodMap.put(k, m1);
							}
							
							boolean isRight = (Boolean) m1.invoke(v);
							
							if(isRight) {
								if(countMap.containsKey(k)) {
									countMap.put(k, countMap.get(k)+1);
								} else {
									countMap.put(k, 1);
								}
							} else {
								extList.add(k);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
					
				}
				
				List<String> tmp = new ArrayList<String>();
				List<String> tmpList = new ArrayList<String>();
				boolean isAddStr = false;
				for(int k=1;k<=91;k++) {
					if(countMap.containsKey(k) && countMap.get(k)>=continueRightCount1 && countMap.get(k)<=continueRightCount2) {
						try {
							boolean isRight = (Boolean) methodMap.get(k).invoke(v1);
							if(!isRight) {
								isAddStr = true;
							}
							
							String mName = "getFormula"+k+"Value";
							Method m3 = RedFormulaCacl.class.getMethod(mName);
							int value = (Integer)m3.invoke(tmpCacl);
							
							tmp.add("公式序号："+SsqUtils.build2BitIntStr(k)+"------"+"连续正确数："+SsqUtils.build2BitIntStr(countMap.get(k))+"----"
									+", 预测值："+SsqUtils.build2BitIntStr(value)+", 预测是否正确："+isRight);
							
							if(!tmpList.contains(SsqUtils.build2BitIntStr(value))) {
								tmpList.add(SsqUtils.build2BitIntStr(value));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				Collections.sort(tmpList);
				String tmpStr = "";
				for(String str1:tmpList) {
					tmpStr = tmpStr+str1+" ";
				}
				if(isAddStr) {
					result.add(str+"，预测有错");
				} else {
					result.add(str);
				}
				result.addAll(tmp);
				result.add("连续"+continueRightCount1+"---"+continueRightCount2+"期正确："+tmpStr);
				result.add("\n");
			}
			
			count++;
		}
		
		return result;
	}
	
	public static List<String> buildMulFormula50Right(int ssqIndex1, int ssqIndex2, int rightCount1, int rightCount2) {
		List<String> result= new ArrayList<String>();
		
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");
		SsqRecordDao ssqRecordDao = (SsqRecordDao)ClientBeanUtil.getDao("ssqRecordDao");
		
		String hql1 = "from RedFormulaCaclMulStats s where s.toSsqIndex = ? and s.spaceNum = 50";
		List<BaseEntity> statsList = ssqBaseStatsDao.find(hql1, new Integer[] { ssqIndex2 });
		RedFormulaCaclMulStats stats = (RedFormulaCaclMulStats) statsList.get(0);
		
		String hql = "from RedFormulaCacl s where s.ssqIndex = ? order by s.ssqIndex";
		List<BaseEntity> caclList = ssqBaseStatsDao.find(hql, new Integer[]{ssqIndex2});
		RedFormulaCacl cacl = (RedFormulaCacl) caclList.get(0);
		
		Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
		Map<Integer, Method> methodMap2 = new HashMap<Integer, Method>();
		
		String str1 = "";
		List<String> tmpList = new ArrayList<String>();
		for(int k=1;k<=91;k++) {
			try {
				Method m1 = null;
				if(methodMap.containsKey(k)) {
					m1 = methodMap.get(k);
				} else {
					String mName = "getFormula"+k+"KillRightCount";
					m1 = RedFormulaCaclMulStats.class.getMethod(mName);
					methodMap.put(k, m1);
				}
				
				int rightCount = (Integer) m1.invoke(stats);
				
				if(rightCount>=rightCount1 && rightCount<=rightCount2) {
					String mName = "getFormula"+k+"Value";
					Method m2 = RedFormulaCacl.class.getMethod(mName);
					int tmp = (Integer)m2.invoke(cacl);
						
					if(!tmpList.contains(SsqUtils.build2BitIntStr(tmp))) {
						tmpList.add(SsqUtils.build2BitIntStr(tmp));
					}
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		Collections.sort(tmpList);
		for(String aa:tmpList) {
			str1 = str1 + aa+" ";
		}
		result.add(str1);
		
		List<SsqRecord> recordList = ssqRecordDao.findSsqRecordsByIndexScope(ssqIndex1, ssqIndex2);
		
		for(int i=recordList.size()-1;i>=1;i--) {
			SsqRecord r = recordList.get(i);
			int ssqIndex = r.getSsqIndex();
			
			SsqRecord r1 = recordList.get(i-1);
			int ssqIndex_ = r1.getSsqIndex();
			
			result.add("--------预测期："+ssqIndex+"--------");
			result.add("--------50期内正确数:"+rightCount1+"----"+rightCount2+"--------");
			
			hql1 = "from RedFormulaCaclMulStats s where s.toSsqIndex = ? and s.spaceNum = 50";
			statsList = ssqBaseStatsDao.find(hql1, new Integer[] { ssqIndex_ });
			stats = (RedFormulaCaclMulStats) statsList.get(0);

			String hql2 = "from RedFormulaCaclVerify s where s.targetSsqIndex = ? ";
			List<BaseEntity> verifyList = ssqBaseStatsDao.find(hql2, new Integer[] { ssqIndex });
			RedFormulaCaclVerify verify = (RedFormulaCaclVerify) verifyList.get(0);
			
			String hql3 = "from RedFormulaCacl s where s.targetSsqIndex = ? ";
			caclList = ssqBaseStatsDao.find(hql3, new Integer[]{ssqIndex});
			cacl = (RedFormulaCacl) caclList.get(0);
			
			for(int k=1;k<=91;k++) {
				try {
					Method m1 = null;
					if(methodMap.containsKey(k)) {
						m1 = methodMap.get(k);
					} else {
						String mName = "getFormula"+k+"KillRightCount";
						m1 = RedFormulaCaclMulStats.class.getMethod(mName);
						methodMap.put(k, m1);
					}
					
					int rightCount = (Integer) m1.invoke(stats);
					
					if(rightCount>=rightCount1 && rightCount<=rightCount2) {
						Method m2 = null;
						if(methodMap2.containsKey(k)) {
							m2 = methodMap2.get(k);
						} else {
							String mName = "isKillFormula"+k+"Right";
							m2 = RedFormulaCaclVerify.class.getMethod(mName);
							methodMap2.put(k, m2);
						}
						boolean isRight = (Boolean) m2.invoke(verify);
						
						String mName = "getFormula"+k+"Value";
						Method m3 = RedFormulaCacl.class.getMethod(mName);
						int tmp = (Integer)m3.invoke(cacl);
						
						String str = "公式编号:"+SsqUtils.build2BitIntStr(k)+" ,预测值："+SsqUtils.build2BitIntStr(tmp)+" ,预测是否正确："+isRight;
						result.add(str);
					}	
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			result.add("\n");
		}
		
		
		return result;
	}
	
	public static void main(String[] args) {
//		List<String> lis = buildMulFormula50Right(2013001, 2013113, 44, 44);
		
		List<String> lis = buildMulFormulaContinueRight(2013119, 3, 4);
		
		for(String str:lis)
			System.out.println(str);
	}
}
