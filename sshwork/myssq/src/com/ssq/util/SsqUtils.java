package com.ssq.util;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssq.control.DataFactory;

public class SsqUtils {
	
	/**
	 * 产生随机投注
	 * @param count   投注数
	 * @return
	 */
	public static List<String> genRandomBets(int count) {
//		Map<Integer, Integer> validMap = new HashMap<Integer, Integer>();
//
//		SecureRandom ra = new SecureRandom();
//		List<String> result = new ArrayList<String>();
//		for (int x = 0; x < count; x++) {
//			int index = 0;
//
//			// 随机红球
//			List<Integer> tmp = new ArrayList<Integer>();
//			while (index < 6) {
//				int aa = ra.nextInt(33);
//				if (!validMap.containsKey(new Integer(aa)) && aa != 0) {
//					validMap.put(aa, null);
//					tmp.add(aa);
//					index++;
//				}
//			}
//			
//			Collections.sort(tmp);
//
//			// 随机篮球
//			int blue = ra.nextInt(16);
//			while (blue == 0) {
//				blue = ra.nextInt(16);
//			}
//			tmp.add(new Integer(blue));
//
//			StringBuffer sb = new StringBuffer();
//			for (int j = 0; j < tmp.size(); j++) {
//				sb.append((((Integer) tmp.get(j)).intValue() < 10 ? "0"
//						+ tmp.get(j) : tmp.get(j))
//						+ " ");
//			}
//			result.add(sb.toString().substring(0, sb.toString().length() - 1));
//
//			validMap.clear();
//		}
//		return result;
		
		return getAStrList();
	}
	
	public static List<String> getAStrList(){
		List<String> result = new ArrayList<String>();
		for(int i=0;i<1000;i++) {
			result.add(String.valueOf(i));
		}
		
		return result;
	}
	
	/**
	 * 从 sourceList中排除excludeList中的投注
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
	
	public static void main(String[] args) {
		System.getProperties();
		System.out.println();
	}

}
