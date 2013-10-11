package com.wzm.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Floder
 * @version 0.1 2008-01-30
 * @此类是产生投注工具类
 */
public class CombinationUtil {
	
	/**
	 * 此方法是从一组(source.length个)对象中任意取出n个元素进行组合，产生组合列表的方法，
	 * 如我要1，2，3，4，5，6，7，8，9中取6个数产生组合，看看有多少种组合(不是排列)
	 * @param source  对象数组
	 * @param n  取几个元素进行组合，必须小于source的长度，大于1。
	 * @param dyn  动态数，没迭代一次减1，开始时与n相等
	 * @param result java.util.List<String> 结果集
	 */
	public static void createCombo(Object[]source, int n,int dyn,List<String> result){
		if(source.length==2) {
			if(dyn==2) {
				String str = source[0]+","+source[1];
				result.add(str);
			} else {
				
			}
		} else {
			if(dyn==2 ) { //&& source.length>=n) {
				if(dyn==n) {
					
					for(int i=0;i<source.length-1;i++) {
						for(int j=i+1;j<source.length;j++) {
							StringBuffer sb = new StringBuffer();
							sb.append(source[i]+","+source[j]);
							sb.append(",");
							sb.append(source[j]);
							result.add(sb.toString());
						}
					}
				} else {
					for(int j=1;j<source.length;j++) {
						String str = source[0]+","+source[j];
						result.add(str);
					}
				}
			} else {
				if(dyn>n-1) {
					for(int i=0;i<source.length-n+1;i++) {
						for(int j=i+1;j<source.length;j++) {
							String str = source[i]+","+source[j];
							Object[] oo = new Object[source.length-j];
							oo[0] = str;
							for(int x=j+1,index=1;x<source.length;x++,index++) {
								oo[index] = source[x];
							}
							createCombo(oo, n,dyn-1,result);
						}
					}
				}
				else {
					for(int j=1;j<source.length;j++) {
						String str = source[0]+","+source[j];
						Object[] oo = new Object[source.length-j];
						oo[0] = str;
						for(int x=j+1,index=1;x<source.length;x++,index++) {
							oo[index] = source[x];
						}
						createCombo(oo, n,dyn-1,result);
					}
				}	
			}
		}
	}
	
	public static String sortStr(String str) {
		String[] tmp = str.split(",");
		List<Integer> intTmp = new ArrayList<Integer>();
		for(int i=0;i<tmp.length;i++) {
			intTmp.add(new Integer(Integer.parseInt(tmp[i])));
		}
		Collections.sort(intTmp);
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<intTmp.size();i++){
			sb.append(  (((Integer)intTmp.get(i)).intValue()<10?"0"+intTmp.get(i):intTmp.get(i))+",");
		}
		return sb.toString().substring(0,sb.toString().length()-1);
	}
	
//	public static void main(String[] args) {
//		Object[] m = {1,2,3,4,5,6,7,8,"ddd"}; 
//		
//		List dd = new ArrayList();
//		long t1 = System.currentTimeMillis();
//		CombinationUtil.createCombo(m,6,6,dd);
//		long t2 = System.currentTimeMillis();
//		System.out.println("时间:"+(t2-t1));
//		System.out.println("长度: "+dd.size());
//	}
}
