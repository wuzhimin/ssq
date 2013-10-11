package com.wzm.server.entity.ssq;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球多期红球尾数统计表
 * 
 */

@Entity
@Table(name = "t_ssqmulchstats", catalog = "ssq")
public class SsqMulColdHotStats extends BaseEntity implements MulStatsCompute {

	private static final long serialVersionUID = -7961872223761312797L;

	private List<StatsCompute> records; // 双色球红球尾数统计记录

	private int fromSsqIndex; // 开始双色球开奖期号
	private int toSsqIndex; // 结束双色球开奖期号

	private int spaceNum; // 间隔期数

	private int ch1count;   // 冷热数1个数
	private int ch2count;   // 冷热数2个数
	private int ch3count;   // 冷热数3个数
	private int ch4count;   // 冷热数4个数
	private int ch5count;   // 冷热数5个数
	private int ch6count;   // 冷热数6个数
	private int ch7count;   // 冷热数7个数
	private int ch8count;   // 冷热数8个数
	private int ch9count;   // 冷热数9个数
	private int ch10count;   // 冷热数10个数
	private int ch11count;   // 冷热数11个数
	private int ch12count;   // 冷热数12个数
	private int ch13count;   // 冷热数13个数
	private int ch14count;   // 冷热数14个数
	private int ch15count;   // 冷热数15个数
	private int ch16count;   // 冷热数16个数
	private int ch17count;   // 冷热数17个数
	private int ch18count;   // 冷热数18个数
	private int ch19count;   // 冷热数19个数
	private int ch20count;   // 冷热数20个数
	private int ch21count;   // 冷热数21个数
	private int ch22count;   // 冷热数22个数
	private int ch23count;   // 冷热数23个数
	private int ch24count;   // 冷热数24个数
	private int ch25count;   // 冷热数25个数
	private int ch26count;   // 冷热数26个数
	private int ch27count;   // 冷热数27个数
	private int ch28count;   // 冷热数28个数
	private int ch29count;   // 冷热数29个数
	private int ch30count;   // 冷热数30个数
	private int ch31count;   // 冷热数31个数
	private int ch32count;   // 冷热数32个数
	private int ch33count;   // 冷热数33个数

	public SsqMulColdHotStats() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(List<StatsCompute> records) {
		if (!(records.get(0) instanceof SsqColdHotStats)) {
			return;
		}

		int size = records.size();

		setFromSsqIndex(((SsqColdHotStats) records.get(0)).getSsqIndex());

		setToSsqIndex(((SsqColdHotStats) records.get(size - 1)).getSsqIndex());

		setSpaceNum(size);

		for (int j = 0; j < size; j++) {
			setCh1count(getCh1count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh1()?1:0 ) );
			
			setCh2count(getCh2count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh2()?1:0 ) );
			
