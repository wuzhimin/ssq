package com.wzm.ssq.client;

import java.util.ArrayList;
import java.util.List;

import com.wzm.server.service.formula.FormulaService;
import com.wzm.server.service.ssq.SsqForcastService;
import com.wzm.server.service.ssq.SsqRecordService;
import com.wzm.util.ClientBeanUtil;
import com.wzm.util.SsqStatsType;

public class TestUIHelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public static void buildSSqRecordAndStats() {
		SsqRecordService ssqService = (SsqRecordService) ClientBeanUtil
				.getService("ssqRecordService");

		SsqForcastService forcastService = (SsqForcastService) ClientBeanUtil
				.getService("ssqForcastService");

		FormulaService formulaService = (FormulaService) ClientBeanUtil
				.getService("formulaService");

		// 保存双色球开奖记录
		ssqService.writeAllSsqRecord("");

		// 写到目前未存在的统计信息
		writeNotExistSsqStatsByNow(ssqService);

		// 写到目前未存在的预测信息
		forcastNotExist(forcastService);
		
		// 构造公式相关结果
		buildFormulaResult(formulaService);

	}

	/**
	 * 构造公式相关结果
	 * @param formulaService
	 */
	private static void buildFormulaResult(FormulaService formulaService) {
		try {
			formulaService.caclAllRedFormula();
			formulaService.verifyAllRedFormula();
			
			formulaService.writeAllRedFormulaCaclVerifyMulStats(50);
			formulaService.writeAllRedFormulaCaclVerifyMulStats(60);
			formulaService.writeAllRedFormulaCaclVerifyMulStats(80);
			formulaService.writeAllRedFormulaCaclVerifyMulStats(100);
			
			
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(50,300);
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(60,300);
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(80,300);
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(100,300);
			
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(50,400);
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(60,400);
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(80,400);
			formulaService.writeAllRedFormulaCaclVerifyMulForcast(100,400);
			
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(50,300);
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(60,300);
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(80,300);
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(100,300);
			
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(50,400);
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(60,400);
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(80,400);
			formulaService.verifyAllRedFormulaCaclVerifyMulForcast(100,400);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeNotExistSsqStatsByNow(SsqRecordService ssqService) {

		// 一定范围内统计信息
		List<Integer> spaceNums = new ArrayList<Integer>();
		spaceNums.add(10);
		spaceNums.add(20);
		spaceNums.add(30);
		spaceNums.add(40);
		spaceNums.add(50);
		spaceNums.add(60);
		spaceNums.add(80);
		spaceNums.add(100);

		// 基本统计表
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.BASE_STATS);
		
		// 行列遗失统计表
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.COL_ROW_STATS);

		// 蓝球统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.BLUE_STATS);

		// 尾数统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.TAIL_STATS);

		// 质数统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.PRIME_STATS);

		// 冷热统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.COLD_HOT_STATS);

		// AC值、散度统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.AC_SANDU_STATS);
		
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.LAST_SAME_STATS);
		

		// 多期统计
		for (int spaceNum : spaceNums) {
			ssqService.writeNotExistSsqMulStatsByNow(
					SsqStatsType.MUL_BLUE_STATS, spaceNum);
			ssqService.writeNotExistSsqMulStatsByNow(
					SsqStatsType.MUL_TAIL_STATS, spaceNum);
			ssqService.writeNotExistSsqMulStatsByNow(
					SsqStatsType.MUL_COLD_HOT_STATS, spaceNum);
			ssqService.writeNotExistSsqMulStatsByNow(
					SsqStatsType.MUL_PRIME_STATS, spaceNum);
		}
	}

	public static void forcastNotExist(SsqForcastService forcastService) {
		List<Integer> spaceNums = new ArrayList<Integer>();
		spaceNums.add(10);
		spaceNums.add(20);
		spaceNums.add(30);
		spaceNums.add(40);
		spaceNums.add(50);
		spaceNums.add(60);
		spaceNums.add(80);
		spaceNums.add(100);

		List<Integer> forcastSpaceNums = new ArrayList<Integer>();
		forcastSpaceNums.add(200);
		forcastSpaceNums.add(300);
		forcastSpaceNums.add(400);
		for (int spaceNum : spaceNums) {
			for (int forcastSpaceNum : forcastSpaceNums) {
				forcastService.writeNotExistSsqTailForcast(spaceNum,
						forcastSpaceNum);
				forcastService.verifyNotExistSsqTailForcast(spaceNum,
						forcastSpaceNum);
				forcastService.writeNotExistSsqChForcast(spaceNum,
						forcastSpaceNum);
				forcastService.verifyNotExistSsqCHForcast(spaceNum,
						forcastSpaceNum);
			}
		}
	}

}
