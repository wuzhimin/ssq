package utils.cache;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest extends Thread {
	private List<Integer> aa;
	private int sum;

	int from;
	int to;
	
	private boolean isEnd = false;
	
	public void run() {
		for(int i=from;i<aa.size() && i<=to;i++) {
			sum = sum + aa.get(i);
		}
		setEnd(true);
	}
	
	public ThreadTest(List<Integer> aa, int from, int to) {
		super();
		this.aa = aa;
		this.from = from;
		this.to = to;
	}
	
	public List<Integer> getAa() {
		return aa;
	}

	public void setAa(List<Integer> aa) {
		this.aa = aa;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	
	public static void main(String[] args) {
		List<Integer> aa = new ArrayList<Integer>();
		for(int i=1;i<=100;i++) {
			aa.add(i);
		}
		
		ThreadTest t1 = new ThreadTest(aa, 0, 30);
		ThreadTest t2 = new ThreadTest(aa, 31, 70);
		ThreadTest t3 = new ThreadTest(aa, 71, 100);
		t1.start();
		t2.start();
		t3.start();
		
		while(true) {
			if(t1.isEnd() && t2.isEnd() && t3.isEnd()) {
				System.out.println(t1.getSum()+t2.getSum()+t3.getSum());
				break;
			}
		}
		
//		try {
//			t1.join();
//			t2.join();
//			t3.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

}
