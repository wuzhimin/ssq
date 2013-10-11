package com.wzm.test;

import java.util.ArrayList;
import java.util.List;




public class Test1 {
	public static void main(String[] args) {
		aaa();
	}

	private static void aaa() {
		String str = " select ";
		for(int i=1;i<=91;i++) {
//			System.out.println("private int formula"+i+"KillRightMaxCount;");
//			System.out.println("private int formula"+i+"KillRightMinCount=100;\n");
			
			
//			System.out.println("@Column(name = \"fformula"+i+"KRMaxCount\", nullable = false)");
//			System.out.println("public int getFormula"+i+"KillRightMaxCount() {");
//			System.out.println("return formula"+i+"KillRightMaxCount;");
//			System.out.println("}\n");
//
//			System.out.println("public void setFormula"+i+"KillRightMaxCount(int formula"+i+"KillRightMaxCount) {");
//			System.out.println("this.formula"+i+"KillRightMaxCount = formula"+i+"KillRightMaxCount;");
//			System.out.println("}\n\n");
//							
//							
//			System.out.println("@Column(name = \"fformula"+i+"KRMinCount\", nullable = false)");
//			System.out.println("public int getFormula"+i+"KillRightMinCount() {");
//			System.out.println("return formula"+i+"KillRightMinCount;");
//			System.out.println("}\n");
//
//			System.out.println("public void setFormula"+i+"KillRightMinCount(int formula"+i+"KillRightMinCount) {");
//			System.out.println("this.formula"+i+"KillRightMinCount = formula"+i+"KillRightMinCount;");
//			System.out.println("}\n");	
			str = str+"sum(a.fkillf"+i+"right) ,";
			
		}
		
		System.out.println(str.substring(0,str.length()-1));
	}
	
	private static void bbb() {
		String str = " ";
		List<Integer> tmp = new ArrayList<Integer>();
//		tmp.add(arg0)
		for(int i=1;i<=91;i++) {
//			System.out.println("private int formula"+i+"KillRightMaxCount;");
//			System.out.println("private int formula"+i+"KillRightMinCount=100;\n");
			
			
//			System.out.println("@Column(name = \"fformula"+i+"KRMaxCount\", nullable = false)");
//			System.out.println("public int getFormula"+i+"KillRightMaxCount() {");
//			System.out.println("return formula"+i+"KillRightMaxCount;");
//			System.out.println("}\n");
//
//			System.out.println("public void setFormula"+i+"KillRightMaxCount(int formula"+i+"KillRightMaxCount) {");
//			System.out.println("this.formula"+i+"KillRightMaxCount = formula"+i+"KillRightMaxCount;");
//			System.out.println("}\n\n");
//							
//							
//			System.out.println("@Column(name = \"fformula"+i+"KRMinCount\", nullable = false)");
//			System.out.println("public int getFormula"+i+"KillRightMinCount() {");
//			System.out.println("return formula"+i+"KillRightMinCount;");
//			System.out.println("}\n");
//
//			System.out.println("public void setFormula"+i+"KillRightMinCount(int formula"+i+"KillRightMinCount) {");
//			System.out.println("this.formula"+i+"KillRightMinCount = formula"+i+"KillRightMinCount;");
//			System.out.println("}\n");	
			str = str+"sum(a.fkillf"+i+"right) ,";
			
		}
		
		System.out.println(str.substring(0,str.length()-1));
	}
}
