package com.ssq.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ssq.util.FileUtil;
import com.ssq.util.SsqUtils;

public class ExcludeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<String> sourceList = null;
		try {
			sourceList = FileUtil.readLineFile("C:\\Users\\Administrator\\Documents\\±à¼­2.TXT");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File path = new File("d:\\out");
		File[] files = path.listFiles();
		for(File file:files) {
			if(file.getName().indexOf(".txt")>=0) {
				try {
					List<String> excludeList = FileUtil.readLineFile(file.getPath());
					SsqUtils.excludeBets(sourceList, excludeList);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println();

	}

}
