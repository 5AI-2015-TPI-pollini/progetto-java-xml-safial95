package meteo;


import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.UnknownHostException;


public class Connection {

    public Connection() {
    }
    
    
    
    public void setProxy(String ip , String port , final String user , final String pw){
        System.setProperty("proxySet", "true");
        System.setProperty("http.proxyHost", ip);
        System.setProperty("http.proxyPort", port);
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,pw.toCharArray());
            }
        }); 
    }
}