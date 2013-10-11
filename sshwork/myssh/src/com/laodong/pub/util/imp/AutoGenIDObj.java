package com.laodong.pub.util.imp;

import com.laodong.pub.product.spring.NllDObj;

/**@类名称
 * @业务描述
 *
 * @author lhh
 * @时间 2007-7-1423:15:42
 */
public class AutoGenIDObj extends NllDObj{
    private String tablename;
    private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
    
}
