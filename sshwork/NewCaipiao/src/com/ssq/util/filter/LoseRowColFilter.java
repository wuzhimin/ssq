package com.ssq.util.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.FileUtil;

/**
 * 断行断列遗失数过滤，行列遗失数在loseCount1和loseCount2之间的过滤掉
 * @author Administrator
 *
 */
public class LoseRowColFilter implements Filter {
	// 1为列遗失，2为行遗失
	private int type =1;
	
	// 遗失行列数小值
	private int loseCount1;
	
	// 遗失行列数大值
	private int loseCount2;
	
	private List<Map<Integer, String>> sourceListMap = new ArrayList<Map<Integer,String>>();
	
	private Map<Integer, String> source1 = new HashMap<Integer, String>();
	private Map<Integer, String> source2 = new HashMap<Integer, String>();
	private Map<Integer, String> source3 = new HashMap<Integer, String>();
	private Map<Integer, String> source4 = new HashMap<Integer, String>();
	private Map<Integer, String> source5 = new HashMap<Integer, String>();
	private Map<Integer, String> source6 = new HashMap<Integer, String>();
	
	public LoseRowColFilter(int type, int loseCount1, int loseCount2) {
		this.type = type;
		this.loseCount1  = loseCount1;
		this.loseCount2  = loseCount2;
		
		if(this.type ==1) {
			initloseType();
		} else if(this.type ==2) {
			initRowType();
		} else {
			initOtherType();
		}
	}
	
	private void initOtherType() {
		source1.put(1, null);
		source1.put(7, null);
		source1.put(15, null);
		source1.put(19, null);
		source1.put(25, null);
		source1.put(31, null);
		
		source2.put(2, null);
		source2.put(9, null);
		source2.put(14, null);
		source2.put(20, null);
		source2.put(26, null);
		source2.put(32, null);
		
		source3.put(3, null);
		source3.put(8, null);
		source3.put(13, null);
		source3.put(21, null);
		source3.put(23, null);
		source3.put(33, null);
		
		source4.put(4, null);
		source4.put(10, null);
		source4.put(16, null);
		source4.put(22, null);
		source4.put(28, null);
		
		source5.put(5, null);
		source5.put(11, null);
		source5.put(17, null);
		source5.put(27, null);
		source5.put(30, null);
		
		source6.put(6, null);
		source6.put(12, null);
		source6.put(18, null);
		source6.put(24, null);
		source6.put(29, null);
		
		addToTotal();
		
	}

	private void addToTotal() {
		sourceListMap.add(source1);
		sourceListMap.add(source2);
		sourceListMap.add(source3);
		sourceListMap.add(source4);
		sourceListMap.add(source5);
		sourceListMap.add(source6);
	}

	private void initloseType() {
		source1.put(1, null);
		source1.put(7, null);
		source1.put(13, null);
		source1.put(19, null);
		source1.put(25, null);
		source1.put(31, null);
		
		source2.put(2, null);
		source2.put(8, null);
		source2.put(14, null);
		source2.put(20, null);
		source2.put(26, null);
		source2.put(32, null);
		
		source3.put(3, null);
		source3.put(9, null);
		source3.put(15, null);
		source3.put(21, null);
		source3.put(27, null);
		source3.put(33, null);
		
		source4.put(4, null);
		source4.put(10, null);
		source4.put(16, null);
		source4.put(22, null);
		source4.put(28, null);
		
		source5.put(5, null);
		source5.put(11, null);
		source5.put(17, null);
		source5.put(23, null);
		source5.put(29, null);
		
		source6.put(6, null);
		source6.put(12, null);
		source6.put(18, null);
		source6.put(24, null);
		source6.put(30, null);
		
		addToTotal();
	}
	
	private void initRowType() {
		source1.put(1, null);
		source1.put(2, null);
		source1.put(3, null);
		source1.put(4, null);
		source1.put(5, null);
		source1.put(6, null);
		
		source2.put(7, null);
		source2.put(8, null);
		source2.put(9, null);
		source2.put(10, null);
		source2.put(11, null);
		source2.put(12, null);
		
		source3.put(13, null);
		source3.put(14, null);
		source3.put(15, null);
		source3.put(16, null);
		source3.put(17, null);
		source3.put(18, null);
		
		source4.put(19, null);
		source4.put(20, null);
		source4.put(21, null);
		source4.put(22, null);
		source4.put(23, null);
		source4.put(24, null);
		
		source5.put(25, null);
		source5.put(26, null);
		source5.put(27, null);
		source5.put(28, null);
		source5.put(29, null);
		source5.put(30, null);
		
		source6.put(31, null);
		source6.put(32, null);
		source6.put(33, null);
		
		addToTotal();
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
		
		boolean[] b = new boolean[6];
		for(int i=0;i<6;i++) {
			if(sourceListMap.get(i).containsKey(t1)) {
				b[i] = true;
			}
			
			if(sourceListMap.get(i).containsKey(t2)) {
				b[i] = true;
			}
			
			if(sourceListMap.get(i).containsKey(t3)) {
				b[i] = true;
			}
			
			if(sourceListMap.get(i).containsKey(t4)) {
				b[i] = true;
			}
			
			if(sourceListMap.get(i).containsKey(t5)) {
				b[i] = true;
			}
			
			if(sourceListMap.get(i).containsKey(t6)) {
				b[i] = true;
			}
		}
		
		int tmpCount = 0;
		for(int i=0;i<6;i++) {
			if(!b[i]) {
				tmpCount++;
			}
		}
		
		if(tmpCount>=loseCount1 && tmpCount<=loseCount2) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String str = "断行断列遗失数过滤，行列遗失数范围:"+loseCount1+"---"+loseCount2;
		return str;
	}
	
	public static void main(String[] args) {
		LoseRowColFilter f = new LoseRowColFilter(1,3,6);
		FilterChain chain = new FilterChain(f);
		
		LoseRowColFilter f1 = new LoseRowColFilter(2,3,6);
		FilterChain chain1= new FilterChain(f1);
		chain.setNextNode(chain1);
		
		List<String> list = null;
		try {
			list = FileUtil.readLineFileWithoutCheckRepeat("C:/Users/Administrator/Documents/编辑2.TXT");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=list.size()-1;i>=0;i--) {
			String str = list.get(i);
			boolean b = chain.doFilter(str);
			if(b) {
				list.remove(i);
			}
		}
		
		System.out.println();
	}

}
