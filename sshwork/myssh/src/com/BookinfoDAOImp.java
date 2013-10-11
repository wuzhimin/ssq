package com;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.jdbc.Expectation;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BookinfoDAOImp extends HibernateDaoSupport implements BookinfoDAO {
    private String name;
    private int money;
//    private static String hql = "from Bookinfo u where u.name=? ";
	

    public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void isValidUser(String username, String password) {
/**实体查询*/
    	//    	String hql = "from Bookinfo u where u.name= '" + username + "'";
//       List list = this.getHibernateTemplate().find(hql, username);
    	String hql = null;
//    	String hql = "from Bookinfo";
    	List list = null;
//    	List list = this.getHibernateTemplate().find(hql);
//    	
//    	System.out.println("list.size(): " + list.size());
//		for(int i=0;i<list.size();i++){
//			Bookinfo obj = (Bookinfo)list.get(i);
//			System.out.println("id: " + obj.getId().intValue() + "   name: " + obj.getName());
//		}
    	Session session = this.getSession();
		/**属性查询 类的包名可加可不加，因在配置文件里有（不能用表名）*/
//        hql = "select t.name from Bookinfo t";
//        Query query = session.createQuery(hql);
//        list = query.list();
//        for(int i=0;i<list.size();i++){
//        	System.out.println(list.get(i));
//		}
    	
//        hql = "select t.id, t.name from Bookinfo t";
//        list = this.getHibernateTemplate().find(hql);
//        for(int i=0;i<list.size();i++){
//        	Object[] objs = (Object[])list.get(i);
//        	System.out.print(objs[0] + "  ");
//        	System.out.println(objs[1]);
//		}
    /**动态构造实例对象*/	
//    	hql = "select new com.Tt(t.id, t.name, t) from Bookinfo t";
//        list = this.getHibernateTemplate().find(hql);
//        for(int i=0;i<list.size();i++){
//        	Tt obj = (Tt)list.get(i);
//        	System.out.print(obj.getId() + "  ");
//        	System.out.println(obj.getName() + " obj.name");
//        	System.out.println(obj.getBook().getName());
//		}
    	
//        hql = "select new com.Tt(t.id, t.name, t) from Bookinfo t where t.id = ? and name = ?";
//        Session session = this.getSession();
//        Query query = session.createQuery(hql);
//        query.setInteger(0, 3);
//        query.setString(1, "人");
//        list = query.list();
//        for(int i=0;i<list.size();i++){
//        	Tt obj = (Tt)list.get(i);
//        	System.out.print(obj.getId() + "  ");
//        	System.out.println(obj.getName() + " obj.name");
//        	System.out.println(obj.getBook().getName());
//		}
    	/**按参数位置邦定*/
//    	hql = "select new com.Tt(t.id, t.name, t) from Bookinfo t where t.id = ? and name = ?";
//        Session session = this.getSession();
//        Query query = session.createQuery(hql);
//        query.setInteger(0, 3);
//        query.setString(1, "人");
//        list = query.list();
//        for(int i=0;i<list.size();i++){
//        	Tt obj = (Tt)list.get(i);
//        	System.out.print(obj.getId() + "  ");
//        	System.out.println(obj.getName() + " obj.name");
//        	System.out.println(obj.getBook().getName());
//		}
    	/**按参数名称绑定*/
//        hql = "select new com.Tt(t.id, t.name, t) from Bookinfo t where t.id = :id and name = :name";
        
//        Query query = session.createQuery(hql);
//        query.setInteger("id", 3);
//        query.setString("name", "人们");
//        list = query.list();
//        for(int i=0;i<list.size();i++){
//        	Tt obj = (Tt)list.get(i);
//        	System.out.print(obj.getId() + "  ");
//        	System.out.print(obj.getName() + " obj.name: ");
//        	System.out.println(obj.getBook().getName());
//		}
        /**Criteria query 面向对象标准查询*/
//        Criteria criteria = session.createCriteria(Bookinfo.class);
//        criteria.add(Expression.eq("id", new Integer(2)));
//        list = criteria.list();
//        for(int i=0;i<list.size();i++){
//			Bookinfo obj = (Bookinfo)list.get(i);
//			System.out.println("id: " + obj.getId().intValue() + "   name: " + obj.getName());
//		}
        /**原生sql查询 要用表名（既为原生了，故用表名）*/
//        SQLQuery sQLQuery = session.createSQLQuery("select {g.*} from bookinfo {g}");
//        sQLQuery.addEntity("g", Bookinfo.class);
//        list = sQLQuery.list();
//        for(int i=0;i<list.size();i++){
//			Bookinfo obj = (Bookinfo)list.get(i);
//			System.out.println("id: " + obj.getId().intValue() + "   name: " + obj.getName());
//		}
    	session.close();
    }

}

