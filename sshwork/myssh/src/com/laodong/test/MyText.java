package com.laodong.test;

import java.util.HashMap;

public class MyText {
   public static void main(String[] args){
	   MyEquals m = new MyEquals();
	   m.a = 2;
	   m.b = 8;
	   MyEquals m1 = new MyEquals();
	   m1.a = 2;
	   m1.b = 8;
	   if(m.equals(m1))
		   System.out.println("=");
	   else
		   System.out.println("!=");
	   HashMap map = new HashMap();
	   map.put(m,m);
	   map.put(m1,m1);
	   System.out.println(map);
	   System.out.println(map.size());
   }
}

