package com.ssq.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import com.ssq.common.util.CommonConstant;
import com.ssq.common.util.FileUtil;
import com.ssq.util.filter.ACFilter;
import com.ssq.util.filter.Filter;
import com.ssq.util.filter.FilterChain;
import com.ssq.util.filter.FormulaFilter;
import com.ssq.util.filter.FourContinueFilter;
import com.ssq.util.filter.RedSumFilter;
import com.ssq.util.filter.SanduFilter;
import com.ssq.util.filter.ThreeNumberTwoContinueFilter;
import com.ssq.util.filter.TwoNumberThreeContinueFilter;

/**
 * 测试将所有红球组合字串写入文件
 * @author Administrator
 *
 */
public class WriteFullRedCombinationTest implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		new Thread(new WriteFullRedCombinationTest()).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
//				Object[] source= new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
//				List<String> result = new ArrayList<String>();
//				CombinationUtil.createCombo(source, 6, 6, result);
				
				List<String> result = null;
				List<String> result1 = new ArrayList<String>();
				try {
					result = FileUtil.readLineFileWithoutCheckRepeat("d:/ddd/red-full-16.txt");
					result1 = FileUtil.readLineFileWithoutCheckRepeat("d:/ddd/red-full-17.txt");
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				result = new ArrayList<String>();
				result.add("04,08,12,19,21,25");
				
				// 4连，5连，6连过滤
				Filter filter1 = new FourContinueFilter();
				FilterChain chain1 = new FilterChain(filter1);
				
				// 三个两连过滤
				Filter filter2 = new ThreeNumberTwoContinueFilter();
				FilterChain chain2 = new FilterChain(filter2);
				chain1.setNextNode(chain2);
				
				// 两个3连过滤
				Filter filter3 = new TwoNumberThreeContinueFilter();
				FilterChain chain3 = new FilterChain(filter3);
				chain2.setNextNode(chain3);
				
				// 和值过滤
				Filter filter4 = new RedSumFilter("all", 0, 42);
				FilterChain chain4 = new FilterChain(filter4);
				chain3.setNextNode(chain4);
				
				Filter filter5 = new RedSumFilter("all", 160, 4000);
				FilterChain chain5 = new FilterChain(filter5);
				chain4.setNextNode(chain5);
				
				Filter filter6 = new RedSumFilter("all", 44, 46);
				FilterChain chain6 = new FilterChain(filter6);
				chain5.setNextNode(chain6);
				
				Filter filter7 = new RedSumFilter("all", 55, 55);
				FilterChain chain7 = new FilterChain(filter7);
				chain6.setNextNode(chain7);
				
				Filter filter8 = new RedSumFilter("all", 147, 147);
				FilterChain chain8 = new FilterChain(filter8);
				chain7.setNextNode(chain8);
				
				// 公式过滤
				// 第一个红球大于等于14的三联
				String formula = "r1>=14 && ( (r2==r1+1 && r3==r2+1) || (r3==r2+1 && r4==r3+1) || (r4==r3+1 && r5==r4+1) || (r5==r4+1 && r6==r5+1))";
				Filter filter9 = new FormulaFilter(formula);
				FilterChain chain9 = new FilterChain(filter9);
				chain8.setNextNode(chain9);
				
				// 相减（1级）3个相等，且大于等于4
				formula = "(r2-r1==r3-r2 && r3-r2==r4-r3 && r2-r1>=4) || (r2-r1==r3-r2 && r3-r2==r5-r4 && r2-r1>=4) || (r2-r1==r3-r2 && r3-r2==r6-r5 && r2-r1>=4) || (r3-r2==r4-r3 && r4-r3==r5-r4 && r3-r2>=4) || (r3-r2==r4-r3 && r4-r3==r6-r5 && r3-r2>=4) || (r4-r3==r5-r4 && r5-r4==r6-r5 && r4-r3>=4)";
				Filter filter10 = new FormulaFilter(formula);
				FilterChain chain10 = new FilterChain(filter10);
				chain9.setNextNode(chain10);
				
				// ac值过滤
				Filter filter11 = new ACFilter(1,3);  // ac值1,3间
				FilterChain chain11 = new FilterChain(filter11);
				chain10.setNextNode(chain11);
				
				Filter filter12 = new ACFilter(10,300);  // ac值大于等于10
				FilterChain chain12 = new FilterChain(filter12);
				chain11.setNextNode(chain12);
				
				// 散度值过滤
				Filter filter13 = new SanduFilter(1,3);   // 散度值1,3间
				FilterChain chain13 = new FilterChain(filter13);
				chain12.setNextNode(chain13);
				
				Filter filter14 = new SanduFilter(16,300);  // 散度值大于等于16
				FilterChain chain14 = new FilterChain(filter14);
				chain13.setNextNode(chain14);
				
				// 第一个红球大于等于20
				formula = "r1>=20";
				Filter filter15 = new FormulaFilter(formula);
				FilterChain chain15 = new FilterChain(filter15);
				chain14.setNextNode(chain15);
				
				// 相减数（2级）3个相等
				formula = "(r3-r1==r4-r2 && r4-r2==r5-r3) || (r4-r2==r5-r3 && r5-r3==r6-r4) || (r3-r1==r4-r2 && r4-r2==r6-r4)";
				Filter filter16 = new FormulaFilter(formula);
				FilterChain chain16 = new FilterChain(filter16);
				chain15.setNextNode(chain16);
				
				// 相减数（3级）全相等
				formula = "(r4-r1==r5-r2 && r5-r2==r6-r3)";
				Filter filter17 = new FormulaFilter(formula);
				FilterChain chain17 = new FilterChain(filter17);
				chain16.setNextNode(chain17);
				
				for(int i=result.size()-1;i>=0;i--) {
					if(chain1.doFilter(result.get(i))) {        //  TODO1
						result1.add(result.get(i));
						result.remove(i);
					}
				}
				try {
					FileUtil.writeToFile("d:/ddd/red-full-16.txt", result);
					
					Collections.sort(result1);
					FileUtil.writeToFile("d:/ddd/red-full-17.txt", result1);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.print("finish");
			}
		}).start();
		
		

	}

	@Override
	public void run() {
		List<String> tmp = new ArrayList<String>();
		int file_index =12;
		int totalCount = 0;
		while(true) {
			if(CommonConstant.strQueue.size()>0) {
				try{
				tmp.add(CommonConstant.strQueue.poll());
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
			}
			
			totalCount = totalCount+tmp.size();
			try {
				FileUtil.writeToFileTail("d:/ddd/red-full-"+file_index+".txt", tmp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			tmp = new ArrayList<String>();
			
			if(totalCount>=100000) {
				file_index++;
				totalCount =0;
			}
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
