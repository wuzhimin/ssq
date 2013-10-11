package com.wzm.util;

import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;

import com.wzm.server.entity.ssq.SsqRecord;

public class FormulaUtil {

	private static final String C_BLUE1 = "c_blue1";
	private static final String C_RED6 = "c_red6";
	private static final String C_RED5 = "c_red5";
	private static final String C_RED4 = "c_red4";
	private static final String C_RED3 = "c_red3";
	private static final String C_RED2 = "c_red2";
	private static final String C_RED1 = "c_red1";
	private static final String C_INDEX = "c_index";

	/**
	 * 计算公式
	 * 
	 * @param formulaStr
	 * @param formulaName
	 * @param ssqRecord
	 * @param expMap
	 * @return
	 */
	public static int calculate(String formulaStr, String formulaName,
			SsqRecord ssqRecord, Map<String, Interpreter> expMap) {

		int cIndex = 0;
		int cR[] = new int[6];
		int cB = 0;

		cIndex = ssqRecord.getSsqIndex() % 1000;
		cR[0] = ssqRecord.getR1();
		cR[1] = ssqRecord.getR2();
		cR[2] = ssqRecord.getR3();
		cR[3] = ssqRecord.getR4();
		cR[4] = ssqRecord.getR5();
		cR[5] = ssqRecord.getR6();
		cB = ssqRecord.getB1();

		int result = 0;

		Interpreter iexp = null;
		if (expMap.containsKey(formulaName)) {
			iexp = expMap.get(formulaName);
		} else {
			iexp = new Interpreter();
			expMap.put(formulaName, iexp);
		}

		try {

			iexp.set(C_INDEX, new Integer(cIndex));
			iexp.set(C_RED1, new Integer(cR[0]));
			iexp.set(C_RED2, new Integer(cR[1]));
			iexp.set(C_RED3, new Integer(cR[2]));
			iexp.set(C_RED4, new Integer(cR[3]));
			iexp.set(C_RED5, new Integer(cR[4]));
			iexp.set(C_RED6, new Integer(cR[5]));
			iexp.set(C_BLUE1, new Integer(cB));

			result = ((Integer) iexp.eval(formulaStr)).intValue();
		} catch (EvalError e) {
			e.printStackTrace();
		}
		return result;
	}

}
