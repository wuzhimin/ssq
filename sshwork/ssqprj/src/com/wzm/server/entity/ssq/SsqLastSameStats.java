package com.wzm.server.entity.ssq;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssq.common.util.SsqUtils;
import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球上期对比统计表
 * 
 */

@Entity
@Table(name = "t_ssqlastsamestats", catalog = "ssq")
public class SsqLastSameStats extends BaseEntity {

	private static final long serialVersionUID = 1664823111750852101L;

	// 双色球开奖记录
	private SsqRecord record; 
	
	// 双色球上期开奖记录
	private SsqRecord lastRecord; 

	// 双色球开奖期号
	private int ssqIndex; 
	
	// 双色球上期开奖期号
	private int lastSsqIndex; 
	
	// 相同个数
	private int sameCount;
	
	// 相同红球
	private String sameNumber;

	/*
	 * 计算统计值
	 */
	public void buildStats(SsqRecord record, SsqRecord lastRecord) {
		setRecord(record);
		setLastRecord(lastRecord);
		
		ssqIndex = record.getSsqIndex();
		lastSsqIndex = lastRecord.getSsqIndex();
		
		Map<Integer, String> lastMap = new HashMap<Integer, String>();
		lastMap.put(lastRecord.getR1(), null);
		lastMap.put(lastRecord.getR2(), null);
		lastMap.put(lastRecord.getR3(), null);
		lastMap.put(lastRecord.getR4(), null);
		lastMap.put(lastRecord.getR5(), null);
		lastMap.put(lastRecord.getR6(), null);
		
		sameCount = 0;
		sameNumber = "";
		
		if(lastMap.containsKey(record.getR1())) {
			sameCount++;
			sameNumber = sameNumber+SsqUtils.build2BitIntStr(record.getR1())+",";
		}
		
		if(lastMap.containsKey(record.getR2())) {
			sameCount++;
			sameNumber = sameNumber+SsqUtils.build2BitIntStr(record.getR2())+",";
		}
		
		if(lastMap.containsKey(record.getR3())) {
			sameCount++;
			sameNumber = sameNumber+SsqUtils.build2BitIntStr(record.getR3())+",";
		}
		
		if(lastMap.containsKey(record.getR4())) {
			sameCount++;
			sameNumber = sameNumber+SsqUtils.build2BitIntStr(record.getR4())+",";
		}
		
		if(lastMap.containsKey(record.getR5())) {
			sameCount++;
			sameNumber = sameNumber+SsqUtils.build2BitIntStr(record.getR5())+",";
		}
		
		if(lastMap.containsKey(record.getR6())) {
			sameCount++;
			sameNumber = sameNumber+SsqUtils.build2BitIntStr(record.getR6())+",";
		}
		
		if(sameCount>0) {
			sameNumber = sameNumber.substring(0,sameNumber.length()-1);
		}
		
	}
	
	public SsqLastSameStats(){
		
	}

	@Column(name = "fssqindex", unique = true, nullable = false)
	public int getSsqIndex() {
		return ssqIndex;
	}

	public void setSsqIndex(int ssqIndex) {
		this.ssqIndex = ssqIndex;
	}

	@ManyToOne(targetEntity=SsqRecord.class)
	@JoinColumn(name = "frecordid", unique = true, nullable = false)
	public SsqRecord getRecord() {
		return record;
	}

	public void setRecord(SsqRecord record) {
		this.record = record;
	}

	@ManyToOne(targetEntity=SsqRecord.class)
	@JoinColumn(name = "flastrecordid", unique = true, nullable = false)
	public SsqRecord getLastRecord() {
		return lastRecord;
	}

	public void setLastRecord(SsqRecord lastRecord) {
		this.lastRecord = lastRecord;
	}

	@Column(name = "flastssqindex", unique = true, nullable = false)
	public int getLastSsqIndex() {
		return lastSsqIndex;
	}

	public void setLastSsqIndex(int lastSsqIndex) {
		this.lastSsqIndex = lastSsqIndex;
	}

	@Column(name = "fsamecount", nullable = false)
	public int getSameCount() {
		return sameCount;
	}

	public void setSameCount(int sameCount) {
		this.sameCount = sameCount;
	}

	@Column(name = "fsamenumber", nullable = true)
	public String getSameNumber() {
		return sameNumber;
	}

	public void setSameNumber(String sameNumber) {
		this.sameNumber = sameNumber;
	}

}
