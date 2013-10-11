package com.ttserver.client.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import tokyotyrant.RDB;
import tokyotyrant.transcoder.DoubleTranscoder;
import tokyotyrant.transcoder.IntegerTranscoder;

/**
 *
 * blog http://www.micmiu.com
 *
 * @author Michael
 *
 */
public class RDBExample {

	public static void main(String[] args) throws IOException {

		RDB db = new RDB();
		try {
			// connect to the server
			db.open(new InetSocketAddress("192.168.1.4", 11211));

			Object key;
			Object value="";
			// store records
			if (db.put("my_firstname", "Sun")) {
				System.out.println("db put my_firstname successful.");
			} else {
				System.out.println("db put my_firstname error.");
			}

			if (db.put("my_lastname", "Michael")) {
				System.out.println("db put my_lastname successful.");
			} else {
				System.out.println("db put my_lastname error.");
			}

			if (db.put("my_blogurl", "www.micmiu.com")) {
				System.out.println("db put my_blogurl successful.");
			} else {
				System.out.println("db put my_blogurl error.");
			}

			if (db.put("my_weibo", "www.sina.com/ctosun")) {
				System.out.println("db put my_weibo successful.");
			} else {
				System.out.println("db put my_weibo error.");
			}

			// retrieve records
			value = db.get("my_blogurl");
			System.out.println("test_blogurl =: " + value);

			value = db.get("test_noexit");
			System.out.println("test_noexit =: " + value);

			System.out.println("===== test repeat put ");
			db.put("test_desc", "hello world");
			System.out.println("test_desc =: " + db.get("test_desc"));
			db.put("test_desc", "repeat put value is hello Michael");
			System.out.println("test_desc =: " + db.get("test_desc"));

			// Initialize the iterator
			System.out.println("===== access all key ");
			db.iterinit();
			while ((key = db.iternext()) != null) {
				value = db.get(key);
				System.out.println(key + " =: " + value);
			}
			System.out.println("===== test int double ");
			// add int
			db.put("int_i", 3, new IntegerTranscoder());
			int i = db.addint("int_i", 4);
			System.out.println(" i =: " + i);
			System.out.println("int_i =: "
					+ db.get("int_i", new IntegerTranscoder()));

			// add double
			db.put("dou_d", 3.0D, new DoubleTranscoder());
			double d = db.adddouble("dou_d", 4.0D);
			System.out.println(" d =: " + d);
			System.out.println("dou_d =: "
					+ db.get("dou_d", new DoubleTranscoder()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			db.close();
		}
	}
}