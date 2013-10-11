package com.laodong.pub.util.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAOTool {
    public static Connection getConn()throws Exception{
    	Context initCtx=new InitialContext();
	    DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/laodong");
	    return ds.getConnection();
    }
    public static void closeConn(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if(rs!=null)
			   rs.close();
		if(stmt!=null)
			   stmt.close();
		if(conn!=null)
			   conn.close();
	}
    public static void closeConn(Connection conn, Statement stmt) throws SQLException {
		if(stmt!=null)
			   stmt.close();
		if(conn!=null)
			   conn.close();
	}
    public static void closeConn(ResultSet rs) throws SQLException {
		if(rs!=null)
			   rs.close();
	}
    public static void closeCon(Connection conn) throws SQLException {
		if(conn!=null)
			   conn.close();
	}
    public static void closeCon(Connection conn, Statement stmt) throws SQLException {
    	if(stmt!=null)
			   stmt.close();
		if(conn!=null)
			   conn.close();
	}
}
