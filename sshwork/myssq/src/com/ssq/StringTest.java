package com.ssq;

import java.io.UnsupportedEncodingException;

public class StringTest {
	public static void main(String[] args) {
		System.out.println(new String("asb").getBytes().length);
		try {
			System.out.println(new String("asbÖÐ¹ý").getBytes("UTF-8").length);
			System.out.println(new String("asb").getBytes("UTF-16").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
