package com.laodong.pub.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowCallbackHandler;
import com.laodong.pub.product.spring.UserDAOSx;
import com.laodong.pub.util.dao.NLLDDAOTool;

public class UserDAO extends UserDAOSx{
	public LoginObj findLoginByAccount(String userid,String password)throws Exception{
		String sql = "select * from userinfo where userid = '" + userid + "' and password = '" + password + "'";
		   final LoginObj obj = new LoginObj();
		   jdbcTemplate.query(sql, new RowCallbackHandler() {
		   	public void processRow(ResultSet rs) throws SQLException {
		   		obj.setNull(false);
		   		obj.setUserid(rs.getString("userid"));
		   		obj.setPassword(rs.getString("password"));
		   		obj.setXb(rs.getString("xb"));
		   		obj.setAccount(rs.getString("account"));
		   	}
		   });
		   return (LoginObj)obj.getObject();		   
	}
	public LoginObj findLoginObjByUserid(String userid)throws Exception{
		String sql = "select * from userinfo where userid = '" + userid + "'";
		 final LoginObj obj = new LoginObj();
		   jdbcTemplate.query(sql, new RowCallbackHandler() {
		   	public void processRow(ResultSet rs) throws SQLException {
		   		obj.setNull(false);
		   		obj.setUserid(rs.getString("userid"));
		   		obj.setPassword(rs.getString("password"));
		   		obj.setXb(rs.getString("xb"));
		   		obj.setAccount(rs.getString("account"));
		   	}
		   });
		   return (LoginObj)obj.getObject();	
	}
	/*
	 * ×¢²áÓÃ»§
	 */
	public void registeruser(String userid, String password, String account)throws Exception{
		    String sql = "insert into userinfo (userid, password, account) values ('" + userid + "', '" + password + "', '" + account + "')";
		    NLLDDAOTool.executeUpdate(sql);  
	}
	public void updpassword(String userid, String password)throws Exception{
	    String sql = "update userinfo set password ='" + password + "' where userid = '" + userid + "'";
	    NLLDDAOTool.executeUpdate(sql);  
    }
}
