package com.ttserver.client.test;

import java.net.InetSocketAddress;
import java.util.List;

import tokyotyrant.RDB;

import com.ssq.util.SsqUtils;

public class TestTTServerPutFunction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		RDB db = new RDB();
		try {
			// connect to the server
			db.open(new InetSocketAddress("192.168.1.4", 11211));

//			Object key;
			Object value="y";
			
			while(true) {
				List<String> list = SsqUtils.genRandomBetsWithoutBlue(5);
				for(String str:list) {
					db.put(str+"22", value);
				}
				
				Thread.sleep(5);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			db.close();
		}
	

	}

}
