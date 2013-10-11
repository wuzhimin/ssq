package com.wzm.server.entity.ssq;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球多期蓝球统计表
 * 
 */

@Entity
@Table(name = "t_ssqmulbluestats", catalog = "ssq")
public class SsqMulBlueStats extends BaseEntity implements MulStatsCompute {

	private static final long serialVersionUID = 1874123111711852101L;

	private List<StatsCompute> records; // 双色球红球质数统计记录

	private int fromSsqIndex; // 开始双色球开奖期号
	private int toSsqIndex; // 结束双色球开奖期号

	private int spaceNum; // 间隔期数

	private int b1count; // 篮球1个数
	private int b2count; // 篮球2个数
	private int b3count; // 篮球3个数
	private int b4count; // 篮球4个数
	private int b5count; // 篮球5个数
	private int b6count; // 篮球6个数
	private int b7count; // 篮球7个数
	private int b8count; // 篮球8个数
	private int b9count; // 篮球9个数
	private int b10count; // 篮球10个数
	private int b11count; // 篮球11个数
	private int b12count; // 篮球12个数
	private int b13count; // 篮球13个数
	private int b14count; // 篮球14个数
	private int b15count; // 篮球15个数
	private int b16count; // 篮球16个数

	private int blueSum; // 篮球和

	private int oddCount; // 奇数个数
	private int evenCount; // 偶数个数
	private int primeCount; // 质数个数
	private int bigCount; // 大数个数
	private int smallCount; // 小数个数

