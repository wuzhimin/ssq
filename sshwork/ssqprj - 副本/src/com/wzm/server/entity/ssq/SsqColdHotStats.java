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
 * @author wzm 类说明：双色球红球冷热数统计表
 * 
 */

@Entity
@Table(name = "t_ssqchstats", catalog = "ssq")
public class SsqColdHotStats extends BaseEntity implements StatsCompute {

	private static final long serialVersionUID = 1664123111750852101L;

	private SsqRecord record; // 双色球开奖记录

	private int ssqIndex; // 双色球开奖期号
	
	private boolean hasCh1;   // 是否有冷热数1
	private boolean hasCh2;   // 是否有冷热数2
	private boolean hasCh3;   // 是否有冷热数3
	private boolean hasCh4;   // 是否有冷热数4
	private boolean hasCh5;   // 是否有冷热数5
	private boolean hasCh6;   // 是否有冷热数6
	private boolean hasCh7;   // 是否有冷热数7
	private boolean hasCh8;   // 是否有冷热数8
	private boolean hasCh9;   // 是否有冷热数9
	private boolean hasCh10;   // 是否有冷热数10
	private boolean hasCh11;   // 是否有冷热数11
	private boolean hasCh12;   // 是否有冷热数12
	private boolean hasCh13;   // 是否有冷热数13
	private boolean hasCh14;   // 是否有冷热数14
	private boolean hasCh15;   // 是否有冷热数15
	private boolean hasCh16;   // 是否有冷热数16
	private boolean hasCh17;   // 是否有冷热数17
	private boolean hasCh18;   // 是否有冷热数18
	private boolean hasCh19;   // 是否有冷热数19
	private boolean hasCh20;   // 是否有冷热数20
	private boolean hasCh21;   // 是否有冷热数21
	private boolean hasCh22;   // 是否有冷热数22
	private boolean hasCh23;   // 是否有冷热数23
	private boolean hasCh24;   // 是否有冷热数24
	private boolean hasCh25;   // 是否有冷热数25
	private boolean hasCh26;   // 是否有冷热数26
	private boolean hasCh27;   // 是否有冷热数27
	private boolean hasCh28;   // 是否有冷热数28
	private boolean hasCh29;   // 是否有冷热数29
	private boolean hasCh30;   // 是否有冷热数30
	private boolean hasCh31;   // 是否有冷热数31
	private boolean hasCh32;   // 是否有冷热数32
	private boolean hasCh33;   // 是否有冷热数33

