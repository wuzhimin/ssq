package utils.cache;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
  
  
public class RouterPassAuth extends Authenticator {  
      
    @Override  
    public PasswordAuthentication getPasswordAuthentication() {  
        return null;
//    	return new PasswordAuthentication(adslIp.ROUTE_USER,  
//        		adslIp.ROUTE_PWD.toCharArray());  
    }  
  
}  