package com.ssq.control;

import java.util.List;

import com.sina.sae.memcached.SaeMemcache;

public class DataFactory {

	public void writeDataToCache(List<String> dataList, SaeMemcache mc) {
		for(String str:dataList) {
			mc.set(str, str);
		}

	}

}
