package com.laodong.pub.util.dao;

import java.sql.Connection;

import com.laodong.test.conn.ConnectionDispenser;

/**
 * 类名称 事务测试
 * 业务描述
 *
 * 2007-1-2622:45:26
 */
public class SwTest {
	public void test(String userid) {
		try {

			ConnectionDispenser cc = new ConnectionDispenser();
			Connection conn1 = cc.getConnection();
			System.out.println("conn1: " + conn1);
			// conn1.close();
			ConnectionDispenser cc2 = new ConnectionDispenser();
			Connection conn2 = cc2.getConnection();
			System.out.println("conn2: " + conn2);
			// conn2.close();
			ConnectionDispenser cc3 = new ConnectionDispenser();
			Connection conn3 = cc.getConnection();
			System.out.println("conn3: " + conn3);
			// conn3.close();
			Connection conn4 = cc.getConnection();
			System.out.println("conn4: " + conn4);
			// conn4.close();
			Connection conn5 = cc.getConnection();
			System.out.println("conn5: " + conn5);
			// conn5.close();
			Connection conn6 = cc.getConnection();
			System.out.println("conn6: " + conn6);
			conn6.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
