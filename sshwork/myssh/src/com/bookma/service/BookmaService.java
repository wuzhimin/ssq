package com.bookma.service;

import java.util.List;

import com.Bookinfo;
import com.BookinfoDAOImp;
import com.DefaultService;
import com.DefaultServiceImpl;
import com.NlldService;
import com.bookma.struts.dao.BookmaDAO;

public class BookmaService extends DefaultService{
    private BookinfoDAOImp bookinfoDAO;
    private BookmaDAO bookmaDAO;
	
	public BookmaDAO getBookmaDAO() {
		return bookmaDAO;
	}


	public void setBookmaDAO(BookmaDAO bookmaDAO) {
		this.bookmaDAO = bookmaDAO;
	}


	public BookinfoDAOImp getBookinfoDAO() {
		return bookinfoDAO;
	}


	public void setBookinfoDAO(BookinfoDAOImp bookinfoDAO) {
		this.bookinfoDAO = bookinfoDAO;
	}
    /**
     * hibernate≤‚ ‘∑Ω∑®
     * @param obj
     */
	public void hibernateTest(Bookinfo obj){
		bookinfoDAO.isValidUser("‘⁄", "d");
	}
	public void taxInsBookinfoObj(Bookinfo obj)throws Exception{
		bookmaDAO.insBookinfoObj(obj);
	}
	public List findAllBookinfoObjList()throws Exception{
		return bookmaDAO.findAllBookinfoObjList();
	}
}
