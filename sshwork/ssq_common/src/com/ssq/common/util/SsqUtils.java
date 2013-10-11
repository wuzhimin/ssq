package com.ssq.common.util;

import java.security.SecureRandom;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SsqUtils {
	
	private static NumberFormat nf;
	private static NumberFormat nf1;

	/**
	 * 产生随机投注
	 * @param count   投注数
	 * @return
	 */
	public static List<String> genRandomBets(int count) {
		Map<Integer, Integer> validMap = new HashMap<Integer, Integer>();

		SecureRandom ra = new SecureRandom();
		List<String> result = new ArrayList<String>();
		for (int x = 0; x < count; x++) {
			int index = 0;

			// 随机红球
			List<Integer> tmp = new ArrayList<Integer>();
			while (index < 6) {
				int aa = ra.nextInt(33);
				if (!validMap.containsKey(new Integer(aa)) && aa != 0) {
					validMap.put(aa, null);
					tmp.add(aa);
					index++;
				}
			}
			
			Collections.sort(tmp);

			// 随机篮球
			int blue = ra.nextInt(16);
			while (blue == 0) {
				blue = ra.nextInt(16);
			}
			tmp.add(new Integer(blue));

			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < tmp.size(); j++) {
				sb.append((((Integer) tmp.get(j)).intValue() < 10 ? "0"
						+ tmp.get(j) : tmp.get(j))
						+ " ");
			}
			result.add(sb.toString().substring(0, sb.toString().length() - 1));

			validMap.clear();
		}
		return result;
	}
	
	public static List<String> genRandomBetsWithoutBlue(int count) {
		Map<Integer, Integer> validMap = new HashMap<Integer, Integer>();

		SecureRandom ra = new SecureRandom();
		List<String> result = new ArrayList<String>();
		for (int x = 0; x < count; x++) {
			int index = 0;

			// 随机红球
			List<Integer> tmp = new ArrayList<Integer>();
			while (index < 6) {
				int aa = ra.nextInt(33);
				if (!validMap.containsKey(new Integer(aa)) && aa != 0) {
					validMap.put(aa, null);
					tmp.add(aa);
					index++;
				}
			}
			
			Collections.sort(tmp);

			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < tmp.size(); j++) {
				sb.append((((Integer) tmp.get(j)).intValue() < 10 ? "0"
						+ tmp.get(j) : tmp.get(j))
						+ " ");
			}
			result.add(sb.toString().substring(0, sb.toString().length() - 1));

			validMap.clear();
		}
		return result;
	}
	
	/**
	 * 从 sourceList中排除excludeList中的投注，包括篮球
	 * @param sourceList
	 * @param excludeList
	 */
	public static void excludeBets(List<String> sourceList, List<String> excludeList) {
		for(int i=sourceList.size()-1;i>=0;i--) {
			if(excludeList.contains(sourceList.get(i))) {
				sourceList.remove(i);
			}
		}
	}
	
	/**
	 * 从 sourceList中排除在excludeList中的红球投注，excludeList是红球投注集合
	 * @param sourceList
	 * @param excludeList
	 */
	public static void excludeRedBets(List<String> sourceList, List<String> excludeList) {
		for(int i=sourceList.size()-1;i>=0;i--) {
			String str = sourceList.get(i);
			str= str.substring(0,17);
			if(excludeList.contains(str)) {
				sourceList.remove(i);
			}
		}
	}
	
	
	/**
	 * 获得AC值
	 * 
	 * @param a
	 * @return
	 */
	public static int getACValue(int[] a) {
		Map<Integer, Integer> acMap = new HashMap<Integer, Integer>();
		for (int m = 0; m < a.length - 1; m++) {
			for (int j = m + 1; j < a.length; j++) {
				acMap.put(Math.abs(a[j] - a[m]), Math
						.abs(a[j] - a[m]));
			}
		}
		int count = 0;
		for (Iterator<Integer> it1 = acMap.keySet().iterator(); it1.hasNext();) {
			it1.next();
			count++;
		}
		count = count - 5;
		return count;
	}

	/**
	 * 获得散度
	 * 
	 * @param a
	 * @return
	 */
	public static int getSandu(int[] a) {
		int sandu = -1;
		for (int j = 1; j <= 33; j++) {
			int t1 = Math.abs(j - a[0]);
			int t2 = Math.abs(j - a[1]);
			int t3 = Math.abs(j - a[2]);
			int t4 = Math.abs(j - a[3]);
			int t5 = Math.abs(j - a[4]);
			int t6 = Math.abs(j - a[5]);

			if (t1 >= t2) {
				t1 = t2;
			}

			if (t1 >= t3) {
				t1 = t3;
			}

			if (t1 >= t4) {
				t1 = t4;
			}

			if (t1 >= t5) {
				t1 = t5;
			}

			if (t1 >= t6) {
				t1 = t6;
			}

			if (t1 >= sandu) {
				sandu = t1;
			}
		}
		return sandu;
	}
	
	/**
	 * 返回2位固定格式的整数字符串
	 * @param number
	 * @return
	 */
	public static String build2BitIntStr(int number) {
		if(nf==null) {
			nf = java.text.NumberFormat.getNumberInstance();  
			nf.setMinimumIntegerDigits(2);//整数显示最少位数不足前面补零  
			nf.setMaximumIntegerDigits(2);//整数显示最多位数超出前面截取  
			nf.setMinimumFractionDigits(0); //小数显示最少位数不足后面补零  
			nf.setMaximumFractionDigits(0); //小数显示最多位数超出四舍五入  
		}
		return nf.format(number);
	}
	
	public static String buildBitIntStr(int bit, int number) {
		if(nf1==null) {
			nf1 = java.text.NumberFormat.getNumberInstance();  
			nf1.setMinimumIntegerDigits(bit);//整数显示最少位数不足前面补零  
			nf1.setMaximumIntegerDigits(bit);//整数显示最多位数超出前面截取  
			nf1.setMinimumFractionDigits(0); //小数显示最少位数不足后面补零  
			nf1.setMaximumFractionDigits(0); //小数显示最多位数超出四舍五入  
		}
		return nf1.format(number);
	}
	
	public static void main(String[] args) {
		
		System.out.println(build2BitIntStr(2));
		System.out.println(build2BitIntStr(1));
		System.out.println(build2BitIntStr(9));
		
		
//		// 抽取双色球开奖记录
//		String ssqFileName = CommonConstant.SSQ_PATH + CommonConstant.SSQ_HISTORY_FILE;
//		
//		DataFactory dataFactory = new DataFactory();
//		
//		Map<String, String> result = null;
//		try {
//			result = FileUtil.readSSQHistory1(ssqFileName);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		while(true) {
//			List<String> dd = genRandomBets(5);
//			
//			// 保存数据
//			dataFactory.buildData(dd);
//			
//			// 校验是否往期中奖
//			for(String str:dd) {
//				if(result.containsKey(str)) {
//					System.out.println(str+"-------"+result.get(str));
//				}
//			}
//			
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
