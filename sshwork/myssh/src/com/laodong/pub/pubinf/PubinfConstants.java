package com.laodong.pub.pubinf;

public class PubinfConstants {
	/*****直接放在session的用户信息******/
	
	/**用户登录名：userid*/
	public final static String userLonginName = "userid";
	
	/** 用户信息 com.laodong.pub.login.pubobj.UserInfoObj */
	public final static String userinfoobj = "userinfoobj";
	
	/**用户所在建筑编号*/
	public final static String userInjzdeptid = "jzdeptid";
	
	/**用户所在主题对象*/
	public final static String YHSZ_ZT_DX = "YHSZ_ZT_DX";
	
	/**用户所在主题对象 默认编号*/
	public final static int YHSZ_ZT_DX_MRBH = 14;
	
	/**用户所在主题对象 默认名称*/
	public final static String YHSZ_ZT_DX_MRMC = "java";
	
	/**用户所在主题对象 默认层次字串*/
	public final static String YHSZ_ZT_DX_MRCCZC = "00000501";
	
	/**交易区管理中心 层次字串*/
	public final static String 	JYQGLZX_CCZC = "000005";
	
	/**司法部所在建筑编号*/
	public final static int sfbInjzdeptid = 26;
	
	/**人事小组所在建筑编号*/
	public final static int rsbInjzdeptid = 24;
	
	/**人事部所用聊天室编号放在session中的参数名称*/
	public final static String rsbInChatId = "rsbInChatId";
	
	/**专家管理模块所用工作聊天室编号放在session中的参数名称*/
	public final static String ZjMkChatId = "zjMkChatId";

	/**自然移动：在主建筑上空间，移动有效*/
	public final static int At_ZhuJZ_Move_Vali = 1;
	
	/**无效移动：移动到其它子建筑上（非子建筑大门上），移动无效*/
	public final static int Move_To_ZiJZ_NoVali = 2;
	
	/**进入大门：移动到其它子建筑大门上，移动有效*/
	public final static int Move_To_ZiJZDoor_Vali = 3;
	
	/**移动超界：移动超出主坐标系（非从主建筑大门开始移动），移动无效*/
	public final static int Move_To_WaiJZ_NoVali = 4;
	
	/**出大门：从主建筑大门上移动超出主坐标系，移动有效*/
	public final static int Move_To_WaiJZDoor_Vali = 5;
	
	/**x++ 向右移动*/
	public final static int rightmove = 1;
	/**x-- 向左移动*/
	public final static int letfmove = 2;
	/**y++ 向上移动*/
	public final static int upmove = 3;
	/**y-- 向下移动*/
	public final static int downmove = 4;
	
	/**颗粒度纬度*/
	public final static int keliduWeidu = 1;
	/**类别纬度*/
	public final static int sortWeidu = 2;
	/**时间纬度*/
	public final static int timeWeidu = 3;
	/**备忘录表类别*/
	public final static int memoTable_Type = 1;
	/**主题表类别*/
	public final static int zhuTiTable_Type = 2;
	
	/**提示信息*/
	public final static String MY_MSG = "mymessage";
	/**系统管理员*/
	public final static String ADMINISTRATOR = "Administrator";
	/**是否必须由人事部任免的最低行政级别  （目前取常务付组长行政级别）*/
	public final static int RSB_RM_MINJBZ = 7;
}
