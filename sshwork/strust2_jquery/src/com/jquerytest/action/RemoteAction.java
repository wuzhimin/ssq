package com.jquerytest.action;

import java.util.ArrayList;
import java.util.List;

import com.jquerytest.entity.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class RemoteAction extends ActionSupport {
	
	public static String forword_str="content";
	
	public static String forword_table="table";
	/**
	 * 
	 */
	private static final long serialVersionUID = -6763513660067913568L;
	
	
	private String myText;

	private List<Customer> customers;
	
	public String getSports() {
		myText = "体育世界";
		return forword_str;
	}
	
	public String getCar() {
		myText = "汽车风云";
		return forword_str;
	}
	
	public String getNews() {
		myText = "新闻联播";
		return forword_str;
	}
	
	public String getMyText() {
		return myText;
	}

	public void setMyText(String myText) {
		this.myText = myText;
	}
	
	public String getTableJson() { 
		customers = new ArrayList<Customer>();
		for(int i=0;i<223;i++) {
			Customer c = new Customer();
			c.setId(i);
			c.setName("name"+i);
			c.setCity("city"+i);
			c.setCountry("country"+i);
			customers.add(c);
		}
		return forword_table;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
