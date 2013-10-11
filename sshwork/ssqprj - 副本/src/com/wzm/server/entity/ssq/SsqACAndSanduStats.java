package com.wzm.server.entity.ssq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;
import com.wzm.util.SsqConstant;

/**
 * 
 * @author wzm 类说明：双色球AC值和散度统计表
 * 
 */

@Entity
@Table(name = "t_ssqacsandustats", catalog = "ssq")
public class SsqACAndSanduStats extends BaseEntity implements StatsCompute {


	private static final long serialVersionUID = -8123367099602117088L;

	private SsqRecord record; // 双色球开奖记录

	private int ssqIndex; // 双色球开奖期号

	private int acValue; // AC值

	private int sanduValue; // 散度值

	public SsqACAndSanduStats() {

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

		acValue = SsqConstant.getACValue(new int[]{r1,r2,r3,r4,r5,r6});
		sanduValue = SsqConstant.getSandu(new int[]{r1,r2,r3,r4,r5,r6});
		
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

	@Column(name = "facvalue", nullable = false)
	public int getAcValue() {
		return acValue;
	}

	public void setAcValue(int acValue) {
		this.acValue = acValue;
	}

	@Column(name = "fsanduvalue", nullable = false)
	public int getSanduValue() {
		return sanduValue;
	}

	public void setSanduValue(int sanduValue) {
		this.sanduValue = sanduValue;
	}

	

}
