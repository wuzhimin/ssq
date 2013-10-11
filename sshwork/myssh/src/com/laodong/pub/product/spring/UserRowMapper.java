package com.laodong.pub.product.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @类名称
 * @业务描述
 * 
 * @author lhh
 * @时间 2007-7-623:17:57
 */
public class UserRowMapper extends JdbcDaoSupport implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException
	{
		User u = new User();
		u.setUserid(rs.getString("USERID"));
		u.setPassword(rs.getString("Password"));
		return u;
	}

	public User selectByUserid(String userid) {
		String sql = "select * from userinfo where userid=?";
		final User u = new User();
		final Object[] params = new Object[] { userid };
		this.getJdbcTemplate().query(sql, params, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				u.setUserid(rs.getString("USERID"));
				u.setPassword(rs.getString("PASSWORD"));
			}
		});
		return u;
	}
}
