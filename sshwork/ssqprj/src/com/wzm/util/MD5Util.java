package com.wzm.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class MD5Util {
	public static String md5(String str) {
		if(!StringUtils.hasText(str)) {
			return "";
		}
		return DigestUtils.md5Hex(str);
	}
}
