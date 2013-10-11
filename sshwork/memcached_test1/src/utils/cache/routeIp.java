package utils.cache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
  
public class routeIp {  
  
	private static final routeIp ip = new routeIp();
	
	public void routeIp(){
		
	}
	
	public static routeIp getInstance() {  
//		Authenticator.setDefault(new RouterPassAuth());  
	    return ip;  
	} 
	
	private static String getWanPacket() throws IOException {  
       URL url = new URL("http://192.168.1.1:80/userRpm/StatusRpm.htm?Connect=Á¬ ½Ó&wan=1");  
       InputStream ins = null;  
       try {  
           ins = url.openConnection().getInputStream();  
           BufferedReader reader = new BufferedReader(new InputStreamReader(ins));  
           String str;  
           StringBuffer wanPacket = new StringBuffer();  
           while ((str = reader.readLine()) != null) {  
        	   wanPacket.append(str); 
           }
           return wanPacket.toString();  
       }finally{  
           if(ins!=null){  
               ins.close();  
           }  
       }
	}
	  
    public static void main(String[] args) {  
    	routeIp ip = routeIp.getInstance();
    	try {
			System.out.println(ip.getWanPacket());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
}  