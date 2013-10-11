package com.wzm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ssq.common.util.FileUtil;

/**
 * 过滤每次历史开奖任意5个红球是不是和现有投注5个红球相同
 * @author Administrator
 *
 */
public class TestHistoryFeatureForAnyOrder {
	public static void main(String[] args) {

		List<String> xx = filterIn(false);
		for(String str:xx)
			System.out.println(str);
	}

	public static void buildHistoryFeatureAnyOrder() {
		
		List<String> result = new ArrayList<String>();

		List<String> list = new ArrayList<String>();
		List<String> list2013 = new ArrayList<String>();
		try {
			list = FileUtil.getSSQHistoryList();
			list2013 = FileUtil.getSSQHistoryList(2013);

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<List<String>> list1 = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			if (str.startsWith("2013")) {
				continue;
			}
			String[] strs = str.substring(8).substring(0, 20)
					.replaceAll(" ", ",").split(",");

			List<String> tmp = new ArrayList<String>();
			tmp.add(strs[0]);
			tmp.add(strs[1]);
			tmp.add(strs[2]);
			tmp.add(strs[3]);
			tmp.add(strs[4]);
			tmp.add(strs[5]);
			tmp.add(str);
			list1.add(tmp);
		}

		for (String str : list2013) {
			String[] strs = str.substring(8).substring(0,20).replaceAll(" ", ",").split(",");
			
			List<String> tmp = null;
			for (int j=0;j<list1.size();j++) {
				tmp = list1.get(j);
				int count = 0;
				for (int i = 0; i < 6; i++) {
					if (tmp.contains(strs[i])) {
						count++;
					}
				}
				
				if(count>=5) {
					System.out.println(str+"-----"+tmp.get(6));
					result.add(str+"-----"+tmp.get(6));
					break;
				}
			}
			
			List<String> tmp1 = new ArrayList<String>();
			tmp1.add(strs[0]);
			tmp1.add(strs[1]);
			tmp1.add(strs[2]);
			tmp1.add(strs[3]);
			tmp1.add(strs[4]);
			tmp1.add(strs[5]);
			tmp1.add(str);
			list1.add(tmp1);
		}
		
		try {
			FileUtil.writeToFile("C:\\Users\\Administrator\\Documents\\historyFeatureAnyOrder.txt", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static List<String> filterIn(boolean isIn) {
		
		List<String> result = new ArrayList<String>();

		List<String> listSSQ = new ArrayList<String>();
		List<String> listSource = new ArrayList<String>();
		try {
			listSSQ = FileUtil.getSSQHistoryList();

			listSource = FileUtil
					.readLineFileWithoutCheckRepeat("C:\\Users\\Administrator\\Documents\\编辑3.TXT");

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<List<String>> listTarget = new ArrayList<List<String>>();
		for (int i = 0; i < listSSQ.size(); i++) {
			String str = listSSQ.get(i);
			
			String[] strs = str.substring(8).substring(0, 20)
					.replaceAll(" ", ",").split(",");

			List<String> tmp = new ArrayList<String>();
			tmp.add(strs[0]);
			tmp.add(strs[1]);
			tmp.add(strs[2]);
			tmp.add(strs[3]);
			tmp.add(strs[4]);
			tmp.add(strs[5]);
			tmp.add(str);
			listTarget.add(tmp);
		}

		for (int k=listSource.size()-1;k>=0;k--) {
			String str = listSource.get(k);
			String[] strs = str.split(",");
			
			List<String> tmp = null;
			int count = 0;
			for (int j=0;j<listTarget.size();j++) {
				tmp = listTarget.get(j);
				for (int i = 0; i < 6; i++) {
					if (tmp.contains(strs[i])) {
						count++;
					}
				}
				if(count>=5) {
					
					if(isIn) {
						listSource.remove(k);
					} else {
						result.add(str);
					}
					System.out.println(str+"-----"+tmp.get(6));
					count = 0;
					break;
				} 
				count = 0;
			}
		}
		
		if(isIn) {
			result = listSource;
		}
		return result;
	}
}
