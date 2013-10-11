package com.laodong.pub.product.spring;
/**@类名称
 * @业务描述
 *
 * @author lhh
 * @时间 2007-7-70:46:51
 */
public class UserService {
	public User user;
    public void myt(){
    	user.selectWithTemp();
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void taxd12()throws Exception{
		user.dog();
	}
	public void ptd12()throws Exception{
		user.dog();
	}
}
