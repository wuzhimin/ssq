package com.laodong.test.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.laodong.pub.util.dao.DAOTool;

/**
 * 类名称
 * 业务描述
 *
 * 2007-1-2622:12:59
 */
public class ConnectionDispenser {

	private static ThreadLocalConnection conn = new ThreadLocalConnection();

	public static Connection getConnection() {
		Connection con = (Connection)conn.get();
		try {
			if(con==null||!con.isClosed())
				conn.set(conn.initialValue());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Connection) conn.get();
	}
	public static void closeConnection(){
		try {
			getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.set(null);
	}

}
