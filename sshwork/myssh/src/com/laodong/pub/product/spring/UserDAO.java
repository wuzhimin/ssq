package com.laodong.pub.product.spring;

import java.util.List;

/**@类名称
 * @业务描述
 *
 * @author lhh
 * @时间 2007-7-623:23:07
 */
public interface UserDAO {


    public void test();

    public void selectWithTemp();

    public List select(String where);

    public void update(String how);

    public void insert(User u);

    public User selectByUserid(String userid);

    public Object selectByPk(String id);
}
