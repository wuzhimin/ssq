package com.ssq.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.CombinationUtil;
import com.ssq.common.util.FileUtil;

/**
 * 尾数过滤util
 * @author Administrator
 *
 */
public class TailFilterUtil {
	
	/**
	 * 创建某几个尾数个数列表
	 * @param divisor    除数
	 * @return
	 */
	public static List<String> buildTailCountFeatureList(int year, List<String> tailsList, int count1) {
		List<String> result = new ArrayList<String>();
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		Map<String, Integer> extendMap = new HashMap<String, Integer>();
		try {
			List<String> ssqList = FileUtil.getSSQHistoryList(year);
			
			
			for(String str:ssqList) {
				String redStr = str.substring(8).substring(0,17).replaceAll(" ", ",");
				String[] tmp = redStr.split(",");
				int r1 = Integer.parseInt(tmp[0]) % 10;
				int r2 = Integer.parseInt(tmp[1]) % 10;
				int r3 = Integer.parseInt(tmp[2]) % 10;
				int r4 = Integer.parseInt(tmp[3]) % 10;
				int r5 = Integer.parseInt(tmp[4]) % 10;
				int r6 = Integer.parseInt(tmp[5]) % 10;
				
				String featureStr = "";
				for(String tails:tailsList) {
					String[] tmp1 = tails.split(",");
					int count = 0;
					for(int j=0;j<tmp1.length;j++) {
						if(r1==Integer.parseInt(tmp1[j])) {
							count++;
						}
						
						if(r2==Integer.parseInt(tmp1[j])) {
							count++;
						}
						
						if(r3==Integer.parseInt(tmp1[j])) {
							count++;
						}
						
						if(r4==Integer.parseInt(tmp1[j])) {
							count++;
						}
						
						if(r5==Integer.parseInt(tmp1[j])) {
							count++;
						}
						
						if(r6==Integer.parseInt(tmp1[j])) {
							count++;
						}
					}
					
//					if(!extendMap.containsKey(tails)) {
//						if(countMap.containsKey(tails)) {
//							if(count==4) {
//								countMap.put(tails, countMap.get(tails)+1);
//							} else if(count>4){
//								countMap.remove(tails);
//								extendMap.put(tails, null);
//							}
//						} else {
//							if(count==4) {
//								countMap.put(tails, 1);
//							} else if(count>4){
//								extendMap.put(tails, null);
//							}
//						}
//					}
					
					if(!extendMap.containsKey(tails)) {
						if(countMap.containsKey(tails)) {
							if(count>=5) {
								countMap.put(tails, countMap.get(tails)+1);
							}
						} else {
							if(count>=5) {
								countMap.put(tails, 1);
							} 
						}
					}
					
					if(featureStr.equals("")) {
						featureStr = str+"---"+tails+":"+count+"    ";
					} else {
						featureStr = featureStr+"---"+tails+":"+count+"    ";
					}
				}
				result.add(featureStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Iterator<String> it = countMap.keySet().iterator();it.hasNext();) {
			String key = it.next();
			if(countMap.get(key)<=count1)
			System.out.println(key+"------"+countMap.get(key));
		}
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> tailsList = new ArrayList<String>();
		
		// 第一阵营
//		tailsList.add("01,02,03,08");
//		tailsList.add("01,02,05,09");
//		tailsList.add("01,02,06,07");
//		tailsList.add("01,02,05,06");
//		tailsList.add("01,02,04,08");
//		tailsList.add("00,01,03,08");
//		tailsList.add("02,03,05,07");
//		tailsList.add("02,04,05,07");
//		tailsList.add("01,02,06,09");
		
		
		CombinationUtil.createCombo(new String[]{"01","02","03","04","05","06","07","08","09","00",}, 4, 4, tailsList, ",");
		
		// 第二阵营
//		tailsList.add("00,02,03,07");
//		tailsList.add("00,01,02,05");
//		tailsList.add("00,02,07,09");
//		tailsList.add("01,03,05,08");
//		tailsList.add("02,03,04,09");
//		tailsList.add("02,03,08,09");
//		tailsList.add("03,04,08,09");
//		tailsList.add("02,03,07,09");
//		tailsList.add("01,02,03,07");
//		tailsList.add("00,01,03,06");
//		tailsList.add("01,02,03,09");
//		tailsList.add("02,05,08,09");
//		tailsList.add("00,02,03,09");
//		tailsList.add("00,02,03,04");
//		tailsList.add("02,03,06,08");
//		tailsList.add("00,01,02,04");
//		tailsList.add("03,04,07,09");
//		tailsList.add("03,04,06,08");
//		tailsList.add("00,02,05,06");
//		tailsList.add("02,03,04,08");
		
//		tailsList.add("02,03,06,09");
//		tailsList.add("02,04,07,08");
//		tailsList.add("01,03,06,09");
		
//		tailsList.add("05,06,09");
//		tailsList.add("05,06,07");
//		tailsList.add("05,09,00");
//		tailsList.add("07,08,00");
//		tailsList.add("05,06,08");
//		tailsList.add("04,07,09");
//		tailsList.add("04,07,08");
//		tailsList.add("04,06,07");
//		tailsList.add("03,04,07");
//		tailsList.add("04,07,00");
//		tailsList.add("06,08,09");
//		tailsList.add("06,09,00");
//		tailsList.add("01,08,09");
//		tailsList.add("01,05,08");
//		tailsList.add("05,07,09");
//		tailsList.add("05,07,08");
//		tailsList.add("06,08,00");
//		tailsList.add("01,09,00");
//		tailsList.add("03,05,09");
//		tailsList.add("05,06,00");
//		tailsList.add("05,08,00");
//		tailsList.add("06,07,08");
//		tailsList.add("04,05,00");
//		tailsList.add("07,08,09");
//		tailsList.add("05,07,00");
//		tailsList.add("02,05,00");
		
		
		
		
		List<String> test = buildTailCountFeatureList(2013, tailsList, 100);
		
		try {
			FileUtil.writeToFile("D:/ddd/guilv/tmp1.txt", test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(test);

	}

}
