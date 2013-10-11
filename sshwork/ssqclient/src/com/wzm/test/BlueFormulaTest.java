package com.wzm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.FileUtil;
import com.wzm.server.service.ssq.SsqRecordService;
import com.wzm.util.ClientBeanUtil;

public class BlueFormulaTest {


	public static void main(String[] args) {
//		buildBlue();
	}

	public static void buildBlue(int ssqIndex) {
		
		//  获取SsqRecordService
		SsqRecordService ssqService = (SsqRecordService)ClientBeanUtil.getService("ssqRecordService");
		
		List<String> formulas = buildBlueFormulas1();
		
		formulas.addAll(buildBlueFormulas());
		
		Map<String, Object[]> result = ssqService.testBlue(ssqIndex, formulas);
		
		List<String> summaryList = (List<String>)result.get("summary")[1];
		
		result.remove("summary");

		for (String key : result.keySet()) {
			summaryList.add("\n\n------------------------------公式：" + key
					+ "--------------------------------\n");

			summaryList.add("总期数：" + result.get(key)[0]+"\n");

			@SuppressWarnings("unchecked")
			List<String> outs = (List<String>) result.get(key)[1];
			if (outs.size() > 0) {
				summaryList.add("预测数大于16期数：" + outs.size()+"\n");
				for (String tmp : outs) {
					summaryList.add(tmp);
				}
			}

			@SuppressWarnings("unchecked")
			List<String> rights = (List<String>) result.get(key)[2];
			if (rights.size() > 0) {
				summaryList.add("预测数正确期数：" + rights.size()+"\n");
				for (String tmp : rights) {
					if (tmp.indexOf("2012") >= 0 || tmp.indexOf("2013") >= 0)
						summaryList.add(tmp);
				}
			}

		}
		
		try {
			FileUtil.writeToFile("C:\\Users\\Administrator\\Documents\\blue.txt", summaryList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> buildBlueFormulas() {
		List<String> formulas = new ArrayList<String>();

		int match = 16;
		for (int i = 1; i <= 16; i++) {
			String str = " (c_red1 * c_red1 + " + i + ") % " + match
					+ " == 0 ? " + match + " : (c_red1 * c_red1 + " + i
					+ ") % " + match + "";
			String str1 = " (c_red1  + " + i + ") % " + match + " == 0 ? "
					+ match + " : (c_red1  + " + i + ") % " + match + "";
			String str2 = " (c_red2  + " + i + ") % " + match + " == 0 ? "
					+ match + " : (c_red2  + " + i + ") % " + match + "";
			String str3 = " (c_red3  + " + i + ") % " + match + " == 0 ? "
					+ match + " : (c_red3  + " + i + ") % " + match + "";
			String str4 = " (c_red4  + " + i + ") % " + match + " == 0 ? "
					+ match + " : (c_red4  + " + i + ") % " + match + "";
			String str5 = " (c_red5  + " + i + ") % " + match + " == 0 ? "
					+ match + " : (c_red5  + " + i + ") % " + match + "";

			formulas.add(str);
			formulas.add(str1);
			formulas.add(str2);
			formulas.add(str3);
			formulas.add(str4);
			formulas.add(str5);

		}
		
		formulas.add("c_red1 % 16 == 0 ?16 : c_red1 % 16");
		formulas.add("c_red2 % 16 == 0 ?16 : c_red2 % 16");
		formulas.add("c_red3 % 16 == 0 ?16 : c_red3 % 16");
		formulas.add("c_red4 % 16 == 0 ?16 : c_red4 % 16");
		formulas.add("c_red5 % 16 == 0 ?16 : c_red5 % 16");
		formulas.add("c_red6 % 16 == 0 ?16 : c_red6 % 16");
		
		return formulas;
	}
	
	private static List<String> buildBlueFormulas1() {
		List<String> formulas = new ArrayList<String>();

		int match = 16;
		for (int i = 1; i <= 16; i++) {
			String str = " (c_red1 * c_red1 * c_red1+ " + i + ") % " + match
					+ " == 0 ? " + match + " : (c_red1 * c_red1 * c_red1+ " + i
					+ ") % " + match + "";
			
			String str1 = " (c_red1 * "+match+"  + " + i + ") % " + match + " == 0 ? "
					+ match + " : (c_red1  * "+match+" + " + i + ") % " + match + "";
			
			formulas.add(str);
			formulas.add(str1);
		}
		
		return formulas;
	}

}
