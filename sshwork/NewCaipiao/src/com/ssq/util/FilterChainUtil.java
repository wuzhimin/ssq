package com.ssq.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssq.common.util.CommonConstant;
import com.ssq.common.util.FileUtil;
import com.ssq.util.filter.ACFilter;
import com.ssq.util.filter.Filter;
import com.ssq.util.filter.FilterChain;
import com.ssq.util.filter.FormulaFilter;
import com.ssq.util.filter.HistoryFeatureFilter;
import com.ssq.util.filter.LoseRowColFeatureFilter;
import com.ssq.util.filter.LoseRowColFeatureSameOrDiffFilter;
import com.ssq.util.filter.LoseRowColFilter;
import com.ssq.util.filter.RedSumFilter;
import com.ssq.util.filter.ResidueFeatureFilter;
import com.ssq.util.filter.SanduFilter;

public class FilterChainUtil {
	
	// 标准过滤配置文件
	public static final String STANDARD_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/standard_filter_config.txt";
	
	// 和值过滤配置文件
	public static final String SUM_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/sum_filter_config.txt";
	
	// 余数特征过滤配置文件
	public static final String RESIDUE_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/residue_filter_config.txt";
	
	// ac值过滤配置文件
	public static final String AC_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/ac_filter_config.txt";
	
	// 散度值过滤配置文件
	public static final String SANDU_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/sandu_filter_config.txt";
	
	// 公式过滤配置文件
	public static final String FORMULA_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/formula_filter_config.txt";
		
	// 历史特征值过滤配置文件
	public static final String HISTORY_FEATURE_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/history_feature_filter_config.txt";
	
	// 断行断列遗失数过滤配置文件
	public static final String LOSE_ROW_COL_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/lose_row_col_filter_config.txt";
	
	// 断行断列遗失数特征值过滤配置文件
	public static final String LOSE_ROW_COL_FEATURE_FILTER_FILE = CommonConstant.FILTER_PATH + "formula/lose_row_col_feature_filter_config.txt";
		
		
	public static FilterChain buildAFullFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		//  标准过滤链
		FilterChain chain1 = buildAStandardFilterChain();
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		// 余数特征过滤链
		FilterChain chainR3 = buildAResidueFeatureFilterChain();
		// 指针移到第一位
		chainR3 = makePoint2First(chainR3);
		chain1.setNextNode(chainR3);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		//  和值过滤链
		FilterChain chain2 = buildASumFilterChain();
		// 指针移到第一位
		chain2 = makePoint2First(chain2);
		chain1.setNextNode(chain2);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		// ac值过滤链
		FilterChain chain3 = buildAACFilterChain();
		// 指针移到第一位
		chain3 = makePoint2First(chain3);
		chain1.setNextNode(chain3);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		// 散度值过滤链
		FilterChain chain4 = buildASanduFilterChain();
		// 指针移到第一位
		chain4 = makePoint2First(chain4);
		chain1.setNextNode(chain4);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		// 历史特征值过滤链		
		FilterChain chain6 = buildAHistoryFeatureFilterChain();
		// 指针移到第一位
		chain6 = makePoint2First(chain6);
		chain1.setNextNode(chain6);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		// 断行断列过滤链		
		FilterChain chain7 = buildALoseRowColFilterChain();
		// 指针移到第一位
		chain7 = makePoint2First(chain7);
		chain1.setNextNode(chain7);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		// 断行断列特征值过滤链		
		FilterChain chain8 = buildALoseRowColFeatureFilterChain();
		// 指针移到第一位
		chain8 = makePoint2First(chain8);
		chain1.setNextNode(chain8);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		// 断行断列特征值是否相同过滤链		TODO 
		String lastColLoseStr = "145";
		String lastRowLoseStr = "001";
		
		FilterChain chain10 = new FilterChain(
				new LoseRowColFeatureSameOrDiffFilter(LoseRowColFeatureSameOrDiffFilter.NOT_COL_SAME, lastColLoseStr,lastRowLoseStr));
		// 指针移到第一位
		chain10 = makePoint2First(chain10);
		chain1.setNextNode(chain10);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
				
