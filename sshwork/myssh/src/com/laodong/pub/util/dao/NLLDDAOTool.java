package com.laodong.pub.util.dao;

import com.laodong.pub.product.spring.UserDAOImp;
import com.laodong.pub.pubinf.control.InitServlet;
import com.laodong.pub.util.AutoGenID;

public class NLLDDAOTool {
	private static UserDAOImp dao = (UserDAOImp)NLLDDAOTool.getBean("userDAO");
	public static Object getBean(String name){
        return InitServlet.getBeanFactory().getBean(name);
	}
	public static void executeUpdate(String sql){
	    dao.update(sql);
	}
	/**
	 * 采用Spring框架
	 * 获得自动编号
	 * 从1开始的整数，推荐使用 
	 */
	public static int getAutoIDWithInt(String tablename)throws Exception{
		return AutoGenID.getAutoIDWithInt(tablename);
	}
	/**
	 * @方法名称 推荐
	 * @业务描述
	 *  主键从1开始
	 * @author lhh
	 * @时间 2007-7-19 20:50:48
	 */
	public static int getAutoIDring(String tablename)throws Exception{
		return AutoGenID.getAutoIDWithInt(tablename);
	}
	
	/**获得自动编号 实现了spring 主键从1开始*/
	public static int getAutoId(String tablename)throws Exception{
		return AutoGenID.getAutoIDWithInt(tablename);
	}
	
	/**获得自动编号*/
	public static int getAutoIdWithSW(String tablename)throws Exception{
		return AutoGenID.getAutoIDWithInt(tablename);
	}
	
	/**获得自动编号(字串格式)*/
	public static String getAutoIdStr(String tablename)throws Exception{
		return AutoGenID.getAutoID(tablename);
	}
	
	/**根据主键id查询*/
	public static String getfindByidsql(String tablename, String idname, String idtype, 
			String id){
		return SQLStrTool.getfindStr(tablename, idname, idtype, id);
	}
	/**根据主键id查询*/
	public static String getfindByidsql_bz(String tablename, int id){
		return SQLStrTool.getfindStr(tablename, "id", "int", "" + id);
	}
	/**查询全部记录*/
	public static String getfindAll(String tablename){
		return SQLStrTool.getfindAllStr(tablename);
	}
	/** 通用查询：根据任意个普通字段（非主键）查询 */
	public static String findtysql(String tablename, String[][] attrs){
		String sql = "select * from " + tablename + " where ";
		for(int i=0;i<attrs.length;i++){
			sql = sql + attrs[i][0] + " = ";
			sql = sql + method(attrs[i]);
			sql = sql + " and ";
		}
		sql = sql.substring(0,sql.length()-4);
		return sql;
	}
	/** 通用查询：根据任意个普通字段（非主键）查询，可加排序字串（完整字串） */
	public static String findtysqlWithSort(String tablename, String[][] attrs, String sortstr){
		String sql = findtysql(tablename, attrs);
		sql += sortstr;
		return sql;
	}
	public static void update(String tablename, String idname, String idtype, 
			String id, String[][] attrs)throws Exception{
		    if(attrs==null||attrs.length==0)
		    	return;
			String sql = updatesql(tablename, idname, idtype, id, attrs);
			executeUpdate(sql);
	}
	public static String updatestr(String tablename, String idname, String idtype, 
		String id, String[][] attrs){
		return updatesql(tablename, idname, idtype, id, attrs);
	}
	public static void updateBz(String tablename, int id, String[][] attrs)throws Exception{
			String sql = updatesql(tablename, "id", "int", "" + id, attrs);
			executeUpdate(sql);
	}
	public static String updateBzresql(String tablename, int id, String[][] attrs)throws Exception{
			return updatesql(tablename, "id", "int", "" + id, attrs);
	}
	/**
	 * @方法名称 不推荐使用
	 * @业务描述
	 *
	 * @author lhh
	 * @时间 2007-4-4 21:09:56
	 */
	public static void updateWithSW(String tablename, String idname, String idtype, 
			String id, String[][] attrs)throws Exception{
			String sql = updatesql(tablename, idname, idtype, id, attrs);
			executeUpdate(sql);
	}
	/**
	 * @param attrs1 修改字段
	 * @param attrs2 条件字段
	 */
	public static void updatety(String tablename, String[][] attrs1, String[][] attrs2)throws Exception{
			String sql = updatetysql(tablename, attrs1, attrs2);
			executeUpdate(sql);
	}
	public static void updateBySql(String sql)throws Exception{
		executeUpdate(sql);
	}
	public static void insert(String tablename, 
			String[][] attrs)throws Exception{
		String sql = insertsql(tablename, attrs);
		executeUpdate(sql);
	}
	public static String reInsertSqlStr(String tablename, String[][] attrs){
		String sql = insertsql(tablename, attrs);
		return sql;
	}
	public static void delete(String tablename, String idname, String idtype, String id)throws Exception{
		String sql = deletesql(tablename, idname, idtype, id);
		executeUpdate(sql);
	}
	public static void delete_BZ(String tablename, int id)throws Exception{
		String sql = deletesql(tablename, "id", "String", "" + id);
		executeUpdate(sql);
	}
	public static void deleteWithSW(String tablename, String idname, String idtype, String id)throws Exception{
		String sql = deletesql(tablename, idname, idtype, id);
		executeUpdate(sql);
	}
	public static void delete(String sql)throws Exception{
		executeUpdate(sql);
	}
	public static String updatesql(String tablename, String idname, String idtype, String id, String[][] attrs){
		String sql = "update " + tablename + " set ";
		for(int i=0;i<attrs.length;i++){
			sql = sql + attrs[i][0] + " = ";
			sql = sql + method(attrs[i]);
			sql = sql + ", ";
		}
		sql = sql.substring(0,sql.length()-2);
		sql = sql + " where " + idname + " = ";
		if("String".equals(idtype))
			sql = sql + "'" + id +"'";
		else
			sql = sql + id;
		return sql;
	}
	/**
	 * 通用修改：根据任意个普通字段（非主键）修改任意个普通字段（非主键）
	 * @param tablename
	 * @param idname
	 * @param idtype
	 * @param attrs1 修改字段
	 * @param attrs2 条件字段
	 * @return
	 */
	public static String updatetysql(String tablename, String[][] attrs1, String[][] attrs2){
		String sql = "update " + tablename + " set ";
		for(int i=0;i<attrs1.length;i++){
			sql = sql + attrs1[i][0] + " = ";
			sql = sql + method(attrs1[i]);
			sql = sql + ", ";
		}
		sql = sql.substring(0,sql.length()-2);
		sql = sql + " where ";
		for(int i=0;i<attrs2.length;i++){
			sql = sql + attrs2[i][0] + " = ";
			sql = sql + method(attrs2[i]);
			sql = sql + " and ";
		}
		sql = sql.substring(0,sql.length()-4);
		return sql;
	}
	public static String deletesql(String tablename, String idname, String idtype, String id){
		String sql = "delete from " + tablename + " where ";
		sql = sql + idname + " = ";
		if("String".equals(idtype))
			sql = sql + "'" + id +"'";
		else
			sql = sql + id;
		return sql;
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

	public static void main(String[] args){
		String[][] attrs1 = {
				{"deptid", "int", "1"},
				{"deptid", "String", "ew"},
				{"deptid", "Strin", "null"}};
//		String[][] attrs2 = {
//				{"deptid", "int", "1"},
//				{"deptid", "String", "ew"}};
		String ss= NLLDDAOTool.findtysql("de", attrs1);
		System.out.println(ss);
	}
}
