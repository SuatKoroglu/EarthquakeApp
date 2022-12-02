package com.example.application.data.entity;
import javax.annotation.Nonnull;

import com.vaadin.flow.component.template.Id;



//The Filterform with Country, Place, Magnitude, Date, Time
public class Earthquake  {

    @Nonnull
    @Id
    private String Country = "";

    @Nonnull
    private String Place= "";

    @Nonnull
    private String Magnitude="";

    @Nonnull
    private String Date="";

    @Nonnull
    private String Time="";

    
    public String getCountry() {
        return Country;
    }

    public void setCountry(@Nonnull String Country) {
        this.Country = Country;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(@Nonnull String Place) {
        this.Place = Place;
    }

    public String getMagnitude() {
        return Magnitude;
    }

    public void setMagnitude(@Nonnull String Magnitude) {
        this.Magnitude = Magnitude;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(@Nonnull String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(@Nonnull String Time) {
        this.Time = Time;
    }

    public Earthquake(@Nonnull String country, @Nonnull String place,@Nonnull String magnitude,
        @Nonnull String date, @Nonnull  String time) {
        Country = country;
        Place = place;
        Magnitude = magnitude;
        Date = date;
        Time = time;
    }
}