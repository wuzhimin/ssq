package util;

import java.util.*;
import java.io.*;
/**
 * 
 * 遵纪守法, 过滤敏感字.
 *
 */
public class BadWordFilter {
	private static List<String> wordList;// 敏感字列表
	
	static {
		setWordList(testReadBadWordsListFromFile());
	}
	
	/**
	 * 过滤敏感字.
	 * @param input
	 * @return
	 */
	public static String filterBadWords(String input) {
		if(StringUtil.isEmpty(input)) {
			return input;
		}

	
		for(String word : getWordList()) {
			input = input.replaceAll(word, "****");
		}
		
		return input;
	}
	
	/**
	 * 从配置文件载入非法词汇列表.
	 * @return
	 */
	private static List<String> testReadBadWordsListFromFile() {
		// 从配置文件加载关键字信息, 存入到一个List中, 一行一个关键字
		
		InputStream in = BadWordFilter.class.getResourceAsStream("/badwords.txt");
		
		ArrayList<String> list = new ArrayList<String>();// 关键字
		
		// 转成 Reader, 一次读一行用 BufferedReader
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			
			while( (line = br.readLine()) != null) {
				list.add(line);
			}
			
			in.close();
			br.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 检查输入字符串是否有非法词汇.
	 * @param input
	 * @return
	 */
	public static boolean hasBadWords(String input) {
	
		if(StringUtil.isEmpty(input)) {
			return false;
		}
		
		for(String word : getWordList()) {
			if(input.indexOf(word) != -1) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
//		setWordList(testReadBadWordsListFromFile());
		System.out.println(filterBadWords("我日, 我要抵制奥运!"));
		System.out.println(hasBadWords("我日 奥运天下太平 江泽民!"));
	}

	public static List<String> getWordList() {
		return wordList;
	}

	public static void setWordList(List<String> wordList) {
		BadWordFilter.wordList = wordList;
	}


}
