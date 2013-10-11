package com.wzm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.FileUtil;
import com.wzm.server.dao.ssq.SsqBaseStatsDao;
import com.wzm.server.entity.ssq.SsqColRowStats;
import com.wzm.util.ClientBeanUtil;

/**
 * 合并断行断列规律
 * @author Administrator
 *
 */
public class TestMergeColRow  {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		mergeColRowLose();
		
	}
	
	public static String buildFullStr(int n) {
		return n<10?"0"+n:""+n;
	}

	public static void mergeColRowLose(int currentSsqIndex) {
		
		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao)ClientBeanUtil.getDao("ssqBaseStatsDao");
		
		String hql = "select s from SsqColRowStats s where s.record.ssqIndex <= ?";
		List<SsqColRowStats> lis = ssqBaseStatsDao.findSsqColRowStatsesByHql(hql, new Integer[]{currentSsqIndex});
		
		List<String> col = new ArrayList<String>();
		List<String> row = new ArrayList<String>();
		for(SsqColRowStats colRow:lis) {
			String strCol = colRow.getSsqIndex()+" " 
					+ buildFullStr(colRow.getRecord().getR1()) + " "
					+ buildFullStr(colRow.getRecord().getR2()) + " " 
					+ buildFullStr(colRow.getRecord().getR3()) + " "
					+ buildFullStr(colRow.getRecord().getR4()) + " "
					+ buildFullStr(colRow.getRecord().getR5()) + " "
					+ buildFullStr(colRow.getRecord().getR6()) + " "
					+ buildFullStr(colRow.getRecord().getB1()) + "----->"
					+ colRow.getCol1()+""+colRow.getCol2()+""+colRow.getCol3();
			col.add(strCol);
			
			String strRow = colRow.getSsqIndex()+" " 
					+ buildFullStr(colRow.getRecord().getR1()) + " "
					+ buildFullStr(colRow.getRecord().getR2()) + " " 
					+ buildFullStr(colRow.getRecord().getR3()) + " "
					+ buildFullStr(colRow.getRecord().getR4()) + " "
					+ buildFullStr(colRow.getRecord().getR5()) + " "
					+ buildFullStr(colRow.getRecord().getR6()) + " "
					+ buildFullStr(colRow.getRecord().getB1()) + "----->"
					+ colRow.getRow1()+""+colRow.getRow2()+""+colRow.getRow3();
			row.add(strRow);
			
		}
		//2004001 01 02 03 07 10 25 07----->346
			
//		try {
//			FileUtil.writeToFile("C:/Users/Administrator/Documents/col.txt",col);
//			FileUtil.writeToFile("C:/Users/Administrator/Documents/row.txt",row);
//			row = FileUtil.readLineFileWithoutCheckRepeat("C:/Users/Administrator/Documents/row.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		Map<String, String> map = new HashMap<String, String>();
		
		String tmp = null;
		for(int i=0;i<col.size();i++) {
			String[] strs = col.get(i).split("----->");
			
			if(tmp==null) {
				tmp = strs[1];
			} else {
				
				int t1 = Integer.parseInt(tmp);
				int t2 = Integer.parseInt(strs[1]);
				
				if(tmp.equals(strs[1])) {
					col.set(i, col.get(i)+"--C    ");
				} else if(t1+1 == t2 || t1-1 == t2){
					tmp = strs[1];
					col.set(i, col.get(i)+"--col_1");
				} else {
					tmp = strs[1];
					col.set(i, col.get(i)+"--     ");
				}
			}
		}
		
		tmp = null;
		for(int i=0;i<row.size();i++) {
			String[] strs = row.get(i).split("----->");
			if(tmp==null) {
				tmp = strs[1];
			} else {
				int t1 = Integer.parseInt(tmp.split("--")[0]);
				int t2 = Integer.parseInt(strs[1].split("--")[0]);
				
				if(tmp.equals(strs[1])) {
					row.set(i, row.get(i)+"--C");
				} else if(t1+1 == t2 || t1-1 == t2){
					tmp = strs[1];
					row.set(i, row.get(i)+"--row_1");
				} else {
					tmp = strs[1];
					row.set(i, row.get(i)+"-- ");
				}
			}
		}
		
		for(String str:row) {
			String[] strs = str.split("----->");
			map.put(strs[0], strs[1]);
		}
		
		for(int i=0;i<col.size();i++) {
			String str = col.get(i);
			String[] strs = str.split("----->");
			
			int tmp1 = Integer.parseInt(map.get(strs[0]).split("--")[0]);
			int tmp2 = Integer.parseInt(strs[1].split("--")[0]);
			
			String blanks = "           ";
			if(map.get(strs[0]).split("--")[0].equals(strs[1].split("--")[0])) {
				col.set(i, str+blanks+map.get(strs[0])+"   same");
			} else if(tmp1+1 == tmp2 || tmp1-1 == tmp2) {
				col.set(i, str+blanks+map.get(strs[0])+"   col_row_1");
			} else {
				col.set(i, str+blanks+map.get(strs[0]));
			}
			
		}
		
		try {
			FileUtil.writeToFile("C:/Users/Administrator/Documents/colrow.txt", col);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
