package com.ssq.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ssq.common.util.FileUtil;

/**
 * 相减过滤util
 * @author Administrator
 *
 */
public class SubsetFilterUtil {
	
	/**
	 * 创建相减数规律
	 * @param divisor    除数
	 * @return
	 */
	public static List<String> buildSubsetFeatureList(List<String> ssqList) {
		List<String> result = new ArrayList<String>();
		try {
			for(String str:ssqList) {
				String redStr = str.substring(0,17).replaceAll(" ", ",");
				String[] tmp = redStr.split(",");
				int r1 = Integer.parseInt(tmp[0]);
				int r2 = Integer.parseInt(tmp[1]);
				int r3 = Integer.parseInt(tmp[2]);
				int r4 = Integer.parseInt(tmp[3]);
				int r5 = Integer.parseInt(tmp[4]);
				int r6 = Integer.parseInt(tmp[5]);
				
				int t1 = r2-r1;
				int t2 = r3-r2;
				int t3 = r4-r3;
				int t4 = r5-r4;
				int t5 = r6-r5;
				
				
				String featureStr = str+"----"+ (t1<10?"0"+t1:t1+"")+ " " +  (t2<10?"0"+t2:t2+"") +" " +    (t3<10?"0"+t3:t3+"")
						+ " " +   (t4<10?"0"+t4:t4+"") +" " +    (t5<10?"0"+t5:t5+"");
				
				result.add(featureStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static List<String> buildSubset4ContinueFeatureList() {
		List<String> result = new ArrayList<String>();
		try {
			List<String> ssqList = FileUtil.getSSQHistoryList();
			for(String str:ssqList) {
				String redStr = str.substring(8).substring(0,17).replaceAll(" ", ",");
				String[] tmp = redStr.split(",");
				int r1 = Integer.parseInt(tmp[0]);
				int r2 = Integer.parseInt(tmp[1]);
				int r3 = Integer.parseInt(tmp[2]);
				int r4 = Integer.parseInt(tmp[3]);
				int r5 = Integer.parseInt(tmp[4]);
				int r6 = Integer.parseInt(tmp[5]);
				
				int t1 = r2-r1;
				int t2 = r3-r2;
				int t3 = r4-r3;
				int t4 = r5-r4;
				int t5 = r6-r5;
				
				boolean b1 = (t4==t3+4 && t3==t2+4 );
				boolean b2 = (t5==t4+4 && t4==t3+4 );
				boolean b3 = (t3==t2+4 && t2==t1+4 );
				
				if(b1 || b2 || b3) {
					String featureStr = str+"--------"+t1+" "+t2+" "+t3+" "+t4+" "+t5;
					result.add(featureStr);
				}
				
//				Map<Integer, String> map = new HashMap<Integer, String>();
//				map.put(t1, null);
//				map.put(t2, null);
//				map.put(t3, null);
//				map.put(t4, null);
//				map.put(t5, null);
//				
//				for( Iterator<Integer> it = map.keySet().iterator();it.hasNext();) {
//					int a = it.next();
//					if(map.containsKey(a+1) && map.containsKey(a+2) &&map.containsKey(a+3) ) {
//						String featureStr = str+"--------"+t1+" "+t2+" "+t3+" "+t4+" "+t5;
//						result.add(featureStr);
//						break;
//					}
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		List<String> ssqList = null;
//		try {
//			ssqList = FileUtil.readLineFileWithoutCheckRepeat("C:/Users/Administrator/Documents/编辑3.TXT");
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		List<String> test = buildSubsetFeatureList(ssqList);
		
		List<String> test = buildSubset4ContinueFeatureList();
		
		try {
			FileUtil.writeToFile("D:/ddd/guilv/tmp1.txt", test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(test);

	}

}
