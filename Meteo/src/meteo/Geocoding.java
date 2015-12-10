/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteo;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import static sun.nio.cs.Surrogate.is;

/**
 *
 * @author safial hassan
 */
public class Geocoding {
    private String localita;
    private String longitudine;
    private String latitudine;
    

    public Geocoding(String localita) throws MalformedURLException, XPathExpressionException {
            this.localita=localita;
        try {
            InputStream is ;
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address="+localita);
            URLConnection con = url.openConnection();
            is = con.getInputStream();
            
            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String line = null;
            String xml="";
             while ((line = br.readLine()) != null) {
                 xml+=line;
             }
             
             
              
              latitudine=xpathQuery("/GeocodeResponse/result/geometry/location/lat/text()",xml);
              longitudine=xpathQuery("/GeocodeResponse/result/geometry/location/lng/text()",xml); 
            
        } catch (IOException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    //"/GeocodeResponse/result/geometry/location/lat/text()"
    
        private String xpathQuery(String query, String xml){
             
        try {
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            InputSource source = new InputSource(new StringReader(xml));
            return xpath.evaluate(query, source);
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            
        } 
       
    String getLatitudine(){
       return latitudine;
    }
        
    String getLongitudine(){
        return longitudine;
    }
    @Override
         public String toString(){
            return ("\n"+localita+"\nLatitudine:"+latitudine+"\nLongitudine:"+longitudine);
        }
        
        
        
        
}//fine GeoCoding
    

