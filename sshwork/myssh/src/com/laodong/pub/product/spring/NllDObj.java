package com.laodong.pub.product.spring;
/**
 * @类名称 spring根类
 * @业务描述
 *
 * @author lhh
 * @时间 2007-7-1323:56:15
 */
public class NllDObj {
	/**
	 * 表示null对象
	 */
	private boolean isNull = true;

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public NllDObj getObject(){
		if(isNull)
			return null;
		else
			return this;
	}
}
