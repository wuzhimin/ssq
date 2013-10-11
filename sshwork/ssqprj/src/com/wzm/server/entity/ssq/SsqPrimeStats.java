package com.wzm.server.entity.ssq;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球红球质数统计表
 * 
 */

@Entity
@Table(name = "t_ssqprimestats", catalog = "ssq")
public class SsqPrimeStats extends BaseEntity implements StatsCompute {

	private static final long serialVersionUID = 1687123111750852101L;

	private SsqRecord record; // 双色球开奖记录

	private int ssqIndex; // 双色球开奖期号
	
	private boolean hasPrime2;   // 是否有质数2
	private boolean hasPrime3;   // 是否有质数3
	private boolean hasPrime5;   // 是否有质数5
	private boolean hasPrime7;   // 是否有质数7
	private boolean hasPrime11;   // 是否有质数11
	private boolean hasPrime13;   // 是否有质数13
	private boolean hasPrime17;   // 是否有质数17
	private boolean hasPrime19;   // 是否有质数19
	private boolean hasPrime23;   // 是否有质数23
	private boolean hasPrime29;   // 是否有质数29
	private boolean hasPrime31;   // 是否有质数31
	
	private int primeCount;   // 质数个数
	private int primeSum;   // 质数和
	
	public static Map<Integer, String> PRIME_NUM_MAP = new HashMap<Integer, String>();
	
	static {
		PRIME_NUM_MAP.put(2, null);
		PRIME_NUM_MAP.put(3, null);
		PRIME_NUM_MAP.put(5, null);
		PRIME_NUM_MAP.put(7, null);
		PRIME_NUM_MAP.put(11, null);
		PRIME_NUM_MAP.put(13, null);
		PRIME_NUM_MAP.put(17, null);
		PRIME_NUM_MAP.put(19, null);
		PRIME_NUM_MAP.put(23, null);
		PRIME_NUM_MAP.put(29, null);
		PRIME_NUM_MAP.put(31, null);
	}
	
	public SsqPrimeStats() {
		
	}

	/*
	 * 计算统计值
	 */
	public void buildStats(SsqRecord aRecord) {

		setRecord(aRecord);
		
		int r1 = record.getR1();

		int r2 = record.getR2();

		int r3 = record.getR3();

		int r4 = record.getR4();

		int r5 = record.getR5();

		int r6 = record.getR6();

		ssqIndex = record.getSsqIndex();
		
		Map<Integer, String> tmp = new HashMap<Integer, String>();
		tmp.put(r1, null);
		tmp.put(r2, null);
		tmp.put(r3, null);
		tmp.put(r4, null);
		tmp.put(r5, null);
		tmp.put(r6, null);
		
		hasPrime2 = tmp.containsKey(2)?true:false;
		if(hasPrime2) {
			primeCount++;
			primeSum = primeSum+2;
		}
		
		hasPrime3 = tmp.containsKey(3)?true:false;
		if(hasPrime3) {
			primeCount++;
			primeSum = primeSum+3;
		}
		
		hasPrime5 = tmp.containsKey(5)?true:false;
		if(hasPrime5) {
			primeCount++;
			primeSum = primeSum+5;
		}
		
		hasPrime7 = tmp.containsKey(7)?true:false;
		if(hasPrime7) {
			primeCount++;
			primeSum = primeSum+7;
		}
		
		hasPrime11 = tmp.containsKey(11)?true:false;
		if(hasPrime11) {
			primeCount++;
			primeSum = primeSum+11;
		}
		
		hasPrime13 = tmp.containsKey(13)?true:false;
		if(hasPrime13) {
			primeCount++;
			primeSum = primeSum+13;
		}
		
		hasPrime17= tmp.containsKey(17)?true:false;
		if(hasPrime17) {
			primeCount++;
			primeSum = primeSum+17;
		}
		
		hasPrime19 = tmp.containsKey(19)?true:false;
		if(hasPrime19) {
			primeCount++;
			primeSum = primeSum+19;
		}
		
		hasPrime23 = tmp.containsKey(23)?true:false;
		if(hasPrime23) {
			primeCount++;
			primeSum = primeSum+23;
		}
		
		hasPrime29 = tmp.containsKey(29)?true:false;
		if(hasPrime29) {
			primeCount++;
			primeSum = primeSum+29;
		}
		
		hasPrime31 = tmp.containsKey(31)?true:false;
		if(hasPrime31) {
			primeCount++;
			primeSum = primeSum+31;
		}
	}

