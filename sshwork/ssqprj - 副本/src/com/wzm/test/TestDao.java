package com.wzm.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wzm.server.dao.ssq.SsqBaseStatsDao;
import com.wzm.server.entity.ssq.SsqBaseStats;

public class TestDao {

	public String getPath() {
		return this.getClass().getResource("/").getPath();
	}

	public static void main(String[] args) {
		String path = new TestDao().getPath();
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path + "applicationContext.xml");

		SsqBaseStatsDao ssqBaseStatsDao = (SsqBaseStatsDao) ctx.getBean("ssqBaseStatsDao");

		String sumType = "redSum";
		
		String hql = "select distinct "+sumType+" from SsqBaseStats s order by "+sumType;
		List listSum = ssqBaseStatsDao.find(hql);

		for (Object baseStats : listSum) {
			int sum = ((Integer) baseStats).intValue();

			ddd(ssqBaseStatsDao, sumType, sum);
		}

	}

	private static void ddd(SsqBaseStatsDao ssqBaseStatsDao, String sumType, int sum) {
		System.out.println("\n-----------" + sum + "-----------");
		
		String hql = "from SsqBaseStats s where s."+sumType+" = ?";
		List<SsqBaseStats> list = ssqBaseStatsDao
				.findSsqBaseStatsesBySumScope(sumType, sum, sum);

		hql = " select count(s.ssqIndex) from SsqBaseStats s where s.ssqIndex>=? and s.ssqIndex<=?";

		long max = -1;
		long min = 10000;
		for (int i = 1; i < list.size(); i++) {
			int ssqIndex1 = list.get(i - 1).getSsqIndex();
			int ssqIndex2 = list.get(i).getSsqIndex();
			long count = ssqBaseStatsDao.getFunctionLongValue(hql,
					new Integer[] { ssqIndex1, ssqIndex2 }) - 2;

			if (count > max) {
				max = count;
			}

			if (count < min) {
				min = count;
			}
			System.out.println(ssqIndex1 + "-----" + ssqIndex2 + "-------"
					+ count);
		}

		if(max == -1) {
			max =0;
		}
		
		if(min == 10000) {
			min =0;
		}
		System.out.println("最小遗漏期数："+min + "-----" +"最大遗漏期数："+ max);
		
		max = -1;
		min = 10000;
	}

}
