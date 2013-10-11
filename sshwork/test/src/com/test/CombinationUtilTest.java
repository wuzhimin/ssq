package com.test;

public class CombinationUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long tmpxx =  caclCombination(6, 33);
		
		tmpxx =0;
		for(int i=32;i>=5;i--) {
			tmpxx = caclCombination(5, i) + tmpxx;
		}
		long tmp =  caclCombination(5, 32);
		long tmp1 =  caclCombination(6, 25) + 
				caclCombination(1, 25)*caclCombination(5, 8) + 
				caclCombination(2, 25)*caclCombination(4, 8) + 
				caclCombination(3, 25)*caclCombination(3, 8) + 
				caclCombination(4, 25)*caclCombination(2, 8) + 
				caclCombination(5, 25)*caclCombination(1, 8) + 
				caclCombination(6, 8);
		
		long tmp2 =  caclCombination(1, 25)*caclCombination(5, 8);
		long tmp3 =  caclCombination(2, 25)*caclCombination(4, 8);
		long tmp4 =  caclCombination(3, 25)*caclCombination(3, 8);
		long tmp5 =  caclCombination(4, 25)*caclCombination(2, 8);
		long tmp6 =  caclCombination(5, 25)*caclCombination(1, 8);
		
		long tmp7 =  caclCombination(6, 8);
		long tmp8 =  caclCombination(5, 31);
		
		
		System.out.println(tmpxx);
		System.out.println(tmp);
		System.out.println(tmp1);
		System.out.println(tmp2);
		System.out.println(tmp3);
		System.out.println(tmp4);
		System.out.println(tmp5);
		System.out.println(tmp6);
		System.out.println(tmp7);
		System.out.println(tmp8);
		
		

	}
	
	public static long caclCombination(long n1, long n2) {
		long m = 1;
		long n = 1;
		
		for(long i=n1;i>=1;i--,n2--) {
			m = m*n2;
			n = n*i;
		}
		
		return m/n;
	}

}
