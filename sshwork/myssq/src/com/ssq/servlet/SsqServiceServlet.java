package com.ssq.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.sae.memcached.SaeMemcache;
import com.ssq.control.DataFactory;
import com.ssq.util.SsqUtils;

public class SsqServiceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SsqServiceServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		deal(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		deal(request, response);
	}

	private void deal(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String command = request.getParameter("command");
		
		if(command !=  null  && command.equals("begin")) {
			response.getOutputStream().println("command:"+command);
			new BeginThread().start();
			
//			SaeMemcache mc=new SaeMemcache();
//			mc.init();
//			
//			for(int i=0;i<100000;i++) {
//				mc.set(i+"", i+"");
//			}
//			
//			response.getOutputStream().println("1:"+mc.get("1"));
//			response.getOutputStream().println("2:"+mc.get("2"));
//			response.getOutputStream().println("3:"+mc.get("3"));
//			response.getOutputStream().println("4:"+mc.get("4"));
			
		} else if(command !=  null  && command.equals("get")) {
			String key = request.getParameter("key");
			
			SaeMemcache mc=new SaeMemcache();
			mc.init();
			response.getOutputStream().println(key+":"+mc.get(key));
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
	}
	
	private class BeginThread extends Thread {
		
		private DataFactory dataFactory = null;
		
		public BeginThread() {
			dataFactory = new DataFactory();
		}

		public void run () {
			SaeMemcache mc=new SaeMemcache();
			mc.init();
			
			while(true) {
				List<String> dd = SsqUtils.genRandomBets(5);
				
				// 保存数据
				dataFactory.writeDataToCache(dd, mc);
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
