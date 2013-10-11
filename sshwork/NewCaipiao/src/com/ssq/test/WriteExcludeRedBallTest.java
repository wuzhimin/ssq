package com.ssq.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.FileUtil;
import com.ssq.util.FilterChainUtil;
import com.ssq.util.filter.ACFilter;
import com.ssq.util.filter.FilterChain;

/**
 * 测试将所有红球组合字串写入文件
 * 
 * @author Administrator
 * 
 */
public class WriteExcludeRedBallTest {
	
	public static List<String> filter(List<String> listInclude, FilterChain fChain) {
		List<String> listExclude = new ArrayList<String>();
		for (int i = listInclude.size() - 1; i >= 0; i--) {
			String str = listInclude.get(i);
			if (fChain.doFilter(str)) {
				listExclude.add(str);
				listInclude.remove(i);
			}
		}
		return listExclude;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		includeToExclude();
//		excludeToInclude();
	}

	/**
	 * 根据过滤条件，把记录从包含文件中弄出到排除文件中
	 */
	public static void includeToExclude() {
		// Object[] source= new
		// Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
		// List<String> result = new ArrayList<String>();
		// CombinationUtil.createCombo(source, 6, 6, result);

		List<String> resultInclude = null;
		List<String> resultExclude = null;
		try {
			resultInclude = FileUtil.readLineFileWithoutCheckRepeat("d:/ddd/red-include.txt");
			resultExclude = FileUtil.readLineFileWithoutCheckRepeat("d:/ddd/red-exclude.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 分别用28个list<String> 来存储红球投注,即把投注分为28个区
		Map<Integer, List<String>> resultIncludeMap = new HashMap<Integer, List<String>>();
		for (int i = 1; i <= 28; i++) {
			resultIncludeMap.put(i, new ArrayList<String>());
		}

		for (int i = resultInclude.size() - 1; i >= 0; i--) {
			String str = resultInclude.get(i);
			String[] strs = str.split(",");
			int t1 = Integer.parseInt(strs[0]);
			resultIncludeMap.get(t1).add(str);
			resultInclude.remove(i);
		}
		
		// 构造每区的过滤链
		List<FilterChain> filterChainList = new ArrayList<FilterChain>();
		for(int i=1;i<=13;i++) {
			String formulaFileName = "D:/ddd/formula/formula"+i+".txt";
			FilterChain chainBegin = FilterChainUtil.buildFilterChainFromFileWithPointToEnd(formulaFileName);
			filterChainList.add(chainBegin);
		}
		
		// 进行过滤
		for(int i=0;i<13;i++) {
			List<String> listInclude = resultIncludeMap.get(i+1);
			FilterChain fChain = filterChainList.get(i);
			resultExclude.addAll(filter(listInclude, fChain));
		}
		
		// 合并
		for (int i = 1; i <= 28; i++) {
			resultInclude.addAll(resultIncludeMap.get(i));
		}

		try {
			FileUtil.writeToFile("d:/ddd/red-include.txt", resultInclude);

			Collections.sort(resultExclude);
			
			FileUtil.writeToFile("d:/ddd/red-exclude.txt", resultExclude);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("finish");
	}
	
	
	/**
	 * 根据过滤条件，把记录从排除文件中弄出到包含文件中
	 */
	public static void excludeToInclude() {

		List<String> resultInclude = null;
		List<String> resultExclude = null;
		try {
			resultInclude = FileUtil.readLineFileWithoutCheckRepeat("d:/ddd/red-include.txt");
			resultExclude = FileUtil.readLineFileWithoutCheckRepeat("d:/ddd/red-exclude.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ACFilter filter = new ACFilter(10,300);
		FilterChain chain = new FilterChain(filter);
		for (int i = resultExclude.size() - 1; i >= 0; i--) {
			String str = resultExclude.get(i);
			if (chain.doFilter(str)) {
				resultInclude.add(str);
				resultExclude.remove(i);
			}
		}
		

		try {
			Collections.sort(resultInclude);
			Collections.sort(resultExclude);
			
			FileUtil.writeToFile("d:/ddd/red-include.txt", resultInclude);
			FileUtil.writeToFile("d:/ddd/red-exclude.txt", resultExclude);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("finish");
	}

}
