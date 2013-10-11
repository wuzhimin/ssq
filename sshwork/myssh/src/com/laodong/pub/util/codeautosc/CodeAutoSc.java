package com.laodong.pub.util.codeautosc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.StringTokenizer;
import com.doginfo.struts.obj.DoginfoObj;

public class CodeAutoSc {
	public static void main(String[] args){
		   
		
//		   code51();  //从建模工具表图生成类字段
		   getDAOCode(DoginfoObj.class);//得到类代码  临时类TempObj
//		   getPageCode("zhcxForm", "khthcsobj");//得到标准页面

//		   code3(YhHdxzBgjlObj.class, "obj", "obj");// obj1.setId(obj2.getId());//有注释，需要向页面复制GrscZsflGlObj字段
//		   code3WithoutBz(GrDakhThcsjlObj.class, "thobj", "fobj");//无注释
//		   code1(com.laodong.module.workarea.program.jyqjjfzyzcgh.shzj.meet.struts.obj.ChatUserObj.class); //  obj.setId(rs.getInt("id"));
//		   code2(JyqRyZhDqZtObj.class); // {"id","int",""+obj.getId()},
//		   code31(RsSxJlObj.class, "obj");//obj1.setName(name);
//		   code21(SfJlObj.class, "oldobj", "obj"); //更新记录 根据字段值是否变化，生成修改字串
//		   code211(SfJlObj.class, "oldobj", "obj");//备份记录 根据字段值是否变化，生成修改字串
//         code212(GrClObj.class);  //根据条件，处理显示字段内容
		   
//		   code4("jobfbobj");               //得到增加、修改的页面
//		   code41("deptJobFbForm", "jobfbobj");//得到查看的页面
//		   code5();                            //得到列表页面
//		   code6(TempObj.class, "obj", "zyjyhetongobj");//得到自动刷新控制字串

	}
	public static void getDAOCode(Class cl){
		System.out.println("----------插入到表  把对象转换成表记录----------");
		System.out.println();
		code2(cl);
		System.out.println();
		System.out.println("----------查询  把表记录转换成对象----------");
		System.out.println();
		code1(cl);
	}
	
