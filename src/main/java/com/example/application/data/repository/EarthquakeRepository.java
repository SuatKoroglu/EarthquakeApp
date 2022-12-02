package com.example.application.data.repository;

import java.util.List;

import com.example.application.data.entity.Earthquake;
import com.example.application.data.service.EarthquakeService;
import com.example.application.data.service.services;

public class EarthquakeRepository{
//if filter is null then use defult variable (3), if not use the data given by the user
    public static List<Earthquake> findallearthquakes(String filterText){
        if(filterText== null || filterText.isEmpty()){
            return EarthquakeService.getdata("3");
        }else{
            return EarthquakeService.getdata(filterText);
        }
    }
    
    
}


