package com.wzm.server.entity.ssq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球行列遗失统计表
 * 
 */

@Entity
@Table(name = "t_ssqcolrowstats", catalog = "ssq")
public class SsqColRowStats extends BaseEntity implements StatsCompute {

	private static final long serialVersionUID = 1664823111750852101L;

	// 双色球开奖记录
	private SsqRecord record; 

	// 双色球开奖期号
	private int ssqIndex; 

	// 列遗失第一列
	private int col1; 

	// 列遗失第二列
	private int col2; 

	// 列遗失第三列
	private int col3; 

	// 行遗失第一行
	private int row1; 

	// 行遗失第二行
	private int row2; 
	
	// 行遗失第三行
	private int row3; 
	
	public static List<Map<Integer, String>> colList = new ArrayList<Map<Integer,String>>();
	
	private static Map<Integer, String> colMap1 = new HashMap<Integer, String>();
	private static Map<Integer, String> colMap2 = new HashMap<Integer, String>();
	private static Map<Integer, String> colMap3 = new HashMap<Integer, String>();
	private static Map<Integer, String> colMap4 = new HashMap<Integer, String>();
	private static Map<Integer, String> colMap5 = new HashMap<Integer, String>();
	private static Map<Integer, String> colMap6 = new HashMap<Integer, String>();
	
	public static List<Map<Integer, String>> rowList = new ArrayList<Map<Integer,String>>();
	
	private static Map<Integer, String> rowMap1 = new HashMap<Integer, String>();
	private static Map<Integer, String> rowMap2 = new HashMap<Integer, String>();
	private static Map<Integer, String> rowMap3 = new HashMap<Integer, String>();
	private static Map<Integer, String> rowMap4 = new HashMap<Integer, String>();
	private static Map<Integer, String> rowMap5 = new HashMap<Integer, String>();
	private static Map<Integer, String> rowMap6 = new HashMap<Integer, String>();
	
	static {
		initCol();
		initRow();
	}



	public static void initCol() {
		colMap1.put(1, null);
		colMap1.put(7, null);
		colMap1.put(13, null);
		colMap1.put(19, null);
		colMap1.put(25, null);
		colMap1.put(31, null);
		
		colMap2.put(2, null);
		colMap2.put(8, null);
		colMap2.put(14, null);
		colMap2.put(20, null);
		colMap2.put(26, null);
		colMap2.put(32, null);
		
		colMap3.put(3, null);
		colMap3.put(9, null);
		colMap3.put(15, null);
		colMap3.put(21, null);
		colMap3.put(27, null);
		colMap3.put(33, null);
		
		colMap4.put(4, null);
		colMap4.put(10, null);
		colMap4.put(16, null);
		colMap4.put(22, null);
		colMap4.put(28, null);
		
		colMap5.put(5, null);
		colMap5.put(11, null);
		colMap5.put(17, null);
		colMap5.put(23, null);
		colMap5.put(29, null);
		
		colMap6.put(6, null);
		colMap6.put(12, null);
		colMap6.put(18, null);
		colMap6.put(24, null);
		colMap6.put(30, null);
		
		colList.add(colMap1);
		colList.add(colMap2);
		colList.add(colMap3);
		colList.add(colMap4);
		colList.add(colMap5);
		colList.add(colMap6);
	}
	
	public static void initRow() {
		rowMap1.put(1, null);
		rowMap1.put(2, null);
		rowMap1.put(3, null);
		rowMap1.put(4, null);
		rowMap1.put(5, null);
		rowMap1.put(6, null);
		
		rowMap2.put(7, null);
		rowMap2.put(8, null);
		rowMap2.put(9, null);
		rowMap2.put(10, null);
		rowMap2.put(11, null);
		rowMap2.put(12, null);
		
		rowMap3.put(13, null);
		rowMap3.put(14, null);
		rowMap3.put(15, null);
		rowMap3.put(16, null);
		rowMap3.put(17, null);
		rowMap3.put(18, null);
		
		rowMap4.put(19, null);
		rowMap4.put(20, null);
		rowMap4.put(21, null);
		rowMap4.put(22, null);
		rowMap4.put(23, null);
		rowMap4.put(24, null);
		
		rowMap5.put(25, null);
		rowMap5.put(26, null);
		rowMap5.put(27, null);
		rowMap5.put(28, null);
		rowMap5.put(29, null);
		rowMap5.put(30, null);
		
		rowMap6.put(31, null);
		rowMap6.put(32, null);
		rowMap6.put(33, null);
		
		rowList.add(rowMap1);
		rowList.add(rowMap2);
		rowList.add(rowMap3);
		rowList.add(rowMap4);
		rowList.add(rowMap5);
		rowList.add(rowMap6);
	}

	