	public SsqMulBlueStats() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(List<StatsCompute> records) {
		if (!(records.get(0) instanceof SsqBlueStats)) {
			return;
		}

		int size = records.size();

		setFromSsqIndex(((SsqBlueStats) records.get(0)).getSsqIndex());

		setToSsqIndex(((SsqBlueStats) records.get(size - 1)).getSsqIndex());

		setSpaceNum(size);

		for (int j = 0; j < size; j++) {
			setB1count(getB1count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 1 ? 1 : 0));

			setB2count(getB2count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 2 ? 1 : 0));
			setB3count(getB3count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 3 ? 1 : 0));
			setB4count(getB4count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 4 ? 1 : 0));
			setB5count(getB5count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 5 ? 1 : 0));
			setB6count(getB6count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 6 ? 1 : 0));
			setB7count(getB7count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 7 ? 1 : 0));
			setB8count(getB8count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 8 ? 1 : 0));
			setB9count(getB9count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 9 ? 1 : 0));
			setB10count(getB10count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 10 ? 1 : 0));
			setB11count(getB11count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 11 ? 1 : 0));
			setB12count(getB12count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 12 ? 1 : 0));
			setB13count(getB13count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 13 ? 1 : 0));
			setB14count(getB14count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 14 ? 1 : 0));
			setB15count(getB15count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 15 ? 1 : 0));
			setB16count(getB16count()
					+ (((SsqBlueStats) records.get(j)).getB1() == 16 ? 1 : 0));

			blueSum = 1 * b1count + 2 * b2count + 3 * b3count + 4 * b4count + 5
					* b5count + 6 * b6count + 7 * b7count + 8 * b8count + 9
					* b9count + 10 * b10count + 11 * b11count + 12 * b12count
					+ 13 * b13count + 14 * b14count + 15 * b15count + 16
					* b16count;

			oddCount = b1count + b3count + b5count + b7count + b9count
					+ b11count + b13count + b15count;
			evenCount = spaceNum - oddCount;

			smallCount = b1count + b3count + b5count + b7count + b2count
					+ b4count + b6count + b8count;
			bigCount = spaceNum - smallCount;

			primeCount = b2count + b3count + b5count + b7count + b11count
					+ b13count;

		}

	}

	@Transient
	public List<StatsCompute> getRecords() {
		return records;
	}

	public void setRecords(List<StatsCompute> records) {
		this.records = records;
	}

	@Column(name = "ffromssqindex", nullable = false)
	public int getFromSsqIndex() {
		return fromSsqIndex;
	}

	public void setFromSsqIndex(int fromSsqIndex) {
		this.fromSsqIndex = fromSsqIndex;
	}

	@Column(name = "ftossqindex", nullable = false)
	public int getToSsqIndex() {
		return toSsqIndex;
	}

	public void setToSsqIndex(int toSsqIndex) {
		this.toSsqIndex = toSsqIndex;
	}

	@Column(name = "fspacenum", nullable = false)
	public int getSpaceNum() {
		return spaceNum;
	}

	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}

	@Column(name = "fb1count", nullable = false)
	public int getB1count() {
		return b1count;
	}

	public void setB1count(int b1count) {
		this.b1count = b1count;
	}

	@Column(name = "fb2count", nullable = false)
	public int getB2count() {
		return b2count;
	}

	public void setB2count(int b2count) {
		this.b2count = b2count;
	}

	@Column(name = "fb3count", nullable = false)
	public int getB3count() {
		return b3count;
	}

	public void setB3count(int b3count) {
		this.b3count = b3count;
	}

	@Column(name = "fb4count", nullable = false)
	public int getB4count() {
		return b4count;
	}

	public void setB4count(int b4count) {
		this.b4count = b4count;
	}

	@Column(name = "fb5count", nullable = false)
	public int getB5count() {
		return b5count;
	}

	public void setB5count(int b5count) {
		this.b5count = b5count;
	}

	@Column(name = "fb6count", nullable = false)
	public int getB6count() {
		return b6count;
	}

	public void setB6count(int b6count) {
		this.b6count = b6count;
	}

	@Column(name = "fb7count", nullable = false)
	public int getB7count() {
		return b7count;
	}

	public void setB7count(int b7count) {
		this.b7count = b7count;
	}

	@Column(name = "fb8count", nullable = false)
	public int getB8count() {
		return b8count;
	}

	public void setB8count(int b8count) {
		this.b8count = b8count;
	}

	@Column(name = "fb9count", nullable = false)
	public int getB9count() {
		return b9count;
	}

	public void setB9count(int b9count) {
		this.b9count = b9count;
	}

	@Column(name = "fb10count", nullable = false)
	public int getB10count() {
		return b10count;
	}

	public void setB10count(int b10count) {
		this.b10count = b10count;
	}

	@Column(name = "fb11count", nullable = false)
	public int getB11count() {
		return b11count;
	}

	public void setB11count(int b11count) {
		this.b11count = b11count;
	}

	@Column(name = "fb12count", nullable = false)
	public int getB12count() {
		return b12count;
	}

	public void setB12count(int b12count) {
		this.b12count = b12count;
	}

	@Column(name = "fb13count", nullable = false)
	public int getB13count() {
		return b13count;
	}

	public void setB13count(int b13count) {
		this.b13count = b13count;
	}

	@Column(name = "fb14count", nullable = false)
	public int getB14count() {
		return b14count;
	}

	public void setB14count(int b14count) {
		this.b14count = b14count;
	}

	@Column(name = "fb15count", nullable = false)
	public int getB15count() {
		return b15count;
	}

	public void setB15count(int b15count) {
		this.b15count = b15count;
	}

	@Column(name = "fb16count", nullable = false)
	public int getB16count() {
		return b16count;
	}

	public void setB16count(int b16count) {
		this.b16count = b16count;
	}

	@Column(name = "fbsum", nullable = false)
	public int getBlueSum() {
		return blueSum;
	}

	public void setBlueSum(int bSum) {
		this.blueSum = bSum;
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

	@Column(name = "fprimecount", nullable = false)
	public int getPrimeCount() {
		return primeCount;
	}

	public void setPrimeCount(int primeCount) {
		this.primeCount = primeCount;
	}

	@Column(name = "fbigcount", nullable = false)
	public int getBigCount() {
		return bigCount;
	}

	public void setBigCount(int bigCount) {
		this.bigCount = bigCount;
	}

	@Column(name = "fsmallcount", nullable = false)
	public int getSmallCount() {
		return smallCount;
	}

	public void setSmallCount(int smallCount) {
		this.smallCount = smallCount;
	}

}
