package com.laodong.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @类名称
 * @业务描述
 * 
 * @author lhh
 * @时间 2008-5-1713:35:05
 */
public class Gupiao {
	int cwss = 3;//仓位手数
	int cwgs = 6;//仓位个数
	int maxcwcbj = 535;//最低仓位成本价 单位：分
	int cwjl = 15;//仓位距离 单位：分
	int ylmbz = 30;//赢利目标值 单位：分
    //仓位定义 3个月3手收益 1325 交易64次
//	public double cw[][] = { { 6.6, 6.8 }, { 6.4, 6.6 }, { 6.2, 6.4 },
//			{ 6, 6.2 }, { 5.8, 6 }, { 5.6, 5.8 }};
	// 3个月3手收益876 交易34次
//	public double cw[][] = { { 6.75, 7 }, { 6.50, 6.75 }, { 6.25, 6.50 },
//			{ 6, 6.25 }, { 5.75, 6 }, { 5.5, 5.75 }};
	public int cw[][] = new int[cwgs][2];
	public int cs[] = new int[cwgs];
	int js=0;
	int ljsy = 0;// 累计收益
	public void initcw(){
		for(int i=0;i<cw.length;i++){
			cw[i][0] = maxcwcbj;
			cw[i][1] = maxcwcbj + ylmbz;
			maxcwcbj += cwjl;
		}
	}
	public void js() {
		initcw();
		System.out.print("仓位：");
		for (int i = 0, len = cw.length; i < len; i++)
			System.out.print(" [" + cw[i][0] + ", " + cw[i][1] + "]");
		System.out.println();
		System.out.println("---------------");
//		double jg[][] = { { 6.2, 5.9 }, { 6.1, 6.4 }, { 6.1, 6.4 },
//				{ 6.2, 6.4 }, { 6.4, 6.2 }, { 6.2, 5.9 }, { 6.1, 6.2 },
//				{ 6.3, 6.1 }, { 6.2, 5.75 }, { 5.5, 5.9 }, { 5.9, 5.75 },
//				{ 5.7, 5.9 }, { 5.6, 5.9 }, { 6.2, 5.8 }, { 5.8, 6.1 },
//				{ 6.1, 6.4 }, { 6.4, 6.9 }, { 6.7, 6.5 }, { 6.3, 6.5 },
//				{ 6.3, 6.5 } };
		int jg[][] = getsj();
		int ljcs = 0;// 累计仓位数量（即股票数）
		for (int g = 0, glen = cs.length; g < glen; g++)
			ljcs += cs[g];
		double ysgpsz = ljcs * jg[0][0];
		int klen = cw.length;
		for (int i = 0, len = jg.length; i < len; i++) {
			ljcs = 0;// 累计仓位数量（即股票数）
			for (int g = 0, glen = cs.length; g < glen; g++)
				ljcs += cs[g];

			double min = 0;
			double max = 0;
			boolean sfyx = false;// 是否阳线
			if (jg[i][0] <= jg[i][1]) {
				sfyx = true;
				min = jg[i][0];
				max = jg[i][1];
			} else {
				min = jg[i][1];
				max = jg[i][0];
			}
			if (sfyx) {
				for (int k = 0; k < klen; k++) {
					if (min <= cw[k][0]) {
						if (cs[k] == 0) {
							cs[k] = 1;
							dy(cw[k][0], i, jg[i][0], jg[i][1], cs, 0);
							// System.out.println("买入价格：" + cw[k][0] + " 编号：" +
							// (i+1) + " 开盘：" + jg[i][0] + " 收盘：" + jg[i][1]);
						}
					}
				}
				for (int k = 0; k < klen; k++) {
					if (max >= cw[k][1]) {
						if (cs[k] == 1 && ljcs > 0) {
							ljcs--;// 判断是否有进行T+0交易的筹码
							cs[k] = 0;
							dy(cw[k][1], i, jg[i][0], jg[i][1], cs, 1);
							// System.out.println("卖出价格：" + cw[k][1] + " 编号：" +
							// (i+1));
						}
					}
				}
			} else {
				for (int k = 0; k < klen; k++) {
					if (max >= cw[k][1]) {
						if (cs[k] == 1 && ljcs > 0) {
							ljcs--;
							cs[k] = 0;
							dy(cw[k][1], i, jg[i][0], jg[i][1], cs, 1);
							// System.out.println("卖出价格：" + cw[k][1] + " 编号：" +
							// (i+1));
						}
					}
				}
				for (int k = 0; k < klen; k++) {
					if (min <= cw[k][0]) {
						if (cs[k] == 0) {
							cs[k] = 1;
							dy(cw[k][0], i, jg[i][0], jg[i][1], cs, 0);
							// System.out.println("买入价格：" + cw[k][0] + " 编号：" +
							// (i+1));
						}
					}
				}
			}
		}
		ljcs = 0;
		double zhgpsz = 0;
		for (int g = 0, glen = cs.length; g < glen; g++){
			if(cs[g]>0){
				zhgpsz += cs[g]*cw[g][0];
			}
		}
		ljsy -= ysgpsz;
		ljsy += zhgpsz;
		System.out.println("总收益：" + ljsy * cwss);
	}

	public void dy(double a1, int a2, double a3, double a4, int a[], int dklx) {
		js++;
		if (dklx == 0) {
			ljsy -= a1;
			ljsy -= a1*0.004;
			System.out.print("交易次数：" + js + " 日期编号：" + (a2 + 1) + "   买入仓位：" + a1 + "   开盘：" + a3
					+ " 收盘：" + a4 + "   现金收支：" + ljsy * cwss * 100);
		} else {
			ljsy += a1;
			ljsy -= a1*0.004;
			System.out.print("交易次数：" + js + " 日期编号：" + (a2 + 1) + "   卖出仓位：" + a1 + " 开盘：" + a3
					+ " 收盘：" + a4 + " 现金收支：" + ljsy * cwss * 100);
		}
		System.out.print("   仓位：");
		for (int i = 0, len = a.length; i < len; i++)
			System.out.print(a[i] + ", ");
		System.out.println();
	}

	public static int[][] getsj() {
		try {
			String s = new String();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			BufferedReader in = new BufferedReader(new FileReader(
					"e:\\hqsj\\gpzgzdsj.txt"));
			while ((s = in.readLine()) != null) {
				s = s.replaceAll(" ", "　");
				String[] aat = s.trim().split("　");
				// 第1位是日期，第2位开盘价，第3位收盘价，第4位最高价，第5位最低价
				if (Double.parseDouble(aat[4].trim()) <= Double
						.parseDouble(aat[6].trim())) {
					list1.add(aat[14].trim());
					list2.add(aat[12].trim());
				} else {
					list1.add(aat[12].trim());
					list2.add(aat[14].trim());
				}
			}
			int a[][] = new int[list1.size()][2];
			for (int i = 0, len = list1.size(); i < len; i++) {
				a[i][0] = (int)(Double.parseDouble((String) list1.get(i))*100);
				a[i][1] = (int)(Double.parseDouble((String) list2.get(i))*100);
			}
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Gupiao g = new Gupiao();
		g.js();
	}
}
