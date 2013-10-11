package com.ttserver.client.test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tokyotyrant.RDB;

import com.ssq.util.CombinationUtil;
import com.ssq.util.CommonConstant;
import com.ssq.util.FileUtil;

public class TestTTServerGetAll {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		RDB db = new RDB();
		try {
			// connect to the server
			db.open(new InetSocketAddress("192.168.1.4", 11211));
			
			System.out.println("===== access all key ");
			db.iterinit();
			System.out.println(db.size());
			System.out.println(db.rnum());
			

			List<String> list = new ArrayList<String>();
			String key1 = null;
			int i=1;
			while ((key1 = (String)db.iternext()) != null) {
//				if(list.contains(key1)) {
//					System.out.println("same");
//					continue;
//				}
				list.add(key1);
				if(list.size()/10000>=i) {
					System.out.println(i*10000);
					i++;
				}
			}
			
			Collections.sort(list);
			FileUtil.writeToFile("d:/ddd/red-full-fromTT.txt", list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			db.close();
		}

	}

}
