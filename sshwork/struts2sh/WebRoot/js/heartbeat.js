// 缓慢定时调用后台心跳Servlet, 5 分钟调用一次
if(jQuery) {
	window.setInterval(function () {
         jQuery.get("servlet/HeartBeatServlet?timestamp=" + new Date().getTime());
     }, 10000);
     // 1000 * 60 * 5);
}