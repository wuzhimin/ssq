package com.wzm.web.action;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wzm.invoker.HttpinvokeInterface;
import com.wzm.util.BeanUtil;

/**
 * 生成验证码Action
 * @author wzm
 *
 */

@ParentPackage(value = "struts-default")

@Action(value = "generateValidateCode", results = {
		@Result(name = "success", type="stream", params={"contentType","image/jpeg","inputName","inputStream"})
})
		
public class ValidateCodeAction {
	
	private ByteArrayInputStream inputStream;
	
	private String clientValidateCode;
	
	/**
	 * @return inputStream
	 */
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream 設定ByteArrayInputStream
	 */
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	public String execute() {
		try {
			this.setInputStream(generateImage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return com.opensymphony.xwork2.Action.SUCCESS;
	}
	
	/**
	 * 生成图像流
	 * @return
	 * @throws IOException
	 */
	private ByteArrayInputStream generateImage() throws IOException{
		
		ServletContext sctx = ServletActionContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
		HttpinvokeInterface userService = (HttpinvokeInterface)ctx.getBean("httpinvokeService1");
		
		BufferedImage image = new BufferedImage(100,20,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 100, 20);
		drawbg(g);
		drawValidateCode(g);
		ByteArrayInputStream input = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image,"JPEG",bos);
		byte [] buf = bos.toByteArray();
		input = new ByteArrayInputStream(buf);
		return input;
	}
	
	
	/**
	 * backGround Draw (画白板)
	 */
	private void drawbg(Graphics g) {

		Random rand = new Random();
		int randx;
		int randy;
		for (int i = 0; i < rand.nextInt(100) + 500; i++) {
//			g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand
//					.nextInt(255)));
			
			g.setColor(new Color(123, 123, 123));
			
			randx = rand.nextInt(100);
			randy = rand.nextInt(20);
			g.drawLine(randx, randy, randx, randy);
		}
	}
	
	/**
	 * code draw  （在白板上画验证码）
	 */
	private void drawValidateCode(Graphics g) {
		
		String code = generateCode();
		Random rand = new Random();
		int x = 0;
		Font font = new Font("Times New Roman", Font.PLAIN, 18);
		g.setFont(font);
		for (int i = 0; i < code.length(); i++) {
			int y = 20 - rand.nextInt(4);
			g.setColor(new Color(rand.nextInt(150), rand.nextInt(150), rand
					.nextInt(150)));
			g.drawString(code.substring(i, i + 1), x, y);
			x += 20;
		}
	}
	
	@Action(value = "checkValidateCode",  
			results = {@Result( name="checkValidateCode", params={"contentType","text/html","inputName","inputStream"}, type="stream")}) 
	public String checkValidateCode() throws IOException {
		String serverCode = null;
		Map<String, Object> session = BeanUtil.getSession();
		if(null != session) {
			serverCode = session.get("validateCode").toString();
		}
		
		boolean isRight = false; 
		if(!(StringUtils.hasLength(serverCode) || StringUtils.hasLength(clientValidateCode))) {
			isRight = false;
		} else {
			isRight = serverCode.equalsIgnoreCase(clientValidateCode);
		}
		
		inputStream = new ByteArrayInputStream((isRight+"").getBytes());     
		
		return "checkValidateCode";
	}
	
	/**
	 * code generate （生成验证码）
	 */
	private String generateCode() {

		Random rand = new Random();
		StringBuffer sbr = new StringBuffer(
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
		int codeLen = 5;
		StringBuffer codeSbr = new StringBuffer();
		for (int i = 0; i < codeLen; i++) {
			int select = rand.nextInt(sbr.length());
			codeSbr.append(sbr.charAt(select));
			sbr.deleteCharAt(select);
		}
		
		// 把验证码写入session，以供验证
	    Map<String, Object> session = BeanUtil.getSession();
		if(null != session) {
			session.put("validateCode",codeSbr.toString());
		}
		
		return codeSbr.toString();
	}

	public String getClientValidateCode() {
		return clientValidateCode;
	}

	public void setClientValidateCode(String clientValidateCode) {
		this.clientValidateCode = clientValidateCode;
	}
}