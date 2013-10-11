package com.wzm.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.wzm.util.CombinationUtil;

public class ColdCombineTest {

	public String getPath() {
		return this.getClass().getResource("/").getPath();
	}

	public static void main(String[] args) {

		String path = new BlueFormulaTest().getPath();
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path
				+ "applicationContext.xml");

		DataSource ds = (DataSource) ctx.getBean("dataSource");

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(ds);

		final Map<int[], Float> map = new HashMap<int[], Float>();
		final Map<int[], String> sqlmap = new HashMap<int[], String>();
		final Map<int[], String> conditionMap = new HashMap<int[], String>();

		List<int[]> colds = getColds();

		for (final int[] aa : colds) {
			List<String> sqls = getSqls(aa);
			getCombineConditions(aa,conditionMap);
			// for (String tmp : sqls) {
			// System.out.println(tmp);
			// }
			//
			// System.out.println("\n");

			jdbcTemplate.query(sqls.get(0), new RowCallbackHandler() {

				@Override
				public void processRow(ResultSet rs) throws SQLException {
					map.put(aa, rs.getFloat(1));
				}
			});
		}

		List<Map.Entry<int[], Float>> info = new ArrayList<Map.Entry<int[], Float>>(
				map.entrySet());
		Collections.sort(info, new Comparator<Map.Entry<int[], Float>>() {
			public int compare(Map.Entry<int[], Float> obj1,
					Map.Entry<int[], Float> obj2) {
				if (obj2.getValue() - obj1.getValue() > 0) {
					return 1;
				} else if (obj2.getValue() - obj1.getValue() < 0) {
					return -1;
				}
				return 0;
			}
		});

		for (int j = 0; j < info.size(); j++) {
			
			int[] key = info.get(j).getKey();
			
			String tmp = "";
			
			for (int i : key) {
				tmp = tmp + i + "-";
			}
			
			System.out.println(tmp + "------->" + info.get(j).getValue());
			System.out.println(conditionMap.get(key));
		}

	}

	private static List<int[]> getColds() {

		List<int[]> colds = new ArrayList<int[]>();

		List<String> result = new ArrayList<String>();
		CombinationUtil.createCombo(
				new Object[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3, result);

		for (String tmp : result) {
			String[] tmps = tmp.split(",");
			int[] aa = new int[tmps.length];
			for (int i = 0; i < aa.length; i++) {
				aa[i] = Integer.parseInt(tmps[i]);
			}
			colds.add(aa);
		}

		// colds.add(new int[]{3,4,8});
		// colds.add(new int[]{0,3,4});
		// colds.add(new int[]{1,3,4});
		// colds.add(new int[]{0,2,9});
		// colds.add(new int[]{1,2,4});
		// colds.add(new int[]{0,2,5});
		// colds.add(new int[]{3,5,7});
		// colds.add(new int[]{3,4,9});
		// colds.add(new int[]{0,2,4});
		// colds.add(new int[]{1,3,9});
		// colds.add(new int[]{2,5,8});
		// colds.add(new int[]{0,3,5});
		// colds.add(new int[]{0,3,6});
		// colds.add(new int[]{1,2,3});
		// colds.add(new int[]{1,2,5});
		// colds.add(new int[]{0,3,9});
		// colds.add(new int[]{2,5,7});
		// colds.add(new int[]{4,5,9});
		// colds.add(new int[]{4,6,9});

		// colds.add(new int[]{0,3,4,5});
		// colds.add(new int[]{1,2,3,4});
		// colds.add(new int[]{8,3,4,5});
		// colds.add(new int[]{0,3,7,9});
		// colds.add(new int[]{2,3,4,8});
		// colds.add(new int[]{0,2,7,9});
		// colds.add(new int[]{3,5,7,9});
		// colds.add(new int[]{0,2,4,9});
		// colds.add(new int[]{3,4,7,8});
		// colds.add(new int[]{2,3,5,7});
		// colds.add(new int[]{1,3,4,9});
		//
		// colds.add(new int[]{2,5,6,7});
		// colds.add(new int[]{1,2,4,5});
		// colds.add(new int[]{0,2,5,7});
		// colds.add(new int[]{0,2,3,4});
		// colds.add(new int[]{0,1,2,5});

		return colds;
	}

	private static List<String> getSqls(int[] colds) {
		List<String> sqls = new ArrayList<String>();

		int ssqIndex = 2012001;

		String sql1 = "select * from t_ssqtailstats where fssqindex>="
				+ ssqIndex + ";";

		for (int i = 0; i < colds.length; i++) {

		}

		String sql2 = "select fssqindex";

		String tmp = "";

		for (int i = 0; i < colds.length; i++) {
			sql2 = sql2 + ",ft" + colds[i] + "count ";
			tmp = tmp + "ft" + colds[i] + "count +";
		}

		tmp = tmp.substring(0, tmp.length() - 1);

		sql2 = sql2 + "," + tmp + " from t_ssqtailstats where " + tmp
				+ " >0 and fssqindex>=" + ssqIndex
				+ " order by fssqindex desc;";

		String sql3 = "select ((select count(*) from t_ssqtailstats where "
				+ tmp + " >0 and fssqindex>=" + ssqIndex
				+ ") / (select count(*) from t_ssqtailstats where fssqindex>="
				+ ssqIndex + ") ) rate1 ;";

		sqls.add(sql3);
		// sqls.add(sql1);
		sqls.add(sql2);

		System.out.println(sql2 + "\n");

		return sqls;
	}

	public static String getStr(int num) {
		if (num < 10) {
			return "0" + num;
		}

		return num + "";
	}

	private static List<String> getCombineConditions(int[] colds, Map<int[], String> conditionMap) {

		List<String> sqls = new ArrayList<String>();
		String s1 = "1:";
		String sn = ":3";
		String middle = "";
		for (int i : colds) {

			if (i != 0) {
				middle = middle + getStr(i) + ",";
			}
			middle = middle + getStr(i + 10) + ",";
			middle = middle + getStr(i + 20) + ",";
			if (i + 30 <= 33) {
				middle = middle + getStr(i + 30) + ",";
			}
		}

		sqls.add(s1 + middle.substring(0, middle.length() - 1) + sn);
		
		System.out.println(s1 + middle.substring(0, middle.length() - 1) + sn
				+ "\n");
		
		conditionMap.put(colds, s1 + middle.substring(0, middle.length() - 1) + sn);

		return sqls;
	}

}
