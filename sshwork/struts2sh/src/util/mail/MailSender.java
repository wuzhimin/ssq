package util.mail;

/*
 * @(#)MailSender.java 1.00 2004-8-3
 *
 * Copyright 2004 BeanSoft Studio. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * MailSender, 支持基于 SMTP 验证的文本格式邮件发送.
 * 
 * @author BeanSoft
 * @version 1.1 2006-8-1
 */
public class MailSender {
	
    /** 发信人 */
    private String from;
    /** 收信人 */
    private String to;
    /** 主题 */
    private String subject;
    /** 正文 */
    private String body;
    
    private boolean htmlFormat;

    private static Properties props = new Properties();
    static {
        try {
            InputStream in = MailSender.class
                    .getResourceAsStream("MailSender.ini");
            props.load(in);
            in.close();
        } catch (Exception ex) {
            System.err.println("无法加载配置文件 MailSender.ini:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public MailSender() {
    }
    
    /** 
     * 发送邮件.
     * @return boolean - 发送结果 
     */
    public boolean sendMail() {
        if (getBody() == null || getTo() == null || getFrom() == null
                || getSubject() == null) { return false; }
        //--[ Obtain a session
        try {
            //--[ Set up the default parameters
            //        Properties props = new Properties();
            //        props.put("mail.transport.protocol", "smtp" );
            //        props.put("mail.smtp.host", smtpServer );
            //        props.put("mail.smtp.port", "25" );

            //--[ Create the session and create a new mail message
    		java.util.Properties propsSmtp = new java.util.Properties();
    		propsSmtp.put("mail.smtp.auth", "true");
    		propsSmtp.put("mail.smtp.host", props.get("mail.smtp.host"));
//    		propsSmtp.put("mail.debug", "true");
    		
            Session mailSession = Session.getDefaultInstance(propsSmtp);
            Message msg = new MimeMessage(mailSession);

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress(getFrom()));// 设置发件人信息
            msg.addRecipients(Message.RecipientType.TO, InternetAddress
                    .parse(getTo()));// 设置收件人信息, 可以多次添加收件人
            msg.setSentDate(new Date());// 设置发送日期
            msg.setSubject(getSubject());// 设置主题

            //--[ Create the body of the mail
            if(isHtmlFormat()) {
            	msg.setContent(getBody(),
        		"text/html;charset=GBK");// HTML 邮件
            } else  {
            	msg.setText(getBody());// 设置邮件的正文
            }
           

            Transport transport = mailSession.getTransport("smtp");// 处理连接和发邮件工作
            transport.connect( propsSmtp.getProperty("mail.smtp.host"), props.getProperty("username"), 
            		props.getProperty("password"));// connect方法打开连接, SMTP, 发件人帐号, 密码

            transport.sendMessage(msg, msg.getAllRecipients());// send 发邮件
            
            transport.close();// 关闭连接
        } catch (Exception e) {
            System.out.println(e);
            //            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @return Returns the body.
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     *            The body to set.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return Returns the from.
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from
     *            The from to set.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            The subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return Returns the to.
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to
     *            The to to set.
     */
    public void setTo(String to) {
        this.to = to;
    }

    public static void main(String[] args) {
        MailSender sender = new MailSender();

        sender.setFrom("\"Admin\" <admin@earth.org>");
        sender.setTo("beansoft@earth.org");
        sender.setSubject("取回密码");
        sender.setBody("您的新密码是123456!");

        System.out.println(sender.sendMail());
    }

	public boolean isHtmlFormat() {
		return htmlFormat;
	}

	public void setHtmlFormat(boolean htmlFormat) {
		this.htmlFormat = htmlFormat;
	}
}
