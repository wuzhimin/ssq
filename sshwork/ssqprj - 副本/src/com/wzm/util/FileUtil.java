
package com.wzm.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Created on 2006-4-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author wzm
 * @version 0.1
 * 
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
	 * 去掉无效的
	 * @param filePathAndName
	 * @return
	 * @throws IOException
	 */
	public static List<String> readLineFileWithInvalid(String filePathAndName)
			throws IOException {
		List<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(filePathAndName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		while (line != null && !line.startsWith("--") ) {
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
	
	
	public static void writeFile(String filePathAndName, List<String> args)
			throws IOException {
		FileWriter fw = new FileWriter(filePathAndName);
		for (int i = 0; i < args.size(); i++) {
			if((args.get(i)).indexOf("\n")<0) { 
				fw.write(args.get(i) + "\n");
			} else {
				fw.write(args.get(i));
			}
			fw.flush();
		}
		fw.close();
	}

}
