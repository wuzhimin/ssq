package com.ttserver.client.test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import tokyotyrant.RDB;

import com.ssq.util.CombinationUtil;
import com.ssq.util.CommonConstant;
import com.ssq.util.FileUtil;

public class TestTTServerGetFunction1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		RDB db = new RDB();
		try {
			// connect to the server
			db.open(new InetSocketAddress("192.168.1.4", 11211));
			
			List<String> list = FileUtil.readLineFile(CommonConstant.SSQ_PATH+CommonConstant.SSQ_HISTORY_FILE);
			
			for (String str : list) {
				str = str.substring(8);
				str = str.substring(0,17);
				if(db.get(str) != null)
				System.out.println(str+"--------->"+db.get(str));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			db.close();
		}

	}

}
