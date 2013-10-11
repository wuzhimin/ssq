package com.ssq.util.filter;

import com.ssq.common.util.SsqUtils;


/**
 * É¢¶È¹ıÂË
 * 
 * @author Administrator
 * 
 */
public class SanduFilter implements Filter {
	private int sandu1;
	private int sandu2;
	

	public SanduFilter(int ac1, int ac2) {
		this.sandu1 = ac1;
		this.sandu2 = ac2;

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
		int sandu = SsqUtils.getSandu(a);
		
		if(sandu>=sandu1 && sandu<=sandu2) {		
			return true;
		}			

		return false;
	}
	
	public String toString() {
		String str = "É¢¶È¹ıÂË£¬·¶Î§£º"+sandu1+"----"+sandu2;
		return str;
	}

}
