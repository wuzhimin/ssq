package com.laodong.test.conn;

import java.sql.Connection;

import com.laodong.pub.util.dao.DAOTool;

/**类名称
 * 业务描述
 *
 * 2007-1-2623:16:26
 */
public class ThreadLocalConnection extends ThreadLocal {
	public Object initialValue() {
		Connection conn = null;
		try {
			conn = DAOTool.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
