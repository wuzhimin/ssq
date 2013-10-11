package com;
public class Tt {
    public Tt(Integer id, String name, Bookinfo bookinfo){
    	this.id = id;
    	this.name = name;
    	this.book = bookinfo;
    }
	private Integer id;
	private String name;
	private Bookinfo book;
	public Bookinfo getBook() {
		return book;
	}
	public void setBook(Bookinfo book) {
		this.book = book;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
