package com.ssq.util.filter;

import java.util.List;

import com.ssq.util.ResidueFilterUtil;

/**
 * 余数特征字串过滤
 * 
 * @author Administrator
 * 
 */
public class ResidueFeatureFilter implements Filter {
	// 年份
	private int year;
	
	// 除数
	private int divisor;
	
	// 余数特征字串列表
	private List<String> featureList;
	
	public ResidueFeatureFilter(int divisor, int year) {
		this.divisor = divisor;
		this.year = year;
		featureList = ResidueFilterUtil.buildResidueFeatureList(divisor, year);
	}

	@Override
	public boolean doFilter(String str) {
		
		String[] strs = str.split(",");

		int t1 = Integer.parseInt(strs[0]);
		int t2 = Integer.parseInt(strs[1]);
		int t3 = Integer.parseInt(strs[2]);
		int t4 = Integer.parseInt(strs[3]);
		int t5 = Integer.parseInt(strs[4]);
		int t6 = Integer.parseInt(strs[5]);
		
		String tmp = t1 % divisor + "" + t2 % divisor + "" + t3 % divisor + "" + t4 % divisor + "" + t5 % divisor + "" + t6 % divisor;		

		if(featureList.contains(tmp)) {
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		String str = " 余数特征字串过滤，除数："+divisor+", 年分："+year;
		return str;
	}
}
