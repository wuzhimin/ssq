package com.ssq.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ssq.common.util.FileUtil;

/**
 * 余数过滤util
 * @author Administrator
 *
 */
public class ResidueFilterUtil {
	
	/**
	 * 创建余数特性字串列表
	 * @param divisor    除数
	 * @return
	 */
	public static List<String> buildResidueFeatureList(int divisor) {
		List<String> result = new ArrayList<String>();
		try {
			List<String> ssqList = FileUtil.getSSQHistoryList();
			for(String str:ssqList) {
				String redStr = str.substring(8).substring(0,17).replaceAll(" ", ",");
				String[] tmp = redStr.split(",");
				int r1 = Integer.parseInt(tmp[0]) % divisor;
				int r2 = Integer.parseInt(tmp[1]) % divisor;
				int r3 = Integer.parseInt(tmp[2]) % divisor;
				int r4 = Integer.parseInt(tmp[3]) % divisor;
				int r5 = Integer.parseInt(tmp[4]) % divisor;
				int r6 = Integer.parseInt(tmp[5]) % divisor;
				String featureStr = r1+""+r2+""+r3+""+r4+""+r5+""+r6;
				if(!result.contains(featureStr)) {
					result.add(featureStr);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 创建某一年份的余数特性字串列表
	 * @param divisor    除数
	 * @param year       年份
	 * @return
	 */
	public static List<String> buildResidueFeatureList(int divisor, int year) {
		List<String> result = new ArrayList<String>();
		try {
			List<String> ssqList = FileUtil.getSSQHistoryList(year);
			for(String str:ssqList) {
				String redStr = str.substring(8).substring(0,17).replaceAll(" ", ",");
				String[] tmp = redStr.split(",");
				int r1 = Integer.parseInt(tmp[0]) % divisor;
				int r2 = Integer.parseInt(tmp[1]) % divisor;
				int r3 = Integer.parseInt(tmp[2]) % divisor;
				int r4 = Integer.parseInt(tmp[3]) % divisor;
				int r5 = Integer.parseInt(tmp[4]) % divisor;
				int r6 = Integer.parseInt(tmp[5]) % divisor;
				String featureStr = r1+""+r2+""+r3+""+r4+""+r5+""+r6;
				
				if(!result.contains(featureStr)) {
					result.add(featureStr);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public static List<String> buildResidue4ContinueFeatureList(int divisor) {
		List<String> result = new ArrayList<String>();
		try {
			List<String> ssqList = FileUtil.getSSQHistoryList();
			for(String str:ssqList) {
				String redStr = str.substring(8).substring(0,17).replaceAll(" ", ",");
				String[] tmp = redStr.split(",");
				int r1 = Integer.parseInt(tmp[0]) % divisor;
				int r2 = Integer.parseInt(tmp[1]) % divisor;
				int r3 = Integer.parseInt(tmp[2]) % divisor;
				int r4 = Integer.parseInt(tmp[3]) % divisor;
				int r5 = Integer.parseInt(tmp[4]) % divisor;
				int r6 = Integer.parseInt(tmp[5]) % divisor;
				
				boolean b1 = (r4==r3+1 && r3==r2+1 && r2==r1+1);
				boolean b2 = (r5==r4+1 && r4==r3+1 && r3==r2+1);
				boolean b3 = (r6==r5+1 && r5==r4+1 && r4==r3+1);
				
				if(b1 || b2 || b3) {
					String featureStr = str+"--------"+r1+" "+r2+" "+r3+" "+r4+" "+r5+" "+r6;
					result.add(featureStr);
				}
				
//				Map<Integer, String> map = new HashMap<Integer, String>();
//				map.put(r1, null);
//				map.put(r2, null);
//				map.put(r3, null);
//				map.put(r4, null);
//				map.put(r5, null);
//				map.put(r6, null);
				
//				for( Iterator<Integer> it = map.keySet().iterator();it.hasNext();) {
//					int a = it.next();
//					if(map.containsKey(a+1) && map.containsKey(a+2) &&map.containsKey(a+3) ) {
//						String featureStr = str+"--------"+r1+" "+r2+" "+r3+" "+r4+" "+r5+" "+r6;
//						result.add(featureStr);
//						break;
//					}
//				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> test = buildResidue4ContinueFeatureList(10);
		try {
			FileUtil.writeToFile("D:/ddd/guilv/tmp1.txt", test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(test);

	}

}
