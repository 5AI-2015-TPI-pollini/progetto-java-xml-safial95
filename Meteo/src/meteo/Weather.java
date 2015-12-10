/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;

/**
 *
 * @author safial hassan
 */

//  http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=
public class Weather {
    private String tempo;
    
    Weather (String longitudine,String latitudine){
            
        try {
            InputStream is ;
            URL url = new URL("http://api.wunderground.com/api/2fe535a12fd3638f/forecast/conditions/q/"+latitudine+","+longitudine+".xml");
            URLConnection con = url.openConnection();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            String xml="";
            while ((line = br.readLine()) != null) {
                xml+=line;
            }
            
            tempo=xpathQuery("/response/forecast/txt_forecast/forecastdays/forecastday[1]/fcttext_metric/text()", xml);
            //System.out.println(tempo);
        } catch (IOException ex) {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
           // System.err.println("errore");
        }
        
             
             
    }
    
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
       String getTempo(){
           return tempo;
       }
}
