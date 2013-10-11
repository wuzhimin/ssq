package com.ssq.util.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssq.common.util.CombinationUtil;
import com.ssq.common.util.FileUtil;

/**
 * 历史开奖记录特征字串过滤，
 * @author Administrator
 *
 */
public class HistoryFeatureFilter implements Filter {
	
	private List<String> historyList = new ArrayList<String>();
	
	private String indexStr;
	
	public HistoryFeatureFilter(String indexStr) {
		try {
//			for(int year=2004;year<=2012;year++) {
//				historyList.addAll(FileUtil.getSSQHistoryList(year));
//			}
			
			historyList = FileUtil.getSSQHistoryList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<historyList.size();i++) {
			String str = historyList.get(i).substring(8).substring(0,17).replaceAll(" ", ",");
			
			String[] strs = indexStr.split("-");
			String[] strs1 = str.split(",");
			
			String featureStr = "";
			for(int j=0;j<strs.length;j++) {
				featureStr = featureStr+strs1[Integer.parseInt(strs[j]) -1];
				
			}
			historyList.set(i, featureStr);
		}
		
		this.indexStr = indexStr;
	}

	@Override
	public boolean doFilter(String str) {
		String[] strs = indexStr.split("-");
		String[] strs1 = str.split(",");
		
		String featureStr = "";
		for(int i=0;i<strs.length;i++) {
			featureStr = featureStr+strs1[Integer.parseInt(strs[i]) -1];
		}
		
		
		if(historyList != null && historyList.contains(featureStr) ) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String str = "历史开奖记录特征字串过滤,位置特征窜："+indexStr;
		return str;
	}
	
	public static void main(String[] args) {
		
		test1();
//		test2();
	}

	private static void test1() {
		List<String> ss = new ArrayList<String>();
		CombinationUtil.createCombo(new String[]{"01","02","03","04","05","06"}, 5, 5, ss, "-");
		
		List<String> historyList = null;
		try {
			historyList = FileUtil.getSSQHistoryList(2013);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> aa = new ArrayList<String>();
		
		for(String tmp:ss) {
			HistoryFeatureFilter f = new HistoryFeatureFilter(tmp);
			
			for(int i=historyList.size()-1;i>=0;i--) {
				String str = historyList.get(i).substring(8).substring(0,17).replaceAll(" ", ",");
				boolean b = f.doFilter(str);
				if(b) {
					System.out.println(historyList.get(i)+"-----"+tmp+"----->"+b);
					if(!aa.contains(historyList.get(i).substring(0,7))) {
						aa.add(historyList.get(i).substring(0,7));
					}
				}
			}
		}
		
		Collections.sort(aa);
		for(String tmp:aa)
		System.out.println(tmp);
		System.out.println(aa.size());
	}
	
	private static void test2() {
		List<String> ss = new ArrayList<String>();
		CombinationUtil.createCombo(new String[]{"01","02","03","04","05","06"}, 4, 4, ss, "-");
		
		List<String> betsList = null;
		try {
			betsList = FileUtil.readLineFileWithoutCheckRepeat("C:/Users/Administrator/Documents/编辑2.TXT");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> aa = new ArrayList<String>();
		
		for(String tmp:ss) {
			HistoryFeatureFilter f = new HistoryFeatureFilter(tmp);
			
			for(int i=betsList.size()-1;i>=0;i--) {
				String str = betsList.get(i);
				boolean b = f.doFilter(str);
				if(b) {
					System.out.println(str+"-----"+tmp+"----->"+b);
					
					if(!aa.contains(str)) {
						aa.add(str);
					}
					betsList.remove(i);
				}
			}
		}
		
		Collections.sort(aa);
		for(String tmp:aa)
		System.out.println(tmp);
		System.out.println(aa.size());
	}

}
