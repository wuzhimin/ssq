package com.ttserver.client.test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import tokyotyrant.RDB;

import com.ssq.util.CombinationUtil;
import com.ssq.util.SsqUtils;

public class TestTTServerGetFunction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] key = new String[] { "01 02 03 04 05 06",
				"04 14 21 24 30 32", "02 03 15 16 25 29",
				"11 13 18 19 23 31", "15 17 19 20 21 28",
				"02 08 13 15 20 25", "05 09 11 15 27 28",
				"02 03 16 22 29 32", "03 05 12 17 29 30",
				"28 29 30 31 32 33","07 13 17 19 22 26" };
		
//		List<String> list = SsqUtils.genRandomBetsWithoutBlue(10000);
//		list.add("01 02 03 04 05 33");
//		list.add("03 05 17 18 26 27");
		
		List<String>  list = new ArrayList<String>();
		CombinationUtil.createCombo(
				new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
						      ,11, 12, 13, 14, 15 ,16 ,17 ,18 ,19 ,20,
						      21 ,22,23,24}, 6, 6, list);
		

		RDB db = new RDB();
		try {
			// connect to the server
			db.open(new InetSocketAddress("192.168.1.4", 11211));
			System.out.println(db.rnum());
			for (String str : list) {
				if(db.get(str) == null)
				System.out.println(str+"--------->"+db.get(str));
			}
			
//			System.out.println("===== access all key ");
//			db.iterinit();
//			
//			String key1 = null;
//			while ((key1 = (String)db.iternext()) != null) {
//				Object value = db.get(key1);
//				System.out.println(key1 + " =: " + value);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			db.close();
		}

	}

}
