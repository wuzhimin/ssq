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

	private SsqRecord record; // 双色球开奖记录

	private int ssqIndex; // 双色球开奖期号

	private int redSum; // 红球和值

	private boolean redSumIsOdd; // 红球和值是否奇数

	private int sum; // 7球和值（包括篮球）

	private boolean sumIsOdd; // 7球和值是否奇数

	private int oddCount; // 红球奇数个数

	private int evenCount; // 红球偶数个数

	private int oddSum; // 红球奇数和值

	private int evenSum; // 红球偶数和值

	private int smallCount; // 红球小数个数

	private int bigCount; // 红球大数个数

	private int firstZoneCount; // 红球小区（1-11）个数

	private int secondZoneCount; // 红球中区（12-22）个数
	
	private int thirdZoneCount; // 红球大区（23-33）个数

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
		
		bigCount = 6-smallCount;
		
		
		firstZoneCount = (r1<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r2<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r3<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r4<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r5<=11?++firstZoneCount:firstZoneCount);
		firstZoneCount = (r6<=11?++firstZoneCount:firstZoneCount);
		
		secondZoneCount = (r1<=22 && r1>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r2<=22 && r2>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r3<=22 && r3>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r4<=22 && r4>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r5<=22 && r5>11?++secondZoneCount:secondZoneCount);
		secondZoneCount = (r6<=22 && r6>11?++secondZoneCount:secondZoneCount);
		
		thirdZoneCount = 6-firstZoneCount-secondZoneCount;

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

}
