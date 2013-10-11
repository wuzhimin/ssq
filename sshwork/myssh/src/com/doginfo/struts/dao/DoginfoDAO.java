package com.doginfo.struts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultReader;
import com.doginfo.struts.obj.DoginfoObj;
import com.laodong.pub.product.spring.UserDAOSx;
import com.laodong.pub.util.dao.NLLDDAOTool;

public class DoginfoDAO extends UserDAOSx{
	class DoginfoObjRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			DoginfoObj obj = new DoginfoObj();
			obj.setId(rs.getInt("id"));
			obj.setName(rs.getString("name"));
			obj.setMaster(rs.getString("master"));
			obj.setAge(rs.getInt("age"));
			obj.setMs(rs.getString("ms"));
			return obj;
		}
	}
	public List findDoginfoObjListBySql(String sql)throws Exception{
		List list = jdbcTemplate.query(sql, new RowMapperResultReader(
				new DoginfoObjRowMapper()));
		return list;
	}
	public List findAllDoginfoObjList()throws Exception{
		String sql = "select * from doginfo";
		return findDoginfoObjListBySql(sql);
	}
	public List findAllDoginfoObjByCon(String wheresql)throws Exception{
		String sql = "select * from doginfo where 1=1 " + wheresql;
		return findDoginfoObjListBySql(sql);
	}
	public DoginfoObj insDoginfoObj(DoginfoObj obj)throws Exception{
    	int id = NLLDDAOTool.getAutoIDWithInt("doginfo");
		String[][] attrs = { 
				{"id","int",""+id},
				{"name","String",obj.getName()},
				{"master","String",obj.getMaster()},
				{"age","int",""+obj.getAge()},
				{"ms","String",obj.getMs()}
			 };
		NLLDDAOTool.insert("doginfo", attrs);
		obj.setId(id);
    	return obj;
    }
	public void delDoginfoObj(int id){
		String sql = "delete from doginfo where id = " + id;
		update(sql);
	}
	public void updDoginfoObj(int id, String[][] attrs)throws Exception{
    	NLLDDAOTool.update("doginfo", "id", "int", "" + id, attrs);
    }
	public DoginfoObj findDoginfoObjById(int id)throws Exception{
		String sql = NLLDDAOTool.getfindByidsql("doginfo", "id", "int", "" + id);
		final DoginfoObj obj = new DoginfoObj();
		   jdbcTemplate.query(sql, new RowCallbackHandler() {
		   	public void processRow(ResultSet rs) throws SQLException {
		   		obj.setNull(false);
		   		obj.setId(rs.getInt("id"));
		   		obj.setName(rs.getString("name"));
		   		obj.setMaster(rs.getString("master"));
		   		obj.setAge(rs.getInt("age"));
		   		obj.setMs(rs.getString("ms"));
		   	}
		   });
		   return (DoginfoObj)obj.getObject();
	}
}
