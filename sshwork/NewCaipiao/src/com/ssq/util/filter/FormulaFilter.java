package com.ssq.util.filter;

import java.util.Iterator;
import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;


/**
 * 公式过滤
 * 
 * @author Administrator
 * 
 */
public class FormulaFilter implements Filter {
	private String formula = null;
	
	private Interpreter iexp = new Interpreter();

	public FormulaFilter(String formula, Map<String, Object> paramMap) {
		this.setFormula(formula);
		
		if(!paramMap.isEmpty()) {
			for(Iterator<String> it = paramMap.keySet().iterator();it.hasNext();) {
				String key = it.next();
				Object value = paramMap.get(key);
				try {
					iexp.set(key, value);
				} catch (EvalError e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public FormulaFilter(String formula) {
		this.setFormula(formula);
	}

	@Override
	public boolean doFilter(String str) {

		String[] strs = str.split(",");

		int t1 = Integer.parseInt(strs[0]);
		int t2 = Integer.parseInt(strs[1]);
		int t3 = Integer.parseInt(strs[2]);
		int t4 = Integer.parseInt(strs[3]);
		int t5 = Integer.parseInt(strs[4]);
		int t6 = Integer.parseInt(strs[5]);
		
		try {
			iexp.set("r1", t1);
			iexp.set("r2", t2);
			iexp.set("r3", t3);
			iexp.set("r4", t4);
			iexp.set("r5", t5);
			iexp.set("r6", t6);
			
			boolean result = ((Boolean)iexp.eval(getFormula())).booleanValue();

			return result;
		} catch (EvalError e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String toString() {
		String str = "公式过滤，公式："+formula;
		return str;
	}
}
