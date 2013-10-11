package com.bookma.struts.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Bookinfo;
import com.laodong.pub.product.spring.UserDAOSx;
import com.laodong.pub.util.dao.NLLDDAOTool;

public class BookmaDAO extends UserDAOSx{
	public List findBookinfoObjListBySql(String sql)throws Exception{
		List list = this.getHibernateTemplate().find(sql);
		return list;
	}
	public List findAllBookinfoObjList()throws Exception{
		String sql = "from Bookinfo";
		return findBookinfoObjListBySql(sql);
	}
	public Bookinfo insBookinfoObj(Bookinfo obj)throws Exception{
    	int id = NLLDDAOTool.getAutoIDWithInt("bookinfo");
    	Session session = this.getSession();
    	Transaction trans = session.beginTransaction();

		obj.setId(new Integer(id));
		session.save(obj);
//		this.getSession().flush();
		trans.commit();
		this.getSession().close();
		
    	return obj;
    }
	public void delBookinfoObj(int id){
		String sql = "delete from bookinfo where id = " + id;
		update(sql);
	}
	public void updBookinfoObj(Bookinfo obj)throws Exception{
    	this.getSession().update(obj);
    }
	public Bookinfo findBookinfoObjById(int id)throws Exception{
		Bookinfo obj = (Bookinfo)this.getSession().load(Bookinfo.class, new Integer(id));
		return obj;
	}
}
