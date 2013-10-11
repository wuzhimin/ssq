package com.ssq.util.filter;

import com.ssq.common.util.SsqUtils;


/**
 * AC值过滤
 * 
 * @author Administrator
 * 
 */
public class ACFilter implements Filter {
	private int ac1;
	private int ac2;
	

	public ACFilter(int ac1, int ac2) {
		this.ac1 = ac1;
		this.ac2 = ac2;

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
		
		int[] a = new int[]{t1,t2,t3,t4,t5,t6};
		int ac = SsqUtils.getACValue(a);
		
		if(ac>=ac1 && ac<=ac2) {		
			return true;
		}			

		return false;
	}
	
	public String toString() {
		String str = "ac值过滤，范围："+ac1+"----"+ac2;
		return str;
	}
}
