package com.laodong.pub.product.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultReader;

/**
 * @类名称
 * @业务描述
 * 
 * @author lhh
 * @时间 2007-7-623:35:07
 */
public class UserDAOImp extends UserDAOSx implements UserDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	class UserRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int index) throws SQLException
		{
			User u = new User();
			u.setUserid(rs.getString("userid"));
			u.setPassword(rs.getString("Password"));
			return u;
		}
	}

	public void selectWithTemp()

	{
		String sql = "select * from userinfo";
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println("userid: " + rs.getString("userid")
						+ "   password: " + rs.getString("password"));
			}
		});
	}

	public List select(String where)
	{
		List list;
		String sql = where;
		list = jdbcTemplate.query(sql, new RowMapperResultReader(
				new UserRowMapper()));
		return list;
	}

	public User selectByUserid(String userid)
	{
		String sql = "select * from userinfo order by userid";
		final User u = new User();
		final Object[] params = new Object[] { userid };
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				u.setNull(false);
				u.setUserid(rs.getString("userid"));
				u.setPassword(rs.getString("PASSWORD"));
			}
		});
		
		return (User)u.getObject();
	}

	public void update(String how)
	{
		String sql = how;
		jdbcTemplate.update(sql);
	}

	public void insert(User u)
	{
		String sql = "insert into userinfo (USERID,PASSWORD) values (?,?)";
		Object[] params = new Object[] { u.getUserid(), u.getPassword() };
		jdbcTemplate.update(sql, params);
	}
	public Object selectByPk(String id){
		return null;
	}
	public void test() {
	}
}
