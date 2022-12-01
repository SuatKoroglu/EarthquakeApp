package com.example.application.data.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import org.apache.http.protocol.ResponseContent;
import org.json.JSONArray;  
import org.json.JSONObject;  

import org.springframework.stereotype.Service;

import com.example.application.data.entity.Earthquake;
import com.vaadin.flow.component.template.internal.ParserData;

import elemental.json.JsonObject;

@SuppressWarnings("serial")
@Service
public class EarthquakeService implements Serializable {
    public static List<Earthquake> getdata() {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            //Public API:
            //https://www.metaweather.com/api/location/search/?query=<CITY>
            //https://www.metaweather.com/api/location/44418/

            
            
            
            URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2022-01-01&endtime=2022-01-02&minmagnitude=5&format=text");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();



            reader= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((line= reader.readLine()) !=null){
                responseContent.append(line);
                

            }
            reader.close();

            

            //System.out.println(responseContent.toString());


                
                
          
            return parseData(responseContent.toString());
            //return null;
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            
        }
        return parseData(responseContent.toString());
        //return null;
    }

    
    static List<Earthquake> earthquakelist= new ArrayList<>();
    
    public static List<Earthquake> parseData(String response){

            String[] asd = response.split("\\|20");
            for (String i: asd) {
                String wuhu[]= i.split("\\|");
                int count =0;
                for(String j:wuhu){
                    System.out.println(j+" "+count);

                    count++;
                    //if(count)
                }
                String[] place= wuhu[11].split(",");
                String loc;
                String country= place[0];
                if(place.length==2){loc= place[1];
                }else{loc= place[0];}

                String magnitude= wuhu[9];
                String date= wuhu[0].substring(0,8);
                String clock;
                
                if(wuhu[0].length()!=8){
                    clock= wuhu[0].substring(12,17);
                }else{
                    clock= wuhu[0].substring(0,8);
                }
                
                Earthquake earthquake= new Earthquake(country, loc, magnitude, date, clock);
                earthquakelist.add(earthquake);
                
              }
            

            
        
        return earthquakelist;    
        
     }
}



