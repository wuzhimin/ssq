package com.ssq.control;

import java.io.IOException;
import java.util.List;

import com.ssq.common.util.FileUtil;

public class DataFactory {
	private int totalCount = 0;
	
	private String fileName = null;

	public void buildData(List<String> dataList) {
		if(totalCount == 0) {
			String a = String.valueOf(System.currentTimeMillis());
			fileName = "d:/out/" + a + ".txt";
		} 
		
		totalCount = totalCount + dataList.size();
		
		try {
			FileUtil.writeToFileTail(fileName, dataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(totalCount >= 100000) {
			totalCount =0;
		}
	}

}