			setCh3count(getCh3count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh3()?1:0 ) );
			
			setCh4count(getCh4count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh4()?1:0 ) );
			
			setCh5count(getCh5count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh5()?1:0 ) );
			
			setCh6count(getCh6count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh6()?1:0 ) );
			
			setCh7count(getCh7count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh7()?1:0 ) );
			
			setCh8count(getCh8count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh8()?1:0 ) );
			
			setCh9count(getCh9count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh9()?1:0 ) );
			
			setCh10count(getCh10count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh10()?1:0 ) );
			
			setCh11count(getCh11count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh11()?1:0 ) );
			
			setCh12count(getCh12count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh12()?1:0 ) );
			
			setCh13count(getCh13count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh13()?1:0 ) );
			
			setCh14count(getCh14count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh14()?1:0 ) );
			
			setCh15count(getCh15count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh15()?1:0 ) );
			
			setCh16count(getCh16count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh16()?1:0 ) );
			
			setCh17count(getCh17count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh17()?1:0 ) );
			
			setCh18count(getCh18count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh18()?1:0 ) );
			
			setCh19count(getCh19count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh19()?1:0 ) );
			
			setCh20count(getCh20count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh20()?1:0 ) );
			
			setCh21count(getCh21count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh21()?1:0 ) );
			
			setCh22count(getCh22count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh22()?1:0 ) );
			
			setCh23count(getCh23count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh23()?1:0 ) );
			
			setCh24count(getCh24count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh24()?1:0 ) );
			
			setCh25count(getCh25count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh25()?1:0 ) );
			
			setCh26count(getCh26count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh26()?1:0 ) );
			
			setCh27count(getCh27count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh27()?1:0 ) );
			
			setCh28count(getCh28count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh28()?1:0 ) );
			
			setCh29count(getCh29count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh29()?1:0 ) );
			
			setCh30count(getCh30count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh30()?1:0 ) );
			
			setCh31count(getCh31count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh31()?1:0 ) );
			
			setCh32count(getCh32count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh32()?1:0 ) );
			
			setCh33count(getCh33count()
					+ ( ((SsqColdHotStats) records.get(j)).isHasCh33()?1:0 ) );
			
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
	
	@Column(name = "fch1count", nullable = false)
	public int getCh1count() {
		return ch1count;
	}

	public void setCh1count(int ch1) {
		this.ch1count = ch1;
	}

	@Column(name = "fch2count", nullable = false)
	public int getCh2count() {
		return ch2count;
	}

	public void setCh2count(int ch2) {
		this.ch2count = ch2;
	}

	@Column(name = "fch3count", nullable = false)
	public int getCh3count() {
		return ch3count;
	}

	public void setCh3count(int ch3) {
		this.ch3count = ch3;
	}

	@Column(name = "fch4count", nullable = false)
	public int getCh4count() {
		return ch4count;
	}

	public void setCh4count(int ch4) {
		this.ch4count = ch4;
	}

	@Column(name = "fch5count", nullable = false)
	public int getCh5count() {
		return ch5count;
	}

	public void setCh5count(int ch5) {
		this.ch5count = ch5;
	}

	@Column(name = "fch6count", nullable = false)
	public int getCh6count() {
		return ch6count;
	}

	public void setCh6count(int ch6) {
		this.ch6count = ch6;
	}

	@Column(name = "fch7count", nullable = false)
	public int getCh7count() {
		return ch7count;
	}

	public void setCh7count(int ch7) {
		this.ch7count = ch7;
	}

	@Column(name = "fch8count", nullable = false)
	public int getCh8count() {
		return ch8count;
	}

	public void setCh8count(int ch8) {
		this.ch8count = ch8;
	}

	@Column(name = "fch9count", nullable = false)
	public int getCh9count() {
		return ch9count;
	}

	public void setCh9count(int ch9) {
		this.ch9count = ch9;
	}

	@Column(name = "fch10count", nullable = false)
	public int getCh10count() {
		return ch10count;
	}

	public void setCh10count(int ch10) {
		this.ch10count = ch10;
	}
	
	@Column(name = "fch11count", nullable = false)
	public int getCh11count() {
		return ch11count;
	}

	public void setCh11count(int ch11) {
		this.ch11count = ch11;
	}

	@Column(name = "fch12count", nullable = false)
	public int getCh12count() {
		return ch12count;
	}

	public void setCh12count(int ch12) {
		this.ch12count = ch12;
	}

	@Column(name = "fch13count", nullable = false)
	public int getCh13count() {
		return ch13count;
	}

	public void setCh13count(int ch13) {
		this.ch13count = ch13;
	}

	@Column(name = "fch14count", nullable = false)
	public int getCh14count() {
		return ch14count;
	}

	public void setCh14count(int ch14) {
		this.ch14count = ch14;
	}

	@Column(name = "fch15count", nullable = false)
	public int getCh15count() {
		return ch15count;
	}

	public void setCh15count(int ch15) {
		this.ch15count = ch15;
	}

	@Column(name = "fch16count", nullable = false)
	public int getCh16count() {
		return ch16count;
	}

	public void setCh16count(int ch16) {
		this.ch16count = ch16;
	}

	@Column(name = "fch17count", nullable = false)
	public int getCh17count() {
		return ch17count;
	}

	public void setCh17count(int ch17) {
		this.ch17count = ch17;
	}

	@Column(name = "fch18count", nullable = false)
	public int getCh18count() {
		return ch18count;
	}

	public void setCh18count(int ch18) {
		this.ch18count = ch18;
	}

	@Column(name = "fch19count", nullable = false)
	public int getCh19count() {
		return ch19count;
	}

	public void setCh19count(int ch19) {
		this.ch19count = ch19;
	}

	@Column(name = "fch20count", nullable = false)
	public int getCh20count() {
		return ch20count;
	}

	public void setCh20count(int ch20) {
		this.ch20count = ch20;
	}

	@Column(name = "fch21count", nullable = false)
	public int getCh21count() {
		return ch21count;
	}

	public void setCh21count(int ch21) {
		this.ch21count = ch21;
	}

	@Column(name = "fch22count", nullable = false)
	public int getCh22count() {
		return ch22count;
	}

	public void setCh22count(int ch22) {
		this.ch22count = ch22;
	}

	@Column(name = "fch23count", nullable = false)
	public int getCh23count() {
		return ch23count;
	}

	public void setCh23count(int ch23) {
		this.ch23count = ch23;
	}

	@Column(name = "fch24count", nullable = false)
	public int getCh24count() {
		return ch24count;
	}

	public void setCh24count(int ch24) {
		this.ch24count = ch24;
	}

	@Column(name = "fch25count", nullable = false)
	public int getCh25count() {
		return ch25count;
	}

	public void setCh25count(int ch25) {
		this.ch25count = ch25;
	}

	@Column(name = "fch26count", nullable = false)
	public int getCh26count() {
		return ch26count;
	}

	public void setCh26count(int ch26) {
		this.ch26count = ch26;
	}

	@Column(name = "fch27count", nullable = false)
	public int getCh27count() {
		return ch27count;
	}

	public void setCh27count(int ch27) {
		this.ch27count = ch27;
	}

	@Column(name = "fch28count", nullable = false)
	public int getCh28count() {
		return ch28count;
	}

	public void setCh28count(int ch28) {
		this.ch28count = ch28;
	}

	@Column(name = "fch29count", nullable = false)
	public int getCh29count() {
		return ch29count;
	}

	public void setCh29count(int ch29) {
		this.ch29count = ch29;
	}

	@Column(name = "fch30count", nullable = false)
	public int getCh30count() {
		return ch30count;
	}

	public void setCh30count(int ch30) {
		this.ch30count = ch30;
	}

	@Column(name = "fch31count", nullable = false)
	public int getCh31count() {
		return ch31count;
	}

	public void setCh31count(int ch31) {
		this.ch31count = ch31;
	}

	@Column(name = "fch32count", nullable = false)
	public int getCh32count() {
		return ch32count;
	}

	public void setCh32count(int ch32) {
		this.ch32count = ch32;
	}

	@Column(name = "fch33count", nullable = false)
	public int getCh33count() {
		return ch33count;
	}

	public void setCh33count(int ch33) {
		this.ch33count = ch33;
	}
}
