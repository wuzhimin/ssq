package com.lgy.server;

import java.util.List;

import com.lgy.dao.impl.BidImpl;
import com.lgy.entiy.Bid;

public class BidServer {
	private BidImpl bidImpl;

	public BidImpl getBidImpl() {
		return bidImpl;
	}

	public void setBidImpl(BidImpl bidImpl) {
		this.bidImpl = bidImpl;
	}
	public void addBid(Bid bid) {
		bidImpl.addBid(bid);
	}
	//public List<Bid> 

}
