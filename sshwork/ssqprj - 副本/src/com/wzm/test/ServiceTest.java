package com.wzm.test;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.entity.base.BaseEntity;
import com.wzm.server.entity.formula.RedFormulaCaclMulForcast;
import com.wzm.server.service.formula.FormulaService;
import com.wzm.server.service.ssq.SsqForcastService;
import com.wzm.server.service.ssq.SsqService;
import com.wzm.util.SsqStatsType;

public class ServiceTest {
	
	private static ApplicationContext ctx1 = null;


	public String getPath() {
		return this.getClass().getResource("/").getPath();
	}
	
	public static void  main(String[] args) {
		String path  = new ServiceTest().getPath();
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path+"applicationContext.xml");
		
		
		if(args[0].equalsIgnoreCase("formula")) {
			
			FormulaService formulaService = (FormulaService)ctx.getBean("formulaService");
			
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
			
		} else if(args[0].equalsIgnoreCase("stats")) {
			SsqService ssqService = (SsqService)ctx.getBean("ssqService");
			SsqForcastService forcastService = (SsqForcastService)ctx.getBean("forcastService");
			
			// 保存双色球开奖记录
			writeSsqRecord(ssqService);
//			writeSsqAllStats(ssqService);
//			writeSsqStats(ssqService,2011001,2012056);
			
			
			// 写到目前未存在的统计信息
			writeNotExistSsqStatsByNow(ssqService);
			
			// 预测
//			forcast(forcastService);
			
			
			// 写到目前未存在的预测信息
			forcastNotExist(forcastService);
		} 
		
	}
	
	private static ApplicationContext getContext() {
		String path  = new ServiceTest().getPath();
		if(ctx1 == null) {
			ctx1 = new FileSystemXmlApplicationContext(path+"applicationContext.xml");
		}
		return ctx1;
	}
	
	public static void allInOneGenerate() {

		SsqService ssqService = (SsqService)getContext().getBean("ssqService");
		SsqForcastService forcastService = (SsqForcastService)getContext().getBean("forcastService");
		
		// 保存双色球开奖记录
		writeSsqRecord(ssqService);
		
		// 写到目前未存在的统计信息
		writeNotExistSsqStatsByNow(ssqService);
		
		// 写到目前未存在的预测信息
		forcastNotExist(forcastService);
			
		FormulaService formulaService = (FormulaService)getContext().getBean("formulaService");
		
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
	
	public static int getBeginSsqIndex(int spaceNum,int forcastSpaceNum,int toSsqIndex) {

		BaseDao basedao = (BaseDao)getContext().getBean("baseDao");
		
		String hql = "from RedFormulaCaclMulForcast a where a.forcastSpaceNum = ? " +
				" and a.spaceNum=? and a.toSsqIndex=?";
		List<BaseEntity> list = basedao.find(hql,new Object[]{forcastSpaceNum,spaceNum,toSsqIndex});
		return ((RedFormulaCaclMulForcast)list.get(0)).getFromSsqIndex();
	}
	
	public static void  forcast(SsqForcastService forcastService) {
		
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
		for(int spaceNum:spaceNums) {
			for(int forcastSpaceNum:forcastSpaceNums) {
				// 预测尾数
				forcastService.writeSsqTailForcast(2007001, 2012054, spaceNum, forcastSpaceNum);
				forcastService.verifySsqTailForcast(2007001, 2012054, spaceNum, forcastSpaceNum);
				
				// 预测冷热数
				forcastService.writeSsqChForcast(2007001, 2012060, spaceNum, forcastSpaceNum);
				forcastService.verifySsqCHForcast(2007001, 2012060, spaceNum, forcastSpaceNum);
				
			}
		}
		
	}
	
	public static void  forcastNotExist(SsqForcastService forcastService) {
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
		for(int spaceNum:spaceNums) {
			for(int forcastSpaceNum:forcastSpaceNums) {
				forcastService.writeNotExistSsqTailForcast(spaceNum, forcastSpaceNum);
				forcastService.verifyNotExistSsqTailForcast(spaceNum, forcastSpaceNum);
				forcastService.writeNotExistSsqChForcast(spaceNum, forcastSpaceNum);
				forcastService.verifyNotExistSsqCHForcast(spaceNum, forcastSpaceNum);
			}
		}
		
		
		
	}
	
	public static void  writeSsqRecord(SsqService ssqService) {
		ssqService.writeAllSsqRecord("");
		
	}
	
	public static void  writeSsqAllStats(SsqService ssqService) {
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
		ssqService.writeAllSsqBaseStats();
		
		// 蓝球统计
		ssqService.writeAllSsqBlueStats();
		
		//尾数统计
		ssqService.writeAllSsqTailStats();
		
		//质数统计
		ssqService.writeAllSsqPrimeStats();
		
		//冷热统计
		ssqService.writeAllSsqColdHotStats();
		
		//AC值、散度统计
		ssqService.writeAllSsqAcAndSanduStats();
		
		// 多期统计
		for(int spaceNum:spaceNums) {
			ssqService.writeAllSsqMulBlueStats(spaceNum);
			ssqService.writeAllSsqMulTailStats(spaceNum);
			ssqService.writeAllSsqMulPrimeStats(spaceNum);
			ssqService.writeAllSsqMulColdHotStats(spaceNum);
		}
	}
	
	public static void  writeSsqStats(SsqService ssqService,int from,int to) {
		
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
		ssqService.writeSsqBaseStats(from,to);
		
		// 蓝球统计
		ssqService.writeSsqBlueStats(from,to);
		
		//尾数统计
		ssqService.writeSsqTailStats(from,to);
		
		//质数统计
		ssqService.writeSsqPrimeStats(from,to);
		
		//冷热统计
		ssqService.writeSsqColdHotStats(from,to);
		
		//AC值、散度统计
		ssqService.writeSsqAcAndSanduStats(from,to);
		
		// 多期统计
		for(int spaceNum:spaceNums) {
			ssqService.writeSsqMulBlueStats(from,to,spaceNum);
			ssqService.writeSsqMulTailStats(from,to,spaceNum);
			ssqService.writeSsqMulPrimeStats(from,to,spaceNum);
			ssqService.writeSsqMulColdHotStats(from,to,spaceNum);
		}
	}
	
	
	public static void  writeNotExistSsqStatsByNow(SsqService ssqService) {
		
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
		
		// 蓝球统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.BLUE_STATS);
		
		//尾数统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.TAIL_STATS);
		
		//质数统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.PRIME_STATS);
		
		//冷热统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.COLD_HOT_STATS);
		
		//AC值、散度统计
		ssqService.writeNotExistSsqStatsByNow(SsqStatsType.AC_SANDU_STATS);
		
		// 多期统计
		for(int spaceNum:spaceNums) {
			ssqService.writeNotExistSsqMulStatsByNow(SsqStatsType.MUL_BLUE_STATS, spaceNum);
			ssqService.writeNotExistSsqMulStatsByNow(SsqStatsType.MUL_TAIL_STATS, spaceNum);
			ssqService.writeNotExistSsqMulStatsByNow(SsqStatsType.MUL_COLD_HOT_STATS, spaceNum);
			ssqService.writeNotExistSsqMulStatsByNow(SsqStatsType.MUL_PRIME_STATS, spaceNum);
		}
	}
	
}
