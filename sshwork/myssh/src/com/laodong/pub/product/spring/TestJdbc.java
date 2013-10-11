package com.laodong.pub.product.spring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.laodong.pub.login.LoginObj;

/**@类名称
 * @业务描述
 *
 * @author lhh
 * @时间 2007-7-72:36:21
 */
public class TestJdbc {
	public static void main(String[] args)throws Exception {
		aa();
	}
     public static void aa()throws Exception{
    	 Connection conn=null;
 		Statement stmt=null;
 		ResultSet rs = null;
    	 Context initCtx=new InitialContext();
		    DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/laodong");
		    conn=ds.getConnection();
		    stmt=conn.createStatement();
		    String sql = "select * from userinfo ";
		    rs = stmt.executeQuery(sql);
		    while(rs.next()){
		    	System.out.println(rs.getString("userid") + " " + rs.getString("password"));

		    }
     }
}
