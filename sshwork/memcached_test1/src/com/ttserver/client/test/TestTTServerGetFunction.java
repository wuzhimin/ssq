package com.ttserver.client.test;

import java.net.InetSocketAddress;

import tokyotyrant.RDB;

public class TestTTServerGetFunction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] key = new String[] { "09 12 13 15 19 29 09",
				"04 14 21 24 30 32 05", "02 03 15 16 25 29 05",
				"11 13 18 19 23 31 12", "15 17 19 20 21 28 10",
				"02 08 13 15 20 25 15", "05 09 11 15 27 28 07",
				"02 03 16 22 29 32 13", "03 05 12 17 29 30 02",
				"08 14 17 18 29 31 15","ddd1","ddd2","ssssqww" };

		RDB db = new RDB();
		try {
			// connect to the server
			db.open(new InetSocketAddress("192.168.1.4", 11211));

			for (String str : key) {
				System.out.println(db.get(str));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			db.close();
		}

	}

}