	@Column(name = "fssqindex", unique = true, nullable = false)
	public int getSsqIndex() {
		return ssqIndex;
	}

	public void setSsqIndex(int ssqIndex) {
		this.ssqIndex = ssqIndex;
	}

	@Transient
	public SsqRecord getRecord() {
		return record;
	}

	public void setRecord(SsqRecord record) {
		this.record = record;
	}

	@Column(name = "fhasprime2", nullable = false)
	public boolean isHasPrime2() {
		return hasPrime2;
	}

	public void setHasPrime2(boolean hasPrime2) {
		this.hasPrime2 = hasPrime2;
	}

	@Column(name = "fhasprime3", nullable = false)
	public boolean isHasPrime3() {
		return hasPrime3;
	}

	public void setHasPrime3(boolean hasPrime3) {
		this.hasPrime3 = hasPrime3;
	}

	@Column(name = "fhasprime5", nullable = false)
	public boolean isHasPrime5() {
		return hasPrime5;
	}

	public void setHasPrime5(boolean hasPrime5) {
		this.hasPrime5 = hasPrime5;
	}

	@Column(name = "fhasprime7", nullable = false)
	public boolean isHasPrime7() {
		return hasPrime7;
	}

	public void setHasPrime7(boolean hasPrime7) {
		this.hasPrime7 = hasPrime7;
	}

	@Column(name = "fhasprime11", nullable = false)
	public boolean isHasPrime11() {
		return hasPrime11;
	}

	public void setHasPrime11(boolean hasPrime11) {
		this.hasPrime11 = hasPrime11;
	}

	@Column(name = "fhasprime13", nullable = false)
	public boolean isHasPrime13() {
		return hasPrime13;
	}

	public void setHasPrime13(boolean hasPrime13) {
		this.hasPrime13 = hasPrime13;
	}

	@Column(name = "fhasprime17", nullable = false)
	public boolean isHasPrime17() {
		return hasPrime17;
	}

	public void setHasPrime17(boolean hasPrime17) {
		this.hasPrime17 = hasPrime17;
	}

	@Column(name = "fhasprime19", nullable = false)
	public boolean isHasPrime19() {
		return hasPrime19;
	}

	public void setHasPrime19(boolean hasPrime19) {
		this.hasPrime19 = hasPrime19;
	}

	@Column(name = "fhasprime23", nullable = false)
	public boolean isHasPrime23() {
		return hasPrime23;
	}

	public void setHasPrime23(boolean hasPrime23) {
		this.hasPrime23 = hasPrime23;
	}

	@Column(name = "fhasprime29", nullable = false)
	public boolean isHasPrime29() {
		return hasPrime29;
	}

	public void setHasPrime29(boolean hasPrime29) {
		this.hasPrime29 = hasPrime29;
	}

	@Column(name = "fhasprime31", nullable = false)
	public boolean isHasPrime31() {
		return hasPrime31;
	}

	public void setHasPrime31(boolean hasPrime31) {
		this.hasPrime31 = hasPrime31;
	}

	@Column(name = "fprimecount", nullable = false)
	public int getPrimeCount() {
		return primeCount;
	}

	public void setPrimeCount(int primeCount) {
		this.primeCount = primeCount;
	}

	@Column(name = "fprimesum", nullable = false)
	public int getPrimeSum() {
		return primeSum;
	}

	public void setPrimeSum(int primeSum) {
		this.primeSum = primeSum;
	}

	public static void main(String[] args) {
		if(PRIME_NUM_MAP.containsKey(2)) {
			System.out.println("ok");
		}
	}
	
}
