package com.wzm.server.entity.ssq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球基本统计表，包括红球和值，奇偶，奇数和，偶数和等
 * 
 */

@Entity
@Table(name = "t_ssqbasestats", catalog = "ssq")
public class SsqBaseStats extends BaseEntity implements StatsCompute {

	private static final long serialVersionUID = 1664723921750852101L;

	// 双色球开奖记录
	private SsqRecord record; 

	// 双色球开奖期号
	private int ssqIndex; 

	// 红球和值
	private int redSum; 

	// 红球和值是否奇数
	private boolean redSumIsOdd; 

	// 7球和值（包括篮球）
	private int sum; 

	// 7球和值是否奇数
	private boolean sumIsOdd; 

	// 红球奇数个数
	private int oddCount; 

	// 红球偶数个数
	private int evenCount; 

	// 红球奇数和值
	private int oddSum; 

	// 红球偶数和值
	private int evenSum; 

	// 红球小数个数
	private int smallCount; 
	
	// 红球小数和值
	private int smallSum;   
	
	// 红球大数个数
	private int bigCount; 			
	
	// 红球大数和值
	private int bigSum;  			
	
	// 红球小区（1-11）个数
	private int firstZoneCount; 	

	// 红球中区（12-22）个数
	private int secondZoneCount; 	
	
	// 红球大区（23-33）个数
	private int thirdZoneCount; 	
	
	// 红球小区（1-11）和值
	private int firstZoneSum;  
	
	// 红球中区（12-22）和值
	private int secondZoneSum;  
	
	// 红球大区（23-33）和值
	private int thirdZoneSum;  		
	
	// 质数和
	private int primeSum;   
	
	// 质数个数
	private int primeCount;   
	