	public static void getPageCode(String formname, String objname){
		System.out.println("***********查询列表页面代码*************");
		System.out.println();
		code5();
		System.out.println();
		System.out.println("*************增加、修改页面代码***********");
		System.out.println();
		code4(objname);
		System.out.println();
		System.out.println();
		System.out.println("*************查看页面代码***********");
		System.out.println();
		code41(formname, objname);
	}
	public static void code4(String objname){
//       try {
//    	int boo = 0;
//    	String s = new String();
//    	String name = "";
//    	String colms = "";
//		BufferedReader in = new BufferedReader(new FileReader("e:\\mysoure.txt"));
//		while((s=in.readLine())!=null){
//			int i = s.indexOf("//");
//			name = s.substring(i+2).trim();
//			
//			i = s.indexOf(" int ");
//			if(i==-1){
//				if(s.indexOf(" String ")!=-1)
//				   i=s.indexOf(" String ") + 3;
//				else
//				   i=s.indexOf(" float ") + 2;
//			}
//			int ii = s.indexOf("//");
//			colms = s.substring(i+5, ii-1).trim();
//			
//			if(boo==2)
//				boo=0;
//			if(boo==0){
//				System.out.println("<tr class=\"tab-5\">");
//			}
//			
//			System.out.print("   <td align=\"right\" class=\"tab-3\">" + name + "</td>");
//			System.out.println("  <td><html:text property=\"" + objname + "." + colms + "\"/></td>");
//			
//			if(boo==1)
//				System.out.println("</tr>");
//			boo++;
//		}
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
//	
	String str = "";
	try {
    	int boo = 0;
    	String s = new String();
    	String name = "";
    	String colms = "";
		BufferedReader in = new BufferedReader(new FileReader("e:\\mysoure.txt"));
		while((s=in.readLine())!=null){		
			int i = s.indexOf("//");
			int iii = s.indexOf(" ", i+2);
            if(iii!=-1)
            	name = s.substring(i+2, iii).trim();
            else
            	name = s.substring(i+2).trim();
			
			i = s.indexOf(" int ");
			if(i==-1){
				if(s.indexOf(" String ")!=-1)
				   i=s.indexOf(" String ") + 3;
				else
				   i=s.indexOf(" float ") + 2;
			}
			int ii = s.indexOf(";");
			colms = s.substring(i+5, ii).trim();
			
			if(boo==2)
				boo=0;
			if(boo==0){
				str += "<tr class=\"tab-5\">\n";
//				System.out.println("<tr class=\"tab-5\">");
			}
			str += "   <td align=\"right\" class=\"tab-3\">" + name + "</td>";
				str += "  <td><html:text property=\"" + objname + "." + colms + "\"/></td>\n";
//			System.out.print("   <td align=\"right\" class=\"tab-3\">" + name + "</td>");
//			System.out.println("  <td><html:text property=\"" + objname + "." + colms + "\"/></td>");
			
			if(boo==1)
				str += "</tr>\n";
//				System.out.println("</tr>");
			boo++;
		}
		if(!str.endsWith("</tr>\n")){
			int tdindex = str.lastIndexOf("<td>");
			str = str.substring(0, tdindex) + "<td colspan=\"3\">" + str.substring(tdindex + 4) + "</tr>";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
	 System.out.print(str); 
	
	}
	/**
	 * @方法名称
	 * 
	 * @param formname
	 * @param objname
	 * @业务描述
	 * 如果最后一行只有一个字段，要手工加
	 *  colspan="3"
	 *  </tr>
	 * 2006-9-10
	 */
	public static void code41(String formname, String objname){
//	       try {
//	    	int boo = 0;
//	    	String s = new String();
//	    	String name = "";
//	    	String colms = "";
//			BufferedReader in = new BufferedReader(new FileReader("e:\\mysoure.txt"));
//			while((s=in.readLine())!=null){
//				int i = s.indexOf("//");
//				name = s.substring(i+2).trim();
//				
//				i = s.indexOf(" int ");
//				if(i==-1)i=s.indexOf(" String ") + 3;
//				int ii = s.indexOf("//");
//				colms = s.substring(i+5, ii-1).trim();
//				
//				if(boo==2)
//					boo=0;
//				if(boo==0){
//					System.out.println("<tr class=\"tab-5\">");
//				}
//				
//				System.out.print("   <td align=\"right\" class=\"tab-3\">" + name + "</td>");
////				System.out.println("  <td><html:text property=\"" + objname + "." + colms + "\"/></td>");
//				System.out.println("  <td><c:out value=\"${" + formname + "." + objname + "." + colms + "}\"/></td>");
//				
//				if(boo==1)
//					System.out.println("</tr>");
//				boo++;
////				String fff = "ddd";
////				fff.lastIndexOf()
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
           String reStr = "";
	       try {
	    	int boo = 0;
	    	String s = new String();
	    	String name = "";
	    	String colms = "";
			BufferedReader in = new BufferedReader(new FileReader("e:\\mysoure.txt"));
			while((s=in.readLine())!=null){
				int i = s.indexOf("//");
				int iii = s.indexOf(" ", i+2);
	            if(iii!=-1)
	            	name = s.substring(i+2, iii).trim();
	            else
	            	name = s.substring(i+2).trim();
	            
				i = s.indexOf(" int ");
				if((i==-1)&&(s.indexOf(" String ")!=-1))
					i = s.indexOf(" String ") + 3;
				if((i==-1)&&(s.indexOf(" float ")!=-1))
					i = s.indexOf(" float ") + 2;
				
				int ii = s.indexOf(";");
				colms = s.substring(i+5, ii).trim();
				
				if(boo==2)
					boo=0;
				if(boo==0){
					reStr += "<tr class=\"tab-5\">\n";
				}
				
				reStr += "   <td align=\"right\" class=\"tab-3\">" + name + "</td>";
//				System.out.println("  <td><html:text property=\"" + objname + "." + colms + "\"/></td>");
				reStr += "  <td><c:out value=\"${" + formname + "." + objname + "." + colms + "}\"/></td>\n";
				
				if(boo==1)
					reStr += "</tr>\n";
				boo++;
//				String fff = "ddd";
//				fff.lastIndexOf()
			}
			if(!reStr.endsWith("</tr>\n")){
				int tdindex = reStr.lastIndexOf("<td>");
				reStr = reStr.substring(0, tdindex) + "<td colspan=\"3\">" + reStr.substring(tdindex + 4) + "</tr>";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.print(reStr); 
		
	       
		}
	public static void code5(){

	       try {
	    	String s = new String();
			BufferedReader in = new BufferedReader(new FileReader("e:\\mysoure.txt"));
			while((s=in.readLine())!=null){
				int i = s.indexOf("//");
				int ii = s.indexOf(" ", i+2);
                if(ii!=-1)
				    s = s.substring(i+2, ii).trim();
                else
                	s = s.substring(i+2).trim();
				
				System.out.println("    <td align=\"center\">" + s + "</td>");
				
			}
			System.out.println();
			in = new BufferedReader(new FileReader("e:\\mysoure.txt"));
			while((s=in.readLine())!=null){
				int i = s.indexOf(" int ");
				if((i==-1)&&(s.indexOf(" String ")!=-1))
					i = s.indexOf(" String ") + 3;
				if((i==-1)&&(s.indexOf(" float ")!=-1))
					i = s.indexOf(" float ") + 2;
				int ii = s.indexOf(";");
				s = s.substring(i+5, ii).trim();
				
				System.out.println("    <td><c:out value=\"${item." + s + "}\"/></td>");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
		   }
	
	
   //obj.setId(rs.getInt("id"));
   public static void code1(Class cl){
	   Field[] fis = cl.getDeclaredFields();
	   for(int i=0;i<fis.length;i++){
		   String str = "";
		   Field ff= fis[i];
		   String name = ff.getName();
		   String type = ff.getType().getName();
		   if("java.lang.String".equals(type)){
			   str = "obj.set" + name.substring(0, 1).toUpperCase() + name.substring(1) + 
			   "(rs.getString(\"" + name + "\"));";
		   }else if("int".equals(type)){
			   str = "obj.set" + name.substring(0, 1).toUpperCase() + name.substring(1) + 
			   "(rs.getInt(\"" + name + "\"));";
		   }else if("float".equals(type)){
			   str = "obj.set" + name.substring(0, 1).toUpperCase() + name.substring(1) + 
			   "(rs.getFloat(\"" + name + "\"));";
		   }
		   System.out.println(str);
	   }
   }
   // {"id","int",""+obj.getId()},
   public static void code2(Class cl){
	   Field[] fis = cl.getDeclaredFields();
	   for(int i=0;i<fis.length;i++){
		   String str = "";
		   Field ff= fis[i];
		   String name = ff.getName();
		   String type = ff.getType().getName();
		   if("java.lang.String".equals(type)){
			   str = "{\"" + name + "\",\"String\",obj.get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()},";
		   }else if("int".equals(type)||"float".equals(type)){
			   str = "{\"" + name + "\",\"int\",\"\"+obj.get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()},";
		   }
		   System.out.println(str);
	   }
   }
   /**
    * 方法名称 根据字段值是否变化，生成修改字串
    * 业务描述
    * 更新记录
    * oldojb 旧对象名称
    * newobj 新对象名称
    * 2007-3-13 16:06:30
    */
   public static void code21(Class cl, String oldojb, String newobj){
	    //代码应用框架
	    ArrayList list = new ArrayList();
        //此处填写自动生成代码
		String[][] attrs = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			attrs[i] = (String[]) list.get(i);
		}

		if (attrs.length > 0) {
			// 更新
		}

	   Field[] fis = cl.getDeclaredFields();
	   for(int i=0;i<fis.length;i++){
		   String str = "";
		   Field ff= fis[i];
		   String name = ff.getName();
		   String type = ff.getType().getName();
		   String objagetName = oldojb + ".get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()";
		   String objbgetName = newobj + ".get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()";
		   if("java.lang.String".equals(type)){
			   str = "if((" + objagetName + "==null&&" + objbgetName + "!=null)||(" + objagetName + "!=null&&!" + objagetName + ".equals(" + objbgetName + "))){\n";
			   str += "String[] a = ";
			   str += "{\"" + name + "\",\"String\"," + objbgetName + "};\n";
		   }else if("int".equals(type)||"float".equals(type)){
			   str = "if(" + objagetName + "!=" + objbgetName + "){\n";
			   str += "String[] a = ";
			   str += "{\"" + name + "\",\"int\",\"\"+" + objbgetName + "};\n";   
		   }
		   str += "list.add(a);\n";
		   str += "}";
		   System.out.println(str);
	   }
   }
   /**
    * 方法名称 根据字段值是否变化，生成修改字串
    * 业务描述
    * 备份记录
    * oldojb 原对象名称
    * newobj 新对象名称
    * 2007-3-13 16:06:30
    */
   public static void code211(Class cl, String oldojb, String newobj){
	    //代码应用框架
	    ArrayList list = new ArrayList();
       //此处填写自动生成代码
		String[][] attrs = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			attrs[i] = (String[]) list.get(i);
		}

		if (attrs.length > 0) {
			// 修改
		}

	   Field[] fis = cl.getDeclaredFields();
	   for(int i=0;i<fis.length;i++){
		   String str = "";
		   Field ff= fis[i];
		   String name = ff.getName();
		   String type = ff.getType().getName();
		   String objagetName = oldojb + ".get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()";
		   String objbgetName = newobj + ".get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()";
		   if("java.lang.String".equals(type)){
			   str = "if((" + objagetName + "==null&&" + objbgetName + "!=null)||(" + objagetName + "!=null&&!" + objagetName + ".equals(" + objbgetName + "))){\n";
			   str += "String[] a = ";
			   str += "{\"" + name + "\",\"String\"," + objagetName + "};\n";
			   str += "list.add(a);\n";
			   str += "}";
			   str += "else{\n";
			   str += "String[] a = ";
			   str += "{\"" + name + "\",\"String\",\"-11\"};\n";
			   str += "list.add(a);\n";
			   str += "}";
		   }else if("int".equals(type)||"float".equals(type)){
			   str = "if(" + objagetName + "!=" + objbgetName + "){\n";
			   str += "String[] a = ";
			   str += "{\"" + name + "\",\"int\",\"\"+" + objagetName + "};\n";
			   str += "list.add(a);\n";
			   str += "}";
			   str += "else{\n";
			   str += "String[] a = ";
			   str += "{\"" + name + "\",\"int\",\"-11\"};\n";
			   str += "list.add(a);\n";
			   str += "}";
		   }
		   System.out.println(str);
		   System.out.println();
	   }
  }
   /**
    * 方法名称 根据条件，处理显示字段内容
    * 业务描述
    * 更新记录
    * oldojb 旧对象名称
    * newobj 新对象名称
    * 2007-3-13 16:06:30
    */
   public static void code212(Class cl){
	   Field[] fis = cl.getDeclaredFields();
	   String zdstr = "";
	   for(int i=0;i<fis.length;i++){
		   Field ff= fis[i];
		   String name = ff.getName();
		   String type = ff.getType().getName();
		   if("java.lang.String".equals(type))
			   zdstr += "private String " + name + "_show;\n";
		   else
			   zdstr += "private int " + name + "_show;\n";
	   }
	   
	   System.out.println(zdstr);
	   for(int i=0;i<fis.length;i++){
		   String str = "";
		   Field ff= fis[i];
		   String name = ff.getName();
		   String type = ff.getType().getName();
		   if("java.lang.String".equals(type)){
			   str = "if(!\"-11\".equals(" + name + "))\n";
			   str += "" + name + "_show = " + name + ";\n";
		   }else if("int".equals(type)||"float".equals(type)){
			   str = "if(" + name + "!=-11)\n";
			   str += "" + name + "_show = " + name + ";\n";
		   }
		   System.out.println(str);
	   }
   }
   /**
    * 
    * @名称 
    * @描述 
    * @日期 2006-6-25
    * @时间 0:41:37
    * @param cl
    * @param objname 类名
    * @param propertyqzname　页面前缘名字
    */
   public static void code6(Class cl, String objname, String propertyqzname){
	   Field[] fis = cl.getDeclaredFields();
	   String namestr = "";
	   String valuestr = "";
	   for(int i=0;i<fis.length;i++){
		   
		   Field ff= fis[i];
		   String name = ff.getName();
           namestr += "-NLLD27-" + propertyqzname + "." + name;
           valuestr += " + \"-NLLD27-\"" + " + " + objname + ".get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()";
	   }
	   String str = "String autoFreshStr = " + "\"" + namestr.substring(8) + "\" + "+ "\"-NLLD76-\" + " + valuestr.substring(16) + ";";
	   System.out.println(str);
   }
   // obj1.setId(obj2.getId());
   public static void code3(Class cl, String obj1, String obj2) {
	   ArrayList list = new ArrayList();
		try {
			String s = new String();
			BufferedReader in = new BufferedReader(new FileReader(
					"e:\\mysoure.txt"));
			while ((s = in.readLine()) != null) {
				int i = s.indexOf("//");
//				//去掉备注
//				int ii = s.indexOf(" ", i + 2);
//				if (ii != -1)
//					s = s.substring(i + 2, ii).trim();
//				else
					s = s.substring(i + 2).trim();
                list.add(s);
//				System.out.println("    <td align=\"center\">" + s + "</td>");

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Field[] fis = cl.getDeclaredFields();
		for (int i = 0; i < fis.length; i++) {
			String str = "";
			Field ff = fis[i];
			String name = ff.getName();

			str = obj1 + ".set" + name.substring(0, 1).toUpperCase()
					+ name.substring(1) + "(" + obj2 + ".get"
					+ name.substring(0, 1).toUpperCase() + name.substring(1)
					+ "());";
            str += "    //" + list.get(i);
			System.out.println(str);
		}
	}
   public static void code3WithoutBz(Class cl, String obj1, String obj2) {
		Field[] fis = cl.getDeclaredFields();
		for (int i = 0; i < fis.length; i++) {
			String str = "";
			Field ff = fis[i];
			String name = ff.getName();

			str = obj1 + ".set" + name.substring(0, 1).toUpperCase()
					+ name.substring(1) + "(" + obj2 + ".get"
					+ name.substring(0, 1).toUpperCase() + name.substring(1)
					+ "());";
			System.out.println(str);
		}
	}
   public static void code31(Class cl, String obj1){
	   Field[] fis = cl.getDeclaredFields();
	   for(int i=0;i<fis.length;i++){
		   String str = "";
		   Field ff= fis[i];
		   String name = ff.getName();
		 
			   str = obj1 + ".set" + name.substring(0, 1).toUpperCase() + name.substring(1) + 
			   "(" + name + ");";
		   
		   System.out.println(str);
	   }
   }
	public static void code51(){
	       try {
	    	String s = new String();
	    	String fengefu = "	";
			BufferedReader in = new BufferedReader(new FileReader("e:\\mysoure.txt"));
			String str = "";
			boolean sfsh = true;
			while((s=in.readLine())!=null){
				if(sfsh){
					sfsh = false;
					continue;
				}
				StringTokenizer st = new StringTokenizer(s, fengefu);
				int eleindex = 0;
				String[] elesz = new String[7];
				while(st.hasMoreTokens()){
					elesz[eleindex] = st.nextToken();
					eleindex ++;
				}
				if(eleindex==7){
					str += "private " + fenxiDataType(elesz[3]) + " " + elesz[1].toLowerCase() + ";" + "  //" + elesz[0] + "    " + elesz[2] + "\n";
				}
				else
				{
					str += "private " + fenxiDataType(elesz[2]) + " " + elesz[1].toLowerCase() + ";" + "  //" + elesz[0] + "\n";
				}
			}
			System.out.print(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public static String fenxiDataType(String str){
			if(str.indexOf("int")!=-1)
					return "int";
			else if(str.indexOf("float")!=-1)
				return "float";
			else if(str.indexOf("varchar")!=-1)
				return "String";
			return "";
		}
          
		
}
