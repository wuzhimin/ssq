package com.laodong.pub.util.dao;

public class SQLStrTool {
    public static String getfindStr(String tablename, String idname, String idtype, String id){
    	String sql = "select * from " + tablename + " where " + idname + " = ";
    	if("String".equals(idtype))
    		sql = sql + "'" + id + "'";
    	else
    		sql = sql + id;
    	return sql;
    }
    public static String getfindAllStr(String tablename){
    	String sql = "select * from " + tablename;
    	return sql;
    }
}
