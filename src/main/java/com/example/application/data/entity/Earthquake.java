package com.example.application.data.entity;
import javax.annotation.Nonnull;




public class Earthquake  {

    @Nonnull
    private String Country = "";

    @Nonnull
    private String Place= "";


    private float Magnitude=0;

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

    public float getMagnitude() {
        return Magnitude;
    }

    public void setMagnitude(float Magnitude) {
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

    public Earthquake(@Nonnull String country, @Nonnull String place, float magnitude,
        @Nonnull String date, @Nonnull  String time) {
        Country = country;
        Place = place;
        Magnitude = magnitude;
        Date = date;
        Time = time;
    }
}