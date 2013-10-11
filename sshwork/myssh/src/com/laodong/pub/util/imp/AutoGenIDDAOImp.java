package com.laodong.pub.util.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowCallbackHandler;
import com.laodong.pub.product.spring.UserDAOSx;

/**@类名称
 * @业务描述
 *
 * @author lhh
 * @时间 2007-7-1423:08:22
 */
public class AutoGenIDDAOImp extends UserDAOSx{
	public AutoGenIDObj selectBySql(String sql)
	{
		final AutoGenIDObj u = new AutoGenIDObj();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				u.setNull(false);
				u.setId(rs.getString("id"));
			}
		});
		return (AutoGenIDObj)u.getObject();
	}
}
