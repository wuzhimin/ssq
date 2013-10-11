package com.ssq.util.filter;

public class FilterChain {
	private Filter filter;
	
	// 下一个过滤链
	private FilterChain nextNode;
	
	// 上一个过滤链
	private FilterChain preNode;
	
	
	public Filter getFilter() {
		return filter;
	}
	
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	public FilterChain getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(FilterChain nextChain) {
		this.nextNode = nextChain;
		if(nextChain!=null) {
			nextChain.setPreNode(this);
		}
	}
	
	public FilterChain(Filter filter, FilterChain nextChain) {
		this.filter = filter;
		this.nextNode = nextChain;
	}
	
	public FilterChain(Filter filter) {
		this.filter = filter;
	}
	
	public FilterChain() {
		
	}

	public boolean doFilter(String str) {
		if(filter==null) {
			if(nextNode == null) {
				return false;
			}
			return nextNode.doFilter(str);
		} else if(filter.doFilter(str)) {
			return true;
		} else {
			if(nextNode == null) {
				return false;
			}
			return nextNode.doFilter(str);
		}
	}
	
	/**
	 * 过滤，如果为true，打印filter
	 * @param str
	 * @param ifTruePrint
	 * @return
	 */
	public boolean doFilter(String str, boolean ifTruePrint) {
		if(filter==null) {
			if(nextNode == null) {
				return false;
			}
			return nextNode.doFilter(str, ifTruePrint);
		} else if(filter.doFilter(str)) {
			if(ifTruePrint) {
				System.out.println(filter);
			}
			return true;
		} else {
			if(nextNode == null) {
				return false;
			}
			return nextNode.doFilter(str, ifTruePrint);
		}
	}

	public FilterChain getPreNode() {
		return preNode;
	}

	private void setPreNode(FilterChain preChain) {
		this.preNode = preChain;
	}
}
