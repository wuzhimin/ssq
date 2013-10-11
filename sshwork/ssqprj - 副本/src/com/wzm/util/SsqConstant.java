package com.wzm.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SsqConstant {

	public static final Map<Integer, String> PRIME_NUMBER_MAP = new HashMap<Integer, String>();
	
	static {
		PRIME_NUMBER_MAP.put(2, null);
		PRIME_NUMBER_MAP.put(3, null);
		PRIME_NUMBER_MAP.put(5, null);
		PRIME_NUMBER_MAP.put(7, null);
		PRIME_NUMBER_MAP.put(11, null);
		PRIME_NUMBER_MAP.put(13, null);
		PRIME_NUMBER_MAP.put(17, null);
		PRIME_NUMBER_MAP.put(19, null);
		PRIME_NUMBER_MAP.put(23, null);
		PRIME_NUMBER_MAP.put(29, null);
		PRIME_NUMBER_MAP.put(31, null);
		PRIME_NUMBER_MAP.put(37, null);
		PRIME_NUMBER_MAP.put(43, null);
		PRIME_NUMBER_MAP.put(47, null);
	}
	
	/**
	 * 获得AC值
	 * 
	 * @param a
	 * @return
	 */
	public static int getACValue(int[] a) {
		Map<Integer, Integer> acMap = new HashMap<Integer, Integer>();
		for (int m = 0; m < a.length - 1; m++) {
			for (int j = m + 1; j < a.length; j++) {
				acMap.put(new Integer(Math.abs(a[j] - a[m])), new Integer(Math
						.abs(a[j] - a[m])));
			}
		}
		int count = 0;
		for (Iterator<Integer> it1 = acMap.keySet().iterator(); it1.hasNext();) {
			it1.next();
			count++;
		}
		count = count - 5;
		return count;
	}

	/**
	 * 获得散度
	 * 
	 * @param a
	 * @return
	 */
	public static int getSandu(int[] a) {
		int sandu = -1;
		for (int j = 1; j <= 33; j++) {
			int t1 = Math.abs(j - a[0]);
			int t2 = Math.abs(j - a[1]);
			int t3 = Math.abs(j - a[2]);
			int t4 = Math.abs(j - a[3]);
			int t5 = Math.abs(j - a[4]);
			int t6 = Math.abs(j - a[5]);

			if (t1 >= t2) {
				t1 = t2;
			}

			if (t1 >= t3) {
				t1 = t3;
			}

			if (t1 >= t4) {
				t1 = t4;
			}

			if (t1 >= t5) {
				t1 = t5;
			}

			if (t1 >= t6) {
				t1 = t6;
			}

			if (t1 >= sandu) {
				sandu = t1;
			}
		}
		return sandu;
	}
}
