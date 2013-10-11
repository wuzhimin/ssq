package com.laodong.test;

public class MyEquals {
  int a;
  int b;
  public boolean equals(MyEquals o){
	  int c = a + b;
	  int cc = o.a + o.b;
	  if(c==cc)
		  return true;
	  else
		  return false;
  }
  public int hashCode(){
	  return a+b;
  }
}

