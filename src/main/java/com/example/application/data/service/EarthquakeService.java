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
import java.time.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("serial")
@Service
public class EarthquakeService implements Serializable {
//if filter is null then use defult variable (3), if not use the data given by the user
    public static List<Earthquake> findallearthquakes(String filterText){
        if(filterText== null || filterText.isEmpty()){
            return getdata("5");
        }else{
            
            return services.search(filterText);
        }
    }
//function for geting the data from api. 
    public static List<Earthquake> getdata(String many) {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {

            LocalDate today = LocalDate.now();
            LocalDate before= today.plusDays(-Integer.valueOf(many));
            //create a url with using the data given by user
            String myurl= "https://earthquake.usgs.gov/fdsnws/event/1/query&starttime="+before+"&endtime="+today+"&minmagnitude=3&format=text";
            
            
            
            URL url = new URL(myurl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            
            


            //read the data
            reader= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((line= reader.readLine()) !=null){
                responseContent.append(line);
                

            }
            reader.close();
            //call parseData function to parse data into pieces
            return parseData(responseContent.toString());
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            
        }
        return parseData(responseContent.toString());
        
    }

    
    static List<Earthquake> earthquakelist= new ArrayList<>();

  
    //a function for put data in pieces
    public static List<Earthquake> parseData(String response){
        /*
         * Our data comes like this: #EventID|Time|Latitude|Longitude|Depth/km|Author|Catalog|Contributor|ContributorID|MagType|Magnitude|MagAuthor|EventLocationName
         * to parse the data we spit data by "|"
         * 
         */
        String[] wuhu = response.split("\\|");
        //create atributes of Earhquake object
        String country="";
        String loc="";
        String magnitude="";
        String date="";
        String clock="";

        int count =0;
        //for loop in out string array
        for (String i: wuhu) {

            count++;  
            
          //the firs 14 string is : #EventID|Time|Latitude|Longitude|Depth/km|Author|Catalog|Contributor|ContributorID|MagType|Magnitude|MagAuthor|EventLocationName
          //we are not gona use them  
            if(count<14){
                continue;
            }
            else{
                // every 12 times we get the same type of data. Thats why we use %12. The result gives us information about where we are at that moment.
                switch ((count-13)%12){

                   
                    case(10):
                        magnitude=i;
                        break;
                    case(1):

                        date=i;
                        clock=i;
                        break;
                    case(0):

                        country=i.substring(0,i.length()-10);
                        loc=i.substring(0,i.length()-10);
                        break;
                }
                
            }
            //every 12 time we have provided the atribute to create an object. We store the object with the fallowing code
                if(((count-13)%12==0)){
                    Earthquake earthquake= new Earthquake(loc,country, magnitude, date, clock);
                    //save the object in a list
                    earthquakelist.add(earthquake);
                }
                
            }
    //return the list          
    return earthquakelist;  
    
    }



    

}

