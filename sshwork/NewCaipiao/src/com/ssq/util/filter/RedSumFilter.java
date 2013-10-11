package com.ssq.util.filter;

import java.util.HashMap;
import java.util.Map;


/**
 * 2个3连过滤
 * 
 * @author Administrator
 * 
 */
public class RedSumFilter implements Filter {
	
	// 所有红球和
	public static final String ALL = "all";
	
	// 奇数红球和
	public static final String ODD = "odd";
	
	// 偶数红球和
	public static final String EVEN = "even";
	
	// 大数红球和
	public static final String BIG = "big";
	
	// 小数红球和
	public static final String SMALL = "small";
	
	// 质数红球和
	public static final String PRIME = "prime";
	
	// 小区红球和
	public static final String FIRST_ZONE = "first_zone";
	
	// 中区红球和
	public static final String SECOND_ZONE = "second_zone";
	
	// 大区红球和
	public static final String THIRD_ZONE = "third_zone";
	
	public final static Map<Integer, String> PRIME_MAP = new HashMap<Integer, String>();
	
	static {
		PRIME_MAP.put(2, null);
		PRIME_MAP.put(3, null);
		PRIME_MAP.put(5, null);
		PRIME_MAP.put(7, null);
		PRIME_MAP.put(11, null);
		PRIME_MAP.put(13, null);
		PRIME_MAP.put(17, null);
		PRIME_MAP.put(19, null);
		PRIME_MAP.put(23, null);
		PRIME_MAP.put(29, null);
		PRIME_MAP.put(31, null);
	}
	
		
	
	
	// 和值类型
	private String type;
	private int sum1 = 0;
	private int sum2 = 0;

	public RedSumFilter(String type, int sum1, int sum2) {
		this.type = type;
		this.sum1 = sum1;
		this.sum2 = sum2;
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
		
		int[] tt = new int[]{t1,t2,t3,t4,t5,t6};

		int sum = 0;
				
		if(type.equalsIgnoreCase(ALL)) {
			sum = t1 + t2 + t3 + t4 + t5 + t6;
		} else if(type.equalsIgnoreCase(EVEN)) {
			for(int x:tt) {
				sum = (x % 2 == 0) ? sum+x:sum;
			}
		} else if(type.equalsIgnoreCase(ODD)) {
			for(int x:tt) {
				sum = (x % 2 == 1) ? sum+x:sum;
			}
		} else if(type.equalsIgnoreCase(BIG)) {
			for(int x:tt) {
				sum = x>16?sum+x:sum;
			}
		} else if(type.equalsIgnoreCase(SMALL)) {
			for(int x:tt) {
				sum = x<=16?sum+x:sum;
			}
		} else if(type.equalsIgnoreCase(PRIME)) {
			for(int x:tt) {
				sum = PRIME_MAP.containsKey(x)?sum+x:sum;
			}
		} else if(type.equalsIgnoreCase(FIRST_ZONE)) {
			for(int x:tt) {
				sum = x<=11?sum+x:sum;
			}
		} else if(type.equalsIgnoreCase(SECOND_ZONE)) {
			for(int x:tt) {
				sum = (x<=22 && x>11)?sum+x:sum;
			}
		} else if(type.equalsIgnoreCase(THIRD_ZONE)) {
			for(int x:tt) {
				sum = (x>22)?sum+x:sum;
			}
		} 
		
//		System.out.println(sum);
		
		if (sum >= sum1 && sum <= sum2) {
			return true;
		}

		return false;

	}
	
	public String getDescForType(String type) {
		if(type.equalsIgnoreCase(EVEN)) {
			return "偶数和值";
		} else if(type.equalsIgnoreCase(ODD)) {
			return "奇数和值";
		} else if(type.equalsIgnoreCase(BIG)) {
			return "大数和值";
		} else if(type.equalsIgnoreCase(SMALL)) {
			return "小数和值";
		} else if(type.equalsIgnoreCase(PRIME)) {
			return "质数和值";
		} else if(type.equalsIgnoreCase(FIRST_ZONE)) {
			return "小区数和值";
		} else if(type.equalsIgnoreCase(SECOND_ZONE)) {
			return "中区数和值";
		} else if(type.equalsIgnoreCase(THIRD_ZONE)) {
			return "大区数和值";
		} 
		return "所有和值";
	}
	
	public String toString() {
		String str = "红球和值过滤，"+getDescForType(type)+",范围："+sum1+"----"+sum2;
		return str;
	}
	
	public static void main(String[] args) {
		String str =  "1,2,3,33,13,16";
		int sum1 = 9;
		int sum2 = 9;
		RedSumFilter filter = new RedSumFilter(RedSumFilter.THIRD_ZONE, sum1, sum2);
		System.out.println(filter.doFilter(str));
	}

}
