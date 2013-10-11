package com.lgy.dao.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lgy.entiy.Bid;
import com.lgy.entiy.Users;
import com.lgy.server.BidServer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BidAction extends ActionSupport {
	private Bid bid;
	private BidServer bidServer;

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public BidServer getBidServer() {
		return bidServer;
	}

	public void setBidServer(BidServer bidServer) {
		this.bidServer = bidServer;
	}

   	
	

	public String addBid() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   	String time = df.format(new Date());
		Users users = (Users) ActionContext.getContext().getSession().get("users");
		bid.setBcreate_time(time);
		bid.setUsers(users);
		bidServer.addBid(bid);
		return SUCCESS;
	}

}
