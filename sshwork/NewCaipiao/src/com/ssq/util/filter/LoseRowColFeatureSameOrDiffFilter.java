package com.ssq.util.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.FileUtil;
import com.ssq.util.FilterChainUtil;

/**
 * 断行断列遗失特征字符串是否相同过滤，比如行遗失字符串为“002”，列遗失字符串为“002”，并且是相同条件，则这个投注被过滤
 * @author Administrator
 *
 */
public class LoseRowColFeatureSameOrDiffFilter implements Filter {
	
	// 行列遗失相同
	public static final int COL_ROW_SAME = 1;
	
	// 行列遗失不相同
	public static final int NOT_COL_ROW_SAME = 2;
	
	// 列遗失与上期相同
	public static final int COL_SAME = 9;
	
	// 列遗失与上期相同
	public static final int NOT_COL_SAME = 10;
	
	// 行遗失与上期相同
	public static final int ROW_SAME = 11;
	
	// 行遗失与上期相同
	public static final int NOT_ROW_SAME = 12;
		
	// 列遗失前后期间隔等于1
	public static final int COL_SPACE_1 = 3;
	
	// 列遗失前后期间隔不等于1
	public static final int NOT_COL_SPACE_1 = 4;
		
	// 行遗失前后期间隔等于1
	public static final int ROW_SPACE_1 = 5;
	
	// 行遗失前后期间隔不等于1
	public static final int NOT_ROW_SPACE_1 = 6;
	
	// 行列遗失当前间隔等于1
	public static final int COL_ROW_SPACE_1 = 7;
	
	// 行列遗失当前间隔不等于1
	public static final int NOT_COL_ROW_SPACE_1 = 8;
	
	// 列feature和行feature是否相同
	private int compareType = COL_ROW_SAME;
	
	// 最新列遗失
	private String lastColLoseStr = "";
	
	// 最新行遗失
	private String lastRowLoseStr = "";
	
	private List<Map<Integer, String>> colSourceListMap = new ArrayList<Map<Integer,String>>();
	
	private List<Map<Integer, String>> rowSourceListMap = new ArrayList<Map<Integer,String>>();
	
	public LoseRowColFeatureSameOrDiffFilter(int compareType, String lastColLoseStr, String lastRowLoseStr) {
		this.compareType = compareType;
		this.setLastColLoseStr(lastColLoseStr);
		this.setLastRowLoseStr(lastRowLoseStr);
		
		initColType();
		initRowType();
	}
	
	public LoseRowColFeatureSameOrDiffFilter(int compareType) {
		this.compareType = compareType;
		
		initColType();
		initRowType();
	}
	

	private void initColType() {
		Map<Integer, String> source1 = new HashMap<Integer, String>();
		Map<Integer, String> source2 = new HashMap<Integer, String>();
		Map<Integer, String> source3 = new HashMap<Integer, String>();
		Map<Integer, String> source4 = new HashMap<Integer, String>();
		Map<Integer, String> source5 = new HashMap<Integer, String>();
		Map<Integer, String> source6 = new HashMap<Integer, String>();
		
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
		
		colSourceListMap.clear();
		colSourceListMap.add(source1);
		colSourceListMap.add(source2);
		colSourceListMap.add(source3);
		colSourceListMap.add(source4);
		colSourceListMap.add(source5);
		colSourceListMap.add(source6);
	}
	
	private void initRowType() {
		Map<Integer, String> source1 = new HashMap<Integer, String>();
		Map<Integer, String> source2 = new HashMap<Integer, String>();
		Map<Integer, String> source3 = new HashMap<Integer, String>();
		Map<Integer, String> source4 = new HashMap<Integer, String>();
		Map<Integer, String> source5 = new HashMap<Integer, String>();
		Map<Integer, String> source6 = new HashMap<Integer, String>();
		
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
		
		rowSourceListMap.clear();
		rowSourceListMap.add(source1);
		rowSourceListMap.add(source2);
		rowSourceListMap.add(source3);
		rowSourceListMap.add(source4);
		rowSourceListMap.add(source5);
		rowSourceListMap.add(source6);
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
		
		String strCol = buildColFeatureStr(t1, t2, t3, t4, t5, t6);
		String strRow = buildRowFeatureStr(t1, t2, t3, t4, t5, t6);
		
		System.out.println("strCol:"+strCol+"   "+"strRow:"+strRow);
		
		int col = Integer.parseInt(strCol);
		int row = Integer.parseInt(strRow);
		
		int lastCol = Integer.parseInt(getLastColLoseStr());
		int lastRow = Integer.parseInt(getLastRowLoseStr());
		
		if(compareType==COL_ROW_SAME && strCol.equals(strRow)) {
			return true;
		}
		
		if(compareType==NOT_COL_ROW_SAME && !strCol.equals(strRow)) {
			return true;
		}
		
		if(compareType == COL_SAME && col == lastCol) {
			return true;
		}
		
		if(compareType == NOT_COL_SAME && col != lastCol ) {
			return true;
		}
		
		if(compareType == ROW_SAME && row == lastRow) {
			return true;
		}
		
		if(compareType == NOT_ROW_SAME && row != lastRow ) {
			return true;
		}
		
		if(compareType == COL_ROW_SPACE_1 && (col+1 == row || col-1 == row) ) {
			return true;
		}
		
		if(compareType == NOT_COL_ROW_SPACE_1 && (col+1 != row && col-1 != row) ) {
			return true;
		}
		
		if(compareType == COL_SPACE_1 && (col+1 == lastCol || col-1 == lastCol) ) {
			return true;
		}
		
		if(compareType == NOT_COL_SPACE_1 && (col+1 != lastCol && col-1 != lastCol) ) {
			return true;
		}
		
		if(compareType == ROW_SPACE_1 && (row+1 == lastRow || row-1 == lastRow) ) {
			return true;
		}
		
		if(compareType == NOT_ROW_SPACE_1 && (row+1 != lastRow && row-1 != lastRow) ) {
			return true;
		}
		
		return false;
	}


