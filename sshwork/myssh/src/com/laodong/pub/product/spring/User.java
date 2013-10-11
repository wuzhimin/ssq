package com.laodong.pub.product.spring;

import java.util.ArrayList;
import java.util.List;

/**@类名称
 * @业务描述
 *
 * @author lhh
 * @时间 2007-7-623:16:32
 */
public class User extends NllDObj{

    private String userid;

    private String password;

    private UserDAO dao;

    

    public User()

    {

       

    }

    

    public User(String userid, String password)

    {

       this.userid = userid;

       this.password = password;

    }

    

    public void setDao(UserDAO dao)

    {

        this.dao = dao;

    }

    
    public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getPassword() {

        return password;

    }

    

    public void setPassword(String password) {

        this.password = password;

    }

    

    public void getInfo(String userid)

    {

        List list = dao.select("where userid="+userid);

        User u = (User) list.get(0);

        

        this.userid = userid;


        this.password = u.getPassword();

        

    }

    

    public void insert()

    {

        dao.insert(this);

    }

    

    public void update(String sql)

    {

        dao.update(sql);

    }

    

    public void update()

    {

        dao.update("update userinfo set userid='"+userid+"', password='"+password+"' where userid="+userid);

    }

    

    public List selectWithTemp(String where)

    {

        return dao.select(where);

    }

    

    public void selectWithTemp()

    {

        dao.selectWithTemp();

    }

    

    public User selectById(String id)

    {

       return dao.selectByUserid(id);

    }
    public void dog()throws Exception

    {
       User u=new User();
//       u.setUserid("66y");
//       u.setPassword("dd");
//       
//       
//       String sql = "update userinfo set PASSWORD = 'sq511' where userid = 'qw5'";
//       dao.update(sql);
//       System.out.println("--------完成");
//       sql = "insert into userinfo (USERID,PASSWORD) values ('qw6','1')";
//       dao.update(sql);
//    	List list = dao.select("select * from userinfo where 1=1");
//    	System.out.println("list len: " + list.size());
//    	for(int i=0;i<list.size();i++){
//    		User us = (User)list.get(i);
//    		System.out.println("list " + i + " " + us.getUserid() + " " + us.getPassword());
//    	}
    	User u1 = dao.selectByUserid("11111111");
    	System.out.println("user obj: " + u1.getUserid());
       }
    
}
