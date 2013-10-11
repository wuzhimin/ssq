package com.laodong.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.laodong.pub.util.dao.DAOTool;
import com.laodong.pub.util.dao.NLLDDAOTool;

public class Test implements Runnable {
	public static void main(String args[]) {
		System.out.println("kd d");
//		try {
//			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); 
//			Connection conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=laodong", "sa", ""); 
//
////			Connection conn = DAOTool.getConn();
//			Statement stat = conn.createStatement();
//			String sql = "";
//			int js = 112;
//			sql = "select * from deptbhrole where deptid = 16";
//			ResultSet rs = stat.executeQuery(sql);
//			List list = new ArrayList();
//			while (rs.next()) {
//				list.add(find(rs));
//			}
//			sql = "select * from xingzhengstruct where cengnum = 6 and xcengcistr like '%0000' and id not in (16,87)";
//			rs = stat.executeQuery(sql);
//
//			while (rs.next()) {
//				int deptid = rs.getInt("id");
//				for(int i=0;i<list.size();i++){
//					DeptBhRoleObj obj = (DeptBhRoleObj)list.get(i);
//					obj.setDeptid("" + deptid);
//					insDeptBhRoleObj(stat, js, obj);
//					js++;
//				}
//				
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 System.out.println(JavaMD5.encode("51"));
//		 TimerTask task = new MyTask();
//		 Timer timer = new Timer();
//		 Date date = new Date(22, 30, 12);
//		 timer.schedule(task, date, 60*1000);
//		 System.out.print("dd");
	}


	public static String insertsql(String tablename, String[][] attrs){
		String sql = "insert into " + tablename + " (";
		for(int i=0;i<attrs.length;i++){
			sql = sql + attrs[i][0] + ", ";
		}
		sql = sql.substring(0,sql.length()-2);
		sql = sql + ") values (";
		for(int i=0;i<attrs.length;i++){
			sql = sql + method(attrs[i]) + ", ";
		}
		sql = sql.substring(0,sql.length()-2);
		sql = sql + ")";
		return sql;
	}
	public static String method(String[] str){
		if("String".equals(str[1]))
			if(str[2]==null)
				return "null";
			else
			    return "'" + str[2] + "'";
		else
			return str[2];
	}


	public void run() {

	}
}