	public String buildColFeatureStr(int t1, int t2, int t3, int t4, int t5,
			int t6) {
		
		boolean[] b = new boolean[6];
		for(int i=0;i<6;i++) {
			if(colSourceListMap.get(i).containsKey(t1)) {
				b[i] = true;
			}
			
			if(colSourceListMap.get(i).containsKey(t2)) {
				b[i] = true;
			}
			
			if(colSourceListMap.get(i).containsKey(t3)) {
				b[i] = true;
			}
			
			if(colSourceListMap.get(i).containsKey(t4)) {
				b[i] = true;
			}
			
			if(colSourceListMap.get(i).containsKey(t5)) {
				b[i] = true;
			}
			
			if(colSourceListMap.get(i).containsKey(t6)) {
				b[i] = true;
			}
		}
		
		String str1 = "";
		for(int i=0;i<6;i++) {
			if(!b[i]) {
				str1 = str1+(i+1);
			} 
		}
		
		if(str1.length()==0) {
			str1="000";
		} else if(str1.length()==1) {
			str1="00"+str1;
		} else if(str1.length()==2) {
			str1="0"+str1;
		} else {
			str1=str1.substring(0,3);
		}
		return str1;
	}
	
	public String buildRowFeatureStr(int t1, int t2, int t3, int t4, int t5,
			int t6) {
		
		boolean[] b = new boolean[6];
		for(int i=0;i<6;i++) {
			if(rowSourceListMap.get(i).containsKey(t1)) {
				b[i] = true;
			}
			
			if(rowSourceListMap.get(i).containsKey(t2)) {
				b[i] = true;
			}
			
			if(rowSourceListMap.get(i).containsKey(t3)) {
				b[i] = true;
			}
			
			if(rowSourceListMap.get(i).containsKey(t4)) {
				b[i] = true;
			}
			
			if(rowSourceListMap.get(i).containsKey(t5)) {
				b[i] = true;
			}
			
			if(rowSourceListMap.get(i).containsKey(t6)) {
				b[i] = true;
			}
		}
		
		String str1 = "";
		for(int i=0;i<6;i++) {
			if(!b[i]) {
				str1 = str1+(i+1);
			} 
		}
		
		if(str1.length()==0) {
			str1="000";
		} else if(str1.length()==1) {
			str1="00"+str1;
		} else if(str1.length()==2) {
			str1="0"+str1;
		} else {
			str1=str1.substring(0,3);
		}
		return str1;
	}
	
	public String toString() {
//		String str = " 断行断列遗失特征字符串是否相同过滤,是否相等："+isSame;
		return getTypeDesc(compareType);
	}
	
	public String getTypeDesc(int type) {
		if(type == COL_ROW_SAME) {
			return "行列遗失相同";
		} else if(type == NOT_COL_ROW_SAME) {
			return "行列遗失不相同";
		} else if(type == COL_ROW_SPACE_1) {
			return "行列遗失间隔等于1";
		} else if(type == NOT_COL_ROW_SPACE_1) {
			return "行列遗失间隔不等于1";
		} else if(type == COL_SPACE_1) {
			return "列遗失前后期间隔等于1";
		} else if(type == NOT_COL_SPACE_1) {
			return "列遗失前后期间隔不等于1";
		} else if(type == ROW_SPACE_1) {
			return "行遗失前后期间隔等于1";
		} else if(type == NOT_ROW_SPACE_1) {
			return "行遗失前后期间隔不等于1";
		} 
		
		return "";
	}
	
	public static void main(String[] args) {
		
//		LoseRowColFeatureSameOrDiffFilter f = new LoseRowColFeatureSameOrDiffFilter(EQUALS_COL_SPACE_1, "014", "013");

		
		FilterChain chain = null;
		try {
			chain = FilterChainUtil.buildALoseRowColFeatureFilterChain();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		chain = FilterChainUtil.makePoint2First(chain);
//		chain = new FilterChain(f); 
		
		List<String> list = null;
		try {
			list = FileUtil.readLineFileWithoutCheckRepeat("C:/Users/Administrator/Documents/编辑2.TXT");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=list.size()-1;i>=0;i--) {
			String str = list.get(i);
			boolean b = chain.doFilter(str);
			if(b) {
				list.remove(i);
			}
		}
		
		try {
			FileUtil.writeToFile("C:/Users/Administrator/Documents/编辑2.TXT", list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}

	public String getLastColLoseStr() {
		return lastColLoseStr;
	}

	public void setLastColLoseStr(String lastColLoseStr) {
		this.lastColLoseStr = lastColLoseStr;
	}

	public String getLastRowLoseStr() {
		return lastRowLoseStr;
	}

	public void setLastRowLoseStr(String lastRowLoseStr) {
		this.lastRowLoseStr = lastRowLoseStr;
	}

}