	public SsqColRowStats() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(SsqRecord aRecord) {
		setRecord(aRecord);
		
		int t1 = record.getR1();
		int t2 = record.getR2();
		int t3 = record.getR3();
		int t4 = record.getR4();
		int t5 = record.getR5();
		int t6 = record.getR6();
		
		ssqIndex = record.getSsqIndex();
		
		buildColLose(t1, t2, t3, t4, t5, t6);
		buildRowLose(t1, t2, t3, t4, t5, t6);
	}

	public void buildRowLose(int t1, int t2, int t3, int t4, int t5, int t6) {
		boolean[] bRow = new boolean[6];
		for(int i=0;i<6;i++) {
			if(rowList.get(i).containsKey(t1)) {
				bRow[i] = true;
			}
			
			if(rowList.get(i).containsKey(t2)) {
				bRow[i] = true;
			}
			
			if(rowList.get(i).containsKey(t3)) {
				bRow[i] = true;
			}
			
			if(rowList.get(i).containsKey(t4)) {
				bRow[i] = true;
			}
			
			if(rowList.get(i).containsKey(t5)) {
				bRow[i] = true;
			}
			
			if(rowList.get(i).containsKey(t6)) {
				bRow[i] = true;
			}
			
		}
		
		String strRow = "";
		for(int i=0;i<6;i++) {
			if(!bRow[i]) {
				strRow = strRow+(i+1);
			} 
		}
		
		row1 =0;
		row2 =0;
		row3 =0;
		
		if(strRow.length()==0) {
			row1 =0;
			row2 =0;
			row3 =0;
		} else if(strRow.length()==1) {
			row3 = Integer.parseInt(strRow);
		} else if(strRow.length()==2) {
			row2 = Integer.parseInt(strRow.substring(0,1));
			row3 = Integer.parseInt(strRow.substring(1));
		} else {
			row1 = Integer.parseInt(strRow.substring(0,1));
			row2 = Integer.parseInt(strRow.substring(1,2));
			row3 = Integer.parseInt(strRow.substring(2,3));
		}
	}
	
	public void buildColLose(int t1, int t2, int t3, int t4, int t5, int t6) {
		boolean[] bCol = new boolean[6];
		for(int i=0;i<6;i++) {
			if(colList.get(i).containsKey(t1)) {
				bCol[i] = true;
			}
			
			if(colList.get(i).containsKey(t2)) {
				bCol[i] = true;
			}
			
			if(colList.get(i).containsKey(t3)) {
				bCol[i] = true;
			}
			
			if(colList.get(i).containsKey(t4)) {
				bCol[i] = true;
			}
			
			if(colList.get(i).containsKey(t5)) {
				bCol[i] = true;
			}
			
			if(colList.get(i).containsKey(t6)) {
				bCol[i] = true;
			}
			
		}
		
		String strCol = "";
		for(int i=0;i<6;i++) {
			if(!bCol[i]) {
				strCol = strCol+(i+1);
			} 
		}
		
		col1 =0;
		col2 =0;
		col3 =0;
		
		if(strCol.length()==0) {
			col1 =0;
			col2 =0;
			col3 =0;
		} else if(strCol.length()==1) {
			col3 = Integer.parseInt(strCol);
		} else if(strCol.length()==2) {
			col2 = Integer.parseInt(strCol.substring(0,1));
			col3 = Integer.parseInt(strCol.substring(1));
		} else {
			col1 = Integer.parseInt(strCol.substring(0,1));
			col2 = Integer.parseInt(strCol.substring(1,2));
			col3 = Integer.parseInt(strCol.substring(2,3));
		}
	}

	@Column(name = "fssqindex", unique = true, nullable = false)
	public int getSsqIndex() {
		return ssqIndex;
	}

	public void setSsqIndex(int ssqIndex) {
		this.ssqIndex = ssqIndex;
	}

	@ManyToOne(targetEntity=SsqRecord.class)
	@JoinColumn(name = "frecordid", unique = true, nullable = false)
	public SsqRecord getRecord() {
		return record;
	}

	public void setRecord(SsqRecord record) {
		this.record = record;
	}

	@Column(name = "fcol1", nullable = false)
	public int getCol1() {
		return col1;
	}

	public void setCol1(int col1) {
		this.col1 = col1;
	}

	@Column(name = "fcol2", nullable = false)
	public int getCol2() {
		return col2;
	}

	public void setCol2(int col2) {
		this.col2 = col2;
	}

	@Column(name = "fcol3", nullable = false)
	public int getCol3() {
		return col3;
	}

	public void setCol3(int col3) {
		this.col3 = col3;
	}

	@Column(name = "frow1", nullable = false)
	public int getRow1() {
		return row1;
	}

	public void setRow1(int row1) {
		this.row1 = row1;
	}

	@Column(name = "frow2", nullable = false)
	public int getRow2() {
		return row2;
	}

	public void setRow2(int row2) {
		this.row2 = row2;
	}

	@Column(name = "frow3", nullable = false)
	public int getRow3() {
		return row3;
	}

	public void setRow3(int row3) {
		this.row3 = row3;
	}

	

}
