package com.ssq.test;

public class temptest {
	public static void main(String[] args) {
		String sql = "select  ffromssqindex,ftossqindex "+
		" ,fformula&&krcount  from t_redfulcaclmulsts where fspacenum = 50 order by fformula&&krcount DESC;";
		for(int i=1;i<=10;i++) {
			System.out.println(sql.replaceAll("&&", i+""));
		}
	}
}
