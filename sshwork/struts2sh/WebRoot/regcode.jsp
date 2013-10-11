<%@ page language="java" contentType="image/png"
	import="java.util.*,java.awt.*,java.awt.image.*" pageEncoding="UTF-8"%>
<%!Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>


<%
	out.clear();	
	//response.reset();// 清空以前缓冲区
	//生成随机类
	Random random = new Random();
	BufferedImage img = new BufferedImage(100, 20,
			BufferedImage.TYPE_INT_RGB);// 创建彩色缓冲图

	Graphics g = img.getGraphics();// 画笔对象

	// 填充白色的背景
	g.setColor(Color.white);
	g.fillRect(0, 0, 100, 20);

	//随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(100);
		int y = random.nextInt(20);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x, y, x + xl, y + yl);
	}

	// 绘制红色文字
	g.setColor(Color.red);

	// 生成随机数字验证码并存入session, 然后输出到图片中
	Random rand = new Random();
	String code = rand.nextInt(10000000) + "";

	session.setAttribute("regcode", code);

	//设定字体
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

	g.drawString(code, 2, 16);

	g.dispose();// 关闭对象, 释放内存, 刷新到图形对象

	javax.imageio.ImageIO.write(img, "png", response.getOutputStream());// 把内存的图片编码到输出流, 参数依次为: 图片对象, 格式(png,jpg), 输出流
	
%>