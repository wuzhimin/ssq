package com;

import java.util.List;

public interface BookinfoDAO {
//    private static String hql = "from Bookinfo u where u.name=? ";
	

    public int getMoney();
	public void setMoney(int money);
	public String getName();
	public void setName(String name);
	public abstract void isValidUser(String username, String password);
}
