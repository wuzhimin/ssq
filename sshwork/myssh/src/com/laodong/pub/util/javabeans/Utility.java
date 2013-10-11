package com.laodong.pub.util.javabeans;

import com.laodong.pub.util.base.Pager;

public class Utility {
	public static void generatePager(int size, Pager pager) {
		int length = pager.getLength();
		int pagerOffSet = pager.getPagerOffSet();
		pager.setSize(size);

		int begin = 0;
		int end = 0;
		if (size >= pagerOffSet) {
			begin = pagerOffSet;
			if (pagerOffSet + length - 1 >= size)
				end = size;
			else
				end = pagerOffSet + length - 1;
		} else {
			pagerOffSet = size - length;
			if (pagerOffSet >= 1) {
				begin = pagerOffSet;
				if (pagerOffSet + length - 1 >= size)
					end = size;
				else
					end = pagerOffSet + length - 1;
			} else {
				pagerOffSet = 1;
				begin = 0;
				end = size;
			}
		}
		pager.setPagerOffSet(pagerOffSet);
		pager.setBegin(begin);
		pager.setEnd(end);
	}
	public static String getMessage(String str){
		return str;
	}
}
