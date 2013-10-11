package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 使用 JDK 自带的 MD5 算法实现摘要.
 * @author BeanSoft
 *
 */
public class MD5Bean {

	/**
	 * MD5 摘要计算.
	 * @param input
	 * @return
	 */
	public static String md5(byte[] input) {
		//1. 获取摘要算法对象
		 MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			 // 2. 获得来源数据 => byte[] "123456".getBytes()
			 // 3. 调用 digest(byte[]) ==> byte[] 摘要字节
			 byte[] result = md5.digest(input);
			 
			 StringBuffer sb = new StringBuffer();
			 
			 // 4. 把二进制转换成16进制字符串来显示
			 for(int i = 0; i < result.length; i++) {
				 String hex = (Integer.toHexString(result[i]));// byte 负数扩充为 int, 16进制大于2位, 只有最后两位有效
//				 System.out.println("hex=" + hex);
				 // 处理负数的 16 进制字符串
				 if(hex.length() > 2) {
					 hex = hex.substring(hex.length() - 2);
				 }
				 
				 // 把3前面加零, 变成 03
				 if(hex.length() < 2) {
					 hex = "0" + hex;
				 }

				 sb.append(hex.toUpperCase()); // 转成大写
			 }
			 
//			 System.out.println(sb);// 结果
			 return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	/**
	 * 对字符串使用默认编码MD5摘要.
	 * @param string
	 * @return
	 */
	public static String md5(String string) {
		return md5(string.getBytes());
	}
	
	/**
	 * 对字符串使用给定编码MD5摘要.
	 * @param string
	 * @param encoding
	 * @return
	 */
	public static String md5(String string, String encoding) {
		try {
			return md5(string.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(md5("1234".getBytes()));
		System.out.println(Integer.toBinaryString(2008));
	}

}
