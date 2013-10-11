package com.ssq.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssq.common.util.FileUtil;

public class TestBuildThirdExclude {
	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		System.out.println(time);
		List<String> resultSource = null;
		List<String> resultTarget = new ArrayList<String>();;
		try {
			
			resultSource = FileUtil
					.readLineFileWithoutCheckRepeat("C:/Users/Administrator/Documents/±à¼­4.txt");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for(int i=resultSource.size()-1;i>=0;i--) {
			String str = resultSource.get(i).substring(0,17);
			if(!resultTarget.contains(str)) {
				resultTarget.add(str);
			}
			resultSource.remove(i);
		}
		
		try {

			Collections.sort(resultTarget);
			FileUtil.writeToFile("D:/ddd/red-exclude-third-1.txt", resultTarget);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(System.currentTimeMillis() - time);
		System.out.print("finish");
	}
}