	public SsqBaseStats() {

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

		int b1 = record.getB1();

		ssqIndex = record.getSsqIndex();

		redSum = r1 + r2 + r3 + r4 + r5 + r6;
		redSumIsOdd = (redSum % 2 == 1) ? true : false;

		sum = redSum + b1;
		sumIsOdd = (sum % 2 == 1) ? true : false;

		oddCount = ((r1 % 2 == 1) ? ++oddCount : oddCount);
		oddCount = ((r2 % 2 == 1) ? ++oddCount : oddCount);
		oddCount = ((r3 % 2 == 1) ? ++oddCount : oddCount);
		oddCount = ((r4 % 2 == 1) ? ++oddCount : oddCount);
		oddCount = ((r5 % 2 == 1) ? ++oddCount : oddCount);
		oddCount = ((r6 % 2 == 1) ? ++oddCount : oddCount);
		
		evenCount = 6 - oddCount;

		oddSum = ((r1 % 2 == 1) ? oddSum + r1 : oddSum);
		oddSum = ((r2 % 2 == 1) ? oddSum + r2 : oddSum);
		oddSum = ((r3 % 2 == 1) ? oddSum + r3 : oddSum);
		oddSum = ((r4 % 2 == 1) ? oddSum + r4 : oddSum);
		oddSum = ((r5 % 2 == 1) ? oddSum + r5 : oddSum);
		oddSum = ((r6 % 2 == 1) ? oddSum + r6 : oddSum);
		
		evenSum = redSum-oddSum;
		
		smallCount = (r1<=16?++smallCount:smallCount);
		smallCount = (r2<=16?++smallCount:smallCount);
		smallCount = (r3<=16?++smallCount:smallCount);
		smallCount = (r4<=16?++smallCount:smallCount);
		smallCount = (r5<=16?++smallCount:smallCount);
		smallCount = (r6<=16?++smallCount:smallCount);
		
		smallSum = ((r1 <= 16) ? smallSum + r1 : smallSum);
		smallSum = ((r2 <= 16) ? smallSum + r2 : smallSum);
		smallSum = ((r3 <= 16) ? smallSum + r3 : smallSum);
		smallSum = ((r4 <= 16) ? smallSum + r4 : smallSum);
		smallSum = ((r5 <= 16) ? smallSum + r5 : smallSum);
		smallSum = ((r6 <= 16) ? smallSum + r6 : smallSum);
		
		bigCount = 6 - smallCount;
		bigSum = redSum - smallSum;
		
		
		firstZoneCount = (r1<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r2<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r3<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r4<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r5<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r6<=11?++firstZoneCount:firstZoneCount);
		
		firstZoneSum = ((r1 <= 11) ? firstZoneSum + r1 : firstZoneSum);
		firstZoneSum = ((r2 <= 11) ? firstZoneSum + r2 : firstZoneSum);
		firstZoneSum = ((r3 <= 11) ? firstZoneSum + r3 : firstZoneSum);
		firstZoneSum = ((r4 <= 11) ? firstZoneSum + r4 : firstZoneSum);
		firstZoneSum = ((r5 <= 11) ? firstZoneSum + r5 : firstZoneSum);
		firstZoneSum = ((r6 <= 11) ? firstZoneSum + r6 : firstZoneSum);
		
		secondZoneCount = (r1<=22 && r1>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r2<=22 && r2>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r3<=22 && r3>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r4<=22 && r4>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r5<=22 && r5>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r6<=22 && r6>11?++secondZoneCount:secondZoneCount);
		
		secondZoneSum = ((r1 > 11 && r1 <= 22) ? secondZoneSum + r1 : secondZoneSum);
		secondZoneSum = ((r2 > 11 && r2 <= 22) ? secondZoneSum + r2 : secondZoneSum);
		secondZoneSum = ((r3 > 11 && r3 <= 22) ? secondZoneSum + r3 : secondZoneSum);
		secondZoneSum = ((r4 > 11 && r4 <= 22) ? secondZoneSum + r4 : secondZoneSum);
		secondZoneSum = ((r5 > 11 && r5 <= 22) ? secondZoneSum + r5 : secondZoneSum);
		secondZoneSum = ((r6 > 11 && r6 <= 22) ? secondZoneSum + r6 : secondZoneSum);
		
		thirdZoneCount = 6-firstZoneCount-secondZoneCount;
		thirdZoneSum = redSum - firstZoneSum - secondZoneSum;
		
		primeSum = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r1) ? primeSum + r1 : primeSum;
		primeSum = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r2) ? primeSum + r2 : primeSum;
		primeSum = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r3) ? primeSum + r3 : primeSum;
		primeSum = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r4) ? primeSum + r4 : primeSum;
		primeSum = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r5) ? primeSum + r5 : primeSum;
		primeSum = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r6) ? primeSum + r6 : primeSum;
		
		primeCount = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r1) ? primeCount + 1 : primeCount;
		primeCount = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r2) ? primeCount + 1 : primeCount;
		primeCount = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r3) ? primeCount + 1 : primeCount;
		primeCount = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r4) ? primeCount + 1 : primeCount;
		primeCount = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r5) ? primeCount + 1 : primeCount;
		primeCount = SsqPrimeStats.PRIME_NUM_MAP.containsKey(r6) ? primeCount + 1 : primeCount;
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

	@Column(name = "fredsum", nullable = false)
	public int getRedSum() {
		return redSum;
	}

	public void setRedSum(int redSum) {
		this.redSum = redSum;
	}

	@Column(name = "fredsumisodd", nullable = false)
	public boolean getRedSumIsOdd() {
		return redSumIsOdd;
	}

	public void setRedSumIsOdd(boolean redSumIsOdd) {
		this.redSumIsOdd = redSumIsOdd;
	}

	@Column(name = "fsum", nullable = false)
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Column(name = "fsumisodd", nullable = false)
	public boolean getSumIsOdd() {
		return sumIsOdd;
	}

	public void setSumIsOdd(boolean sumIsOdd) {
		this.sumIsOdd = sumIsOdd;
	}

	@Column(name = "foddcount", nullable = false)
	public int getOddCount() {
		return oddCount;
	}

	public void setOddCount(int oddCount) {
		this.oddCount = oddCount;
	}

	@Column(name = "fevencount", nullable = false)
	public int getEvenCount() {
		return evenCount;
	}

	public void setEvenCount(int evenCount) {
		this.evenCount = evenCount;
	}

	@Column(name = "foddsum", nullable = false)
	public int getOddSum() {
		return oddSum;
	}

	public void setOddSum(int oddSum) {
		this.oddSum = oddSum;
	}

	@Column(name = "fevensum", nullable = false)
	public int getEvenSum() {
		return evenSum;
	}

	public void setEvenSum(int evenSum) {
		this.evenSum = evenSum;
	}

	@Column(name = "fsmallcount", nullable = false)
	public int getSmallCount() {
		return smallCount;
	}

	public void setSmallCount(int smallCount) {
		this.smallCount = smallCount;
	}

	@Column(name = "fbigcount", nullable = false)
	public int getBigCount() {
		return bigCount;
	}

	public void setBigCount(int bigCount) {
		this.bigCount = bigCount;
	}

	@Column(name = "ffirstzonecount", nullable = false)
	public int getFirstZoneCount() {
		return firstZoneCount;
	}

	public void setFirstZoneCount(int firstZoneCount) {
		this.firstZoneCount = firstZoneCount;
	}

	@Column(name = "fsecondzonecount", nullable = false)
	public int getSecondZoneCount() {
		return secondZoneCount;
	}

	public void setSecondZoneCount(int secondZoneCount) {
		this.secondZoneCount = secondZoneCount;
	}

	@Column(name = "fthirdzonecount", nullable = false)
	public int getThirdZoneCount() {
		return thirdZoneCount;
	}

	public void setThirdZoneCount(int thirdZoneCount) {
		this.thirdZoneCount = thirdZoneCount;
	}

	@Column(name = "fsmallsum", nullable = false)
	public int getSmallSum() {
		return smallSum;
	}

	public void setSmallSum(int smallSum) {
		this.smallSum = smallSum;
	}

	@Column(name = "fbigsum", nullable = false)
	public int getBigSum() {
		return bigSum;
	}

	public void setBigSum(int bigSum) {
		this.bigSum = bigSum;
	}

	@Column(name = "ffirstZoneSum", nullable = false)
	public int getFirstZoneSum() {
		return firstZoneSum;
	}

	public void setFirstZoneSum(int firstZoneSum) {
		this.firstZoneSum = firstZoneSum;
	}

	@Column(name = "fsecondZoneSum", nullable = false)
	public int getSecondZoneSum() {
		return secondZoneSum;
	}

	public void setSecondZoneSum(int secondZoneSum) {
		this.secondZoneSum = secondZoneSum;
	}

	@Column(name = "fthirdZoneSum", nullable = false)
	public int getThirdZoneSum() {
		return thirdZoneSum;
	}

	public void setThirdZoneSum(int thirdZoneSum) {
		this.thirdZoneSum = thirdZoneSum;
	}

	@Column(name = "fprimeSum", nullable = false)
	public int getPrimeSum() {
		return primeSum;
	}

	public void setPrimeSum(int primeSum) {
		this.primeSum = primeSum;
	}

	@Column(name = "fprimeCount", nullable = false)
	public int getPrimeCount() {
		return primeCount;
	}

	public void setPrimeCount(int primeCount) {
		this.primeCount = primeCount;
	}

}
