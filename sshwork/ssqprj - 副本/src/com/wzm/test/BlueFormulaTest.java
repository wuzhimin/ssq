package com.wzm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wzm.server.service.ssq.SsqService;

public class BlueFormulaTest {

	public String getPath() {
		return this.getClass().getResource("/").getPath();
	}

	public static void main(String[] args) {
		String path = new BlueFormulaTest().getPath();
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path
				+ "applicationContext.xml");

		SsqService ssqService = (SsqService) ctx.getBean("ssqService");

		List<String> formulas = buildBlueFormulas1();
		
		formulas.addAll(buildBlueFormulas());

		Map<String, Object[]> result = ssqService.testBlue(formulas);

		for (String key : result.keySet()) {
			System.out.println("\n\n------------------------------公式：" + key
					+ "--------------------------------");

			System.out.println("总期数：" + result.get(key)[0]);

			@SuppressWarnings("unchecked")
			List<String> outs = (List<String>) result.get(key)[1];
			if (outs.size() > 0) {
				System.out.println("预测数大于16期数：" + outs.size());
				for (String tmp : outs) {
					System.out.println(tmp);
				}
			}

			@SuppressWarnings("unchecked")
			List<String> rights = (List<String>) result.get(key)[2];
			if (rights.size() > 0) {
				System.out.println("预测数正确期数：" + rights.size());
				for (String tmp : rights) {
					if (tmp.indexOf("2012") >= 0 || tmp.indexOf("2013") >= 0)
						System.out.println(tmp);
				}
			}

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
			
//			String str2 = " (c_red2  + " + i + ") % " + match + " == 0 ? "
//					+ match + " : (c_red2  + " + i + ") % " + match + "";
//			
//			String str3 = " (c_red3  + " + i + ") % " + match + " == 0 ? "
//					+ match + " : (c_red3  + " + i + ") % " + match + "";
//			
//			String str4 = " (c_red4  + " + i + ") % " + match + " == 0 ? "
//					+ match + " : (c_red4  + " + i + ") % " + match + "";
//			
//			String str5 = " (c_red5  + " + i + ") % " + match + " == 0 ? "
//					+ match + " : (c_red5  + " + i + ") % " + match + "";

			formulas.add(str);
			formulas.add(str1);
//			formulas.add(str2);
//			formulas.add(str3);
//			formulas.add(str4);
//			formulas.add(str5);

		}
		
		return formulas;
	}

}
