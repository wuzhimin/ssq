package com.ssq.util.filter;

/**
 * 3个2连过滤
 * @author Administrator
 *
 */
public class ThreeNumberTwoContinueFilter implements Filter {

	@Override
	public boolean doFilter(String str) {
		String[] strs = str.split(",");

		int[] a = new int[6];
		a[0] = Integer.parseInt(strs[0]);
		a[1] = Integer.parseInt(strs[1]);
		a[2] = Integer.parseInt(strs[2]);
		a[3] = Integer.parseInt(strs[3]);
		a[4] = Integer.parseInt(strs[4]);
		a[5] = Integer.parseInt(strs[5]);
		
		boolean b1 =   (a[1] == a[0] + 1);
		boolean b2 =   (a[3] == a[2] + 1);
		boolean b3 =   (a[5] == a[4] + 1);
		
		if(b1 && b2 && b3) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String str = "3个两连红球过滤";
		return str;
	}

}