		// 公式过滤链		
		FilterChain chain5 = buildAFormulaFilterChain();
		// 指针移到第一位
		chain5 = makePoint2First(chain5);
		chain1.setNextNode(chain5);
		// 指针移到最后一位
		chain1 = makePoint2End(chain1);
		
		
		// 指针移到第一位
		chain1 = makePoint2First(chain1);
		
		return chain1;
	}
	
	/**
	 * 创建一个标准过滤链
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildAStandardFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> standardConfigList = FileUtil.readLineFileWithoutInvalid(STANDARD_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<standardConfigList.size();i++) {
			String str = standardConfigList.get(i);
			Filter filter = (Filter)Class.forName(str).newInstance();
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 创建一个余数特征过滤
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildAResidueFeatureFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> residueConfigList = FileUtil.readLineFileWithoutInvalid(RESIDUE_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<residueConfigList.size();i++) {
			String str = residueConfigList.get(i);
			String[] tmp= str.split(",");
			int divisor = Integer.parseInt(tmp[0]);
			int year = Integer.parseInt(tmp[1]);
			Filter filter = new ResidueFeatureFilter(divisor, year);
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 创建一个历史特征值过滤链
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildAHistoryFeatureFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> historyfeatureConfigList = FileUtil.readLineFileWithoutInvalid(HISTORY_FEATURE_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<historyfeatureConfigList.size();i++) {
			String str = historyfeatureConfigList.get(i);
			
			Filter filter = new HistoryFeatureFilter(str);
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildALoseRowColFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> loseRowColConfigList = FileUtil.readLineFileWithoutInvalid(LOSE_ROW_COL_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<loseRowColConfigList.size();i++) {
			String str = loseRowColConfigList.get(i);
			String[] strs = str.split(",");
			int type = Integer.parseInt(strs[0]);
			int loseCount1 = Integer.parseInt(strs[1]);
			int loseCount2 = Integer.parseInt(strs[2]);
			
			Filter filter = new LoseRowColFilter(type, loseCount1, loseCount2);
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 创建一个断行断列遗失特征字符串过滤链，比如行列遗失字符串为“002”，那么则用目标的行列遗失字符串来和它比较，相等或不相等
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildALoseRowColFeatureFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> loseRowColFeatureConfigList = FileUtil.readLineFileWithoutInvalid(LOSE_ROW_COL_FEATURE_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<loseRowColFeatureConfigList.size();i++) {
			String str = loseRowColFeatureConfigList.get(i);
			String[] strs = str.split(",");
			int type = Integer.parseInt(strs[0]);
			String featureStr = strs[1];
			boolean isEqual = Integer.parseInt(strs[2])==1;
			
			Filter filter = new LoseRowColFeatureFilter(type, featureStr, isEqual);
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 创建一个和值过滤链
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildASumFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> sumConfigList = FileUtil.readLineFileWithoutInvalid(SUM_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<sumConfigList.size();i++) {
			String str = sumConfigList.get(i);
			String[] strs = str.split(",");
			String type = strs[0];
			int sum1 = Integer.parseInt(strs[1]);
			int sum2 = Integer.parseInt(strs[2]);
			
			Filter filter = new RedSumFilter(type, sum1, sum2);
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 创建一个AC值过滤链
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildAACFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> sumConfigList = FileUtil.readLineFileWithoutInvalid(AC_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<sumConfigList.size();i++) {
			String str = sumConfigList.get(i);
			String[] strs = str.split(",");
			int ac1 = Integer.parseInt(strs[0]);
			int ac2 = Integer.parseInt(strs[1]);
			
			Filter filter = new ACFilter(ac1, ac2);
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 创建一个散度值过滤链
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildASanduFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> sanduConfigList = FileUtil.readLineFileWithoutInvalid(SANDU_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<sanduConfigList.size();i++) {
			String str = sanduConfigList.get(i);
			String[] strs = str.split(",");
			int sandu1 = Integer.parseInt(strs[0]);
			int sandu2 = Integer.parseInt(strs[1]);
			
			Filter filter = new SanduFilter(sandu1, sandu2);
			if(i==0) {
				chain = new FilterChain(filter);
			} else {
				FilterChain swapChain = new FilterChain(filter);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			}
		}
		
		return chain;
	}
	
	/**
	 * 创建一个公式过滤链
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static FilterChain buildAFormulaFilterChain() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		List<String> formulaConfigList = FileUtil.readLineFileWithoutInvalid(FORMULA_FILTER_FILE);
		
		FilterChain chain = null;
		
		for(int i=0;i<formulaConfigList.size();i++) {
			String formulaFileName = formulaConfigList.get(i);
			
			if(i==0) {
				chain = buildFilterChainFromFileWithPointToEnd(formulaFileName);
			} else {
				FilterChain swapChain = buildFilterChainFromFileWithPointToFirst(formulaFileName);
				chain.setNextNode(swapChain);
				chain = makePoint2End(chain);
			} 
		}
		
		return chain;
	}

	

	/**
	 * 从文件构造公式过滤链,指针到第一个
	 * @param formulaFile
	 * @return
	 */
	public static FilterChain buildFilterChainFromFileWithPointToFirst(String formulaFile) {
		List<String> formulaList = null;
		
		try {
			formulaList = FileUtil.readLineFileWithoutInvalid(formulaFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		FilterChain chainCurrent = null;
		for (int i = 0; i < formulaList.size(); i++) {
			String str = formulaList.get(i);
			Filter filterx = new FormulaFilter(str, new HashMap<String, Object>());
			if (i == 0) {
				chainCurrent = new FilterChain(filterx);
			} else {
				FilterChain chainTmp1 = new FilterChain(filterx);
				chainCurrent.setNextNode(chainTmp1);
				chainCurrent = chainTmp1;
			}
		}
		
		chainCurrent = makePoint2First(chainCurrent);
		return chainCurrent;
	}

	/**
	 * 挪到第一个位置
	 * @param chainCurrent
	 * @return
	 */
	public static FilterChain makePoint2First(FilterChain chainCurrent) {
		while(chainCurrent.getPreNode() != null) {
			chainCurrent = chainCurrent.getPreNode();
		}
		return chainCurrent;
	}
	
	/**
	 * 挪到最后一个位置
	 * @param chainCurrent
	 * @return
	 */
	public static FilterChain makePoint2End(FilterChain chainCurrent) {
		while(chainCurrent.getNextNode() != null) {
			chainCurrent = chainCurrent.getNextNode();
		}
		return chainCurrent;
	}
	
	/**
	 * 从文件构造公式过滤链,指针到最后
	 * @param formulaFile
	 * @return
	 */
	public static FilterChain buildFilterChainFromFileWithPointToEnd(String formulaFile) {
		List<String> formulaList = null;
		
		try {
			formulaList = FileUtil.readLineFileWithoutInvalid(formulaFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		FilterChain chainCurrent = null;
		for (int i = 0; i < formulaList.size(); i++) {
			String str = formulaList.get(i);
			Filter filterx = new FormulaFilter(str, new HashMap<String, Object>());
			if (i == 0) {
				chainCurrent = new FilterChain(filterx);
			} else {
				FilterChain chainTmp1 = new FilterChain(filterx);
				chainCurrent.setNextNode(chainTmp1);
				chainCurrent = makePoint2End(chainCurrent);
			}
		}
		
		return chainCurrent;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			List<String> aa = new ArrayList<String>();
			aa.add("09,12,13,24,27,33,12");
			
			FilterChain chain = FilterChainUtil.buildAFullFilterChain();
			
			chain = makePoint2First(chain);
			
			for(String str:aa) {
				boolean isFilter = chain.doFilter(str,true);
				if(isFilter) {
					System.out.println(str+"------"+isFilter);
					System.out.println("-----------");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
