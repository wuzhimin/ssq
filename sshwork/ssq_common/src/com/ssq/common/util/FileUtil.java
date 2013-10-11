package com.ssq.common.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created on 2006-4-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Floder
 * @version 0.1
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FileUtil {

	public static List<String> readLineFile(String filePathAndName)
			throws IOException {
		List<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(filePathAndName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		while (line != null ) {
			String line1 = new String(line.trim());
			if(!result.contains(line1) && !line1.trim().equals("")) {
				result.add(line1);
			}
			line = br.readLine();
		}
		br.close();
		fr.close();
		return result;
	}
	
	/**
	 * 不检查重复或空
	 * @param filePathAndName
	 * @return
	 * @throws IOException
	 */
	public static List<String> readLineFileWithoutCheckRepeat(String filePathAndName)
			throws IOException {
		List<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(filePathAndName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		while (line != null && line.trim().length()>0) {
			result.add(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
		return result;
	}
	
	/**
	 * 去掉无效的
	 * @param filePathAndName
	 * @return
	 * @throws IOException
	 */
	public static List<String> readLineFileWithoutInvalid(String filePathAndName)
			throws IOException {
		List<String> result = readLineFile(filePathAndName);
		for(int j=result.size()-1;j>=0;j--) {
			if(result.get(j).trim().startsWith("--")) {
				result.remove(j);
			}
		}
		return result;
	}
	
	
	/**
	 * 增量导入，将list传进来
	 * @param filePathAndName
	 * @param result
	 * @return
	 * @throws IOException
	 */
	public static void readLineFile(String filePathAndName,List<String> result)
		throws IOException {
		if(result==null) {
			result = new ArrayList<String>(); 
		}
		FileReader fr = new FileReader(filePathAndName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		
		while (line != null && !line.trim().equals("")) {
			String line1 = new String(line.trim());
			if(!result.contains(line1)) {
				result.add(line1);
			}
			line = br.readLine();
		}
		br.close();
		fr.close();
	}

	public static void writeToFile(String filePathAndName, List<String> args)
			throws IOException {
		FileWriter fw = new FileWriter(filePathAndName);
		for (int i = 0; i < args.size(); i++) {
			if((args.get(i)).indexOf("\n")<0) { 
				fw.write(args.get(i) + "\n");
			} else {
				fw.write((String)args.get(i));
			}
			fw.flush();
		}
		fw.close();
	}
	
	/**
	 * 追加写文件
	 * @param fileName
	 * @param strList
	 * @throws IOException
	 */
	public static void writeToFileTail(String fileName, List<String> strList)
			throws IOException {
		FileWriter fw = new FileWriter(fileName,true);
		for (int i = 0; i < strList.size(); i++) {
			fw.write(strList.get(i)+"\n");
		}
		fw.close();
	}
	
	/**
	 * 读取双色球开奖记录
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> readSSQHistory1()
			throws IOException {
		String fileName = CommonConstant.SSQ_PATH+CommonConstant.SSQ_HISTORY_FILE;
		Map<String, String> result = new HashMap<String, String>();
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		while (line != null ) {
			String value = new String(line.trim());
			String key = value.substring(8);
			if(!result.containsKey(key) && !key.trim().equals("")) {
				result.put(key, value);
			}
			line = br.readLine();
		}
		br.close();
		fr.close();
		return result;
	}

	/**
	 * 读取双色球开奖记录到list
	 * @return
	 * @throws IOException
	 */
	public static List<String> getSSQHistoryList()
			throws IOException {
		String fileName = CommonConstant.SSQ_PATH+CommonConstant.SSQ_HISTORY_FILE;
		return FileUtil.readLineFileWithoutCheckRepeat(fileName);
	}

	/**
	 * 获取某一年份的双色球开奖记录
	 * @param year
	 * @return
	 * @throws IOException
	 */
	public static List<String> getSSQHistoryList(int year)
			throws IOException {
		String fileName = CommonConstant.SSQ_PATH+CommonConstant.SSQ_HISTORY_FILE;
		List<String> result = FileUtil.readLineFileWithoutCheckRepeat(fileName);
		for(int i=result.size()-1;i>=0;i--) {
			String str = result.get(i);
			if(!str.startsWith(String.valueOf(year))) {
				result.remove(i);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		try {
			List<String> s = getSSQHistoryList(2013);
			s.get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
