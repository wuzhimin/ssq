package com.wzm.server.entity.ssq;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球多期红球质数统计表
 * 
 */

@Entity
@Table(name = "t_ssqmulprimestats", catalog = "ssq")
public class SsqMulPrimeStats extends BaseEntity implements MulStatsCompute {

	private static final long serialVersionUID = 1874123111711852101L;

	private List<StatsCompute> records; // 双色球红球质数统计记录

	private int fromSsqIndex; // 开始双色球开奖期号
	private int toSsqIndex; // 结束双色球开奖期号

	private int spaceNum; // 间隔期数

	private int p2count; // 质数2个数
	private int p3count; // 质数3个数
	private int p5count; // 质数5个数
	private int p7count; // 质数7个数
	private int p11count; // 质数11个数
	private int p13count; // 质数13个数
	private int p17count; // 质数17个数
	private int p19count; // 质数19个数
	private int p23count; // 质数23个数
	private int p29count; // 质数29个数
	private int p31count; // 质数31个数
	
	public SsqMulPrimeStats() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(List<StatsCompute> records) {
		if (!(records.get(0) instanceof SsqPrimeStats)) {
			return;
		}

		int size = records.size();

		setFromSsqIndex(((SsqPrimeStats) records.get(0)).getSsqIndex());

		setToSsqIndex(((SsqPrimeStats) records.get(size - 1)).getSsqIndex());

		setSpaceNum(size);

		for (int j = 0; j < size; j++) {
			setP2count(getP2count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime2()?1:0) );
			
			setP3count(getP3count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime3()?1:0) );
			
			setP5count(getP5count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime5()?1:0) );
			
			setP7count(getP7count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime7()?1:0) );
			
			setP11count(getP11count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime11()?1:0) );
			
			setP13count(getP13count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime13()?1:0) );
			
			setP17count(getP17count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime17()?1:0) );
			
			setP19count(getP19count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime19()?1:0) );
			
			setP23count(getP23count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime23()?1:0) );
			
			setP29count(getP29count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime29()?1:0) );
			
			setP31count(getP31count()
					+ (((SsqPrimeStats) records.get(j)).isHasPrime31()?1:0) );
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

	@Column(name = "fp2count", nullable = false)
	public int getP2count() {
		return p2count;
	}

	public void setP2count(int p2count) {
		this.p2count = p2count;
	}

	@Column(name = "fp3count", nullable = false)
	public int getP3count() {
		return p3count;
	}

	public void setP3count(int p3count) {
		this.p3count = p3count;
	}

	@Column(name = "fp5count", nullable = false)
	public int getP5count() {
		return p5count;
	}

	public void setP5count(int p5count) {
		this.p5count = p5count;
	}

	@Column(name = "fp7count", nullable = false)
	public int getP7count() {
		return p7count;
	}

	public void setP7count(int p7count) {
		this.p7count = p7count;
	}

	@Column(name = "fp11count", nullable = false)
	public int getP11count() {
		return p11count;
	}

	public void setP11count(int p11count) {
		this.p11count = p11count;
	}

	@Column(name = "fp13count", nullable = false)
	public int getP13count() {
		return p13count;
	}

	public void setP13count(int p13count) {
		this.p13count = p13count;
	}

	@Column(name = "fp17count", nullable = false)
	public int getP17count() {
		return p17count;
	}

	public void setP17count(int p17count) {
		this.p17count = p17count;
	}

	@Column(name = "fp19count", nullable = false)
	public int getP19count() {
		return p19count;
	}

	public void setP19count(int p19count) {
		this.p19count = p19count;
	}

	@Column(name = "fp23count", nullable = false)
	public int getP23count() {
		return p23count;
	}

	public void setP23count(int p23count) {
		this.p23count = p23count;
	}

	@Column(name = "fp29count", nullable = false)
	public int getP29count() {
		return p29count;
	}

	public void setP29count(int p29count) {
		this.p29count = p29count;
	}

	@Column(name = "fp31count", nullable = false)
	public int getP31count() {
		return p31count;
	}

	public void setP31count(int p31count) {
		this.p31count = p31count;
	}
}
