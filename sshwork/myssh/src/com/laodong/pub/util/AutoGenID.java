package com.laodong.pub.util;

import com.laodong.pub.util.dao.NLLDDAOTool;
import com.laodong.pub.util.imp.AutoGenIDDAOImp;
import com.laodong.pub.util.imp.AutoGenIDObj;

public class AutoGenID {
	private static AutoGenIDDAOImp dao = (AutoGenIDDAOImp)NLLDDAOTool.getBean("autoGenIDDAO");
	public static synchronized int getAutoIDWithInt(String tablename)throws Exception{
		String id = findIdPrimary(tablename);
		if(id==null){
			insSQ(tablename, "1");
			return 1;
		}
		else{
			int idnum = Integer.parseInt(id) + 1;
			updSQ(tablename, "" + idnum);
			return idnum;
		}
	}
	public static synchronized String getAutoID(String tablename)throws Exception{
		String id = findIdPrimary(tablename);
		if(id==null){
			insSQ(tablename, "1");
			return "1";
		}
		else{
			id = Integer.toString(Integer.parseInt(id) + 1);
			updSQ(tablename, id);
			return id;
		}
	}
	public static String findIdPrimary(String tablename)throws Exception{
		    String sql = "select id from autoid where tablename = '" + tablename + "'";
		    AutoGenIDObj obj = dao.selectBySql(sql);
		    if(obj==null)
		    	return null;
		    else
		    	return obj.getId();	   
	}
	public static void insSQ(String tablename, String id)throws Exception{
		String sql = "insert into autoid values ('" + tablename + "', '" + id + "')";
		dao.update(sql);	   
	}
	public static void delSQ(String tablename)throws Exception{
		    String sql = "delete from autoid where tablename = '" + tablename + "'";
			dao.update(sql);
	}
	public static void updSQ(String tablename, String id) throws Exception {
		String sql = "update autoid set id = " + id + " where tablename = '"
				+ tablename + "'";
		dao.update(sql);
	}
}
