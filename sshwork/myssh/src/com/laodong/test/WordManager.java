package com.laodong.test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class WordManager {
	public static void mainn(String[] args) {

		ActiveXComponent app = new ActiveXComponent("Word.Application"); //启动word
		String inFile = "F:\\test.doc"; //要替换的word文件
		boolean flag = false;
		try {
		app.setProperty("Visible", new Variant(false)); //设置word不可见
		Dispatch docs = app.getProperty("Documents").toDispatch();
		
		Dispatch doc = Dispatch.invoke(
				docs, 
				"Open", 
				Dispatch.Method,
		        new Object[] {inFile, new Variant(false), new Variant(false)},
		        new int[1]
		        ).toDispatch(); //打开word文件，注意这里第三个参数要设为false，这个参数表示是否以只读方式打开，因为我们要保存原文件，所以以可写方式打开。
		System.out.println("----222");
		Dispatch content = Dispatch.get(doc, "Content").toDispatch(); //提取word文档内容对象
		Dispatch finder = Dispatch.get(content, "Find").toDispatch(); //提取find对象，也就查找替换的那个对象
		Variant f = new Variant(false);

		boolean rt = true;
		while (rt) {
		rt = Dispatch.invoke(finder, "Execute", Dispatch.Method,
		new Object[] {"ww", f, f, f, f, f, f, f, f, "我68的",  //前面是要被替换掉的字串，后面是新字串
		new Variant(true)}
		, new int[1]).toBoolean(); //替换Old ---> New
		}

		Dispatch.call(doc, "Save"); //保存
		Dispatch.call(doc, "Close", f);
		flag = true;
		System.out.println("is over");
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		finally {
		app.invoke("Quit", new Variant[] {});
		}
		}
	public static void main(String[] args) {
		ActiveXComponent app = null;
		try{
		String bookMarkKey = "LB_KJGG";
		String inFile = "F:\\test.doc"; //要替换的word文件
		app = new ActiveXComponent("Word.Application");
		Dispatch docs = app.getProperty("Documents").toDispatch();
		System.out.println("----111");
		
		Dispatch doc = Dispatch.invoke(
		docs,
		"Open",
		Dispatch.Method,
		new Object[] { inFile, new Variant(false), new Variant(false) }, 
		new int[1]
		).toDispatch(); // 打开word文件
		System.out.println("----222");
		Dispatch activeDocument = app.getProperty("ActiveDocument")
		.toDispatch();
		System.out.println("activedocument");
		   
		Dispatch bookMarks = app.call(activeDocument, "Bookmarks")
		.toDispatch();



		boolean bookMarkExist1 = Dispatch.call(bookMarks, "Exists",
		bookMarkKey).toBoolean();

		if (bookMarkExist1 == true) {
		System.out.println("exists bookmark!");
		Dispatch rangeItem = Dispatch.call(bookMarks, "Item",
		bookMarkKey).toDispatch();
		System.out.println("range item!");
		Dispatch range = Dispatch.call(rangeItem, "Range").toDispatch();
		System.out.println("range !");
//		 取标签的值
		String bookMarkValue = Dispatch.get(range, "Text").toString();
		bookMarkValue = "test76";
		if (bookMarkValue != null) {
		Dispatch.put(range, "Text", new Variant(bookMarkValue));
		}

		} else {
		System.out.println("not exists bookmark!");
		}
		Dispatch.call(doc, "Save"); //保存
		Variant f = new Variant(false);
		Dispatch.call(doc, "Close", f);
		/**
		 * 如果不关闭就会出错
		 */
		boolean flag = true;
		System.out.println("is over");
//		 保存文件
//		Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
//				inFile, new Variant(0) }, new int[1]);
		}
		catch (Exception e) {
			System.out.println("----enter Exception");
			e.printStackTrace();
			}
			finally {
				System.out.println("---enter finally");
				/**
				 * 必须在这里捕获未正常关闭时的异常，以便于释放文件，否则会发生死锁现象
				 */
			app.invoke("Quit", new Variant[] {});
			}

	}

}

