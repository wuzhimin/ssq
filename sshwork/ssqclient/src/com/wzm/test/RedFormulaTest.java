package com.wzm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssq.common.util.FileUtil;
import com.wzm.server.service.ssq.SsqRecordService;
import com.wzm.util.ClientBeanUtil;

public class RedFormulaTest {

	public static void main(String[] args) {
//		buildRed();
	}

	public static void buildRed(int ssqIndex) {
		SsqRecordService ssqService = (SsqRecordService) ClientBeanUtil.getService("ssqRecordService");
		
		List<String> formulas = bulidRedFormulas();

		Map<String, Object[]> result = ssqService.testRed(ssqIndex, formulas);
		
		List<String> summaryList = (List<String>)result.get("summary")[1];
		
		result.remove("summary");

		for (String key : result.keySet()) {
			summaryList.add("\n\n------------------------------公式：" + key
					+ "--------------------------------\n");

			summaryList.add("总期数：" + result.get(key)[0]+"\n");

			List<String> outs = (List<String>) result.get(key)[1];
			if (outs.size() > 0) {
				summaryList.add("预测数大于33期数：" + outs.size()+"\n");
				for (String tmp : outs) {
					summaryList.add(tmp);
				}
			}

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
			FileUtil.writeToFile("C:\\Users\\Administrator\\Documents\\red.txt", summaryList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> bulidRedFormulas() {
		List<String> formulas = new ArrayList<String>();

		for (int i = 1; i <= 33; i++) {
			String str = " (c_red1 * c_red1 + " + i
					+ ") % 33 == 0 ? 33 : (c_red1 * c_red1 + " + i + ") % 33";
			String str1 = " (c_red1  + " + i + ") % 33 == 0 ? 33 : (c_red1  + "
					+ i + ") % 33";
			String str2 = " (c_red2  + " + i + ") % 33 == 0 ? 33 : (c_red2  + "
					+ i + ") % 33";
			String str3 = " (c_red3  + " + i + ") % 33 == 0 ? 33 : (c_red3  + "
					+ i + ") % 33";
			String str4 = " (c_red4  + " + i + ") % 33 == 0 ? 33 : (c_red4  + "
					+ i + ") % 33";
			String str5 = " (c_red5  + " + i + ") % 33 == 0 ? 33 : (c_red5  + "
					+ i + ") % 33";

			formulas.add(str);
			formulas.add(str1);
			formulas.add(str2);
			formulas.add(str3);
			formulas.add(str4);
			formulas.add(str5);

		}
		return formulas;
	}
	
	private static List<String> bulidRedFormulas1() {
		List<String> formulas = new ArrayList<String>();
		formulas.add("(c_red1 * c_red1 + c_red1) % 33 == 0 ? 33 : (c_red1 * c_red1 + c_red1) % 33");
		formulas.add("(c_red1 * c_red1 - c_red1) % 33 == 0 ? 33 : (c_red1 * c_red1 - c_red1) % 33");
		formulas.add("(c_red1 * c_red1 + c_red2) % 33 == 0 ? 33 : (c_red1 * c_red1 + c_red2) % 33");
		formulas.add("(c_red1 * c_red1 + c_red3) % 33 == 0 ? 33 : (c_red1 * c_red1 + c_red3) % 33");
		// formulas.add("(c_red1 * c_red1 + 78) % 33 == 0 ? 33 : (c_red1 * c_red1 + 78) % 33");
		// formulas.add("(c_red1  + 64) % 33 == 0 ? 33 : (c_red1  + 64) % 33");
		// formulas.add("(c_red2  + 68) % 33 == 0 ? 33 : (c_red2  + 68) % 33");
		// formulas.add("(c_red1  + 97) % 33 == 0 ? 33 : (c_red1  + 97) % 33");
		// formulas.add("(c_red2  + 2) % 33 == 0 ? 33 : (c_red2  + 2) % 33");
		// formulas.add("(c_red1  + 38) % 33 == 0 ? 33 : (c_red1  + 38) % 33");
		// formulas.add("(c_red1  + 71) % 33 == 0 ? 33 : (c_red1  + 71) % 33");
		// formulas.add("(c_red1 * c_red1 + 63) % 33 == 0 ? 33 : (c_red1 * c_red1 + 63) % 33");
		// formulas.add("(c_red1 * c_red1 + 45) % 33 == 0 ? 33 : (c_red1 * c_red1 + 45) % 33");
		// formulas.add("(c_red2  + 35) % 33 == 0 ? 33 : (c_red2  + 35) % 33");
		// formulas.add("(c_red1 * c_red1 + 30) % 33 == 0 ? 33 : (c_red1 * c_red1 + 30) % 33");
		// formulas.add("(c_red1  + 31) % 33 == 0 ? 33 : (c_red1  + 31) % 33");

		// formulas.add("( (( c_red2 * c_red2) % 13) == 0 ? 13 : (( c_red2 * c_red2) % 13) )");
		// formulas.add("( (( c_red2 * c_red2* c_red2) % 13) == 0 ? 13 : (( c_red2 * c_red2* c_red2) % 13) )");
		// formulas.add("( (( c_red3 * c_red3) % 33) == 0 ? 33 : (( c_red3 * c_red3) % 33) )");
		// formulas.add("( (( c_red4 * c_red4) % 33) == 0 ? 33 : (( c_red4 * c_red4) % 33) )");
		// formulas.add("( (( c_red5 * c_red5) % 33) == 0 ? 33 : (( c_red5 * c_red5) % 33) )");
		// formulas.add("( (( c_red6 * c_red6) % 33) == 0 ? 33 : (( c_red6 * c_red6) % 33) )");
		// formulas.add("( (( c_blue1 * c_blue1) % 33) == 0 ? 33 : (( c_blue1 * c_blue1) % 33) )");
		
		return formulas;
	}

}