	public SsqColdHotStats() {

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
		
		Map<Integer, String> rMap = new HashMap<Integer, String>();
		rMap.put(r1, null);
		rMap.put(r2, null);
		rMap.put(r3, null);
		rMap.put(r4, null);
		rMap.put(r5, null);
		rMap.put(r6, null);
		
		hasCh1 = rMap.containsKey(1)?true:false;
		hasCh2 = rMap.containsKey(2)?true:false;
		hasCh3 = rMap.containsKey(3)?true:false;
		hasCh4 = rMap.containsKey(4)?true:false;
		hasCh5 = rMap.containsKey(5)?true:false;
		hasCh6 = rMap.containsKey(6)?true:false;
		hasCh7 = rMap.containsKey(7)?true:false;
		hasCh8 = rMap.containsKey(8)?true:false;
		hasCh9 = rMap.containsKey(9)?true:false;
		hasCh10 = rMap.containsKey(10)?true:false;
		hasCh11 = rMap.containsKey(11)?true:false;
		hasCh12 = rMap.containsKey(12)?true:false;
		hasCh13 = rMap.containsKey(13)?true:false;
		hasCh14 = rMap.containsKey(14)?true:false;
		hasCh15 = rMap.containsKey(15)?true:false;
		hasCh16 = rMap.containsKey(16)?true:false;
		hasCh17 = rMap.containsKey(17)?true:false;
		hasCh18 = rMap.containsKey(18)?true:false;
		hasCh19 = rMap.containsKey(19)?true:false;
		hasCh20 = rMap.containsKey(20)?true:false;
		hasCh21 = rMap.containsKey(21)?true:false;
		hasCh22 = rMap.containsKey(22)?true:false;
		hasCh23 = rMap.containsKey(23)?true:false;
		hasCh24 = rMap.containsKey(24)?true:false;
		hasCh25 = rMap.containsKey(25)?true:false;
		hasCh26 = rMap.containsKey(26)?true:false;
		hasCh27 = rMap.containsKey(27)?true:false;
		hasCh28 = rMap.containsKey(28)?true:false;
		hasCh29 = rMap.containsKey(29)?true:false;
		hasCh30 = rMap.containsKey(30)?true:false;
		hasCh31 = rMap.containsKey(31)?true:false;
		hasCh32 = rMap.containsKey(32)?true:false;
		hasCh33 = rMap.containsKey(33)?true:false;
		
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

	@Column(name = "fhasch1", nullable = false)
	public boolean isHasCh1() {
		return hasCh1;
	}

	public void setHasCh1(boolean hasCh1) {
		this.hasCh1 = hasCh1;
	}

	@Column(name = "fhasch2", nullable = false)
	public boolean isHasCh2() {
		return hasCh2;
	}

	public void setHasCh2(boolean hasCh2) {
		this.hasCh2 = hasCh2;
	}

	@Column(name = "fhasch3", nullable = false)
	public boolean isHasCh3() {
		return hasCh3;
	}

	public void setHasCh3(boolean hasCh3) {
		this.hasCh3 = hasCh3;
	}

	@Column(name = "fhasch4", nullable = false)
	public boolean isHasCh4() {
		return hasCh4;
	}

	public void setHasCh4(boolean hasCh4) {
		this.hasCh4 = hasCh4;
	}

	@Column(name = "fhasch5", nullable = false)
	public boolean isHasCh5() {
		return hasCh5;
	}

	public void setHasCh5(boolean hasCh5) {
		this.hasCh5 = hasCh5;
	}

	@Column(name = "fhasch6", nullable = false)
	public boolean isHasCh6() {
		return hasCh6;
	}

	public void setHasCh6(boolean hasCh6) {
		this.hasCh6 = hasCh6;
	}

	@Column(name = "fhasch7", nullable = false)
	public boolean isHasCh7() {
		return hasCh7;
	}

	public void setHasCh7(boolean hasCh7) {
		this.hasCh7 = hasCh7;
	}

	@Column(name = "fhasch8", nullable = false)
	public boolean isHasCh8() {
		return hasCh8;
	}

	public void setHasCh8(boolean hasCh8) {
		this.hasCh8 = hasCh8;
	}

	@Column(name = "fhasch9", nullable = false)
	public boolean isHasCh9() {
		return hasCh9;
	}

	public void setHasCh9(boolean hasCh9) {
		this.hasCh9 = hasCh9;
	}

	@Column(name = "fhasch10", nullable = false)
	public boolean isHasCh10() {
		return hasCh10;
	}

	public void setHasCh10(boolean hasCh10) {
		this.hasCh10 = hasCh10;
	}
	
	@Column(name = "fhasch11", nullable = false)
	public boolean isHasCh11() {
		return hasCh11;
	}

	public void setHasCh11(boolean hasCh11) {
		this.hasCh11 = hasCh11;
	}

	@Column(name = "fhasch12", nullable = false)
	public boolean isHasCh12() {
		return hasCh12;
	}

	public void setHasCh12(boolean hasCh12) {
		this.hasCh12 = hasCh12;
	}

	@Column(name = "fhasch13", nullable = false)
	public boolean isHasCh13() {
		return hasCh13;
	}

	public void setHasCh13(boolean hasCh13) {
		this.hasCh13 = hasCh13;
	}

	@Column(name = "fhasch14", nullable = false)
	public boolean isHasCh14() {
		return hasCh14;
	}

	public void setHasCh14(boolean hasCh14) {
		this.hasCh14 = hasCh14;
	}

	@Column(name = "fhasch15", nullable = false)
	public boolean isHasCh15() {
		return hasCh15;
	}

	public void setHasCh15(boolean hasCh15) {
		this.hasCh15 = hasCh15;
	}

	@Column(name = "fhasch16", nullable = false)
	public boolean isHasCh16() {
		return hasCh16;
	}

	public void setHasCh16(boolean hasCh16) {
		this.hasCh16 = hasCh16;
	}

	@Column(name = "fhasch17", nullable = false)
	public boolean isHasCh17() {
		return hasCh17;
	}

	public void setHasCh17(boolean hasCh17) {
		this.hasCh17 = hasCh17;
	}

	@Column(name = "fhasch18", nullable = false)
	public boolean isHasCh18() {
		return hasCh18;
	}

	public void setHasCh18(boolean hasCh18) {
		this.hasCh18 = hasCh18;
	}

	@Column(name = "fhasch19", nullable = false)
	public boolean isHasCh19() {
		return hasCh19;
	}

	public void setHasCh19(boolean hasCh19) {
		this.hasCh19 = hasCh19;
	}

	@Column(name = "fhasch20", nullable = false)
	public boolean isHasCh20() {
		return hasCh20;
	}

	public void setHasCh20(boolean hasCh20) {
		this.hasCh20 = hasCh20;
	}

	@Column(name = "fhasch21", nullable = false)
	public boolean isHasCh21() {
		return hasCh21;
	}

	public void setHasCh21(boolean hasCh21) {
		this.hasCh21 = hasCh21;
	}

	@Column(name = "fhasch22", nullable = false)
	public boolean isHasCh22() {
		return hasCh22;
	}

	public void setHasCh22(boolean hasCh22) {
		this.hasCh22 = hasCh22;
	}

	@Column(name = "fhasch23", nullable = false)
	public boolean isHasCh23() {
		return hasCh23;
	}

	public void setHasCh23(boolean hasCh23) {
		this.hasCh23 = hasCh23;
	}

	@Column(name = "fhasch24", nullable = false)
	public boolean isHasCh24() {
		return hasCh24;
	}

	public void setHasCh24(boolean hasCh24) {
		this.hasCh24 = hasCh24;
	}

	@Column(name = "fhasch25", nullable = false)
	public boolean isHasCh25() {
		return hasCh25;
	}

	public void setHasCh25(boolean hasCh25) {
		this.hasCh25 = hasCh25;
	}

	@Column(name = "fhasch26", nullable = false)
	public boolean isHasCh26() {
		return hasCh26;
	}

	public void setHasCh26(boolean hasCh26) {
		this.hasCh26 = hasCh26;
	}

	@Column(name = "fhasch27", nullable = false)
	public boolean isHasCh27() {
		return hasCh27;
	}

	public void setHasCh27(boolean hasCh27) {
		this.hasCh27 = hasCh27;
	}

	@Column(name = "fhasch28", nullable = false)
	public boolean isHasCh28() {
		return hasCh28;
	}

	public void setHasCh28(boolean hasCh28) {
		this.hasCh28 = hasCh28;
	}

	@Column(name = "fhasch29", nullable = false)
	public boolean isHasCh29() {
		return hasCh29;
	}

	public void setHasCh29(boolean hasCh29) {
		this.hasCh29 = hasCh29;
	}

	@Column(name = "fhasch30", nullable = false)
	public boolean isHasCh30() {
		return hasCh30;
	}

	public void setHasCh30(boolean hasCh30) {
		this.hasCh30 = hasCh30;
	}

	@Column(name = "fhasch31", nullable = false)
	public boolean isHasCh31() {
		return hasCh31;
	}

	public void setHasCh31(boolean hasCh31) {
		this.hasCh31 = hasCh31;
	}

	@Column(name = "fhasch32", nullable = false)
	public boolean isHasCh32() {
		return hasCh32;
	}

	public void setHasCh32(boolean hasCh32) {
		this.hasCh32 = hasCh32;
	}

	@Column(name = "fhasch33", nullable = false)
	public boolean isHasCh33() {
		return hasCh33;
	}

	public void setHasCh33(boolean hasCh33) {
		this.hasCh33 = hasCh33;
	}
}
