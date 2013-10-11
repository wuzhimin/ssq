package com.ssq.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ssq.common.util.FileUtil;

public class TestHistoryInThirdExclude {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> source = new ArrayList<String>();
		try {
			source.addAll(FileUtil.getSSQHistoryList(2012));
			source.addAll(FileUtil.getSSQHistoryList(2011));
			source.addAll(FileUtil.getSSQHistoryList(2013));
			
			List<String> target = FileUtil.readLineFileWithoutCheckRepeat("D:/ddd/red-exclude-third-1.txt");
			for(String str:source) {
				if(target.contains(str.substring(8).substring(0,17).replaceAll(" ", ","))) {
					System.out.println(str);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

	}
	
	

}
